package dblpwrap;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/*
 * This class is responsible for the events while reading the XML file.
 * All events are handled by this class and then sent to the SQLGenerator class,
 * for the SQL file creation.
 */

class InproceedingsHandler extends DefaultHandler {
    
    private Inproceedings inproceedings;

    private boolean bInproceedings;
    private boolean bAuthor;
    private boolean bTitle;
    private boolean bPages;
    private boolean bYear;
    private boolean bBooktitle;
    private boolean bEE;
    private boolean bCrossref;
    private boolean bUrl;

    StringBuilder sb = new StringBuilder();
        
    @Override
    public void startDocument() throws org.xml.sax.SAXException {            
        System.out.println("Starting to scan document...");
        //Optionally, create a new document or append to an existing one
    }

    @Override
    public void endDocument() throws org.xml.sax.SAXException { 
        System.out.println("endDocument.");
    }

    @Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            System.out.println("uri: " + uri);
            System.out.println("localName: " + localName);
            System.out.println("qName: " + qName);
    			sb.delete(0, sb.length());
            if (qName.equalsIgnoreCase("inproceedings")) {
                bInproceedings = true;
                inproceedings = new Inproceedings();
            }

            if (bInproceedings) {

                    if (qName.equalsIgnoreCase("title")) {
                            bTitle = true;
                    } else if (qName.equalsIgnoreCase("author")) {
                            bAuthor = true;
                    } else if (qName.equalsIgnoreCase("title")) {
                            bTitle = true;
                    } else if (qName.equalsIgnoreCase("pages")) {
                            bPages = true;
                    } else if (qName.equalsIgnoreCase("year")) {
                            bYear = true;
                    } else if (qName.equalsIgnoreCase("booktitle")) {
                            bBooktitle = true;
                    } else if (qName.equalsIgnoreCase("ee")) {
                            bEE = true;
                    } else if (qName.equalsIgnoreCase("crossref")) {
                            bCrossref = true;
                    } else if (qName.equalsIgnoreCase("url")) {
                            bUrl = true;
                    }

            }

	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
            if (qName.equalsIgnoreCase("inproceedings")) {
                bInproceedings = false;
                if( inproceedings.getYear() >= Constants.LOWER_YEAR_LIMIT &&
                    inproceedings.getYear() <= Constants.HIGHER_YEAR_LIMIT ){
                		System.out.println("Item is located between the year limit!");
                	}   
            }

            if (bInproceedings) {

                    if (bAuthor) {

                            System.out.println("Author: " + sb.toString());
                            if (inproceedings != null) {
                                    inproceedings.addAuthors(sb.toString());
                            }
                            sb.delete(0, sb.length());
                            bAuthor = false;
                            return;

                    } else if (bTitle) {

                            System.out.println("Title: " + sb.toString());
                            if (inproceedings != null) {                                
                                    inproceedings.setTitle(sb.toString().replaceAll("'","`"));
                                    
                            }
                            sb.delete(0, sb.length());
                            bTitle = false;
                            return;

                    } else if (bPages) {

                            System.out.println("Pages: " + sb.toString());
                            if (inproceedings != null) {
                                    inproceedings.setPages(sb.toString());
                            }
                            sb.delete(0, sb.length());
                            bPages = false;
                            return;

                    } else if (bYear) {

                            System.out.println("Year: " + sb.toString());
                            if (inproceedings != null) {
                                    inproceedings.setYear(Integer.parseInt(sb.toString()));
                            }
                            sb.delete(0, sb.length());
                            bYear = false;
                            return;

                    } else if (bBooktitle) {

                            System.out.println("Booktitle: " + sb.toString());
                            if (inproceedings != null) {
                                    inproceedings.setBooktitle(sb.toString());
                            }
                            sb.delete(0, sb.length());
                            bBooktitle = false;
                            return;

                    } else if (bEE) {

                            System.out.println("EE: " + sb.toString());
                            if (inproceedings != null) {
                                    inproceedings.setEe(sb.toString());
                            }
                            sb.delete(0, sb.length());
                            bEE = false;
                            return;

                    } else if (bCrossref) {

                            System.out.println("Crossref: " + sb.toString());
                            if (inproceedings != null) {
                                    inproceedings.setCrossref(sb.toString());
                            }
                            sb.delete(0, sb.length());
                            bCrossref = false;
                            return;

                    } else if (bUrl) {

                            System.out.println("Url: " + sb.toString());
                            if (inproceedings != null) {
                                    inproceedings.setUrl(sb.toString());
                            }
                            sb.delete(0, sb.length());
                            bUrl = false;
                            return;

                    } else
                            return;

            }

	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		sb.append(new String(ch, start, length));
	}	

}