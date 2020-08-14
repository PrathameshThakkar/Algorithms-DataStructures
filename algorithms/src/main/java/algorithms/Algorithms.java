package algorithms;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Algorithms {
	public static void main(String[] args) {
		
//		new bfs().dobfs();	 
//		
//    new dfs().dodfs();
//  
//    new topologicalSort().doTopologicalSort();
//
//    new priorityOrder().order();
//		
//    new quickselect().doQuickSelect();	
//		
//    new mergeSort().doMergeSort();
		
//		 new unionfind().doUnionFind();
		
 //      new bubblesort().doBubbleSort();		
		
		MinHeap<Integer> m = new MinHeap<Integer>();
		m.add(0);
		m.add(5);
		m.add(-2);
		m.add(20);
		m.add(4);
		m.add(1);
		m.add(7);
		m.add(10);
		m.add(2);
		
		System.out.println(m.poll());
		System.out.println(m);
		System.out.println(m.poll());
		System.out.println(m);
		System.out.println(m.poll());
		System.out.println(m);
		System.out.println(m.poll());
		System.out.println(m);
		System.out.println(m.poll());
		System.out.println(m);
		System.out.println(m.poll());
		System.out.println(m);
		System.out.println(m.poll());
		System.out.println(m);
		System.out.println(m.poll());
		System.out.println(m);
		System.out.println(m.poll());
		
		
	}
}
