package algorithms.graphs.NetworkFlow;

import java.util.*;

public class HerpKarp_HalmitonianCircuit {
	
	 int[][] graph;
	 int N;
	 ArrayList<HashSet<Integer>> subsets;
	 HashMap<Index,Integer> minCostDP;
	 HashMap<Index,Integer> parent;
	 int minCost = Integer.MAX_VALUE;
	 ArrayList<Integer> tour;
	 int S= 0;
	 
	 public HerpKarp_HalmitonianCircuit(int[][] graph) {		 
		 
		  N = graph.length;
		  this.graph = graph;
		  minCostDP = new HashMap<Index, Integer>();
		  parent = new HashMap<Index, Integer>();
		  subsets = new ArrayList<HashSet<Integer>>();
		  
      solve();	
      getTour();
	 }
	 
	 public int solve() {
		 
		 for( HashSet<Integer> subset : subsets) {
			  for(int next= 1 ; next<N; next++) {
			  	if(subset.contains(next)) continue;
			  	
			  	Index index = new Index(next, subset);
			  	int mincost = Integer.MAX_VALUE;
			  	int minindex = -1;
			  	for(int previousNode : subset) {
			  		
			  		 int cost = graph[previousNode][next]+ getCost((HashSet<Integer>)subset.clone(),previousNode);
			  		 if(cost< mincost) {
			  			 mincost = cost;
			  			 minindex = previousNode;
			  		 }
			  	}
			  	
			  	if(subset.size() == 0) {
			  		minCost = graph[S][next];
			  		minindex = S;
			  	}
			  	
			  	minCostDP.put(index,mincost);
			  	parent.put(index,minindex);
			  }
		 }
		 
		 HashSet<Integer> endSet = new HashSet<Integer>();
		 for(int i= 1; i<N; i++) {
			 endSet.add(i);
		 }
		 int lastMinIndex = -1;
		 for(int endNode : endSet) {
			  int cost = graph[endNode][S]+getCost((HashSet<Integer>)endSet.clone(), endNode);
			  if(cost < minCost) {
			  	minCost = cost;
			  	lastMinIndex = endNode;
			  }
		 }
		 
		 parent.put(new Index(S,endSet),lastMinIndex);
		 
		 return minCost;
	 }
	 
	 public ArrayList<Integer> getTour(){
		 
		 HashSet<Integer> endSet = new HashSet<Integer>();
		 for(int i= 0; i<N; i++) {
			 endSet.add(i);
		 }
		 
		 Integer start = S;
		 
		 while(true) {
			 
			 tour.add(start);
			 endSet.remove(start);
			 start = parent.get(new Index(start,endSet));
			 if(start == null) break;
		 }
		 
		 Collections.reverse(tour);
		 
		 return tour;
	 }
	 
	 public int getCost(HashSet<Integer> subset, int previousNode) {
		 subset.remove(previousNode);
		 Index index = new Index(previousNode,subset);
		 return minCostDP.get(index);
	 }
	 
	 public void generateSubsets() {
		 
		 generateSubsets(1,new HashSet<Integer>());
		 
		 Collections.sort(subsets, new Comparator<HashSet<Integer>>() {
			  public int compare(HashSet<Integer> set1, HashSet<Integer> set2) {
			  	 return Integer.compare(set1.size(), set2.size());
			  }
		 });
		 
	 }
	 
	public void generateSubsets(int at, HashSet<Integer> subResult) {
		
		if(at==N) {
			 subsets.add((HashSet<Integer>)subResult.clone());
			 return;
		}
		
		for(int i=at; i<N; i++) {
			subResult.add(i);
			generateSubsets(at+1,subResult);
			subResult.remove(i);
		}
		
	}
	
	
   class Index{
  	 
  	 int prevIndex;
  	 HashSet<Integer> subset;
  	   	   	 
  	 public Index(int prevIndex, HashSet<Integer> subset) {
  		  this.prevIndex = prevIndex;
  		  this.subset = subset;
  	 }
   }
   
   
   
}
