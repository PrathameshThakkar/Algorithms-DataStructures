package algorithms;

import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

import javafx.util.Pair;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class PlayGround {
	
	public void print(int[] arr) {
		System.out.println(Arrays.toString(arr));
	}
	
	int[] arr = {1,2,3};
	int currMin = Integer.MAX_VALUE;
	ArrayList<ArrayList<Character>> copy = new ArrayList<ArrayList<Character>>();

	List<LinkedList<Integer>> allSets = new ArrayList<LinkedList<Integer>>();
	
	
	public void prin(char[][] arr) {
		for(int i=0; i<arr.length; i++) {
			 System.out.println("");
			 for(int j=0; j<arr[0].length; j++) {
				 System.out.print(arr[i][j]+" ");					 
			 }
			 System.out.println("");
		 }
}
	
	public void prin(ArrayList<ArrayList<Character>> arr) {
		for(int i=0; i<arr.size(); i++) {
			 System.out.println("");
			 for(int j=0; j<arr.get(0).size(); j++) {
				 System.out.print(arr.get(i).get(j)+" ");					 
			 }
			 System.out.println("");
		 }
}
	
	
	public void solve(){
		char[][] arr= {{'.','S','.','.'}, 
				           {'.','#','#','.'},
				           {'.','.','#','#'},
									 {'.','E','.','.'}};
		
		ArrayList<ArrayList<Character>> l = new ArrayList<ArrayList<Character>>();
		
		for(int i=0; i<arr.length; i++) {
			 l.add(new ArrayList<Character>());
			 for(int j =0; j<arr.length; j++) {
				 l.get(i).add(arr[i][j]);
			 }
		}
		
		
		dfs(l,0,1,'E',0);
		
		//bfs(arr);
		
		prin(arr);
		
		//prin(copy);
		
//		if(dfs(arr,0,2,'E')) {
//			 for(int i=0; i<arr.length; i++) {
//				 System.out.println("");
//				 for(int j=0; j<arr[0].length; j++) {
//					 System.out.print(arr[i][j]+" ");					 
//				 }
//				 System.out.println("");
//			 }
//		}
		
	}
	
	public void bfs(char[][] arr) {
		
		int C = arr[0].length;
		int R = arr.length;
		
		boolean foundEnd = false;
		
		boolean[][] visited = new boolean[R][C];
		HashMap<Integer,Integer> prev = new HashMap<Integer,Integer>();
		int end = -1;
		int[] dr = {0,0,-1,1};
		int[] dc = {-1,1,0,0};
				
		Queue<Integer> q = new LinkedList<Integer>();
		
	  q.add(0+1*C);
	  visited[0][1] = true;
	 	  
	  while(!q.isEmpty()) {
	  	
	  	  int curr = q.poll();
	  	  int r = curr%C;
	  	  int c = curr/C;
	  	  
	  	  if(arr[r][c] == 'E') {
	  	  	foundEnd = true;
	  	  	end = r+c*C;
	  	  	break;
	  	  }
	  	  
	  	  visited[r][c] = true;
	  	  
	  	  for(int i = 0; i<dr.length; i++) {
	  	  	
	  	  	int cr = r+dr[i];
	  	  	int cc = c+dc[i];
	  	  	
	  	  	if(cr>=0 && cr<R && cc>=0 && cc<C && arr[cr][cc]!='#' && visited[cr][cc]==false) {
	  	  		
	  	  		prev.put(cr+cc*C,curr);
	  	  		visited[cr][cc]=true;
	  	  		q.add(cr+cc*C);
	  	  		
	  	  		if(arr[cr][cc] == 'E') {
	  	  			foundEnd= true;
	  	  			end = cr+cc*C;
	  	  			break;
	  	  		}
	  	  	}
	  	  }
	  }
	  
	  if(!foundEnd) {
	  	 System.out.println("Trapped");
	  }else {
	  	
	  	 Integer curr = end;
	  	 for(curr = prev.get(end); curr!=(0+1*C) ; curr = prev.get(curr)) {
	  		 
	  		 int r = curr%C;
	  		 int c = curr/C;
	  		 
	  		 arr[r][c] = '*';
	  		 
	  	 }
	  	
	  }
		
	}
	
	public void dfs(ArrayList<ArrayList<Character>> arr, int r, int c, char end,int distance) {
		  if(arr.get(r).get(c) == end) {
		  	 return;
		  }
		  
		  int[] dr = {0,0,-1,1};
		  int[] dc = {-1,1,0,0};
		  
		  int R = arr.size();
		  int C = arr.get(0).size();
		  
		  //boolean reachEnd = false;		  
		  for(int i=0; i<dr.length; i++) {
		  	int cr = r + dr[i];
		  	int cc = c + dc[i];
		  	
		  	if(cr>=0 && cr<R && cc>=0 && cc<C) {
		  		if(arr.get(cr).get(cc)=='.') {
		  			 arr.get(cr).set(cc,'*');
			  		 dfs(arr,cr,cc,end,distance+1);
			  		 arr.get(cr).set(cc,'.');
			  		
		  		}else if(arr.get(cr).get(cc)==end) {
		  			 
		  			 if(distance<currMin) {
		  				 copy = (ArrayList<ArrayList<Character>>)arr.clone();
		  				 currMin = distance;
		  			 }
		  			 return;
		  		}
		  	}
		  }	
	}
}
	
