package algorithms;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class priorityOrder {

	public void order(){
		PriorityQueue<List<Integer>> order = new PriorityQueue<List<Integer>>(new Comparator<List<Integer>>() {

			public int compare(List<Integer> p1, List<Integer> p2) {
				// TODO Auto-generated method stub
				 return Double.compare(distanceFromStart(p1), distanceFromStart(p2));
			}
			
		});
		
		order.add(new ArrayList<Integer>() {{ add(1); add(2);}});
		order.add(new ArrayList<Integer>() {{ add(-1); add(1);}});
		order.add(new ArrayList<Integer>() {{ add(1); add(1);}});
		order.add(new ArrayList<Integer>() {{ add(4); add(0);}});
		order.add(new ArrayList<Integer>() {{ add(9); add(0);}});
		order.add(new ArrayList<Integer>() {{ add(2); add(1);}});
				
		while(!order.isEmpty()) {
			List<Integer> l = order.poll();
			System.out.print(l);
			System.out.println(distanceFromStart(l));
		}
		
	}
	
	
	 public double distanceFromStart(List<Integer> points) {
	 		return (Math.pow(points.get(0), 2) + Math.pow(points.get(1), 2));
	 	}
	
}
