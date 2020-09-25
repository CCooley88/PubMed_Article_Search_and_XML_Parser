package pubmed;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SearchHistory {
	
	private ArrayList<KeyWordNode> keyWordNodeList = new ArrayList<>();;
	
	public ArrayList<KeyWordNode> getKeyWordNodeList() {
		return keyWordNodeList;
	}

	public void setKeyWordNodeList(ArrayList<KeyWordNode> keyWordNodeList) {
		this.keyWordNodeList = keyWordNodeList;
	}
	
	public void addNewNode(String keyWord) {
		boolean foundKeyWord = false;
		for(KeyWordNode node: this.keyWordNodeList) {
			if(node.getKeyWord().equalsIgnoreCase(keyWord)) {
				node.addTimeStamp();
				foundKeyWord = true;
			}	
		}
		if(!foundKeyWord) {
			this.addNode(keyWord);
		}
	}

	public void addNode(String keyWord) {
		KeyWordNode kwn = new KeyWordNode(keyWord);
		keyWordNodeList.add(kwn);
	}
	
	public void printTimeList(KeyWordNode node) {
		ArrayList<LocalDateTime> timeList = node.getTimeStampList();
		for(LocalDateTime ldt : timeList) {
			System.out.println(ldt);
		}
	}
		
	public void printAllNodesAndTimes() {
		for(KeyWordNode kwn : this.keyWordNodeList) {
			System.out.println(kwn.getKeyWord());
			ArrayList<LocalDateTime> tsl = kwn.getTimeStampList();
			for(LocalDateTime ldt : tsl) {
				System.out.println("  Hour:" + ldt.getHour() + " Date:" + ldt.getDayOfMonth() + " " + ldt.getMonth() + " " + ldt.getYear());
			}
		}
	}
		
	
//	Contains a node subclass
//	nodes contain the term and a linkedlist of dates
	class KeyWordNode{

		private String keyWord;
		private ArrayList<LocalDateTime> timeStampList;
		
		KeyWordNode(String keyWord){
			this.keyWord = keyWord;
			this.timeStampList = new ArrayList<LocalDateTime>();
			this.timeStampList.add(java.time.LocalDateTime.now());
		}
		
		public String getKeyWord() {
			return keyWord;
		}
		public void setKeyWord(String keyWord) {
			this.keyWord = keyWord;
		}
		public ArrayList<LocalDateTime> getTimeStampList() {
			return timeStampList;
		}
		public void setTimeStampList(ArrayList<LocalDateTime> timeStampList) {
			this.timeStampList = timeStampList;
		}
		
		public void addTimeStamp() {
			this.timeStampList.add(java.time.LocalDateTime.now());
		}

	}
}
