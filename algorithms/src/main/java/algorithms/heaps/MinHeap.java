package algorithms.heaps;

import java.util.ArrayList;

public class MinHeap<T extends Comparable<T>> {
	
	 private ArrayList<T> heap;
	 
	 public MinHeap(int size) {
		 
		 heap = new ArrayList<T>(size);
		 
	 }
	 
	 public MinHeap() {
		 heap = new ArrayList<T>();
	 }
	 
	 public int getSize() {
		 if(heap == null) return 0;
		 return heap.size();
	 }
	 
	 public void add(T element) {
		 
		 if(element == null) return;
		 
		 heap.add(element);
		 int lastInsertedInd = heap.size()-1;
		 swim(lastInsertedInd);
		 
		 stringifyHeap();
	 }
	 
	 public boolean contains(T element) {
		 return containsElement(element)!= -1;
	 }
	 
	 private int containsElement(T element) {
		 if(element == null) return -1;
		 for(int i = 0; i< heap.size(); i++) {
			 if(heap.get(i) == element) return i;
		 }
		 
		 return -1;
	 }
	 
	 public void removeAt(int elementInd) {
		 
		 int lastElement = heap.size()-1;
		 T lastElementValue = heap.get(lastElement);
		 
		 swap(elementInd, lastElement);
		 
		 heap.remove(lastElement);
		 
		 if(elementInd == lastElement) return;
		 
		 swim(elementInd);
		 
		 if(heap.get(elementInd)==lastElementValue) sink(elementInd);
		 
	 }
	 
	 
	 public void remove(T element) {
		 if(element == null) return;
		 
		 int elementInd = containsElement(element);
		 
		 if(elementInd!=-1) {
			 
			removeAt(elementInd);
			 
		 }
	 }
	 
	 private int smallest(int i, int j) {
		  if(heap.get(i).compareTo(heap.get(j))<=0) {
		  	return i;
		  }else {
		  	return j;
		  }
	 }
	 
	 public void sink(int index) {
		 
		 while(true) {
			 int left = 2*index+1;
			 int right = 2*index+2;
			 
			 int smallestNode = left;
			 
			 if(right<heap.size() && smallest(left, right)==right) smallestNode=right;
			
			 if(smallestNode>=heap.size() || smallest(smallestNode,index)==index) return;
			 
			 swap(index,smallestNode);	 			 
			 index = smallestNode;
		 }
		 
	 }
	 
	 public void swim(int index) {
		 
		 while(true) {
			 
			 int parent = (index - 1)/2;
			 
			 if(parent<0 || smallest(index,parent)==parent) return;
			 
			 swap(index,parent);
			 
			 index = parent;
		 }

	 }
	 
	 private void swap(int i, int j) {
		 T temp = heap.get(i);
		 heap.set(i, heap.get(j));
		 heap.set(j, temp);
	 }
	 
	 public T peek() {
		 return heap.get(0);
	 }
	 
	 public T poll() {
		 T element = heap.get(0);
		 removeAt(0);
		 return element;
	 }
	 
	 public void stringifyHeap() {
		 for (T t : heap) {
			System.out.print(t+" ");
		}
		 System.out.println("");
	 }
	 
	 public boolean isMinHeap() {
		 
		 if(heap==null || heap.size()==0) return true;
		 		 
		 return isMinHeapHelper(0);
		 
	 }
	 
	 private boolean isMinHeapHelper(int root) {
		 
		 if(root>=heap.size()) return true;
		 		 
		 int left = 2*root+1;
		 int right = 2*root+2;
		 
		 if(left<heap.size() && smallest(root,left)==left) return false;
		 
		 if(right<heap.size() && smallest(root,right)==right) return false;
		 
		 return isMinHeapHelper(left) && isMinHeapHelper(right);
	 }

}
