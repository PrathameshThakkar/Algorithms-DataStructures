package algorithms;

public class InsertionSort {

	int[] arr = null;
	 public InsertionSort() {
		 arr = new int[] {-1,12,9,4,6,-10,20};
		 doInsertionSort();
		 for(int x:arr) System.out.print(x+" ");
	 }
	private void doInsertionSort() {
		
		for(int i=1; i<arr.length; i++) {
			
			int current = arr[i];
			int j = i-1;
			
			while(j>=0 && arr[j]>current) {
				arr[j+1] = arr[j];
				j--;
			}
			
			arr[j+1] = current;
		}
	}
}
