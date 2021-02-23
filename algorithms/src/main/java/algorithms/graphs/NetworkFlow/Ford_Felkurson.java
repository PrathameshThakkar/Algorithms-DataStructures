package algorithms.graphs.NetworkFlow;

import java.util.*;

public class Ford_Felkurson {
	
	int n,s,t;
	ArrayList<Edge>[] graph; 
	int maxFlow;
	static final int INF = Integer.MAX_VALUE;
	int[] visited;
	int visitedStatus=1;
	
	public Ford_Felkurson(int n) {
		this.n = n;
		s = n-1;
		t = n-2;
		graph = new ArrayList[n];
		visited = new int[n];
		addEdges();
	}
	
	public void addEdges() {
		
		try {
			//Start
			addEdge(s, 0, 10);
			addEdge(s, 1, 3);
			addEdge(s, 2, 5);
			
			//Middle
			addEdge(0, 4, 10);
			addEdge(4, 5, 9);
			addEdge(7, 5, 5);
			addEdge(2, 3, 10);
			addEdge(6, 8, 3);
			addEdge(3, 4, 5);
			
			//End
			addEdge(8, t, 20);
			addEdge(9, t, 30);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int solve() {
		
		for(int bottleNeck=dfs(s,INF); bottleNeck!=0; bottleNeck=dfs(s,INF)) {
			 maxFlow+=bottleNeck;
			 visitedStatus++;
		}
		return maxFlow;
	}
	
	public int dfs(int node, int flow) {
		if(node==t) return flow;
		
		visited[node] = visitedStatus;
		
		for(Edge e : graph[node]) {
			 if(visited[e.to]==visitedStatus || e.remainningCapacity()==0) continue;
			 
			 int bottleNeck = dfs(e.to, Math.min(flow, e.remainningCapacity()));
			 
			 if(bottleNeck>0) {
				 e.augment(bottleNeck);
				 return bottleNeck;
			 }
		}
    
		return 0;
	}
	
	public void addEdge(int from, int to, int capacity) throws Exception {
		if(capacity == 0) throw new Exception("Capacity can't be null");
		Edge e1 = new Edge(from,to,capacity);
		Edge e2 = new Edge(to,from,0);
		
		e1.residual = e2;
		e2.residual = e1;
		
		graph[from].add(e1);
		graph[to].add(e2);
	}
	
	class Edge{
		int from;
		int to;
		int capacity;
		int flow;
		Edge residual;
		
		public Edge(int from, int to,int capacity) {
			 this.from = from;
			 this.to = to;
			 this.capacity = capacity;
		}
		
		public boolean isResidual() {
			return this.capacity==0;
		}
		
		public void augment(int flow) {
			 this.flow +=flow;
			 this.residual.flow -=flow;
		}
		
		public int remainningCapacity() {
			return this.capacity-this.flow;
		}
	}
	
	

}
