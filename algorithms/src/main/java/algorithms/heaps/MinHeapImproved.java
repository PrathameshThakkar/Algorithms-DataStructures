package algorithms.heaps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

public class MinHeapImproved<T extends Comparable<T>> {
	
	private ArrayList<T> heap;
	private HashMap<T, TreeSet<Integer>> map;
	private int heapSize = 0;
	
	public MinHeapImproved() {
		heap = new ArrayList<T>();
		map = new HashMap<T, TreeSet<Integer>>();
	}
	
	public void add(T element) {
		
		if(element == null) return;
		
		heap.add(element);
		
		heapSize++;
		
		int indexOfLastElement = heapSize-1;
		
		mapAdd(indexOfLastElement,element);
		
		swim(indexOfLastElement);
	}
	
	public boolean contains(T element) {
		return map.containsKey(element);
	}
	
	
	public void remove(T element) {
		
		if(element == null || !contains(element)) return;
		
		int getIndexOfElement = getIndexFromMap(element);
		
		removeAt(getIndexOfElement);	
	}
	
	private void removeAt(int index) {
		
		if(index>= heapSize) return;
		
		int lastIndexOfHeap = heapSize-1;
		
		swap(index, lastIndexOfHeap);
		
		T elementToDelete = heap.get(lastIndexOfHeap);
		
		heap.remove(lastIndexOfHeap);
				
		mapRemove(heapSize-1,elementToDelete);
		
		heapSize --;
		
		T elementSwaped = heap.get(index);
		
		skip(index);
		
		if(heap.get(index)==elementSwaped) swim(index);
		
	}
	
	private int getIndexFromMap(T element) {
		
		TreeSet<Integer> s = map.get(element);
		
		return s.last();
	}
	
	private void swap(int i, int j) {
		
		T temp = heap.get(i);
		
		heap.set(i, heap.get(j));
		
		heap.set(j, temp);
		
		mapSwap(i,j,heap.get(j),heap.get(i));
		
	}
	
	private void mapSwap(int i,int j, T value_i, T value_j) {
		
		TreeSet<Integer> si = map.get(value_i);
		
		TreeSet<Integer> sj = map.get(value_j);
		
		si.remove(i);
		si.add(j);
		
		sj.remove(j);
		sj.add(i);
		
		map.put(value_i, si);
		map.put(value_j, sj);
		
	}
	
	private void mapAdd(int index, T element) {
		
		TreeSet<Integer> s = map.getOrDefault(element, new TreeSet<Integer>());
		
		s.add(index);
		
		map.put(element, s);
		
	}
	
	private void mapRemove(int index, T element) {
		
		TreeSet<Integer> s = map.get(element);
		
		s.remove(index);
		
		map.put(element, s);
		
	}
	
	private void swim(int index) {
		
		int parent = (index-1)/2;
		
		while(index>0 && heapConditionVerified(index,parent)) {
			
			swap(index,parent);
			
			index = parent;
			
			parent = (parent-1)/2;
			
		}
	}
	
	
	private void skip(int index) {
		
		while(true) {
			
			int left = 2*index+1;
			
			int right = 2*index+2;
			
			
			int smallest = left;
			
			if(right<heapSize && heapConditionVerified(right,left)) smallest = right;
			
			else if(left>=heapSize || heapConditionVerified(index,left)) break;			
			
			
			swap(index,smallest);
			
			index = smallest;
		}
		
	}
	
	private boolean heapConditionVerified(int i, int j) {
		return heap.get(i).compareTo(heap.get(j))<=0;
	}
	
	public T poll() {
		T element = heap.get(0);
		removeAt(0);
		return element;
	}
	
	public boolean isMinHeap() {
		if(heapSize==0) return true;
		
		return isMinHeapHelper(0);
	}
	
	
	public boolean isMinHeapHelper(int root) {
		
		if(root>=heapSize) return true;
		
		int left = 2*root +1;
		int right = 2*root +2;
		
		if(left<heapSize && !heapConditionVerified(root,left)) return false;
		
		if(right<heapSize && !heapConditionVerified(root,right)) return false;
		
		return isMinHeapHelper(left) && isMinHeapHelper(right);
		
	}

}
