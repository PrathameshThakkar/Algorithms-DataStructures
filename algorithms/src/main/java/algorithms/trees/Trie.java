package algorithms.trees;

import java.util.HashMap;
import java.util.Map;

public class Trie {
	
	class Node  {
		public char ch;
		
		public int content;
		
		public boolean isWholeWord;
		
		public String word;
		
		
		public Map<Character,Node> children = new HashMap<Character,Node>();
		
		public Node(char ch) {
			this.ch = ch;
		}
		
		public void addChildren(char c, Node child) {
			this.children.put(c,child);
		}
	}
	
	private char rootCharacter = '#';
	
	private Node root = new Node(rootCharacter);
	
	public void add(String word) {
		
		Node node = root;
		
		for(int i=0; i<word.length(); i++) {
			
			char currentChar = word.charAt(i);
			
			Node nextNode = null;
			
			if(node.children!=null && node.children.containsKey(currentChar)) {
				  nextNode = node.children.get(currentChar);
			}else{
				  nextNode = new Node(currentChar);
				  node.addChildren(currentChar, nextNode);
			}

			nextNode.content++;
			node = nextNode;
		}
		
		node.isWholeWord = true;
		node.word = word;
	}
	
	public void delete(String word) {
		
		 if(!contains(word)) {
			 return;
		 }
		 
		 Node node = root;
		 
		 for(int i = 0; i<word.length(); i++) {
			 
			 Node nextNode = node.children.get(word.charAt(i));
			 
			 nextNode.content--;
			 
			 if(nextNode.content <=0) {
				 clear(nextNode);
				 node.children.remove(nextNode.ch);
				 return;
			 }
			 node = nextNode;
		 }
		 
		 node.isWholeWord = false;
		 node.word = null;
	}
	
	public void clear(Node node) {
		
		if(node == null) return;
		
		for(Map.Entry<Character, Node> entrySet: node.children.entrySet()) {
			
			clear(entrySet.getValue());
			
		}
		
		node.children.clear();
		node.children = null;
		
	}
	
	public boolean contains(String word) {
		
		Node node = root;
		
		for(int i = 0; i< word.length(); i++) {
			
			if(node.children==null || !node.children.containsKey(word.charAt(i))) {
				return false;
			}
			
			node = node.children.get(word.charAt(i));
		}
		
		return true;
	}
}
