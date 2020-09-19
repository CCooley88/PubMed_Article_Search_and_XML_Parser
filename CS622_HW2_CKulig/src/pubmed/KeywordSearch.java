//CKulig BU CS 622 HW2 9/20
package pubmed;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File; 
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.zip.GZIPInputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;



public class KeywordSearch {
	
	public static void main(String[] args) {
		
//		 Locations of source and target files for decompression
		String unmergedSourceFolder = "C:\\Users\\Chris\\Documents\\BU Classes\\CS 622 Advanced Programming\\CS622_HW2\\pubmed\\Unmerged Source\\";
		String targetFile = "C:\\Users\\Chris\\Documents\\BU Classes\\CS 622 Advanced Programming\\CS622_HW2\\pubmed\\mergedXMLDoc.xml";
		Path sourceDecompress = Paths.get("C:\\Users\\Chris\\Documents\\BU Classes\\CS 622 Advanced Programming\\CS622_HW2\\pubmed\\pubmed20n1025.xml.gz");
		Path targetDecompress = Paths.get("C:\\Users\\Chris\\Documents\\BU Classes\\CS 622 Advanced Programming\\CS622_HW2\\pubmed\\pubmed20n1025_unzipped.xml");
                     
       String keywordToSearch = "";
       String selectedOption = "-1";
       Scanner scanner = new Scanner(System.in); 
       System.out.println("Enter command:");
		 selectedOption = scanner.nextLine();
		    while(selectedOption != "0"){
		    	switch (selectedOption) {
		    	case "1":
		    		System.out.println("Hold for unzip");
		    		try {
						decompressGzip(sourceDecompress, targetDecompress);
					} catch (IOException e) {
						e.printStackTrace();
					}	 
		    		break;
		    	case "2": 
		    		System.out.println("What term would youl like to search on:");
		    		keywordToSearch = scanner.nextLine();
		    		keywordSearch(targetFile, keywordToSearch);
		    		break;
		    	case "3":
		    		MergeXML mergeXML = new MergeXML(unmergedSourceFolder, targetFile);
		    		mergeXML.mergeXMLDocuments();
		    		break;	
		    	}	
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
	
	// This is used to unzip .gz files takes a source and target document path as arguments
    public static void decompressGzip(Path source, Path target) throws IOException {    	
        if (Files.notExists(source)) {
	           System.err.printf("The path %s doesn't exist!", source);
	           return;
	       }
	       
        try (GZIPInputStream gis = new GZIPInputStream(
                                      new FileInputStream(source.toFile()));
             FileOutputStream fos = new FileOutputStream(target.toFile())) {
            byte[] buffer = new byte[1024];
            int len;
            while ((len = gis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
        }
    }
    
    public static void printMenu() {
    	System.out.println("Welcome to the PubMed ");
    }

}


