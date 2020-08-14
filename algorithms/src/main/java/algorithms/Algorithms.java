package algorithms;

public class Algorithms {
	public static void main(String[] args) {
		
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		
		b.add(10);
		b.add(8);
		b.add(9);
		b.add(11);
		b.add(13);
		b.add(2);
		b.add(3);
		b.add(0);
		b.add(15);
		
		
		b.inorder();
		
		System.out.println("");
		
		b.preorder();
		
		System.out.println("");

		b.postorder();
		
		System.out.println("");

		b.levelOrder();
		
	}
}