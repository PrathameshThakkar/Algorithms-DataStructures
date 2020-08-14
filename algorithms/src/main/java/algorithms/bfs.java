package algorithms;

import java.util.LinkedList;
import java.util.Queue;

public class bfs {
	
	   public void dobfs() {
	  	 char[][] grid = {
	 				{'S','0','0','0'},
	 				{'0','1','0','0'},
	 				{'0','0','0','X'},
	 				{'0','0','E','0'}
	 		 };
	  	 int shortestPath =  bfsSolver(grid,'X');
	  	 System.out.println(shortestPath);
	   }
	   
	   public int bfsSolver(char[][] grid, char end) {
	  	 	  	 
	  	 int moveCount = 0;
	  	 int leftInLayer = 1;
	  	 int nextInLayer = 0;
	  	 
	  	 int R = grid.length;
	  	 int C = grid[0].length;
	  	 
	  	 boolean[][] visited = new boolean[R][C];
	  	 
	  	 Queue<Integer> qr = new LinkedList<Integer>();
	  	 Queue<Integer> qc = new LinkedList<Integer>();
	  	 
	  	 int[] dr = {0,1,0,-1};
	  	 int[] dc = {-1,0,1,0};
	  	 
	  	 qr.add(0);
	  	 qc.add(0);
	  	 visited[0][0] = true;
	  	 
	  	 while(!qr.isEmpty()) {
	  		 int r = qr.poll();
	  		 int c = qc.poll();

	  		 for(int i=0; i<4; i++) {
	  			 int cr = r + dr[i];
	  			 int cc = c + dc[i];
	  			 
	  			 if(cc>=0 && cc<C && cr>=0 && cr<R && visited[cr][cc]==false) {
	  				 
	  				  if(grid[cr][cc]==end) {
	  				  	return moveCount+1;
	  				  }
	  				 qr.add(cr);
	  				 qc.add(cc);
	  				 visited[cr][cc]=true;
	  				 nextInLayer ++;
	  			 }
	  		 }
	  		 
	  		 leftInLayer--;
	  		 
	  		 if(leftInLayer == 0) {
	  			 leftInLayer = nextInLayer;
	  			 nextInLayer = 0;
	  			 moveCount++;
	  		 }
	  	 }
	  	 
	  	 return -1;
	   }
	   
	   
}
