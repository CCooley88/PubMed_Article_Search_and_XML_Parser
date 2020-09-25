PubMed Keyword Search & Article Finder

This resource allows the user to do multiple things including:
- Unzip .gz files - in this case the focus is on zipped Pubmed files
- Merge multiple unzipped Pubmed XML files into one file for searching
- Search through the keyword tags within the merged XML document to find articles
	related to a given topic

There are a number of classes used as follows:

KeywordSearch
- Contains the user option selection menu
- Maintains the print menu function
- Calls the XML parser

XMLHandler
- Manages the callbacks for the parsing the XML document using SAX parser

MergeXML
- 