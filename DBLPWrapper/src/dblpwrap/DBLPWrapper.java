package dblpwrap;

import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class DBLPWrapper {
	
	public static String acronym = "";
	public static int idVehicle = 0;
	
	public static Hashtable<Integer,ArrayList<String>> similares = new Hashtable<Integer, ArrayList<String>>();
	
	public static File outputFile = null;
	
    public static void main(String[] args) {
    		System.out.println("Wrapper is initiating...");
        DBLPWrapper ps = new DBLPWrapper();
        ps.Inicializar();
    }
    
    public void parseFile() {
    		File booktitles = new File(Constants.BOOKTITLES_IDS_FILE);
        try {
        		Scanner scan = new Scanner(booktitles);
        		while (scan.hasNext()) {
        			String line = scan.nextLine();
        			String[] data = line.split(Pattern.quote("\\"));
        			similares.putIfAbsent(Integer.parseInt(data[1]), new ArrayList<String>());
        			ArrayList<String> sim = similares.get(Integer.parseInt(data[1]));
        			sim.add(new String(data[0]));
        		}
        		scan.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void Inicializar(){    
    	
    		File inputFile = new File(Constants.REQUIRED_FILE_XML); 
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = null;
        
        parseFile();
        
        System.out.println("The following itens are in the collection:");
        Set<Integer> keys = similares.keySet();
        for(Integer key : keys){
            //System.out.println("Value of " + key + " is: " + similares.get(key));
	        	try {
	        		acronym = similares.get(key).get(0);
	        		idVehicle = key;
	        		outputFile = new File(Constants.OUTPUT_FILE_PATH + acronym + ".sql");
	        		saxParser = factory.newSAXParser();            
	            	InproceedingsHandler inproceedingsHandler =  new InproceedingsHandler();
	            saxParser.parse(inputFile, inproceedingsHandler);
	        	} catch (Exception e) {
	        		e.printStackTrace();
	        	}
        }
        
        //File booktitles = new File(Constants.BOOKTITLES_IDS_FILE);
        
        try {
        		//Scanner scan = new Scanner(booktitles);
        		/*while (scan.hasNext()) {
        			String line = scan.nextLine();
        			System.out.println("Got booktitle and ID -> " + line);
        			String[] data = line.split(Pattern.quote("\\"));
        			System.out.println("Got booktitle -> " + data[0] + " and ID (" + data[1] + ")");
        			ArrayList<String> sim = similares.getOrDefault(new Integer(data[1]), new ArrayList<String>());
        			sim.add(data[0]);
        		}
        		scan.close();*/
        			//acronym = scan.next();
        			//idVehicle = scan.nextInt();
        			//System.out.println("Booktitle: " + acronym + " has ID " + idVehicle);
        		
        } catch (Exception e) {
            e.printStackTrace();
        }		
    
    }
    
}