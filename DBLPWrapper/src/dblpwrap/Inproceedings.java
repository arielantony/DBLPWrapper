package dblpwrap;

import java.util.ArrayList;
import java.util.List;

public class Inproceedings {
    private List<String> authors = new ArrayList<>();
    private String title;
    private String pages;
    private int year;
    private String booktitle;
    private String ee;
    private String crossref;
    private String url;
    private String acronym;

    public void addAuthors(String author) {
        this.authors.add(author);
    }

    public List<String> getAuthors() {
        return authors;
    }

    public String getStringAuthors() {
        String strAuthors = "";
        for (String a : authors) {
                strAuthors += a + "|";
        }
        if (strAuthors != ""){
                strAuthors = strAuthors.substring(0, strAuthors.length()-1);
        }
        return strAuthors.replaceAll("'","''");
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title.replace("'","''");
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getBooktitle() {
        return booktitle.toUpperCase();
    }

    public void setBooktitle(String booktitle) {
        this.booktitle = booktitle;
    }

    public String getEe() {
        return ee;
    }

    public void setEe(String ee) {
        this.ee = ee;
    }

    public String getCrossref() {
        return crossref;
    }

    public void setCrossref(String crossref) {
        this.crossref = crossref;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    @Override
    public String toString() {
        String inText = "";
        String authorsStr = "";

        for (String author : authors) {
            authorsStr += author + "|";
        }

        // authorsStr = authorsStr.substring(0, authorsStr.length()-1);

        inText = this.title + ", " + this.pages + ", " + this.year + ", " + this.booktitle + ", " + authorsStr;

        // inText = this.title;

        return inText;
    }

}

