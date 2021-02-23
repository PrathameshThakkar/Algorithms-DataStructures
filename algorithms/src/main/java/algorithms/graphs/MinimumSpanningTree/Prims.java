package algorithms.graphs.MinimumSpanningTree;

import java.util.*;

public class Prims {
  
	HashMap<Character,ArrayList<Edge>> graph;
	HashMap<Character,Boolean> visited;
	ArrayList<Edge> minSpanTree;
	int minCost;
	char start = 'A';  
	
	boolean minSpanTreeExists = true;
	int N;
	
	public Prims(HashMap<Character,ArrayList<Edge>> graph,int N) {
		this.graph = graph;
		this.N = N;
		minSpanTree = new ArrayList<Edge>();
		solve();
	}
	
	public ArrayList<Edge> solve(){
		
		PriorityQueue<Edge> p = new PriorityQueue<Edge>(new Comparator<Edge>() {
			 public int compare(Edge e1, Edge e2) {
				 return Integer.compare(e1.cost, e2.cost);
			 }
		});
		
		addEdges(start,p);
		int edgeCount = 0;
		
		while(!p.isEmpty() && edgeCount==N-1) {
			
			Edge bestCostEdge = p.poll();
			
			if(visited.get(bestCostEdge.from)) continue;
			
			minSpanTree.add(bestCostEdge);
			minCost += bestCostEdge.cost;
			
			addEdges(bestCostEdge.to,p);
			
		}
		
		minSpanTreeExists = minSpanTree.size()==N-1;
		
		return (minSpanTreeExists)?minSpanTree:null;
	}
	
	public void addEdges(char node, PriorityQueue<Edge> p) {
		
		visited.put(node,true);
				
		for(Edge edge : graph.getOrDefault(node, new ArrayList<Edge>())) {
			
			  if(visited.get(edge.to)) continue;
			
			  p.add(edge);
		}
				
	}
	
	class Edge{
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
