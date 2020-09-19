//CKulig BU CS 622 HW2 9/20
package pubmed;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLHandler extends DefaultHandler{
	private String keywordToSearch = "";
	private static final String ARTICLETITLE = "ArticleTitle";
	private static final String KEYWORD = "Keyword";
	private static final String PUBMEDARTICLE = "PubmedArticle";
	private static final String PUBYEAR = "PubDate";
	private static final String ISSN = "Journal";
	private boolean articelTitle= true;
	private boolean keyword = true;
	private boolean foundKeyword = true;
	private boolean pubMedArticle = true;
	private boolean pubDate = true;
	private boolean issnBool = true;
	private String articleTitleForPrint = "";
	private String pubDateForPrint = "";
	private String issnForPrint = "";
	private boolean articlePrinted = false;
	
	public XMLHandler(String keywordToSearch) {
		this.keywordToSearch = keywordToSearch;
	}
	
//	This is a callback method for the SAX API and is overridden to identify the tag SAX is on so 
//	other callback methods can take the appropriate action
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException{
		switch (qName) {
        case ARTICLETITLE:
        	articelTitle = true;
        	break;
        case KEYWORD:
        	keyword = true;
        	break;
        case PUBMEDARTICLE:
        	pubMedArticle = true;
        	break;
        case PUBYEAR:
        	pubDate = true;
        	break;
        case ISSN:
        	issnBool = true; 
        	break;
		}
	}
	
//	This is a callback method for the SAX API and is overridden to reset the articlePrinted and pubMedArticle bools 
	@Override
	public void endElement(String uri, String localName, String qName) {
		// If this is the closing tag of the article then reset found keyword variable to false
				if(pubMedArticle) {
					articlePrinted = false;
					pubMedArticle = false;
				}
	}
	
//	This is a callback method for the SAX API and performs the heavy lifting for the class
//	by identifying if a keyword tag is found and if the contents of that tag match the search team
//	both search term and keyword are made to lower case
	@Override
	public void characters(char[] chars, int start, int length) throws SAXException{
		// If this is the article title element then assign the title for printing
		if(articelTitle) {
			articleTitleForPrint = new String(chars, start, length);
			articelTitle = false;
		}		
		// If this is a keyword element and it contains the search term then indicate it was found
		if(keyword) {
			String keywordText = new String(chars, start, length);
			if(keywordText.toLowerCase().contains(keywordToSearch.toLowerCase()) && articlePrinted == false) {
				System.out.println("Title:" + articleTitleForPrint);
				System.out.println("ISSN:" + issnForPrint + " Date Printed:" + pubDateForPrint);
				articlePrinted = true;
			}
			keyword = false;
		}	
		if(pubDate) {
			pubDateForPrint = new String(chars, start, length);
			pubDate = false;
		}
		if(issnBool) {
			issnForPrint = new String(chars, start, length);
			issnBool = false;
		}
	}			
}
