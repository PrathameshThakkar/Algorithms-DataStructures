package algorithms.graphs;

import java.util.*;
//Using a linkedList is far better
public class EulerianPathCircuit {
	
	HashMap<Character, ArrayList<Character>> graph;
	HashMap<Character, Boolean> visited;
	int N;
	ArrayList<Character> path;
	boolean pathExist = true;
	char startNode;
	HashMap<Character,Integer> in;
	HashMap<Character,Integer> out;
	
	public EulerianPathCircuit(HashMap<Character, ArrayList<Character>> graph, int N) {
		this.graph = graph;
		this.N = N;
		in = new HashMap<Character,Integer>();
		out = new HashMap<Character,Integer>();
		solve();
	}
	
	public ArrayList<Character> solve() {
		
		if(!pathExist) return null;
		dfs(startNode);
		if(path.size() == N) return path;
		return new  ArrayList<Character>();
	}
	
	public void dfs(char node) {
		
		while(out.get(node) > 0) {
			
			   char to = graph.get(node).get(0);
			   graph.get(node).remove(0);
				 if(visited.get(to)) continue;
				 out.put(node,out.get(node)-1);
				 dfs(to);
			
		}
		path.add(node);
		visited.put(node,true);
	}
	
	public boolean EulerianPathExists() {
		
		for(Map.Entry<Character, ArrayList<Character>> entry : graph.entrySet()) {
			 char node = entry.getKey();
			 out.put(node,entry.getValue().size());
			
			 for(char to : entry.getValue()) {
				 in.put(to,in.getOrDefault(to,0)+1);
			 }
		}
		
		int startNumberNum=0;
		int endNumberNum=0;
		
		for(Map.Entry<Character,Integer> entry : in.entrySet()) {
			
			char node = entry.getKey();
			int inValue = entry.getValue();
			int outValue = out.get(node);
			
			if(inValue - outValue == 1) {
				endNumberNum++;
			}
			
			if(outValue - inValue == 1) {
				startNode = node;
				startNumberNum++;
			}
			
			if(endNumberNum> 1 || startNumberNum>1 || Math.abs(inValue - outValue)>1) {
				pathExist = false;
				return false;
			}					
		}
		
		return true;
	}
}
