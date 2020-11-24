package algorithms.graphs;

import java.util.*;

public class Diajkstra {
	
	  ArrayList<ArrayList<Character>> data = new ArrayList<ArrayList<Character>>() {{
	  	  add(new ArrayList<Character>(Arrays.asList('A','B')));
	  	  add(new ArrayList<Character>(Arrays.asList('B','C')));
	  	  add(new ArrayList<Character>(Arrays.asList('A','C')));
	  }};
	  
	  ArrayList<Integer> values = new ArrayList<Integer>(){{
	  	  add(2);
	  	  add(3);
	  	  add(1);
	  }};
	  HashMap<Character,Character> prev = new HashMap<Character,Character>();
	  HashMap<Character,Integer> dist = new HashMap<Character,Integer>();
	  HashMap<Character,ArrayList<Edge>> graph = new HashMap<Character,ArrayList<Edge>>();
	  HashMap<Character,Boolean> visited = new HashMap<Character,Boolean>();;
	  int n;
	  char start = 'A';
	  char end  = 'C';
	  
	  
	 public Diajkstra() {
		 
		 init();
		 solveSortestPath();
		 recreatepath();
	 }
	 
	 public void solveSortestPath(){
		 
		 PriorityQueue<Edge> q = new PriorityQueue<Edge>(new Comparator<Edge>() {
			 public int compare(Edge a, Edge b) {
				  return Integer.compare(a.cost, b.cost);
			 }
		 });
		 		 
		 q.add(new Edge(start,0));
		 
		 while(!q.isEmpty()) {
			  
			    Edge from = q.poll();
			    			    
			    visited.put(from.node, true);
			    
			    if(dist.get(from.node)<from.cost) continue;
			    
			    for(Edge to : graph.getOrDefault(from.node, new ArrayList<Edge>())) {
			    	
			    	   if(visited.get(to.node) == true) continue;
			    	    
			    	   int newDist = dist.get(from.node)+to.cost;
			    	   
			    	   if(newDist> dist.get(to.node)) continue;
			    	   
			    	   q.add(new Edge(to.node,newDist));
			    	   
			    	   dist.put(to.node, newDist);
			    	   
			    	   prev.put(to.node,from.node);
			    }
		 	}
	 }
	 
	 public void recreatepath() {
		 
		 if(dist.get(end)==Integer.MAX_VALUE) return;
		 
		 ArrayList<Character> path = new ArrayList<Character>();
		 
		 Character at = end;
		 while(at!=null) {
			  path.add(at);
			  at = prev.get(at);
		 }
		 Collections.reverse(path);
	 }
	 
	 public void init() {
		 
		 int index = 0;
		 
		 for(ArrayList<Character> l : data) {
			 
			  char from = l.get(0);
			  char to = l.get(1);
			  
			  ArrayList<Edge> edges = graph.getOrDefault(from, new ArrayList<Edge>());
			  edges.add(new Edge(to,values.get(index)));
			  
			  visited.put(to,false);
			  visited.put(from,false);
			  
			  dist.put(to,Integer.MAX_VALUE);
			  dist.put(from,Integer.MAX_VALUE);
			  
			  prev.put(to,null);
			  prev.put(from,null);
		 }
		 
		 n = visited.size();
	 }
	 
	 class Edge {
		 Character node;
		 int cost;
		 
		 public Edge(Character to, int cost) {
			 this.node = to;
			 this.cost = cost;
		 }
	 }
}
