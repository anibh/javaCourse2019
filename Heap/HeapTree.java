
// heap.java
// demonstrates heaps
// to run this program: C>java HeapApp
import java.io.*;
import java.util.*;

////////////////////////////////////////////////////////////////
class Node {
	public int iData; // data item (key)
	public Node leftChild;
	public Node rightChild;
	public Node parent;
// -------------------------------------------------------------

	public Node(int key) // constructor
	{
		iData = key;
	}

// -------------------------------------------------------------
	public int getKey() {
		return iData;
	}

// -------------------------------------------------------------
	public void setKey(int id) {
		iData = id;
	}

	public void displayNode() {
		System.out.print(iData);
	}
// -------------------------------------------------------------
} // end class Node
////////////////////////////////////////////////////////////////

class HeapTree {
	private Node root; // number of nodes in array
	private int currentSize;
	Stack<Integer> path = new Stack<Integer>();
// -------------------------------------------------------------

	public HeapTree() // constructor
	{
		root = null;
	}

// -------------------------------------------------------------
	public boolean isEmpty() {
		return (root == null);
	}

	private void findPath(int index) {
		while (index >= 1) {
			path.push(index % 2);
			index = index / 2;
		}
		if(!path.isEmpty()) path.pop();
	}

// -------------------------------------------------------------
//	public boolean toss(int key) {
//		if (currentSize == maxSize)
//			return false;
//		Node newNode = new Node(key);
//		heapArray[currentSize] = newNode;
//		currentSize++;
//		return true;
//	}
//
//	public void restoreHeap() {
//		for (int i = currentSize / 2 - 1; i >= 0; i--) {
//			trickleDown(i);
//		}
//	}

	public void insert(int key) {
		Node newNode = new Node(key);
		if (this.isEmpty()) {
			root = newNode;
			currentSize++;
			return;
		}
		findPath(++currentSize);
		Node current = root;
		while (!path.isEmpty()) {
			int direction = path.pop();
			if (path.isEmpty()) {
				if (direction == 0) {
					current.leftChild = newNode;
					newNode.parent = current;
					trickleUp(current.leftChild);
				} else {
					current.rightChild = newNode;
					newNode.parent = current;
					trickleUp(current.rightChild);
				}
			} else {
				if (direction == 0)
					current = current.leftChild;
				else
					current = current.rightChild;
			}
		}
	} // end insert()
// -------------------------------------------------------------

	public void trickleUp(Node current) {
		Node parent = current.parent;
		int bottom = current.getKey();

		while (parent != null && parent.getKey() < bottom) {
			current.setKey(parent.getKey()); // move it down
			current = parent;
			parent = parent.parent;
		} // end while
		current.setKey(bottom);
	} // end trickleUp()
// -------------------------------------------------------------

	public Node remove() // delete item with max key
	{ // (assumes non-empty list)
		findPath(currentSize--);
		Node current = root;
		while (!path.isEmpty()) {
			int direction = path.pop();
			if (path.isEmpty()) {
				if (direction == 0) {
					root.setKey(current.leftChild.getKey());
					current.leftChild = null;
				} else {
					root.setKey(current.rightChild.getKey());
					current.rightChild = null;
				}
			} else {
				if (direction == 0)
					current = current.leftChild;
				else
					current = current.rightChild;
			}
		}
		trickleDown(root);
		return root;
	} // end remove()
// -------------------------------------------------------------

	public void trickleDown(Node current) {
		Node largerChild;
		Node top = current; // save root
		while (current.leftChild != null || current.rightChild != null) // while node has at
		{ // least one child,
			// find larger child
			if (current.rightChild != null && // (rightChild exists?)
					current.leftChild.getKey() < current.rightChild.getKey())
				largerChild = current.rightChild;
			else
				largerChild = current.leftChild;
			// top >= largerChild?
			if (current.getKey() >= largerChild.getKey())
				break;
			int temp = current.getKey();
			current.setKey(largerChild.getKey());
			largerChild.setKey(temp);
			// shift child up
			current = largerChild;
			 // go down
		} // end while
		
	} // end trickleDown()
//// -------------------------------------------------------------
//

	public boolean change(int index, int newValue) {
		if (index < 0 || index > currentSize)
			return false;
		int oldValue;
		findPath(index);
		Node current = root;
		if(path.isEmpty()) {
			oldValue = root.getKey();
			root.setKey(newValue);
			if (oldValue < newValue)
				trickleUp(root);
			else
				trickleDown(root);
		}
		
		while (!path.isEmpty()) {
			int direction = path.pop();
			if (path.isEmpty()) {
				if (direction == 0) {
					oldValue = current.leftChild.getKey();
					current.leftChild.setKey(newValue);
					if (oldValue < newValue)
						trickleUp(current.leftChild);
					else
						trickleDown(current.leftChild);
				} else {
					oldValue = current.rightChild.getKey();
					current.rightChild.setKey(newValue);
					if (oldValue < newValue)
						trickleUp(current.rightChild);
					else
						trickleDown(current.rightChild);
				}
			} else {
				if (direction == 0)
					current = current.leftChild;
				else
					current = current.rightChild;
			}
		}
		return true;
	} // end change()
// -------------------------------------------------------------

	public void displayHeap() {
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
					System.out.print(temp.iData);
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
	}
// -------------------------------------------------------------
} // end class Heap
////////////////////////////////////////////////////////////////

class HeapTreeApp {
	public static void main(String[] args) throws IOException {
		int value, value2;
		HeapTree theHeap = new HeapTree(); // make a Heap; max size 31
		boolean success;

		theHeap.insert(70); // insert 10 items
		theHeap.insert(40);
		theHeap.insert(50);
		theHeap.insert(20);
		theHeap.insert(60);
		theHeap.insert(100);
		theHeap.insert(80);
		theHeap.insert(30);
		theHeap.insert(10);
		theHeap.insert(90);
		theHeap.displayHeap();// displays heap after the initial inserts
		theHeap.remove();
		theHeap.displayHeap();
		theHeap.change(9, 55);
		theHeap.displayHeap();
		theHeap.change(1, 15);
		theHeap.displayHeap();

	}
} // end class HeapApp
////////////////////////////////////////////////////////////////
