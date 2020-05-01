// insertSort.java
// demonstrates insertion sort
// to run this program: C>java InsertSortApp
//--------------------------------------------------------------
class ArrayNoDupsSort {
	private long[] a; // ref to array a
	private int nElems; // number of data items
//--------------------------------------------------------------

	public ArrayNoDupsSort(int max) // constructor
	{
		a = new long[max]; // create the array
		nElems = 0; // no items yet
	}

//--------------------------------------------------------------
	public void insert(long value) // put element into array
	{
		a[nElems] = value; // insert it
		nElems++; // increment size
	}

//--------------------------------------------------------------
	public void display() // displays array contents
	{
		for (int j = 0; j < nElems; j++) // for each element,
			System.out.print(a[j] + " "); // display it
		System.out.println("");
	}

//--------------------------------------------------------------
	public void insertionSort() {
		int in, out;

		for (out = 1; out < nElems; out++) // out is dividing line
		{
			long temp = a[out]; // remove marked item
			in = out; // start shifts at out
			while (in > 0 && a[in - 1] >= temp) // until one is smaller,
			{
				if (a[in-1] > temp) {
					a[in] = a[in - 1]; // shift item to right
					--in;// go left one position
				} else {
					temp = -1; // assuming the array has only positive elements
				}
			}
			a[in] = temp; // insert marked item
		} // end for
		int start = 0;
		for(int i = 0; i<nElems; i++) {
			if(a[i] == -1) continue;
			else a[start++] = a[i];
		}
		
		nElems = start;
	} // end insertionSort()
//--------------------------------------------------------------
} // end class ArrayIns
////////////////////////////////////////////////////////////////

class noDupsSort {
	public static void main(String[] args) {
		int maxSize = 100; // array size
		ArrayNoDupsSort arr; // reference to array
		arr = new ArrayNoDupsSort(maxSize); // create the array

		arr.insert(77); // insert 10 items
		arr.insert(99);
		arr.insert(44);
		arr.insert(55);
		arr.insert(11);
		arr.insert(88);
		arr.insert(11);
		arr.insert(00);
		arr.insert(66);
		arr.insert(33);

		arr.display(); // display items

		arr.insertionSort(); // insertion-sort them

		arr.display(); // display them again
	} // end main()
} // end class InsertSortApp
