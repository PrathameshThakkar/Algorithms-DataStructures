package algorithms.graphs;

import java.util.*;

//Kan's Algo is a node indegree way of doing a topological sort
public class Khans {
	
	 HashMap<Integer,ArrayList<Integer>> g = new HashMap<Integer, ArrayList<Integer>>();
	 ArrayList<ArrayList<Integer>> data = new ArrayList<ArrayList<Integer>>() {{
		  add(new ArrayList<Integer>(Arrays.asList(2,1)));
		  add(new ArrayList<Integer>(Arrays.asList(1,0)));
		  add(new ArrayList<Integer>(Arrays.asList(3,1)));
		  add(new ArrayList<Integer>(Arrays.asList(4,0)));
	 }};
	 HashMap<Integer,Boolean> visited = new HashMap<Integer,Boolean>();
	 int[] result;
	 int n;
	 
	 public Khans() {
		 
		   for(List<Integer> list : data) {
		  	   ArrayList<Integer> l = g.getOrDefault(list.get(1),new ArrayList<Integer>());
		  	   l.add(list.get(0));
		  	   g.put(list.get(1),l);
		  	   
		  	   if(!g.containsKey(list.get(0))) {
		  	  	 g.put(list.get(0),new ArrayList<Integer>());
		  	   }
		  	   
		  	   visited.put(list.get(0),false);
		  	   visited.put(list.get(1),false);
		   } 
		   n = visited.size();
		   result = new int[n];
	 }
	 
	 public int[] getKhan() {
		 int index = 0;
		 
		 HashMap<Integer,Integer> degree = new HashMap<Integer,Integer>();
		 Queue<Integer> q = new LinkedList<Integer>();
		 
		 for(Map.Entry<Integer,ArrayList<Integer>> entry: g.entrySet()) {
			 int node = entry.getKey();
			 ArrayList<Integer> adj = entry.getValue();
			 for(int nei : adj) {
				 int count = degree.getOrDefault(nei,0);
				 degree.put(entry.getKey(), count+1);
			 }
			 
			 if(!degree.containsKey(node)) {
				 degree.put(node, 0);
			 }
		 }
		 
		 for(Map.Entry<Integer,Integer> entry: degree.entrySet()) {
			  if(entry.getValue()==0) {
			  	q.add(entry.getKey());
			  }
		 }
		 
		 while(!q.isEmpty()) {
			 int root = q.poll();
			 result[index++] = root;
			 
			 for(int node : g.getOrDefault(root, new ArrayList<Integer>())) {
				 if(!visited.containsKey(node)) {
					 degree.put(node,degree.get(node)-1);
					 if(degree.get(node)==0) {
						  q.add(node);
					 }
				 }
			 }
		 }
		 		 
		  return index==n? result: null;
	 }

}
