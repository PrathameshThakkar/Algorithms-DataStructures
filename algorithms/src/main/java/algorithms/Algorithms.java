package algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

import algorithms.graphs.HerpKarp_HalmiltonianCircuit_With_Bits;

public class Algorithms {
	
	static void print(char[] arr) {
		 for(char a: arr) System.out.print(a+" ");
	}

	public static void main(String[] args) {
		

		
		
//		System.out.println(l);
//		l.remove(2);
//		System.out.println(l);
		//new PlayGround().solve();
    //new mergeSort();		
		//new InsertionSort();
		//new topologicalSort().doTopologicalSort();
		//new dfs().dodfs();
		//new QuickSort();
		//new bubblesort();8-
		
		///new unionfind().doUnionFind();
		
		ArrayList<String> l = new ArrayList<String>();
		
		l.add("JFK");
		l.add("UFO");
		
		ArrayList<Integer> l1 = (ArrayList<Integer>) l.clone();
		l.remove("JFK");
		System.out.println(l);
    
		System.out.println(l1);
		                      
	}
}

//char[] arr = {'e',' ',' ',' ','p','e','r','f','e','c','t',' ',' ',' ',' ','m','a','k','e','s',' ','p','r','a','c','t','i','c','e'};                   
//
//reverse(0,arr.length-1,arr);
//
//int l = 0;
//int r = 0;
//int n = arr.length;
//
//while(l<n && r<n) {
//	 
//	   while(l<n && arr[l]==' ') {
//	  	 l++;
//	  	 r++;
//	   }
//	   
//	   while(r<n-1 && arr[r+1]!=' ') {
//	  	 r++;
//	   }
//	   
//	   reverse(l,r,arr);
//	   
//	   r++;
//	   l= r;
//	 
//}
//
//print(arr);

//int[][] matrix =  {{1,1,1,1,1,1,1,1,1},
//    {1,2,2,2,2,2,2,2,1},
//    {1,2,3,3,3,3,3,2,1},
//    {1,2,3,4,4,4,3,2,1},
//    {1,2,3,3,3,3,3,2,1},
//    {1,2,2,2,2,2,2,2,1},
//    {1,1,1,1,1,1,1,1,1}};
//
//
//
//int R = matrix.length;
//int C = matrix[0].length;
//
//int[] copy = new int[R*C];
//int j = 0;
//int index = 0;
//
//
//for(int i=0; i<(R+1)/2; i++){ 
////copy row top
//
//for(j = i; j<(C-i); j++) {
//copy[index++] = matrix[i][j];
////System.out.print(i+""+j+""+index+" ");
////System.out.print(matrix[i][j]+" ");
//}
//// System.out.println("");
//// print(copy);
////copy column right
//for(j = i+1; j<(R-i-1); j++) {
//copy[index++] = matrix[j][C-i-1];
////System.out.print(j+""+(C-i-1)+""+index+" ");
////System.out.print(matrix[j][C-i-1]+" ");
//}
////System.out.println("");
////print(copy);
////copy row bottom
//for(j = i; j<C-i && (R-i-1!=i); j++) {
//copy[index++] = matrix[R-i-1][C-j-1];
////System.out.print((R-i-1)+""+(C-j-1)+""+index+" ");
////System.out.print(matrix[R-i-1][C-j-1]+" ");
//}
////System.out.println("");
//// print(copy);
////copy column left
//for(j = i+1; j<(R-i-1) && (C-i-1!=i); j++) {
//copy[index++] = matrix[R-j-1][i];
////System.out.print((R-j-1)+""+i+""+index+" ");
////System.out.print(matrix[R-j-1][i]+" ");
//}
////System.out.println("");
//
//
//}  
//
//print(copy);