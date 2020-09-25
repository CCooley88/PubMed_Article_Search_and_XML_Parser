package pubmed;

import java.time.LocalDateTime;
import java.util.ArrayList;

import pubmed.SearchHistory.KeyWordNode;

public class nodeTesting {

	public static void main(String[] args) {
		SearchHistory sh = new SearchHistory();
		
		sh.addNewNode("Dog");
		sh.addNewNode("Cat");
		sh.addNewNode("Dog");
		ArrayList<KeyWordNode> nodeList = sh.getKeyWordNodeList();
		for(KeyWordNode kwn : nodeList) {
			System.out.println(kwn.getKeyWord());
			ArrayList<LocalDateTime> tsl = kwn.getTimeStampList();
			for(LocalDateTime i : tsl) {
				System.out.println("  " + i);
			}
		}
	}

}
