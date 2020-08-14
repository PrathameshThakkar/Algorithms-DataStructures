package algorithms;

import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree<T extends Comparable<T>> {
  
	class Node{
		
		public T val;
		
		public Node left;
		
		public Node right;
		
		public int height = 0;
		
		public Node(T val) {
			this.val = val;
		}
		
	}
	
	private Node root = null;
	
	public BinarySearchTree() {
		
	}
	
	public boolean contains(T element) {
		
		if(element == null || root== null) return false;
		
		return contains(root,element);
	}
	
	private boolean contains(Node root, T element) {
		
		if(root == null) return false;
		
		if(root.val.compareTo(element)>0) return contains(root.left,element);
		
		if(root.val.compareTo(element)<0) return contains(root.right,element);
		
		return true;
	}
	
	public boolean add(T element) {
		
		if(element == null || contains(element)) return false;
				
	  root = add(root,element);
		
	  return true;
	}
	
	private Node add(Node root, T element) {
		
		if(root==null) return new Node(element);
		
		if(root.val.compareTo(element)>0) root.left = add(root.left,element);
		
		if(root.val.compareTo(element)<0) root.right = add(root.right,element);
		
		root.height++;
		
		return root;
	}
	
	public boolean delete(T element) {
		
		if(element == null || root == null) return false;
		
		root = delete(root,element);
		
		return true;
		
	}
	
	public Node delete(Node root, T element) {
		
		if(root == null) return null;
		
		if(root.val.compareTo(element)>0) root.left = delete(root.left,element);
		
		else if(root.val.compareTo(element)<0) root.right = delete(root.right,element);
		
		else {
			if(root.left == null) return root.right;
			else if(root.right == null) return root.left;
			
			else {
				
				  if(root.left.height < root.right.height) {
				  	
				  	 Node mostRight = findMax(root.left);
				  	 
				  	 root.val = mostRight.val;
				  	 
				  	 root.left = delete(root.left,element);
				  	
				  }else {
				  	
				  	  Node mostLeft = findMin(root.right);
				  	  
				  	  root.val = mostLeft.val;
				  	  
				  	 root.right = delete(root.right,element);
				  }
			}
		}
		
		root.height--;
		
		return root;
	}
	
	public Node findMax(Node root) {
		
		 while(root.right!=null) {
			   root = root.right;
		 }
		
		 return root;
	}
	
	public Node findMin(Node root) {
		
		while(root.left!=null) {
			root = root.left;
		}
		
		return root;
	}
	
	public void preorder() {
		preorderHelper(root);
	}
	
	private void preorderHelper(Node root) {
		if(root == null) return;
		System.out.print(root.val+" ");
		preorderHelper(root.left);
		preorderHelper(root.right);
	}
	
	public void inorder() {
		inorderHelper(root);
	}
	
	private void inorderHelper(Node root) {
		if(root == null) return;
		inorderHelper(root.left);
		System.out.print(root.val+" ");
		inorderHelper(root.right);
	}
	
	public void postorder() {
		postorderHelper(root);
	}
	
	private void postorderHelper(Node root) {
		if(root == null) return;
		postorderHelper(root.left);
		postorderHelper(root.right);
		System.out.print(root.val+" ");
	}
	
	public void levelOrder() {
		
		 Queue<Node> q = new LinkedList<Node>();
		 
		 q.add(root);
		 
		 while(!q.isEmpty()) {
			 			 
			 System.out.println(" ");
			 int size = q.size();
			 
			 for(int i = 0; i<size; i++) {
				 				  
				  Node node = q.poll();
				  
				  System.out.print(node.val+" ");
				  				  
				  if(node.left!=null) q.add(node.left);
				  
				  if(node.right!=null) q.add(node.right);
			 }
			 
		 }
		 
	}
	
}
