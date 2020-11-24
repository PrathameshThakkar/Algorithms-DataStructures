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
	List<LinkedList<Integer>> allSets = new ArrayList<LinkedList<Integer>>();
	
	
	public PlayGround(){
		
	}

	public void solve() {
		
		 generateSubsets(0,new LinkedList<Integer>());
		 
		 System.out.println(allSets);
		
	}
	
	 public void generateSubsets(int pos, LinkedList<Integer> subResult){
		 if(pos == arr.length) {
			 LinkedList<Integer> clone = (LinkedList<Integer>) subResult.clone();
			 allSets.add(clone);
			 return;
		 }		
				
		for(int i = pos; i<arr.length; i++) {
			subResult.add(arr[i]);
			generateSubsets(pos+1, subResult);
			subResult.removeLast();
		}
	 }
	
	class unionfind{
		
		int[] nums;
		int[] sizes;
		
		public unionfind(int size) {
			nums = new int[size];
			sizes = new int[size];
			
			for(int i =0; i< size; i++) {
				nums[i] = i;
				sizes[i] = 1;
			}
		}
		
		public int find(int i) {
		  if(arr[i]!=i) {
		  	arr[i] = find(arr[i]);
		  }
		 return i;
	 }
		
		public void union(int i, int j) {
			
			int P_i =find(i);
			int P_j =find(j);
			
			if(sizes[P_i]<sizes[P_j]) {
				nums[P_i] = nums[P_j];
				sizes[P_j] += sizes[P_i];
			}else {
				nums[P_j] = nums[P_i];
				sizes[P_i] += sizes[P_j];
			}
		}
  }
}
	
