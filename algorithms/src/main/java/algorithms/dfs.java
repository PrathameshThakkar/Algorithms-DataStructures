package algorithms;

public class dfs {
	
	  int C = 0;
	  int R = 0;
	  char[][] grid;
	  boolean[][] visited;
	  char S,E;
	  int[] dr = {0,1,0,-1};
	  int[] dc = {-1,0,1,0};
	  
	  public void dodfs() {
	  	 char[][] grid = {
	 				{'S','0','0','0'},
	 				{'0','1','0','0'},
	 				{'0','0','0','X'},
	 				{'0','0','E','0'}
	 		 };
	  	 boolean pathExist = dfsSolver(grid,'S','E');
	  	 System.out.println(pathExist);
	   }
   
	  public boolean dfsSolver(char[][] grid, char S, char E) {
	  		  	  
	  	  C = grid[0].length;
	  	  R = grid.length;
	  	  this.grid = grid;
	  	  this.S = S;
	  	  this.E = E;
	  	  
	  	  visited = new boolean[R][C];

	  	  return dodfsHelper(0,0);
	  	  
	  }
	  
	  public boolean dodfsHelper(int r, int c) {
	  	
	  	if(grid[r][c] == E) {
	  		return true;
	  	}
	  	
	  	visited[r][c] = true;
	  	
	  	for(int i = 0; i<4; i++) {
	  		 int cr = r + dr[i];
	  		 int cc = c + dc[i];
	  		 
	  		 if(cr>=0  && cr<R && cc>=0 && cc<R && visited[cr][cc]==false) {
	  			 if(dodfsHelper(cr,cc)) {
	  				 return true;
	  			 }
	  		 }
	  	}
	  	
	  	return false;
	  }
}
