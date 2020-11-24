package algorithms.graphs;

import java.util.*;

public class FloydWarshall {
	
	ArrayList<ArrayList<Character>> data = new ArrayList<ArrayList<Character>>() {{
		 add(new ArrayList<Character>(Arrays.asList('A','B')));
		 add(new ArrayList<Character>(Arrays.asList('A','C')));
		 add(new ArrayList<Character>(Arrays.asList('C','D')));
		 add(new ArrayList<Character>(Arrays.asList('D','B')));
	}};
	
	ArrayList<Integer> values = new ArrayList<Integer>() {{
		 add(1);
		 add(2);
		 add(-4);
		 add(8);
	}};
	
	HashMap<Character,Node> nodeMap = new HashMap<Character,Node>();
	
	int n;
	
	int[][] graph;
	int[][] next;
	
	public FloydWarshall() {
		init();
		solveAlShortestPath();
		getpath('A','C');
	}
	
	public void getpath(char start, char end) {
		
		 Node startMap = nodeMap.get(start);
		 Node endMap = nodeMap.get(end);
		 
		 ArrayList<Character> path = new ArrayList<Character>();
		 
		 if(graph[startMap.id][endMap.id]==Integer.MAX_VALUE) return;
		 
		 int at = startMap.id;
		 
		 while(next[at][endMap.id]!=end) {
			 
			 if(next[at][endMap.id] == -1) path = null;
			 path.add(nodeMap.get(at).desc);
			 at = next[at][endMap.id];
		 }
		 path.add(endMap.desc);
		 if(next[at][endMap.id] == -1) path = null;
		 
	}
	
	public void solveAlShortestPath(){
		
		for(int k=0; k<n; k++) {
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					 if(graph[i][k]+graph[k][j]<graph[i][j]) {
						 graph[i][j] = graph[i][k]+graph[k][j];
						 next[i][j] = next[i][k];
					 }
				}
			}
		}
		
		for(int k=0; k<n; k++) {
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					 if(graph[i][k]+graph[k][j]<graph[i][j]) {
						 graph[i][j] = Integer.MIN_VALUE;
						 next[i][j] = -1;
					 }
				}
			}
		}
	}
	
	public void init() {
		
		int index = 0;
		for(ArrayList<Character> l : data) {
			if(!nodeMap.containsKey(l.get(0)))
			nodeMap.put(l.get(0),new Node(index++,l.get(0)));
			if(!nodeMap.containsKey(l.get(1)))
			nodeMap.put(l.get(1),new Node(index++,l.get(1)));
		}
		n = nodeMap.size();
		
		graph = new int[n][n];
		next = new int[n][n];
		
		for(int i = 0; i< n; i++) {
			for(int j = 0; j<n; j++) {
				if(i!=j) {
					graph[i][j] = Integer.MAX_VALUE;
					next[i][j] = Integer.MAX_VALUE;
				}
			}
		}
		
		int k = 0;
		for(ArrayList<Character> l : data) {
			int from = nodeMap.get(l.get(0)).id;
			int to = nodeMap.get(l.get(1)).id;
			
			graph[from][to] = values.get(k++);
			next[from][to] = to;
		}
	}
	
	class Node{
		int id;
		char desc;
		
		public Node (int id, char desc){
			this.id = id;
			this.desc = desc;
		}
	}
}
