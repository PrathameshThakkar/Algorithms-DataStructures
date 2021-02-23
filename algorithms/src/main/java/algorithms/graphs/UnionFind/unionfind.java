package algorithms.graphs.UnionFind;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class unionfind {
	
	  HashMap<String,Integer> mapNodes = new HashMap<String,Integer>();
	 	List<String[]>  nodes = new ArrayList<String[]>() {{
	 		add(new String[] {"item1","item2"});
	 		add(new String[] {"item2","item3"});
	 		add(new String[] {"item1","item4"});
	 		add(new String[] {"item5","item7"});
	 		add(new String[] {"item7","item8"});
	 		add(new String[] {"item10","item11"});
	 		add(new String[] {"item12","item11"});
	 		add(new String[] {"item9","item1"});
	 		add(new String[] {"item15","item5"});
	 		add(new String[] {"item18","item19"});
	 	}};
	 	
	 	
	  public void doUnionFind() {
	  	//map nodes to integers	
	  	int i = 0;
	  	for(String[] s:nodes) {
	  		if(!mapNodes.containsKey(s[0])) {
	  			mapNodes.put(s[0],i++);
	  		}
	  		if(!mapNodes.containsKey(s[1])) {
	  			mapNodes.put(s[1],i++);
	  		}
	  	}
	  	int n = mapNodes.size();
	  	graph g = new graph(n);
	  	
	  	UnionFindClass u = new UnionFindClass(n);
	  	
	  	for(String[] s:nodes) {
	  		u.union(mapNodes.get(s[0]), mapNodes.get(s[1]));
	  	}
	  	
	  	int[] ids = u.getIds();
	  	int groups = u.getComponentSize();
	  	for(Map.Entry<String, Integer> entry: mapNodes.entrySet()) {
	  		mapNodes.put(entry.getKey(), ids[entry.getValue()]);
	  	}	  	
	  	
	  	
	  	System.out.println(groups);
	  	System.out.println(mapNodes);
	  	
	  }
     
		class UnionFindClass{
			 private int[] size;
		   private int[] ids;
		   private int componentSize;
		   
		   public UnionFindClass(int n) {
		  	  size = new int[n];
		  	  ids = new int[n];
		  	  setComponentSize(n);
		  	  
		  	  for(int i=0; i<n; i++) {
		  	  	ids[i] = i;
		  	  	size[i] = 1;
		  	  }
		   }
		   
		   public int[] getIds() {
		  	 return ids;
		   }
		   
		   public int find(int i) {
		  	 int root = i;
		  	 
		  	 while(root!=ids[root]) {
		  		 root = ids[root];
		  	 }
		  	 
		  	 //Path compression
		  	 while(i!=root) {
		  		 int next = ids[i];
		  		 ids[i] = root;
		  		 i = next;
		  	 }
		  	 
		  	 return i;
		   }
		   
		   public void union(int a, int b) {
		  	 
		  	 if(find(a) == find(b)) {
		  		 return;
		  	 }
		  	 
		  	 int rootA = find(a);
		  	 int rootB = find(b);
		  	 
		  	 if(size[rootA]<size[rootB]) {
		  		 ids[rootA] = rootB;
		  		 size[rootB] += size[rootA];
		  	 }else {
		  		 ids[rootB] = rootA;
		  		 size[rootA] += size[rootB];
		  	 }
		  	 setComponentSize(getComponentSize() - 1);
		   }
		
			public int getComponentSize() {
				return componentSize;
			}
		
			public void setComponentSize(int componentSize) {
				this.componentSize = componentSize;
			}
   }
   
	 class graph{
		 int n =0;
		 
		 graph(int n){
			 this.n = n;
		 }
		 Map<Integer, List<Edge>> g = new HashMap<Integer, List<Edge>>();
		 
		 public void addEdge(int from, int to, int cost) {
			 List<Edge> adj = g.getOrDefault(from, new ArrayList<Edge>());
			 adj.add(new Edge(from,to,cost));
			 g.put(from,adj);
		 }
	 }
	 
	 class Edge{
		 public int from;
		 public int to;
		 public int cost;
		 
		 public Edge(int from, int to, int cost) {
			 this.from = from;
			 this.to = to;
			 this.cost = cost;
		 }
	 }
}
