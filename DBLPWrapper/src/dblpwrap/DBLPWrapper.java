package dblpwrap;

import java.io.File;
import java.util.Scanner;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class DBLPWrapper {
	
	public static String acronym = "";
	public static int idVehicle = 0;
	
	public static File outputFile = null;
	
    public static void main(String[] args) {
    		System.out.println("Wrapper is initiating...");
        DBLPWrapper ps = new DBLPWrapper();
        ps.Inicializar();
    }
    
    public void Inicializar(){    
    	
    		File inputFile = new File(Constants.REQUIRED_FILE_XML); 
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = null;
        
        File booktitles = new File(Constants.BOOKTITLES_IDS_FILE);
        
        outputFile = new File(Constants.OUTPUT_FILE_PATH + acronym + ".sql");
        
        try {
        		Scanner scan = new Scanner(booktitles);
        		while (scan.hasNext()) {
        			acronym = scan.next();
        			idVehicle = scan.nextInt();
	        		saxParser = factory.newSAXParser();            
	            	InproceedingsHandler inproceedingsHandler =  new InproceedingsHandler();
	            saxParser.parse(inputFile, inproceedingsHandler);
        			System.out.println("Booktitle: " + acronym + " has ID " + idVehicle);
        		}
        		scan.close();
        } catch (Exception e) {
            e.printStackTrace();
        }		
    
    }
    
        
    
}