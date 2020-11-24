package algorithms.graphs;

import java.util.*;

public class ArticulationPointsTorjan {
	
	ArrayList<ArrayList<Integer>> graph;
	int n;
	boolean[] isArt,visited;
	int[] ids,low;
	int index;
	int rootNodeOutEdgesCount;
	
	public ArticulationPointsTorjan(ArrayList<ArrayList<Integer>> graph, int n) {
		this.graph = graph;
		this.n = n;
		isArt = new boolean[n];
		visited = new boolean[n];
		index=0;
	}
	
	public void solve() {
		
		for(int i=0; i<n; i++) {
			if(visited[i]==false) {
				rootNodeOutEdgesCount = 0;
				dfs(i,i,-1);
				isArt[i] = (rootNodeOutEdgesCount>1);
			}
		}
	}
	
	public void dfs(int root, int at, int parent) {
		 if(root == parent) rootNodeOutEdgesCount++;
		 
		 ids[at] = low[at] = index++;
		 visited[at] = true;
		 
		 for(int to : graph.get(at)) {
			 if(to == parent) continue;
			 
			 if(visited[to] == false) {
				 
				 dfs(root, to, at);
				 low[at] = Math.min(low[at], low[to]);
				 
				 if(ids[at] <= low[to]) {
					 isArt[at] = true;
				 }
			 }else {
				 low[at] = Math.min(low[at],ids[to]);
			 }
		 }
	}
}
