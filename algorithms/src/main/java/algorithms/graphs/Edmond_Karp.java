package algorithms.graphs;

import java.util.*;

public class Edmond_Karp {
	
	int n,s,t;
	int maxFlow;
	int INF = Integer.MAX_VALUE;
	ArrayList<ArrayList<Edge>> graph;
	int[] visited;
	int visitedInd = 1;
	
	public Edmond_Karp(int n) {
		this.n = n;
		s = n-1;
		s = n-2;
		
		graph = new ArrayList<ArrayList<Edge>>();
		
		for(int i=0; i<n; i++) {
			graph.add(new ArrayList<Edge>());
		}
		
		visited = new int[n];
		
		addEdges();
		solve();
	}
	
	public void addEdges() {
		
		//start connection
		addEdge(s, 0, 10);
		addEdge(s, 1, 5);
		
		//middle connections
		addEdge(0, 2, 10);
		addEdge(2, 5, 5);
		addEdge(1, 3, 10);
		addEdge(3, 6, 5);
		
		//end connection
		addEdge(5, t, 10);
		addEdge(6, t, 5);
	}
	
	public void addEdge(int from, int to, int capacity) {
		if(capacity == 0) return;
		
		Edge e1 = new Edge(from, to, capacity);
		Edge e2 = new Edge(from, to, 0);
		
		e1.risidual = e2;
		e2.risidual = e1;
		
		graph.get(from).add(e1);
		graph.get(to).add(e2);
	}
	
	public int solve() {
		
		for(int bottleNeck=bfs(); bottleNeck!=0; bottleNeck=bfs()) {
			visitedInd++;
			maxFlow += bottleNeck;
		}
		return maxFlow;
	}
	
	public int bfs() {
		
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(s);
		visited[s] = visitedInd;
	  Edge[] prev = new Edge[n];
		
		while(!q.isEmpty()) {
			
			int node = q.poll();
			
			ArrayList<Edge>  edgeList = graph.get(node);
			for(Edge e : edgeList) {
				if(e.remainingFow()>0 && visited[e.to]!=visitedInd) {
					 q.add(e.to);
					 prev[e.to] = e;
					 visited[e.to] = visitedInd; 
				}
			}
		}
		
		if(prev[t] == null) return 0;
		
		int bottleNeck = INF;
		
		for(Edge e = prev[t]; e!=null; e = prev[e.from]) {
			bottleNeck = Math.min(bottleNeck, e.capacity);
		}
		
		for(Edge e= prev[t]; e!=null; e= prev[e.from]) {
			e.augment(bottleNeck);
		}
		
		return bottleNeck;
	}
	
	class Edge{
		
		int from;
		int to;
		int capacity;
		int flow;
		Edge risidual;
		
		public Edge(int from, int to, int capacity) {
			 this.from = from;
			 this.to = to;
			 this.capacity = capacity;
		}
		
		public int remainingFow() {
			return this.capacity - this.flow;
		}
		
		public void augment(int flow) {
			 this.flow += flow;
			 this.risidual.flow -= flow;
		}
	}
}
