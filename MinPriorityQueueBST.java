package ae2;

public class MinPriorityQueueBST {
	/**
	 * This is a binary search tree (BST) based binary heap implementation of the
	 * min priority queue. INSERT(Q,x): insert the element x into the queue Q.
	 * MIN(Q): returns the element(node) of Q with the smallest key. EXTRACT-MIN
	 * (Q): removes and returns the element(node) of Q with the smallest key.
	 * 
	 * 1(d): Implement and extension of BST that allows MIN and EXTRACT-MIN
	 * operations in O(1). Briefly describe your implementation in the report. Hint:
	 * maintain an extra pointer attribute in each node.
	 */
	public Node root=null; // root of BST

	// for BST extract min NO SWAPPING when removing
	// just remove (and return) the leftmost node's key
	// which is smallest in a BST
	// then move the pointer to make another node
	// the leftmost node at the end.

	public class Node {
		public int key; // sorted by key
		public Node left, right, pointer, min; // extra pointer attribute to a node with minimum key value.

		public Node(int key) {
			this.key = key;
			this.left = null;
			this.right = null;
			this.pointer = null;
			this.min = null;
		}
	}
	
	/*public int height(Node root) {
		if (root == null) {
			return 0;
		}
		if (root.left == null && root.right == null) {
			return 0;
		}
		return larger(height(root.left), height(root.right)) + 1;
	}
	
	public int larger(int integer1, int integer2) {
		if (integer1 >= integer2) {
			return integer1;
		}
		return integer2;
	}*/
	
	public void insert(int keyValue) {
		Node nodeToInsert = new Node(keyValue);
		Node parent = null;
		Node currentNode = this.root; //currentNode = x, nodeToInsert = z, parent = y
		
		while (currentNode != null) {
			parent = currentNode;
			if (nodeToInsert.key < currentNode.key) {
				currentNode = currentNode.left;
			} else {
				currentNode = currentNode.right;
			}
		}
		nodeToInsert.pointer = parent;
		if (parent == null) { // then it means this.root was null to begin with (and currentNode is already null)
			this.root = nodeToInsert;
		} else if (nodeToInsert.key < parent.key) {
			parent.left = nodeToInsert;
		} else {
			parent.right = nodeToInsert;
		}
		
		if ((this.root.min == null) || (nodeToInsert.key < this.root.min.key)) {
			this.root.min = nodeToInsert;
		}
	}

	public Node min(Node root) {
		/** return node with smallest key without removing it. runs in O(log n) time. */
		while (root.left != null) {
			root = root.left;
		}
		return root;
	}
	
	public Node minConstant() {
		/** runs in O(1) time. */
		return this.root.min;
	}

	public Node extractMin() {
		/** remove and return the node with smallest key. */
		Node nodeToReturn = min(this.root);
		delete(nodeToReturn);
		return nodeToReturn;
	}
	
	public Node extractMinConstant() {
		Node nodeToReturn = minConstant(); // O(1)
		deleteConstant(nodeToReturn); // O(1)
		return nodeToReturn;
	}

	public void delete(Node nodeToDelete) {
		
		if (nodeToDelete.left == null) {
			transplant(nodeToDelete, nodeToDelete.right);
		} else if (nodeToDelete.right == null) {
			transplant(nodeToDelete, nodeToDelete.left);
		} else {
			Node parent = min(nodeToDelete.right); 
			if (parent.pointer != nodeToDelete) {
				transplant(parent, parent.right);
				parent.right = nodeToDelete.right;
				parent.right.pointer = parent;
			}
			transplant(nodeToDelete, parent);
			parent.left = nodeToDelete.left;
			parent.left.pointer = parent;
		}
	}
	
	public void deleteConstant(Node nodeToDelete) {
		if (nodeToDelete.left == null) {
			transplant(nodeToDelete, nodeToDelete.right);
		} else if (nodeToDelete.right == null) {
			transplant(nodeToDelete, nodeToDelete.left);
		} else {
			// pointer = parentNode
			Node temp = this.root;
			this.root = nodeToDelete.right;
			Node parent = minConstant(); // difference
			this.root = temp;
			if (parent.pointer != nodeToDelete) {
				transplant(parent, parent.right);
				parent.right = nodeToDelete.right;
				parent.right.pointer = parent;
			}
			transplant(nodeToDelete, parent);
			parent.left = nodeToDelete.left;
			parent.left.pointer = parent;
		}
	}

	public void transplant(Node firstNode, Node secondNode) {
		/**
		 * Replace the subtree rooted at firstNode with the subtree rooted at
		 * secondNode. I.e. firstNode’s parent becomes secondNode’s parent.
		 */
		
		if (firstNode.pointer == null) {
			root = secondNode;
		} else if (firstNode == firstNode.pointer.left) {
			firstNode.pointer.left = secondNode;
		} else {
			firstNode.pointer.right = secondNode;
		}
		
		if (secondNode != null) {
			secondNode.pointer = firstNode.pointer;
		}
	}
	
	public void traverseInOrder() {
		traverseInOrderRecursive(this.root);
		System.out.println("");
	}
	
	public void traverseInOrderRecursive(Node root) {
		if (root != null) {
			traverseInOrderRecursive(root.left);
			System.out.print(root.key+" ");
			traverseInOrderRecursive(root.right);
		}
	}
}
