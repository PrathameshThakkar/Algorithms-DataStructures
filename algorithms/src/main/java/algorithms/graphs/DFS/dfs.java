package algorithms.graphs.DFS;

public class dfs {
	
	char[][] grid;
	boolean[][] visited;
	int C;
	int R;
	int[] dr = {1,0,-1,0};
	int[] dc = {0,1,0,-1};
	char start,end; 
	
	  public void dodfs() {
	  	 grid = new char[][]{
	 				{'S','0','0','0'},
	 				{'0','1','0','0'},
	 				{'0','0','0','X'},
	 				{'0','0','E','0'}
	 		 };
	 		 
	 		 start = 'S';
	 		 end = 'E';
	 		 C = grid[0].length;
	 		 R = grid.length;
	 		 visited = new boolean[R][C];
	 		 
	 		 for(int i=0; i<R; i++) {
	 			 for(int j = 0; j<C; j++) {
	 				 visited[i][j]=false;
	 			 }
	 		 }
	 		 
	  	 boolean pathExist = dfsSolver(0,0);
	  	 
	  	 System.out.println(pathExist);
	 }
   
	 public boolean dfsSolver(int row, int col) {
		 				 
		 if(grid[row][col] == end) return true;
		 
		 visited[row][col] = true;
		 
		 for(int i=0; i<dr.length; i++) {
			  
			  int rr = row + dr[i];
			  int cc = col + dc[i];
			  if(rr>=0 && cc >=0 && rr<R && cc<C && visited[rr][cc]==false) {
			  	 return dfsSolver(rr, cc);
			  }
		 }
		 
		 return false;
	 }
	  

}
