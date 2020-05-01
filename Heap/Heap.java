
// heap.java
// demonstrates heaps
// to run this program: C>java HeapApp
import java.io.*;

////////////////////////////////////////////////////////////////
class Node {
	private int iData; // data item (key)
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
// -------------------------------------------------------------
} // end class Node
////////////////////////////////////////////////////////////////

class Heap {
	private Node[] heapArray;
	private int maxSize; // size of array
	private int currentSize; // number of nodes in array
// -------------------------------------------------------------

	public Heap(int mx) // constructor
	{
		maxSize = mx;
		currentSize = 0;
		heapArray = new Node[maxSize]; // create array
	}

// -------------------------------------------------------------
	public boolean isEmpty() {
		return currentSize == 0;
	}

// -------------------------------------------------------------
	public boolean toss(int key) {
		if (currentSize == maxSize)
			return false;
		Node newNode = new Node(key);
		heapArray[currentSize] = newNode;
		currentSize++;
		return true;
	}

	public void restoreHeap() {
		for (int i = currentSize / 2 - 1; i >= 0; i--) {
			trickleDown(i);
		}
	}

	public boolean insert(int key) {
		if (currentSize == maxSize)
			return false;
		Node newNode = new Node(key);
		heapArray[currentSize] = newNode;
		trickleUp(currentSize++);
		return true;
	} // end insert()
// -------------------------------------------------------------

	public void trickleUp(int index) {
		int parent = (index - 1) / 2;
		Node bottom = heapArray[index];

		while (index > 0 && heapArray[parent].getKey() < bottom.getKey()) {
			heapArray[index] = heapArray[parent]; // move it down
			index = parent;
			parent = (parent - 1) / 2;
		} // end while
		heapArray[index] = bottom;
	} // end trickleUp()
// -------------------------------------------------------------

	public Node remove() // delete item with max key
	{ // (assumes non-empty list)
		Node root = heapArray[0];
		heapArray[0] = heapArray[--currentSize];
		trickleDown(0);
		return root;
	} // end remove()
// -------------------------------------------------------------

	public void trickleDown(int index) {
		int largerChild;
		Node top = heapArray[index]; // save root
		while (index < currentSize / 2) // while node has at
		{ // least one child,
			int leftChild = 2 * index + 1;
			int rightChild = leftChild + 1;
			// find larger child
			if (rightChild < currentSize && // (rightChild exists?)
					heapArray[leftChild].getKey() < heapArray[rightChild].getKey())
				largerChild = rightChild;
			else
				largerChild = leftChild;
			// top >= largerChild?
			if (top.getKey() >= heapArray[largerChild].getKey())
				break;
			// shift child up
			heapArray[index] = heapArray[largerChild];
			index = largerChild; // go down
		} // end while
		heapArray[index] = top; // root to index
	} // end trickleDown()
// -------------------------------------------------------------

	public boolean change(int index, int newValue) {
		if (index < 0 || index >= currentSize)
			return false;
		int oldValue = heapArray[index].getKey(); // remember old
		heapArray[index].setKey(newValue); // change to new

		if (oldValue < newValue) // if raised,
			trickleUp(index); // trickle it up
		else // if lowered,
			trickleDown(index); // trickle it down
		return true;
	} // end change()
// -------------------------------------------------------------

	public void displayHeap() {
		System.out.print("heapArray: "); // array format
		for (int m = 0; m < currentSize; m++)
			if (heapArray[m] != null)
				System.out.print(heapArray[m].getKey() + " ");
			else
				System.out.print("-- ");
		System.out.println();
		// heap format
		int nBlanks = 32;
		int itemsPerRow = 1;
		int column = 0;
		int j = 0; // current item
		String dots = "...............................";
		System.out.println(dots + dots); // dotted top line

		while (currentSize > 0) // for each heap item
		{
			if (column == 0) // first item in row?
				for (int k = 0; k < nBlanks; k++) // preceding blanks
					System.out.print(' ');
			// display item
			System.out.print(heapArray[j].getKey());

			if (++j == currentSize) // done?
				break;

			if (++column == itemsPerRow) // end of row?
			{
				nBlanks /= 2; // half the blanks
				itemsPerRow *= 2; // twice the items
				column = 0; // start over on
				System.out.println(); // new row
			} else // next item on row
				for (int k = 0; k < nBlanks * 2 - 2; k++)
					System.out.print(' '); // interim blanks
		} // end for
		System.out.println("\n" + dots + dots); // dotted bottom line
	} // end displayHeap()
// -------------------------------------------------------------
} // end class Heap
////////////////////////////////////////////////////////////////

class HeapApp {
	public static void main(String[] args) throws IOException {
		int value, value2;
		Heap theHeap = new Heap(31); // make a Heap; max size 31
		boolean success;

		theHeap.insert(70); // insert 10 items
		theHeap.insert(40);
		theHeap.insert(50);
		theHeap.insert(20);
		theHeap.insert(60);
		theHeap.displayHeap();// displays heap after the initial inserts
		theHeap.toss(100);
		theHeap.toss(80);
		theHeap.toss(30);
		theHeap.toss(10);
		theHeap.toss(90);
		System.out.println("******************THE INCORRECT HEAP FORMED AFTER REPEATED TOSS**********************");
		theHeap.displayHeap();// displays incorrect heap after few toss() functions
		theHeap.restoreHeap();
		System.out.println("******************THE CORRECTED HEAP AFTER RESTOREHEAP() FUNCTION********************");
		theHeap.displayHeap();//displays correct heap after restoreHeap() function
	}
} // end class HeapApp
////////////////////////////////////////////////////////////////