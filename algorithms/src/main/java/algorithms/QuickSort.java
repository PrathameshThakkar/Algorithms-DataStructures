package algorithms;

import java.util.Random;

public class QuickSort {

	   int[] arr = null;
	
	   public QuickSort() {
	  	 
	  	  arr = new int[]{1,10,8,4,11,20,-12,16,30};
	  	  doQuickSort(0, arr.length-1);
	  	  for(int x : arr) System.out.print(x+" ");
	   }
	   
	   public void doQuickSort(int left, int right) {
	  	 
	  	 if(left>=right) return;
	  	 
	  	 Random r = new Random();
	  	 
	  	 int pivot = left + r.nextInt(right - left);
	  	 
	  	 int index_pivot = partition(left,right, pivot);
	  	 
	  	 doQuickSort(left, index_pivot);
	  	 doQuickSort(index_pivot+1, right);
	  	 
	   }
	   
	   public int partition(int left, int right, int pivot_index) {
	  	 
	  	  int pivot_value = arr[pivot_index];
	  	  
	  	  while(left<right) {
	  	  	
	  	  	 while(arr[left]<pivot_value) left++;
	  	  	 
	  	  	 while(arr[right]>pivot_value) right--;
	  	  	 
	  	  	 swap(left,right);
	  	  	
	  	  }
	  	  
	  	  return left;
	  	 
	   }
	   
	   public void swap(int i, int j) {
	  	 
	  	 int temp = arr[j];
	  	 arr[j] = arr[i];
	  	 arr[i] = temp;
	  	 
	   }
	   

}
