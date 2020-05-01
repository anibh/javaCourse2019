import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Array {
	private long[] theArray;
	private int nElems;

	public Array(int max) {
		theArray = new long[max];
		nElems = 0;
	}

	public void insert(long value) {
		theArray[nElems] = value;
		nElems++;
	}

	public void display() {
		System.out.print("A=");
		for (int j = 0; j < nElems; j++)
			System.out.print(theArray[j] + " ");
		System.out.println("");
	}

	public long findKthLargest(int k) { // Outer function to find the kth Largest element
		if (k > nElems) {
			System.out.println("Array size in " + nElems + ". Cannot find " + k + "th largest element");
			return 0;
		}
		recLargest(0, nElems - 1, k);
		return theArray[k - 1];
	}

	private void recLargest(int left, int right, int k) { // Function for finding the kth largest element in the array
		int size = right - left + 1;
		if (size <= 3)
			manualSort(left, right);
		else {
			long median = medianOf3(left, right, (left + right) / 2);
			int partition = partitionIt(left, right, median);
			if (left <= k && partition >= k)
				recLargest(left, partition, k);
			else if (right >= k && partition <= k)
				recLargest(partition, right, k);

		}
	}

	public void quickSort(int k) { // Outer function for Quicksort
		recQuickSort(0, nElems - 1, k);
	}

	private void recQuickSort(int left, int right, int k) { // Quicksort algorithm using the kth index element as pivot
															// instead of n/2th element
		int size = right - left + 1;
		long median;
		if (size <= 3) // manual sort if small
			manualSort(left, right);
		else {
			if (k < right - left)
				median = medianOf3(left, right, left + k);
			else
				median = medianOf3(left, right, (left + right) / 2);
			int partition = partitionIt(left, right, median);
			recQuickSort(left, partition - 1, k);
			recQuickSort(partition + 1, right, k);
		}
	}

	private long medianOf3(int left, int right, int center) {

		if (theArray[left] > theArray[center])
			swap(left, center);

		if (theArray[left] > theArray[right])
			swap(left, right);

		if (theArray[center] > theArray[right])
			swap(center, right);

		swap(center, right - 1);
		return theArray[right - 1];
	}

	private void swap(int dex1, int dex2) {
		long temp = theArray[dex1];
		theArray[dex1] = theArray[dex2];
		theArray[dex2] = temp;
	}

	private int partitionIt(int left, int right, long pivot) {
		int leftPtr = left;
		int rightPtr = right - 1;

		while (true) {
			while (theArray[++leftPtr] < pivot)
				;
			while (theArray[--rightPtr] > pivot)
				;
			if (leftPtr >= rightPtr)
				break;
			else
				swap(leftPtr, rightPtr);
		}
		swap(leftPtr, right - 1);
		return leftPtr;
	}

	private void manualSort(int left, int right) {
		int size = right - left + 1;
		if (size <= 1)
			return;
		if (size == 2) {
			if (theArray[left] > theArray[right])
				swap(left, right);
			return;
		} else {
			if (theArray[left] > theArray[right - 1])
				swap(left, right - 1);
			if (theArray[left] > theArray[right])
				swap(left, right);
			if (theArray[right - 1] > theArray[right])
				swap(right - 1, right);
		}
	}
}

class KthLargestApp {
	public static void main(String[] args) throws IOException {
		int maxSize;
		System.out.println("Enter the size of the array : ");
		maxSize = getInt();
		System.out.println("--------------------------------------------------------------------------------------");
		System.out.println("**There will be two different random generated arrays of the enetered size");
		System.out.println("**One will be used for finding the kth largest element");
		System.out.println("**The other one will be used to demonstarte quicksort with kth element as pivot");
		System.out.println("--------------------------------------------------------------------------------------");
		Array arr1;
		Array arr2;
		arr1 = new Array(maxSize);
		arr2 = new Array(maxSize);
		for (int j = 0; j < maxSize; j++) {
			long n = (int) (java.lang.Math.random() * 99);
			arr1.insert(n);
		}
		for (int j = 0; j < maxSize; j++) {
			long n = (int) (java.lang.Math.random() * 99);
			arr2.insert(n);
		}
		arr1.display();
		System.out.println("Enter k for finding the kth element :");
		int k = getInt();
		long result = arr1.findKthLargest(k);
		arr1.display();
		System.out.println(result);
		arr2.display();
		System.out.println("Enter k for using the index for pivot :");
		k = getInt();
		arr2.quickSort(3);
		arr2.display();
	}

	public static String getString() throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}

	public static int getInt() throws IOException {
		String s = getString();
		return Integer.parseInt(s);
	}
}
