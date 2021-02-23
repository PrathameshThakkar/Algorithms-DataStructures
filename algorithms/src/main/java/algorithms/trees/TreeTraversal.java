package algorithms.trees;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class TreeTraversal {
	
	  class TreeNode {
	  	  
	  	int val;
	  	TreeNode left;
	  	TreeNode right;
	  	
	  	public TreeNode(int val) {
	  		 this.val = val;
	  	}
	  }
	  
	  class NAryNode {
  	  
	  	int val;
	  	LinkedList<NAryNode> children;
	  	
	  	public NAryNode(int val) {
	  		 this.val = val;
	  	}
	  }
	
	  public void PreOrderRecursive(TreeNode root) {
	  	
	  	 if(root == null) return;
	  	 
	  	 System.out.print(root.val+" ");
	  	
	  	 PreOrderIterative(root.left);
	  	 
	  	 PreOrderIterative(root.right);
	  }
	  
	  public void PostOrderRecursive(TreeNode root) {
	  	
	  	if(root== null) return;
	  	
	  	PostOrderIterative(root.left);
	  	
	  	PostOrderIterative(root.right);
	  	
	  	System.out.println(root.val+" ");
	  }
	  
	  public void InOrderRecursive(TreeNode root) {
	  	
	  	if(root == null) return;
	  	
	  	InOrderIetrative(root.left);
	  	
	  	System.out.println(root.val+" ");
	  	
	  	InOrderIetrative(root.right);
	  }
	  
	  public void PreOrderIterative (TreeNode root) {
	  	
	  	if(root == null) return;
	  	
	  	Stack<TreeNode> s = new Stack<TreeNode>();
	  	
	  	s.add(root);
	  	
	  	while(!s.isEmpty()) {
	  		
	  		  TreeNode curr = s.pop();
	  		  
	  		  System.out.print(curr.val);
	  		  
	  		  if(curr.right!=null) {
	  		  	s.push(curr.right);
	  		  }
	  		  
	  		  if(curr.left!=null) {
	  		  	s.push(curr.left);
	  		  }
	  		 
	  	}
	  }
	  
	  public void PostOrderIterative (TreeNode root) {
	  	
	  	if(root == null) return;
	  	
	  	LinkedList<Integer> output = new LinkedList<Integer>();
	  	
	  	Stack<TreeNode> s = new Stack<TreeNode>();
	  	
	  	s.push(root);
	  		  	
	  	while(!s.isEmpty()) {
	  			  		  
	  		 TreeNode curr =  s.pop();
	  		 
	  		 output.addFirst(curr.val);
	  		 
	  		 if(curr.left!=null) {
	  			 s.add(curr.left);
	  		 }
	  		 
	  		 if(curr.right!=null) {
	  			 s.add(curr.right);
	  		 }
	  	}
	  	
	  	while(!output.isEmpty()) {
	  		
	  		System.out.print(output.pollLast());
	  		
	  	}
	  }
	  
	  public void InOrderIetrative (TreeNode root) {
	  	
	  	Stack<TreeNode> s = new Stack<TreeNode>();
	  	
	  	TreeNode curr = root;
	  	
	  	while(curr!=null || !s.isEmpty()) {
	  		
	  		while(curr!=null) {
	  			s.push(curr);
	  			curr = curr.left;
	  		}
	  		
	  		curr = s.pop();
	  		
	  		System.out.print(curr.val+" ");
	  		
	  		curr = curr.right;
	  		
	  	}
	  	
	  }
	  
	  public void levelOrderRecursive(TreeNode root) {
	  	
	  	LinkedList<LinkedList<Integer>> output = new LinkedList<LinkedList<Integer>>();
	  	
	  	Queue<TreeNode> q = new LinkedList<TreeNode>();
	  	
	  	q.add(root);
	  	
	  	while(!q.isEmpty()) {
	  		
	  		 int size = q.size();
	  		 
	  		 LinkedList<Integer> l = new LinkedList<Integer>();
	  		 
	  		 for(int i = 0; i<size; i++) {
	  			 
	  			 TreeNode curr = q.poll();
	  			 
	  			 if(curr.left!=null) q.offer(curr.left);
	  			 
	  			 if(curr.right!=null) q.offer(curr.right);
	  			 
	  			 l.add(curr.val);
	  			 
	  		 }
	  		 
	  		 output.add(l);
	  		
	  	}
	  	
	  }
	  
	  public void levelOrderRecursive(TreeNode root, int level,List<List<Integer>> output) {
	  	
	  	if(root == null) return;
	  	
	  	if(output.size() == level) {
	  		output.add(new LinkedList<Integer>());
	  	}
	  	
	  	output.get(level).add(root.val);
	  	
	  	levelOrderRecursive(root.left, level+1, output);
	  	
	  	levelOrderRecursive(root.right, level+1, output);
	  	
	  }
	  
	  public void TraverseNaryTreeRecursive (NAryNode root) {
	  	
	  	if(root == null) return;
	  	
	  	System.out.print(root.val+" ");
	  		  	
	  	for(NAryNode curr : root.children) {
	  		
	  		TraverseNaryTreeRecursive (curr);
	  		
	  	}
	  }
	  
	  public void TraverseNaryTreeIterative (NAryNode root) {
	  	
	  	if(root == null) {
	  		 return;
	  	}
	  	
	  	Stack<NAryNode> s = new Stack<NAryNode>();
	  	
	  	s.add(root);
	  	
	  	while(!s.isEmpty()) {
	  		
	  		NAryNode curr = s.pop();
	  		
	  		System.out.print(curr.val+" ");
	  		
	  		if(curr.children == null) continue;
	  			  		
	  		for(int i=0; i<curr.children.size(); i++) {
	  			
	  			 NAryNode currChild = curr.children.pollLast();
	  					 
	  			 s.push(currChild);
	  			 
	  			 curr.children.addFirst(currChild);
	  		}
	  	}
	  }
}
