package algorithms;

public class Algorithms {
	public static void main(String[] args) {
		
		MinHeapImproved<Integer> m= new MinHeapImproved<Integer>();
		
		m.add(10);
		m.add(1);
		m.add(4);
		m.add(-4);
		m.add(90);
		m.add(1);
		m.add(109);
		m.add(10);
		
		m.poll();
		m.poll();
		
		
		System.out.println(m.isMinHeap());

	}
}