package dblpwrap;

import java.io.File;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class DBLPWrapper {
	
	public static String[] bts;
	public static String actualAcronym = "";
	
    public static void main(String[] args) {
    		System.out.println("Wrapper is initiating...");
        DBLPWrapper ps = new DBLPWrapper();
        ps.Inicializar();
    }
    
    public void Inicializar(){    
    	
    		File inputFile = new File(Constants.REQUIRED_FILE_XML); 
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = null;
        
        try {
            saxParser = factory.newSAXParser();            
            	InproceedingsHandler inproceedingsHandler =  new InproceedingsHandler();
            	saxParser.parse(inputFile, inproceedingsHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }		
    
    }
    
        
    
}