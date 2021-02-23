package algorithms.graphs.Trees;
import java.util.*;

public class IsomorphicTrees {

	class TreeNode{
		int val;
		TreeNode parent;
		List<TreeNode> children;
		
		public TreeNode(int val) {
			this.val = val;
		}
		
		public TreeNode(int val, TreeNode parent) {
			this.val =  val;
			this.parent = parent;
		}
		
		public TreeNode(int val, TreeNode parent, LinkedList<TreeNode> children) {
			this.val =  val;
			this.parent = parent;
			this.children = children;
		}
	}
	
	HashMap<Integer, ArrayList<Integer>> g = new HashMap<Integer, ArrayList<Integer>>();
	
	public void init() {
		g.put(0, new ArrayList<Integer>(Arrays.asList(1)));
		g.put(1, new ArrayList<Integer>(Arrays.asList(0,2)));
		g.put(2, new ArrayList<Integer>(Arrays.asList(3,4,5)));
		g.put(3, new ArrayList<Integer>(Arrays.asList(2)));
		g.put(4, new ArrayList<Integer>(Arrays.asList(2,6)));
		g.put(5, new ArrayList<Integer>(Arrays.asList(2)));
		g.put(6, new ArrayList<Integer>(Arrays.asList(4,7)));
		g.put(7, new ArrayList<Integer>(Arrays.asList(4,7)));
	}
	
	public ArrayList<Integer> centers(){
		
		ArrayList<Integer> leaves = new ArrayList<Integer>();
		int n = g.size();
		int[] degree = new int[n];
		
		for(int i=0; i<n; i++) {
			degree[i] = g.get(i).size();
			
			if(degree[i]==1 || degree[i]==0) {
				leaves.add(i);
				degree[i] = 0;
			}
		}
		
		int count = leaves.size();
		
		while(count<n) {
			
			ArrayList<Integer> new_leaves = new ArrayList<Integer>();
			
			for(int leaf: leaves) {
				
				 for(int neighbors : g.getOrDefault(leaf, new ArrayList<Integer>())) {
					 degree[neighbors] -= 1;
					 if(degree[neighbors]==1) {
						 new_leaves.add(neighbors);
					 }
				 }
				 degree[leaf] = 0;
			}
			
			count += new_leaves.size();
			leaves = new_leaves;
			
		}
		
		return leaves;
	}
	
	public TreeNode rootTree(int rootVal) {
		
		TreeNode root = new TreeNode(rootVal);
		
		return rootTreeHelper(root,null);
	}
	
	public TreeNode rootTreeHelper(TreeNode node, TreeNode parent) {
		
		if(node == null) return null;
		
		LinkedList<TreeNode> children = new LinkedList<TreeNode>();
		
		for(int val : g.getOrDefault(node.val, new ArrayList<Integer>())) {
			if(val == node.val) continue;
			TreeNode child = new TreeNode(val);
			children.add(child);
			
			rootTreeHelper(child,node);
		}
		
		node.children = children;
		
		return node;
	}
	
	public boolean isIsomorphic(TreeNode tree1, TreeNode tree2) {
		
		if(tree1==null && tree2==null) return true;
		
		if(tree1==null || tree2==null) return false;
		
		String AHU1 = getAHU(tree1);
		
		String AHU2 = getAHU(tree2);
		
		return AHU1.equals(AHU2);
	}
	
	public String getAHU(TreeNode node) {
		if(node == null) return "";
		
		ArrayList<String> res = new ArrayList<String>();
		for(TreeNode child : node.children) {
			 String childRes = getAHU(child); 
			 res.add(childRes);
		}
			
		Collections.sort(res);
		
		StringBuilder str = new StringBuilder();
		str.append('(');
		for(String s : res) {
			str.append(s);
		}
		str.append(')');
		return str.toString();
	}
}

