import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Median {
	private long[] theArray;
	private int nElems;

	public Median(int max) {
		theArray = new long[max];
		nElems = 0;
	}

	public void insert(long value) {
		theArray[nElems] = value;
		nElems++;
	}

	public int size() {
		return nElems;
	}

	public void display() {
		System.out.print("A=");
		for (int j = 0; j < nElems; j++)
			System.out.print(theArray[j] + " ");
		System.out.println("");
	}

	private void partitionIt(int left, int right, int pivot) {
		if (left == right)
			return;
		int leftPtr = left - 1;
		int rightPtr = right + 1;
		while (true) {
			while (leftPtr < right && theArray[++leftPtr] < theArray[pivot])
				;

			while (rightPtr > left && theArray[--rightPtr] > theArray[pivot])
				;
			if (leftPtr >= rightPtr)
				break;
			else {
				swap(leftPtr, rightPtr);
				if (leftPtr == pivot)
					partitionIt(left, rightPtr, pivot);
				else if (rightPtr == pivot)
					partitionIt(leftPtr, right, pivot);
			}
		}
	}

	private void swap(int dex1, int dex2) {
		long temp;
		temp = theArray[dex1];
		theArray[dex1] = theArray[dex2];
		theArray[dex2] = temp;
	}

	private long median(int left, int right) {
		int pivotIndex = nElems / 2;
		partitionIt(left, right, pivotIndex);
		return theArray[pivotIndex];
	}

	private long findMax(int index) {
		long max = 0;
		for (int i = 0; i < index; i++) {
			if (theArray[i] > max)
				max = theArray[i];
		}
		return max;
	}

	public long calMedian() {
		if (nElems % 2 == 0) {
			return (median(0, nElems - 1) + findMax(nElems / 2)) / 2;
		} else {
			return median(0, nElems - 1);
		}
	}
}

class MedianApp {

	public static void main(String[] args) throws IOException {
		int maxSize;
		System.out.println("Enter the size of the array :");
		maxSize = getInt();
		Median arr;
		arr = new Median(maxSize);
		for (int j = 0; j < maxSize; j++) {
			long n = (int) (java.lang.Math.random() * 199);
			arr.insert(n);
		}
		arr.display();
		System.out.println(arr.calMedian());
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
