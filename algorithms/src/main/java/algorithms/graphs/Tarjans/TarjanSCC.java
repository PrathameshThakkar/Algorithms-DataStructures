package algorithms.graphs.Tarjans;

import java.util.*;

public class TarjanSCC {
	
	 static final int UNVISITED = -1;
	
   ArrayList<ArrayList<Character>> data = new ArrayList<ArrayList<Character>>() {{
  	    add(new ArrayList<Character>(Arrays.asList('A','B')));
  	    add(new ArrayList<Character>(Arrays.asList('B','C')));
  	    add(new ArrayList<Character>(Arrays.asList('C','A')));
  	    add(new ArrayList<Character>(Arrays.asList('D','A')));
  	    add(new ArrayList<Character>(Arrays.asList('E','D')));
  	    add(new ArrayList<Character>(Arrays.asList('D','D')));
   }};
   
   HashSet<Character> inStack;
   HashMap<Character,Integer> lowLink,idx;
   HashMap<Character,ArrayList<Character>> sccs;
   HashMap<Character,ArrayList<Character>> graph;
   Stack<Character> stack;
   
   int index;
   int countScc;
   
   public TarjanSCC() {
  	 
  	   inStack = new HashSet<Character>();
  	   lowLink = new HashMap<Character, Integer>();
  	   idx = new HashMap<Character, Integer>();
  	   sccs = new HashMap<Character, ArrayList<Character>>();
  	   graph = new HashMap<Character, ArrayList<Character>>();
  	   stack = new Stack<Character>();
  	   
  	   setUp();
  	   
  	   solveSCC();
   }
   
   public void setUp() {
  	 
  	  for(ArrayList<Character> l : data) {
  	  	 char from = l.get(0);
  	  	 char to = l.get(1);
  	  	 
  	  	 ArrayList<Character> edges= graph.getOrDefault(from, new ArrayList<Character>());
  	  	 edges.add(to);
  	  	 graph.put(from,edges);
  	  	 
  	  	 idx.put(from,UNVISITED);
  	  	 idx.put(to,UNVISITED);
  	  }
   }
   
   public void solveSCC() {
  	 
  	    for(HashMap.Entry<Character,ArrayList<Character>> e : graph.entrySet()) {
  	    	
  	    	  char node = e.getKey();
  	    	  
  	    	  if(idx.get(node)==UNVISITED) {
  	    	  	 dfs(node);
  	    	  }
  	    }
   }
   
   public void dfs(char node) {
  	 
	  	 idx.put(node,index);
	  	 lowLink.put(node,index);
	  	 inStack.add(node);
	  	 stack.push(node);
	  	 index++;
  	     	   
  	   for(char to : graph.getOrDefault(node, new ArrayList<Character>())) {
  	  	  if(idx.get(to)==UNVISITED) {
  	  	  	dfs(to);
  	  	  }
  	  	  if(inStack.contains(to)) {
  	  	  	 lowLink.put(node,Math.min(lowLink.get(node), lowLink.get(to)));
  	  	  }
  	   }
  	   
  	   if(idx.get(node) == lowLink.get(node)) {
	  	  	 ArrayList<Character> scc = new ArrayList<Character>();
		  	   while(!stack.isEmpty()) {
		  	  	 char currNode = stack.pop();
		  	  	 lowLink.put(currNode,idx.get(node));
		  	  	 inStack.remove(currNode);
		  	  	 if(currNode == node) break;
		  	   }
	  	  	 sccs.put(node,scc);
	    	   countScc++;
  	   }
   }
   
}
