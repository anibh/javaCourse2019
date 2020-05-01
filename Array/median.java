
class HighArrayMedian {

	private long[] arr;
	private int nElems;

	public HighArrayMedian(int max) {
		arr = new long[max];
		nElems = 0;
	}

	public boolean find(long key) {

		int j;
		for (j = 0; j < nElems; j++)
			if (arr[j] == key)
				break;

		if (j == nElems)
			return false;

		else
			return true;
	}

	public void insert(long value) {
		arr[nElems] = value;
		nElems++;
	}

	public boolean delete(long key) {

		int j;
		for (j = 0; j < nElems; j++)
			if (key == arr[j])
				break;

		if (j == nElems)
			return false;

		else {
			for (int k = j; k < nElems; k++)
				arr[k] = arr[k + 1];
			nElems--;
			return true;
		}
	}

	public void display() {
		for (int i = 0; i < nElems; i++)
			System.out.print(arr[i] + " ");
		System.out.println(" ");
	}

	public long getMedian() {
		int minIndex = 0;
		long min = arr[0], median = 0;
		for (int i = 0; i <= nElems / 2; i++) {
			minIndex = i;
			min = arr[i];
			for (int j = i; j < nElems; j++) {
				if (arr[j] < min) {
					min = arr[j];
					minIndex = j;
				}
			}
			if (minIndex != i) {
				long temp = arr[minIndex];
				arr[minIndex] = arr[i];
				arr[i] = temp;
			}
		}
		if (nElems % 2 == 0) {
			median = (arr[(nElems - 1) / 2] + arr[nElems / 2]) / 2;
		} else {
			median = arr[nElems / 2];
		}
		return median;
	}
}

class MedianApp {
	public static void main(String[] args) {
		int maxSize = 100;
		HighArrayMedian arr = new HighArrayMedian(maxSize);
		arr.insert(35);
		arr.insert(47);
		arr.insert(89);
		arr.insert(55);
		arr.insert(32);
		arr.insert(17);
		arr.insert(93);
		arr.insert(64);
		arr.insert(27);
		arr.insert(42);
		arr.insert(58);
		arr.insert(77);
		arr.insert(95);
		arr.insert(3);
		arr.insert(29);
		arr.display();
		System.out.println(arr.getMedian());
	}
}