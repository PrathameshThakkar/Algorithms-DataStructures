package algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class topologicalSort {
	
	public void doTopologicalSort() {
		List<List<Character>> givenOrder = new ArrayList<List<Character>>()
			{{
					add(new ArrayList<Character>() {{ add('B'); add('A'); }});
					add(new ArrayList<Character>() {{ add('C'); add('E'); }});
					add(new ArrayList<Character>() {{ add('C'); add('B'); }});
					add(new ArrayList<Character>() {{ add('E'); add('D'); }});
			}};
			
			char[] order = sort(givenOrder);
		
			for(char c: order) {
				System.out.println(c);
			}
	}
	
	enum Status{
		UNVISITED,
		VISITING,
		VISITED;
	}
	
	List<List<Character>> givenOrder;
	HashMap<Character, List<Character>> graph;
	int nodeSize,orderPosition;
	HashMap<Character,Status> visited;
	char[] order;
	boolean isPossible = true;
	
	public char[] sort(List<List<Character>> givenOrder) {
		
		if(givenOrder == null || givenOrder.size()==0) {
			return null;
		}
		
		this.givenOrder = givenOrder;
		
		graph = new HashMap<Character, List<Character>>();
		visited = new HashMap<Character,Status>();
		
		for(List<Character> charList: givenOrder) {
			char from = charList.get(1);
			char to = charList.get(0);
			List<Character> adj = graph.getOrDefault(from, new ArrayList<Character>());
			adj.add(to);
			graph.put(from,adj);
			visited.put(from, Status.UNVISITED);
			visited.put(to, Status.UNVISITED);
		}
		
		nodeSize = visited.size();	
		orderPosition = nodeSize-1;
		order = new char[nodeSize];
		
		
		for(Entry<Character, List<Character>> node: graph.entrySet()) {
			Character currentNode = (char)node.getKey();
			
			if(visited.get(currentNode) == Status.UNVISITED) {
				dfs(currentNode);
			}
		}
		
		if(isPossible) {
			return order;
		}else {
			return null;
		}
	}
	
	public void dfs(Character currentNode) {
		 
		if(currentNode == null || !isPossible) return;
		
		visited.put(currentNode, Status.VISITING);
		
		for(Character adjNode: graph.getOrDefault(currentNode,new ArrayList<Character>())) {
				if(visited.get(adjNode) == Status.VISITING) {
					isPossible = false;
					return;
				}else if(visited.get(adjNode) == Status.UNVISITED) {
					dfs(adjNode);
				}
		}
		
		visited.put(currentNode,Status.VISITED);
		
		order[orderPosition] = currentNode;
		orderPosition -= 1;
	}
}


