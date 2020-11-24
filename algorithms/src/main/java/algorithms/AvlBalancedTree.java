package algorithms;

public class AvlBalancedTree<T extends Comparable<T>> {

	class Node{
		
		public T value;
		
		public int bf;
		
		public int height;
				
		public Node left,right;
		
		public Node(T value) {
			this.value = value;
		}
	}
	
	private Node root;
	
	private int nodeCount = 0;
	
	public int getSize() {
		return this.nodeCount;
	}
	
	public int getHeight() {
		if(root == null) return 0;
		return root.height;
	}
	
	public boolean contains(Node node, T value) {
		if(node == null) return false;
		
		int comp = value.compareTo(node.value);
		
		if(comp<0) return contains(node.left, value);
		
		if(comp>0) return contains(node.right, value);
		
		return true;
	}
	
	public void update(Node node) {
		if(node == null) return;
		
		int leftHeight = (node.left==null)? -1 : node.left.height;
		
		int rightHeight = (node.right==null)? -1 : node.right.height;
		
		node.height = 1 + Math.max(node.left.height, node.right.height);
		
		node.bf = rightHeight - leftHeight;
	}
	
	private Node balance(Node node) {
		if(node == null) return null;
		
		if(node.bf == -2) {
			
			 if(node.left.bf <= 0) {
				 return leftleftcase(node);
			 }else {
				 return leftrightcase(node);
			 }
		}
		
		if(node.bf == 2) {
			
			if(node.right.bf >= 0) {
				return rightrightcase(node);
			}else {
				return rightleftcase(node);
			}
		}
		
		return node;
	}
	
	private Node leftleftcase(Node node) {
		return rightRotation(node);
	}
	
	private Node leftrightcase(Node node) {
		node.left = leftRotation(node.left);
		return leftleftcase(node);
	}
	
	private Node rightrightcase(Node node) {
		return leftRotation(node);
	}
	
	private Node rightleftcase(Node node) {
		node.right = rightRotation(node.right);
		return rightrightcase(node);
	}
	
	private Node leftRotation(Node node) {
		
		Node newParent = node.right;
		node.right = newParent.left;
		newParent.left = node;
		update(node);
		update(newParent);
		return newParent;
	}
	
	private Node rightRotation(Node node)  {
		
		Node newParent = node.left;
		node.left = newParent.right;
		newParent.right = node;
		update(node);
		update(newParent);
		return newParent;
	}
	
	public boolean insert(T value) {
		if(value==null || contains(root,value)) {
			return false;
		}
		
		root = insert(root,value);
		nodeCount++;
		return true;
	}
	
	private Node insert(Node node, T value) {
		if(node == null) return new Node(value);
		
		int comp = value.compareTo(node.value);
		
		if(comp<0) {
			node.left = insert(node.left, value);
		}else {
			node.right = insert(node.right, value);
		}
		update(node);
		
		return balance(node);
	}
	
	public boolean remove(T value) {
		if(value == null || !contains(root,value)) {
			return false;
		}
		
		root = remove(root,value);
		nodeCount--;
		return true;
	}
	
	public Node remove(Node node, T value) {
		
		if(node == null) return null;
		
		int comp = value.compareTo(node.value);
		
		if(comp<0) {
			node.left = remove(node.left, value);
		}
		else if(comp>0) {
			node.right = remove(node.right, value);
		}
		else {
			if(node.left==null) {
				
				return node.right;
				
			}else if(node.right==null) {
				
				return node.left;
				
			}else {
				 if(node.left.height>node.right.height) {
					 Node SuccNode = findMax(node);
					 node.value = SuccNode.value;
					 node.right =  remove(node.right,SuccNode.value);
				 }else {
					 Node SuccNode = findMin(node);
					 node.value = SuccNode.value;
					 node.left = remove(node.left,SuccNode.value);
				 }
			}
		}
			
		update(node);
		return balance(node);
	}
	
	public Node findMin(Node node) {
		while(node.left!=null) {
			node = node.left;
		}
		return node.left;
	}
	
	public Node findMax(Node node) {
		while(node.right!=null) {
			node = node.right;
		}
		
		return node.right;
	}
	
}
