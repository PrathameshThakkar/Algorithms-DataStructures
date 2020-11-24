package algorithms.graphs;

import java.util.LinkedList;
import java.util.Queue;

public class bfs {
	
	 char[][] grid;
	 int R,C;
	 boolean[][] visited;
	 char start;
	 char end;
	   public int dobfs() {
	  	 grid = new char[][]{
	 				{'S','0','0','0'},
	 				{'0','1','0','X'},
	 				{'0','0','0','0'},
	 				{'0','0','E','0'}
	 		 };
	 		 
	 		  R = grid.length;
	 		  C = grid[0].length;
	 		 
	 		 visited = new boolean[R][C];
	 		 
	 		 for(int i = 0; i<R; i++) {
	 			 for(int j=0; j<C; j++) {
	 				 visited[i][j] = false;
	 			 }
	 		 }
	 		 
	 		 
	 		 end  = 'X';
	  	 
	 		 Queue<Integer> q = new LinkedList<Integer>();
	 		 
	 		 q.add(0);
	 		 
	 		 int distance = 0;;
	 		 int nextInlayer =0;
	 		 int leftInlayer =1;
	 		 
	 		 int[] dr = {0,0,-1,1};
	 		 int[] dc = {-1,1,0,0};
	 		 
	 		 while(!q.isEmpty()) {
	 			 
	 			 int root = q.poll();
	 			 
	 			 int r = root%C;
	 			 int c = root/C;
	 			 
	 			 if(grid[r][c]==end) return distance;
	 			 
	 			 visited[r][c] = true;
	 			 leftInlayer--;
	 			 	 			 
	 			 for(int i=0; i<dr.length; i++) {
	 				 
	 				  int cr = r+dr[i];
	 				  int cc = c+dc[i];
	 				  
	 				  if(cc>=0 && cc<C && cr>=0 && cr<R && visited[cr][cc]==false) {
	 				  	q.add(cr+cc*C);
	 				  	nextInlayer++;
	 				  }
	 			 }
	 			 
	 			if(leftInlayer == 0) {
	 				 distance++;
	 				 leftInlayer = nextInlayer;
	 				 nextInlayer = 0;
	 			 }
	 			
	 		 }
	 		 return -1;
	   }	   
}
