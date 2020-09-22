//CKulig BU CS 622 HW2 9/20
package pubmed;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class MergeXML {
	
	private String unmergedSourceFolder = "C:\\Users\\Chris\\Documents\\BU Classes\\CS 622 Advanced Programming\\CS622_HW2\\pubmed\\Unmerged Source\\";
	private String targetFile = "C:\\Users\\Chris\\Documents\\BU Classes\\CS 622 Advanced Programming\\CS622_HW2\\pubmed\\mergedXMLDoc.xml";

	public MergeXML() {}
	
	public MergeXML(String unmergedSourceFolder, String targetFile ) {
		this.unmergedSourceFolder = unmergedSourceFolder;
		this.targetFile = targetFile;
	}
	
//	The only public method for the class, controls merging process
	public void mergeXMLDocuments() {
		File folder = new File(unmergedSourceFolder);
		File[] listOfFiles = folder.listFiles();
		 
		createDocument();
		writeHeader();
		
		for (File file : listOfFiles) {
		    if (file.isFile()) {
		    	 appendXMLToDoc(file, targetFile);
		    }
		}
		appendClosingTag();
	}
	
	// Creates a document specified by the targetFile variable 
	private void createDocument() {
		 try {
		      File myObj = new File(targetFile);
		      if (myObj.createNewFile()) {
		        System.out.println("Target file created: " + myObj.getName());
		      } else {
		        System.out.println("Taget file already exists.");
		      }
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	
	// Writes the xml encoding, the doctype, and the PubmedArticlSet opening tag to the newly created targetDocument
	private void writeHeader() {
	    try {
	        FileWriter myWriter = new FileWriter(targetFile);
	        myWriter.write("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
	        myWriter.write("\n" + "<!DOCTYPE PubmedArticleSet PUBLIC \"-//NLM//DTD PubMedArticle, 1st January 2019//EN\" \"https://dtd.nlm.nih.gov/ncbi/pubmed/out/pubmed_190101.dtd\">");
	        myWriter.write("\n" + "<PubmedArticleSet>" + "\n");
	        myWriter.close();
	        System.out.println("Successfully wrote header to target file.");
	      } catch (IOException e) {
	        System.out.println("An error occurred.");
	        e.printStackTrace();
	      }
	}
	
//	Applies the closing tag to the created document
	private void appendClosingTag() {
	    try {
	        FileWriter myWriter = new FileWriter(targetFile, true);
	        myWriter.append("</PubmedArticleSet>");
	        myWriter.close();
	        System.out.println("Successfully appended closing tag to target file.");
	      } catch (IOException ex) {
	        System.out.println("An error occurred.");
	        ex.printStackTrace();
	      }
	}
	
//	 Goes through a pubmed source XML file and copies lines into the target file
//	 ignoring the xml version, doctype, and pubmed opening/closing tags
	private void appendXMLToDoc(File inputFile, String target) {
		try {			
			FileInputStream fis = new FileInputStream(inputFile);
	        InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
	        BufferedReader reader = new BufferedReader(isr);		    		   
			OutputStream f = new FileOutputStream(target, true);		  
			String currentLine;
	
			while((currentLine = reader.readLine()) != null) {
			    if(currentLine.equals("<?xml version=\"1.0\" encoding=\"utf-8\"?>") || currentLine.contains("<!DOCTYPE PubmedArticleSet PUBLIC") || currentLine.equals("</PubmedArticleSet>") || currentLine.equals("<PubmedArticleSet>")) continue;
			    currentLine = currentLine + "\n";
			    byte[] by=currentLine.getBytes("UTF-8");
			    for (int i=0;i<by.length;i++){
				    byte b=by[i];
				    f.write(b);
			    }
			    System.out.println("...");
			}
			f.close();
			reader.close(); 
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}		
		System.out.println("Completed appending XML to target file for " + inputFile.getName() + ".");
	}
		
}
