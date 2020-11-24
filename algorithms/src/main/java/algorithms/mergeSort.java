package algorithms;

public class mergeSort {
	
	int[] arr = {12,-11,-2,9};
	int[] temp = new int[arr.length];
	
	public void printSortedArray() {
		for(int i: arr) {
			System.out.print(i+" ");
		}
	}
	
	
	public mergeSort() {
		doMergeSort(0,arr.length-1);
		printSortedArray();
	}
	
	public void doMergeSort(int left, int right) {
		
		if(left>=right) return;
		int mid = (right+left)/2;
		doMergeSort(left,mid);
		doMergeSort(mid+1,right);
		merge(left,right);
	}
	
	public void merge(int leftStart, int rightEnd) {
		
		int leftEnd = (rightEnd+leftStart)/2;
		int rightStart = leftEnd+1;
		int left = leftStart;
		int right = rightStart;
		int index = leftStart;
		
		while(left<=leftEnd && right<=rightEnd) {
			
			 if(arr[left]<=arr[right]) {
				 temp[index] = arr[left];
				 left++;
			 }else {
				 temp[index] = arr[right];
				 right++;
			 }
			index++;
		}		
		
		System.arraycopy(arr, left, temp, index, leftEnd-left+1);
		System.arraycopy(arr, right, temp, index, rightEnd-right+1);
		System.arraycopy(temp, leftStart, arr, leftStart, rightEnd-leftStart+1);
		
	}
}
