package algorithms.graphs.TopologicalSort;

import java.util.*;

//shortest and longest path of acyclic directed graph
public class SingleSourceShortPath {

	HashMap<Character, ArrayList<Edge>> g = new HashMap<Character, ArrayList<Edge>>();

	ArrayList<ArrayList<Character>> data = new ArrayList<ArrayList<Character>>() {
		{
			add(new ArrayList<Character>(Arrays.asList('A', 'B')));
			add(new ArrayList<Character>(Arrays.asList('B', 'C')));
			add(new ArrayList<Character>(Arrays.asList('D', 'A')));
			add(new ArrayList<Character>(Arrays.asList('D', 'B')));
		}
	};

	ArrayList<Integer> values = new ArrayList<Integer>() {
		{
			add(9);
			add(7);
			add(1);
			add(4);
		}
	};
	HashMap<Character, Status> visited = new HashMap<Character, Status>();
	int n;
	char[] tpSort;
	boolean possible = true;
	int arrIndex;

	public SingleSourceShortPath() {

		int index = 0;
		for (ArrayList<Character> l : data) {
			char from = l.get(0);
			char to = l.get(1);
			ArrayList<Edge> edges = g.getOrDefault(from, new ArrayList<Edge>());
			edges.add(new Edge(to, values.get(index)));
			g.put(from, edges);

			visited.put(to, Status.UNVISITED);
			if (!g.containsKey(from)) {
				visited.put(from, Status.UNVISITED);
			}

			index++;
		}
		n = visited.size();
		tpSort = new char[n];
		arrIndex = n - 1;

		topologicalSort();

		if (tpSort == null) return;

		singleSourceShortestPath();
	}

	public void singleSourceShortestPath() {

		LinkedHashMap<Character, Integer> shortestPath = new LinkedHashMap<Character, Integer>();

		for (int i = 0; i < tpSort.length; i++) {
			shortestPath.put(tpSort[i], Integer.MAX_VALUE);
		}
		char start = 'A';

		shortestPath.put(start, 0);

		for (int i = 0; i < n; i++) {

			char node = tpSort[i];

			if (shortestPath.get(node) != Integer.MAX_VALUE) {
				int dist = shortestPath.get(node);

				for (Edge e : g.getOrDefault(node, new ArrayList<Edge>())) {
					int newDist = dist + e.cost;
					if (shortestPath.get(e.node) > newDist) {
						shortestPath.put(e.node, newDist);
					}
				}
			}
		}
	}

	public void topologicalSort() {

		for (Map.Entry<Character, ArrayList<Edge>> entry : g.entrySet()) {
			char node = entry.getKey();

			if (possible && visited.get(node) == Status.UNVISITED) {
				dfs(node);
			}

			if (!possible) {
				tpSort = null;
			}
		}

	}

	public void dfs(char node) {
		if (!possible) return;

		visited.put(node, Status.VISITING);

		for (Edge to : g.getOrDefault(node, new ArrayList<Edge>())) {
			if (visited.get(to.node) == Status.UNVISITED) {
				dfs(to.node);
			} else if (visited.get(to.node) == Status.VISITING) {
				possible = false;
				return;
			}
		}

		visited.put(node, Status.VISITED);

		tpSort[arrIndex--] = node;
	}

	enum Status {
		UNVISITED,
		VISITING,
		VISITED;
	}

	class Edge {
		Character node;
		int cost;

		public Edge(Character node, int cost) {
			this.node = node;
			this.cost = cost;
		}
	}

}
