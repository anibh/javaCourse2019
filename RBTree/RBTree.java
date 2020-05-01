
// tree.java
// demonstrates binary tree
// to run this program: C>java TreeApp
import java.io.*;
import java.util.*; // for Stack class
////////////////////////////////////////////////////////////////

class Node {
	public int iData; // data item (key)
	public boolean isRed; // Color of Node
	public Node leftChild; // this node's left child
	public Node rightChild; // this node's right child

	public void displayNode() // display ourself
	{
		System.out.print('{');
		System.out.print(iData);
		System.out.print(", ");
		System.out.print(this.isRed ? 'R' : 'B');
		System.out.print("} ");
	}
} // end class Node
////////////////////////////////////////////////////////////////

class RbTree {
	private Node root; // first node of tree

// -------------------------------------------------------------
	public RbTree() // constructor
	{
		root = null;
	} // no nodes in tree yet
// -------------------------------------------------------------

	public Node find(int key) // find node with given key
	{ // (assumes non-empty tree)
		Node current = root; // start at root
		while (current.iData != key) // while no match,
		{
			if (key < current.iData) // go left?
				current = current.leftChild;
			else // or go right?
				current = current.rightChild;
			if (current == null) // if no child,
				return null; // didn't find it
		}
		return current; // found it
	} // end find()
// -------------------------------------------------------------

	public void insert(int id) {
		Node newNode = new Node(); // make new node
		newNode.iData = id; // insert data
		newNode.isRed = true;
		if (root == null) { // no node in root
			root = newNode;
			newNode.isRed = false;
		} else // root occupied
		{
			Node current = root; // start at root
			flip(current);
			Node parent = null, grand = null, prev = null;
			while (true) // (exits internally)
			{
				if (grand != null)
					prev = grand;
				if (parent != null)
					grand = parent;
				parent = current;
				if (id < current.iData) // go left?
				{
					current = current.leftChild;
					if (current != null) {
						flip(current);
						if (current.isRed && parent.isRed) {
							if (parent == grand.leftChild) {
								changeColor(grand);
								changeColor(parent);
								if (prev == null)
									root = rotateRight(grand);
								else {
									if (prev.rightChild == grand)
										prev.rightChild = rotateRight(grand);
									else
										prev.leftChild = rotateRight(grand);
								}
							} else {
								changeColor(current);
								changeColor(grand);
								grand.rightChild = rotateRight(parent);
								if (prev == null)
									root = rotateLeft(grand);
								else {
									if (prev.rightChild == grand)
										prev.rightChild = rotateLeft(grand);
									else
										prev.leftChild = rotateLeft(grand);
								}
							}
						}
					} else {
						parent.leftChild = newNode;
						if (parent.isRed)
							adjustRbTree(grand, prev, parent, newNode);
						return;
					}
				} // end if go left
				else // or go right?
				{
					current = current.rightChild;
					if (current != null) {
						flip(current);
						if (current.isRed && parent.isRed) {
							if (parent == grand.rightChild) {
								changeColor(grand);
								changeColor(parent);
								if (prev == null)
									root = rotateLeft(grand);
								else {
									if (prev.rightChild == grand)
										prev.rightChild = rotateLeft(grand);
									else
										prev.leftChild = rotateLeft(grand);
								}
							} else {
								changeColor(current);
								changeColor(grand);
								grand.leftChild = rotateLeft(parent);
								if (prev == null)
									root = rotateRight(grand);
								else {
									if (prev.rightChild == grand)
										prev.rightChild = rotateRight(grand);
									else
										prev.leftChild = rotateRight(grand);
								}
							}
						}
					} else {
						parent.rightChild = newNode;
						if (parent.isRed)
							adjustRbTree(grand, prev, parent, newNode);
						return;
					}
				} // end else go right

			} // end while
		} // end else not root
	} // end insert()
// -------------------------------------------------------------

	private Node rotateLeft(Node node) {
		Node g = node;
		Node p, x;
		if (g.rightChild != null)
			p = g.rightChild;
		else
			return g;
		if (p.leftChild != null) {
			x = p.leftChild;
			p.leftChild = g;
			g.rightChild = x;
			return p;
		} else {
			p.leftChild = g;
			g.rightChild = null;
			return p;
		}
	}

	private Node rotateRight(Node node) {
		Node g = node;
		Node p, x;
		if (node.leftChild != null)
			p = node.leftChild;
		else
			return g;
		if (p.rightChild != null) {
			x = p.rightChild;
			p.rightChild = g;
			g.leftChild = x;
			return p;
		} else {
			p.rightChild = g;
			g.leftChild = null;
			return p;
		}
	}

	private void adjustRbTree(Node grand, Node prev, Node parent, Node current) {
		if (grand.leftChild == parent && parent.leftChild == current) {
			changeColor(grand);
			changeColor(parent);
			if (prev == null)
				root = rotateRight(grand);
			else {
				if (prev.rightChild == grand)
					prev.rightChild = rotateRight(grand);
				else
					prev.leftChild = rotateRight(grand);
			}
		} else if (grand.rightChild == parent && parent.rightChild == current) {
			changeColor(grand);
			changeColor(parent);
			if (prev == null)
				root = rotateLeft(grand);
			else {
				if (prev.leftChild == grand)
					prev.leftChild = rotateLeft(grand);
				else
					prev.rightChild = rotateLeft(grand);
			}
		} else if (grand.leftChild == parent && parent.rightChild == current) {
			changeColor(current);
			changeColor(grand);
			grand.leftChild = rotateLeft(parent);
			if (prev == null)
				root = rotateRight(grand);
			else {
				if (prev.rightChild == grand)
					prev.rightChild = rotateRight(grand);
				else
					prev.leftChild = rotateRight(grand);
			}
		} else if (grand.rightChild == parent && parent.leftChild == current) {
			changeColor(current);
			changeColor(grand);
			grand.rightChild = rotateRight(parent);
			if (prev == null)
				root = rotateLeft(grand);
			else {
				if (prev.rightChild == grand)
					prev.rightChild = rotateLeft(grand);
				else
					prev.leftChild = rotateLeft(grand);
			}
		}
	}

	private void flip(Node node) {
		if (!node.isRed) {
			if (node.leftChild != null && node.rightChild != null) {
				if (node.rightChild.isRed && node.leftChild.isRed) {
					if (node != root)
						node.isRed = true;
					node.leftChild.isRed = false;
					node.rightChild.isRed = false;
				}
			}
		}
	}

	private void changeColor(Node node) {
		node.isRed = node.isRed ? false : true;
	}

	public void displayTree() {
		Stack globalStack = new Stack();
		globalStack.push(root);
		int nBlanks = 32;
		boolean isRowEmpty = false;
		System.out.println("......................................................");
		while (isRowEmpty == false) {
			Stack localStack = new Stack();
			isRowEmpty = true;

			for (int j = 0; j < nBlanks; j++)
				System.out.print(' ');

			while (globalStack.isEmpty() == false) {
				Node temp = (Node) globalStack.pop();
				if (temp != null) {
					temp.displayNode();
					localStack.push(temp.leftChild);
					localStack.push(temp.rightChild);

					if (temp.leftChild != null || temp.rightChild != null)
						isRowEmpty = false;
				} else {
					System.out.print("--");
					localStack.push(null);
					localStack.push(null);
				}
				for (int j = 0; j < nBlanks * 2 - 2; j++)
					System.out.print(' ');
			} // end while globalStack not empty
			System.out.println();
			nBlanks /= 2;
			while (localStack.isEmpty() == false)
				globalStack.push(localStack.pop());
		} // end while isRowEmpty is false
		System.out.println("......................................................");
	} // end displayTree()
// -------------------------------------------------------------
} // end class Tree
////////////////////////////////////////////////////////////////

class RBTreeApp {
	public static void main(String[] args) throws IOException {
		int value;
		RbTree rbTree = new RbTree();

		rbTree.insert(50);
		rbTree.insert(25);
		rbTree.insert(75);
		rbTree.insert(12);
		rbTree.insert(37);
		rbTree.insert(6);
		rbTree.insert(18);
		rbTree.insert(3);
		rbTree.insert(43);
		rbTree.insert(30);
		rbTree.insert(33);
		rbTree.insert(87);
		rbTree.insert(93);
		rbTree.insert(97);
		rbTree.displayTree();
	} 
}