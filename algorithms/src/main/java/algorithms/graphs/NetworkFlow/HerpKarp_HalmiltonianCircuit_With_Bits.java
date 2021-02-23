package algorithms.graphs.NetworkFlow;

import java.util.*;
public class HerpKarp_HalmiltonianCircuit_With_Bits {
	
  ArrayList<ArrayList<Character>> data = new ArrayList<ArrayList<Character>>() {{
  	 add(new ArrayList<Character>(Arrays.asList('A','B')));
  	 add(new ArrayList<Character>(Arrays.asList('B','A')));
  	 add(new ArrayList<Character>(Arrays.asList('A','C')));
  	 add(new ArrayList<Character>(Arrays.asList('C','A')));
  	 add(new ArrayList<Character>(Arrays.asList('B','C')));
  	 add(new ArrayList<Character>(Arrays.asList('C','B')));
  	 add(new ArrayList<Character>(Arrays.asList('A','D')));
  	 add(new ArrayList<Character>(Arrays.asList('B','D')));
  	 add(new ArrayList<Character>(Arrays.asList('C','D')));
  	 add(new ArrayList<Character>(Arrays.asList('D','B')));
  	 add(new ArrayList<Character>(Arrays.asList('D','C')));
  	 add(new ArrayList<Character>(Arrays.asList('D','A')));
  }};
  ArrayList<Integer> values = new ArrayList<Integer>() {{
 	 add(1);
 	 add(2);
 	 add(2);
 	 add(8);
 	 add(7);
 	 add(6);
 	 add(1);
 	 add(-4);
 	 add(9);
 	 add(4);
 	 add(8);
 	 add(3);
 }};
 
 HashMap<Character, Integer> nodeID;
 HashMap<Integer, Character> IDNode;
 ArrayList<Integer> tour;
 int[][] graph;
 int[][] memo;
 int N;
 int S = 0;
 int minCost = Integer.MAX_VALUE;
 
	
	public HerpKarp_HalmiltonianCircuit_With_Bits() {
		
		init();
		setUp();
		solve();
		getMinCost();
		getTour();
	}
	
	public void setUp() {
		
		memo = new int[N][1<<N];
		
		for(int next=1; next<N; next++) {
			  memo[next][1<<S | 1<<next] = graph[S][next];
		}
	}
	
	public void solve() {
				
		for(int r = 3; r<N; r++) {
			ArrayList<Integer> subsets = combinations(r);
			for(int subset : subsets) {
				
				if(notflipped(subset, S)) continue;
				
				for(int next=1; next<N; next++) {
					if(next==S || notflipped(subset, next)) continue;
					
					int previousState = subset^(1<<next);
					int mincost = Integer.MAX_VALUE;
					
					for(int end=1; end<N; end++) {
						if(end==next || end== S  || notflipped(previousState, end)) continue;
												
						int cost = memo[end][previousState]+graph[end][next];
						
						if(cost<mincost) {
							mincost = cost;
						}
						
					}
					 memo[next][subset] = mincost;
				}
			}
		}
	}
	
	public void getMinCost() {
		
		int endState = (1<<N)-1;
		
		for(int next=1; next<N; next++) {
			int cost = memo[next][endState]+graph[next][S];	
			if(cost<minCost) {
				minCost = cost;
			}
		}
	}
	
public void getTour() {
		
		int endState = (1<<N)-1;
		int lastIndex = S;
		tour = new ArrayList<Integer>(); 
		tour.add(S);
		
		for(int i=1; i<N; i++) {
			
			  int index = -1;
			  
			  for(int j=1; j<N; j++) {
			  	
			  	if(index == -1) index= j;
			  	
			  	int previousCost = memo[index][endState]+memo[index][lastIndex];
			  	int newCost = memo[j][endState]+memo[j][lastIndex];
			  	
			  	if(newCost<previousCost) {
			  		index = j;
			  	}
			  }
			  
			  tour.add(index);
			  lastIndex = index;
			  endState ^= (1<<index);
		}
	}
	
	public ArrayList combinations(int r) {
		
		ArrayList<Integer> subsets = new ArrayList<Integer>();
		combinations(0,0,r,subsets);
		return subsets;
		
	}
	
	public boolean notflipped(int subset,int i) {
		  return (subset&(1<<i))==1;
	}
	
	public void combinations(int val, int at,int r,ArrayList<Integer> res) {
		if(r==0) {
			 res.add(val);
			 return;
		}
		
		for(int i=at; i<N; i++) {
			 val|=(1<<i);
			 combinations(val,at+1,r-1,res);
			 val&=~(1<<i);
		}
	}
	


	public void init() {
		
		nodeID = new HashMap<Character, Integer>();
		IDNode = new HashMap<Integer, Character>();
		for(ArrayList<Character> l : data) {
			nodeID.put(l.get(0), 0);
			nodeID.put(l.get(1), 0);
		}
		
		N = nodeID.size();
	 int index = 0;
		for(Map.Entry<Character,Integer> entry : nodeID.entrySet()) {
			nodeID.put(entry.getKey(), index);
			IDNode.put(index, entry.getKey());
			index++;
		}
		
		graph = new int[N][N];
		
		index = 0;
		for(ArrayList<Character> l : data) {
			 int from = nodeID.get(l.get(0));
			 int to = nodeID.get(l.get(1));
			 
			 graph[from][to] = values.get(index++);
		}
		
		for(int[] arr : graph) System.out.println(Arrays.toString(arr));
	}

}
