// priorityQ.java
// demonstrates priority queue
// to run this program: C>java PriorityQApp
////////////////////////////////////////////////////////////////
class PriorityQ {
	// array in sorted order, from max at 0 to min at size-1
	private int maxSize;
	private long[] queArray;
	private int nItems;

//-------------------------------------------------------------
	public PriorityQ(int s) // constructor
	{
		maxSize = s;
		queArray = new long[maxSize];
		nItems = 0;
	}

//-------------------------------------------------------------
	public void insert(long item) // insert item
	{
		queArray[nItems++] = item;
	} // end insert()
//-------------------------------------------------------------

	public long remove() // remove minimum item
	{
		int j, minIndex = 0;
		for (j = 1; j < nItems; j++) {
			if (queArray[minIndex] > queArray[j])
				minIndex = j;
		}
		long temp = queArray[minIndex];
		for (int i = minIndex; i < nItems-1; i++)
			queArray[i] = queArray[i + 1];
		nItems--;
		return temp;
	}

//-------------------------------------------------------------
	public void displayInPlace() {
		for (int i = 0; i < nItems; i++) {
			System.out.print(queArray[i] + " ");
		}
		System.out.println("");
	}

	public void displayInOrder() {
		long temp = 0;// assuming the queue contains only positive numbers
		int n = nItems;
		while (n > 0) {
			int minIndex = 0, j;
			long min = 10000;
			for (j = 0; j < nItems; j++) {
				if (min > queArray[j] && queArray[j] > temp)
					if (min > queArray[j]) {
						minIndex = j;
						min = queArray[j];
					}
			}
			temp = queArray[minIndex];
			System.out.print(temp + " ");
			n--;
		}
		System.out.println("");
	}

	public long peekMin() // peek at minimum item
	{
		return queArray[nItems - 1];
	}

//-------------------------------------------------------------
	public boolean isEmpty() // true if queue is empty
	{
		return (nItems == 0);
	}

//-------------------------------------------------------------
	public boolean isFull() // true if queue is full
	{
		return (nItems == maxSize);
	}
//-------------------------------------------------------------
} // end class PriorityQ
////////////////////////////////////////////////////////////////

class PriorityQApp {
	public static void main(String[] args) {
		PriorityQ thePQ = new PriorityQ(5);
		thePQ.insert(30);
		thePQ.insert(50);
		thePQ.insert(10);
		thePQ.insert(40);
		thePQ.insert(20);
		thePQ.displayInPlace();
		thePQ.displayInOrder();

		while (!thePQ.isEmpty()) {
			long item = thePQ.remove();
			System.out.println("");
			System.out.println(item + " removed"); // 10, 20, 30, 40, 50
			thePQ.displayInPlace();
			thePQ.displayInOrder();
		} // end while
	} // end main()
//-------------------------------------------------------------
} // end class PriorityQApp
////////////////////////////////////////////////////////////////
