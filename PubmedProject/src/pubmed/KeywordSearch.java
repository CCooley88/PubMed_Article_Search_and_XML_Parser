//CKulig BU CS 622 HW2 9/20
package pubmed;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

// Testing new project location
public class KeywordSearch {
	
	public static void main(String[] args) {
		
//		 Locations of source and target files for decompression
		String unmergedSourceFolder = "C:\\Users\\Chris\\Documents\\BU Classes\\CS 622 Advanced Programming\\CS622_HW2\\pubmed\\Unmerged Source\\";
		String targetFile = "C:\\Users\\Chris\\Documents\\BU Classes\\CS 622 Advanced Programming\\CS622_HW2\\pubmed\\mergedXMLDoc.xml";
		Path sourceDecompress = Paths.get("C:\\Users\\Chris\\Documents\\BU Classes\\CS 622 Advanced Programming\\CS622_HW2_CKulig\\pubmed\\pubmed20n1018.xml.gz");
		Path targetDecompress = Paths.get("C:\\Users\\Chris\\Documents\\BU Classes\\CS 622 Advanced Programming\\CS622_HW2\\pubmed\\pubmed20n1025_unzipped.xml");
		
       String keywordToSearch = "";
       String selectedOption = "-1";
       Scanner scanner = new Scanner(System.in); 
       SearchHistory searchHistory = new SearchHistory();

       System.out.println("Welcome to the PubMed topic searcher. Options as follows:");
       printMenu();
       
       System.out.println("Enter command:");
		 selectedOption = scanner.nextLine();
		    while(selectedOption != "0"){
		    	switch (selectedOption) {
		    	case "1":		    		
		    		try {
		    			System.out.println("Enter path of the file to unzip.");
		    			sourceDecompress =  Paths.get(scanner.nextLine());
		    			System.out.println("Enter path of the target file.");
		    			targetDecompress =  Paths.get(scanner.nextLine());
		    			System.out.println("Hold for unzip");
		    			DecompressZip.decompressGzip(sourceDecompress, targetDecompress);
					} catch (IOException e) {
						e.printStackTrace();
					}	 
		    		break;
		    	case "2":
		    		System.out.println("Enter path of the folder containing the unmerged files.");
		    		unmergedSourceFolder =  scanner.nextLine();
	    			System.out.println("Enter path of the target file.");
	    			targetFile =  scanner.nextLine();
		    		MergeXML mergeXML = new MergeXML(unmergedSourceFolder, targetFile);
		    		mergeXML.mergeXMLDocuments();
		    		break;
		    	case "3": 
		    		System.out.println("What term would youl like to search for:");
		    		keywordToSearch = scanner.nextLine();
		    		searchHistory.addNewNode(keywordToSearch);
		    		keywordSearch(targetFile, keywordToSearch);
		    		break;		    		
		    	case "4":
		    		searchHistory.printAllNodesAndTimes();
		    	}	
		    	System.out.println();
		     printMenu();
		   	 System.out.println("Enter command:");
			 selectedOption = scanner.nextLine(); 
		    }		    
	}  
	
	public static void keywordSearch(String documentPath, String keywordToSearch) {	
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			XMLHandler handler = new XMLHandler(keywordToSearch);	
			saxParser.parse(documentPath.toString(), handler);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}	
	}

    
    public static void printMenu() {    	
    	System.out.println("1 - Unzip files");
    	System.out.println("2 - Merge XML documents");
    	System.out.println("3 - Search for a term");
    	System.out.println("4 - View search history");
    }
 
}


