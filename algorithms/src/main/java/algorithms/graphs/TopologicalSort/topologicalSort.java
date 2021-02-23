package algorithms.graphs.TopologicalSort;

import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;

public class topologicalSort {
	
	List<List<Character>> graph;
	boolean isPossible = true;
	char[] order;
	Map<Character, List<Character>> adjList = new HashMap<Character, List<Character>>();
	Map<Character, status> visited = new HashMap<Character,status>();
	int position;
	
	public topologicalSort() {
		graph = new ArrayList<List<Character>>()
		{{
				add(new ArrayList<Character>() {{ add('B'); add('A'); }});
				add(new ArrayList<Character>() {{ add('C'); add('E'); }});
				add(new ArrayList<Character>() {{ add('C'); add('B'); }});
				add(new ArrayList<Character>() {{ add('E'); add('D'); }});
		}};
		
		setUp();
		doTopologicalSort();
		printOrder();
	}
	
	public void setUp() {
		for(List<Character> order : graph) {
			List<Character> adjNodes =  adjList.getOrDefault(order.get(1),new ArrayList<Character>());
			adjNodes.add(order.get(0));
			adjList.put(order.get(1), adjNodes);
			
			if(!visited.containsKey(order.get(0)))
				visited.put(order.get(0), status.UNVISITED);
			if(!visited.containsKey(order.get(1)))
				visited.put(order.get(1), status.UNVISITED);
		}
		
		order = new char[visited.size()];
		position = visited.size()-1;
	}
	
	public char[] doTopologicalSort() {
		 	
			for(Map.Entry<Character,List<Character>> e: adjList.entrySet()) {
				if(visited.get(e.getKey()).equals(status.UNVISITED)) {
					dfs(e.getKey());
				}
			}
		
			return (isPossible)? order : null; 
	}
	
	public void dfs(Character node) {
		
		if(node == null || isPossible == false) return;
		
		visited.put(node, status.VISITING);
		
		for(Character nodeAdj : adjList.getOrDefault(node, new ArrayList<Character>())) {
			if(visited.get(nodeAdj).equals(status.VISITING)) {
				isPossible = false;
				return;
			}else if(visited.get(nodeAdj).equals(status.UNVISITED)) {
				dfs(nodeAdj);
			}
		}
		
		visited.put(node, status.VISITED);
		
		order[position--] = node;
	}
	
	public void printOrder() {
		for(char c : order) {
			System.out.print(c+" ");
		}
	}
	
	enum status {
		UNVISITED,
		VISITING,
		VISITED;
	}
	
}


