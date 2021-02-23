package algorithms.sorting;

public class bubblesort {
	
	int[] arr = {1,9,10,-1,-2,11,-12};
	
	private void swap(int[] arr, int x, int y) {
		
		 int temp = arr[x];
		 
		 arr[x] = arr[y];
		 
		 arr[y] = temp;
	}
	
	private boolean isInAscendingOrder(int[] arr, int x) {
		
		if(x>=arr.length) {
			return true;
		}
		
		return arr[x]<arr[x+1];
	}
	
	public void doBubbleSort() {
		
		if(arr == null) return;
		
		boolean isSorted = false;
		
		for(int i=arr.length-1; i>=0 && !isSorted ; i--) {
			
			isSorted = true;
			
			for(int j=0; j<i; j++) {
				
				if(!isInAscendingOrder(arr, j)) {
					swap(arr,j,j+1);
					isSorted = false;
				}				
			}			
		}
		
		printTable();
		
	}
	
	public void printTable() {
		for(int x: arr) System.out.print(x+" ");
	}

}
