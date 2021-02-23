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
	
	int[] nums = {0,4,5,1,2,3,7,6};
	int[] temp = new int[nums.length];
	
	char[][] arr= {{'.','S','.','.'}, 
						     {'.','#','#','.'},
						     {'.','.','#','#'},
								 {'.','E','.','.'}};
	
	public void print(int[] arr) {
		System.out.println(Arrays.toString(arr));
	}
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
	
	int R = arr.length;
	int C = arr[0].length;
	int[][] dr = {{0,1},{1,0},{-1,0},{0,-1}};
	HashSet<Point> visited = new HashSet<Point>();
	int endRow, endCol;
	
	public void solve(){
		
		Queue<Point> q = new LinkedList<Point>();
		
		Point start = new Point(0,1);
		
		q.add(start);
		
		visited.add(start);
		
		int level = 0;
		
		while(!q.isEmpty()) {
			
			int size = q.size();
			
			for(int i=0; i<size; i++) {
				
				Point p = q.poll();
				
				if(p.row == endRow && p.col == endCol) {
					System.out.println(level);
					return;
				}
				
				for(int j=0; j<dr.length; j++) {
					
					int cr = p.row + dr[j][0];
					int cc = p.col + dr[j][1];
					
					if(cr>=0 && cr<R && cc>=0 && cc<C && arr[cr][cc]!='#' && !visited.contains(new Point(cr,cc))) {
						
						if(cr == endRow && cc == endCol) {
							System.out.println(level+1);
							return;
						}
						
						q.add(new Point(cr,cc));
						visited.add(new Point(cr,cc));
					}
				}
			}
			level++;
		}
	}
	
	class Point{
		int row;
		int col;
		
		Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
		
		public boolean equals(Point p) {
			if(!(p instanceof Point) || p==null) return false;
			return this.row == p.row && this.col==p.col;
		}
	}

}
	
