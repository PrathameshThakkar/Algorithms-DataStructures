package algorithms.graphs;

import java.util.*;
public class BridgesTorjan {
	
	ArrayList<ArrayList<Integer>> graph;
	int n;
	boolean[] visited;
	int[] low,ids;
	int rootNodeOutEdgesCount;
	int index;
	ArrayList<ArrayList<Integer>> bridges;

	public BridgesTorjan(int n, ArrayList<ArrayList<Integer>> graph) {
		 this.n = n;
		 this.graph = graph;
		 visited = new boolean[n];
		 low = new int[n];
		 ids = new int[n];	
		 bridges = new ArrayList<ArrayList<Integer>>();
		 index=0;
	}	
	
	public void solve() {
				
		for(int i=0; i<n; i++) {
						
			if(visited[i] == true) {				
				dfs(i,-1);
			}
		}
	}
	
	public void dfs(int at, int parent) {
		
		visited[at] = true;
		low[at] = ids[at] = index++;
		
		for(int to : graph.get(at)) {
			if(to == parent) continue;
			if(visited[to] == false) {
				dfs(to,at);
				low[at] = Math.min(low[at], low[to]);
				if(ids[at] < low[to]) {
					bridges.add(new ArrayList<Integer>(Arrays.asList(at,to)));
				}
			}else {
				low[at] = Math.min(low[at], low[to]);
			}
		}
	}
}
