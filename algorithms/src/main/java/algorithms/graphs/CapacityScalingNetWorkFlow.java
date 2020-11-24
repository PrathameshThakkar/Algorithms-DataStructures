package algorithms.graphs;

import java.util.*;

public class CapacityScalingNetWorkFlow {
		
	int n,s,t,delta = Integer.MIN_VALUE;
	ArrayList<Edge>[] graph;
	int[] visited;
	int maxFlow;
	int visitedInd = 1;
	static final int INF = Integer.MAX_VALUE;
	
	public CapacityScalingNetWorkFlow(int n) {
		this.n = n;
		s = n-1;
		t = n-2;
		
		graph = new ArrayList[n];
		visited = new int[n];
		
		addEdges();
		solve();
	}
	
	public void solve() {
		
		delta = Integer.highestOneBit(delta);
		
		for(delta=Integer.highestOneBit(delta); delta>0; delta/=2) {
			 int bottleNeck = 0;
			 
			 do {
				 bottleNeck = dfs(s,INF);
				 maxFlow += bottleNeck;
				 visitedInd++;
			 }while(bottleNeck!=0);
		}
		
	}
	
	public int dfs(int node, int flow) {
		
		if(node == t) return flow;
		
		visited[node] = visitedInd;
		
		for(Edge e : graph[node]) {
			if(e.remainingFlow()>0 && visited[e.to]!=visitedInd) {
				int bottleNeck = dfs(e.to, Math.min(flow, e.remainingFlow()));
				
				if(bottleNeck >0) {
					e.augment(bottleNeck);
					return bottleNeck;
				}
			}
		}
		
		return 0;
	}
	
	public void addEdges() {
		
		//start Edges
		addEdge(s, 0, 9);
		addEdge(s, 1, 8);
		
		//Middle Edges
		addEdge(0, 2, 9);
		addEdge(1, 3, 8);
		addEdge(3, 4, 9);
		addEdge(2, 5, 8);
		
		//End Edges
		addEdge(4, t, 9);
		addEdge(5, t, 8);
	}
	
	public void addEdge(int from, int to, int capacity) {
		
		 if(capacity == 0) return;
		 
		 Edge e1 = new Edge(from,to,capacity);
		 Edge e2 = new Edge(to,from,0);
		 
		 e1.risidual = e2;
		 e2.risidual = e1;
		
		 graph[from].add(e1);
		 graph[to].add(e2);
		 
		 delta = Math.max(delta, capacity);
	}
  
	class Edge{
		int from;
		int to;
		int capacity;
		int flow;
		Edge risidual;
		
		public Edge(int from, int to, int  capacity) {
			this.from = from;
			this.to = to;
			this.capacity = capacity;
		}
		
		public void augment(int flow) {
			this.flow += flow;
			this.risidual.flow -= flow;
		}
		
		public int remainingFlow() {
			return this.capacity - this.flow;
		}
	}
}
