package algorithms.graphs;

import java.util.*;

public class BellFordman {
	
	 ArrayList<ArrayList<Character>> data = new ArrayList<ArrayList<Character>>() {{
		  add(new ArrayList<Character>(Arrays.asList('A','B')));
		  add(new ArrayList<Character>(Arrays.asList('B','C')));
		  add(new ArrayList<Character>(Arrays.asList('A','C')));
		  add(new ArrayList<Character>(Arrays.asList('C','D')));
	 }};
	 
	 ArrayList<Integer> values = new ArrayList<Integer>() {{
		  add(1);
		  add(2);
		  add(-3);
		  add(9);
	 }};
	 
	 private static final int PATH_AFFECTED_NEGATIVITY= -1;
	 
	 HashMap<Character,Character> prev = new HashMap<Character,Character>();
	 HashMap<Character,Integer> dist = new HashMap<Character,Integer>();
	 ArrayList<Edge> graph = new ArrayList<Edge>();
	 
	 char start;
	 int n;
	 
	 
	 public BellFordman() {
		 
		 init();
		 start = 'A';
		 solveShortestPath();
		 recreatePath('A','D');
	 }
	 
	 public void init() {
		 
		   int index = 0;
		   
		   for(ArrayList<Character> l : data) {
		  	  char from = l.get(0);
		  	  char to = l.get(1);
		  	  
		  	  graph.add(new Edge(from,to,values.get(index)));
		  	  
		  	  prev.put(from,null);
		  	  prev.put(to,null);
		  	  
		  	  dist.put(from,Integer.MAX_VALUE);
		  	  dist.put(to,Integer.MAX_VALUE);
		   }
		   
		   n = prev.size();
		 
	 }
	 
	 public void solveShortestPath() {
		 
		 dist.put(start,0);
		 
		 for(int i = 0; i< n-1; i++) {
			 for(Edge e : graph) {
				  if(dist.get(e.from)+e.cost < dist.get(e.to)) {
				  	dist.put(e.to,dist.get(e.from)+e.cost);
				  }
			 }
		 }
		 
		 for(int i = 0; i< n-1; i++) {
			 for(Edge e : graph) {
				  if(dist.get(e.from)+e.cost < dist.get(e.to)) {
				  	dist.put(e.to,Integer.MIN_VALUE);
				  }
			 }
		 }
	 }
	 
	 public void recreatePath(char start, char end) {
		 
	 }
	 
	 class Edge {
		 char from;
		 char to;
		 int cost;
		 
		 public Edge(char from, char to, int cost) {
			 this.from = from;
			 this.to = to;
			 this.cost = cost;
		 }
	 }
}
