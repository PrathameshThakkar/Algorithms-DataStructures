package algorithms;

import java.util.Random;

public class quickselect {
	
	 int[] arr = {-10,-9,0,5,2,6};
	 int k = 1;
	 
	 public void doQuickSelect() {
			 System.out.println(getKElement());
	 }
	 
	 public int getKElement() {
		 
		 int result = -1;
				 
		 if(arr == null || arr.length ==0 || arr.length<k) return result;
		 
		 return getKElement(0,arr.length-1,k-1);
	 }
	 
	 public int getKElement(int left, int right, int k) {
		 
		  if(left==right) return arr[left];
		  
		  Random r = new Random();
		  int pivot = left + r.nextInt(right-left);
		  
		  int pivot_index = partition(left,right,pivot);
		  
		  if(pivot_index == k) return arr[pivot_index];
		  
		  else if(k<pivot_index) {
		  	return getKElement(left, pivot_index-1, k);
		  }else{
		  	return getKElement(pivot_index+1, right, k);
		  }
	 }

	private int partition(int left, int right, int pivot) {
		
		 int pivot_value = arr[pivot];
		 int store_index = left;
		 
		 swap(pivot,right);
		 
		 for(int i = left; i<=right; i++) {
			 if(arr[i]<pivot_value) {
				 swap(i,store_index);
				 store_index++;
			 }
		 }
		 
		 swap(store_index,right);
		 
		 return store_index;
	}

	private void swap(int i1, int i2) {
		// TODO Auto-generated method stub
		int temp = arr[i1];
		arr[i1] = arr[i2];
		arr[i2] = temp;
	}
}
