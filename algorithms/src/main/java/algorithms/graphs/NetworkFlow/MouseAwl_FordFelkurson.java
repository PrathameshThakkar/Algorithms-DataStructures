package algorithms.graphs.NetworkFlow;

import java.util.*;

public class MouseAwl_FordFelkurson {
	
	int n,s,t;
	ArrayList<Edge>[] graph;
	int[] visited;
	int visitedInd = 1;
	int maxCost;
	int INF = Integer.MAX_VALUE;
	
	public MouseAwl_FordFelkurson() {
		int m = 10;
		int h = 4;
		
		Mouse[] mouses = MouseList(m);
		Hole[] holes = HoleList(h);
		
		n = m+h+2;
		s = n-1;
		t = n-2;
		
		graph = new ArrayList[n];
		visited = new int[n];
		
		createGraph(mouses,holes);
		solve();
	}
	
	public int solve() {
		
		for(int bottleNeck=dfs(s,INF); bottleNeck!=0; bottleNeck=dfs(s,INF)) {
			this.maxCost+=bottleNeck;
			visitedInd++;
		}
		return maxCost;
	}
	
	public int dfs(int node, int flow) {
		
		if(node == t) return flow;
		
		visited[node] = visitedInd;
		
		for(Edge e : graph[node]) {
			
			 if(e.remamingFlow()==0 || visited[e.to] == visitedInd) continue;
			 int bottleNeck = dfs(e.to, Math.min(flow, e.remamingFlow()));
			 
			 if(bottleNeck>0) {
				 e.augment(bottleNeck);
				 return bottleNeck;
			 }
		}
			
		return 0;
	}
	
	public void createGraph(Mouse[] mouses, Hole[] holes){
		
		for(int i = 0; i<mouses.length; i++) {
			addEdge(s,i,1);
		}
		
		for(int i=0; i<mouses.length; i++) {
			 for(int j=0; j<holes.length; j++){
				  if(Math.abs(mouses[i].X - holes[j].X)+Math.abs(mouses[i].Y - holes[j].Y)<=5) {
				  	addEdge(i,mouses.length+j,1);
				  }
			 }
		}
		
		for(int i = 0; i<holes.length; i++) {
			addEdge(i,t,1);
		}
	}
	
	public void addEdge(int from,int to,int capactity) {
		
		if(capactity ==0) return;
		
		Edge e1 = new Edge(from,to,capactity); 
		Edge e2 = new Edge(to,from,0);
		
		e1.risidual = e2;
		e2.risidual = e1;
		
		graph[from].add(e1);
		graph[to].add(e2);
	}
			
	public Mouse[] MouseList(int m) {
		
		  Mouse[] arr = new Mouse[m];
		  for(int i=0; i<m; i++) {
		  	arr[i] = new Mouse(i,i+4);
		  }
		  return arr;
	}
	
	public Hole[] HoleList(int h) {
		
	  Hole[] arr = new Hole[h];
	  for(int i=0; i<h; i++) {
	  	arr[i] = new Hole(i,i+4,i+1);
	  }
	  return arr;
 }
	
	class Mouse{
		int X;
		int Y;
		
		public Mouse(int X, int Y) {
			this.X = X;
			this.Y = Y;
		}
	}
	
	class Hole{
		int X;
		int Y;
		int capacity;
		
		public Hole(int X, int Y, int capacity) {
			this.X = X;
			this.Y = Y;
			this.capacity = capacity;
		}
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
		
		public boolean isRisidual() {
			return this.capacity==0;
		}
		
		public int remamingFlow() {
			 return this.capacity - this.flow;
		}
		
		public void augment(int flow) {
			this.flow += flow;
			this.risidual.flow -= flow;
		}
	}
}
