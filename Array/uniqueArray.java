
class UniqueArray {

	private long[] arr;
	private int nElems;

	public UniqueArray(int max) {
		arr = new long[max];
		nElems = 0;
	}

	public boolean find(long key, int end) {

		int j;
		for (j = 0; j < end; j++)
			if (arr[j] == key)
				break;

		if (j == end)
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

	public void noDups() {
		int sizeOfArray = nElems;
		for(int i=1; i<sizeOfArray; i++) {
			if(this.find(arr[i], i)) {
				arr[i] = arr[sizeOfArray - 1];
				arr[sizeOfArray-1] = 0;
				sizeOfArray--;
				i--;
			}
		}
		nElems = sizeOfArray;
	}
}

class UniqueArrayApp {
	public static void main(String[] args) {
		int maxSize = 100;
		UniqueArray arr = new UniqueArray(maxSize);
		arr.insert(35);
		arr.insert(47);
		arr.insert(89);
		arr.insert(47);
		arr.insert(32);
		arr.insert(17);
		arr.insert(35);
		arr.insert(64);
		arr.insert(27);
		arr.insert(47);
		arr.insert(35);
		arr.insert(77);
		arr.insert(89);
		arr.insert(32);
		arr.insert(29);
		arr.display();
		arr.noDups();
		arr.display();

	}
}