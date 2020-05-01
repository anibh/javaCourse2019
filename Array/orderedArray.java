
class OrderedArray {
	private long[] arr;
	private int nElems;

	public OrderedArray(int max) {
		arr = new long[max];
		nElems = 0;
	}

	public int size() {
		return nElems;
	}

	public int find(long key) {
		int lower = 0;
		int upper = nElems - 1;
		int index;
		while (true) {
			index = (lower + upper) / 2;
			if (arr[index] == key)
				return index;
			else if (lower > upper)
				return nElems;
			else {
				if (arr[index] < key)
					lower = index + 1;
				else
					upper = index - 1;
			}
		}
	}

	public void insert(long value) {
		int lower = 0;
		int upper = nElems - 1;
		int index;
		while (lower <= upper) {
			index = (lower + upper) / 2;
			if (arr[index] < value)
				lower = index + 1;
			else
				upper = index - 1;
		}
		index = lower;
		for (int i = nElems; i > index; i--)
			arr[i] = arr[i - 1];
		arr[index] = value;
		nElems++;
	}

	public boolean delete(long key) {
		int lower = 0;
		int upper = nElems - 1;
		int index;
		while (true) {
			index = (lower + upper) / 2;
			if (arr[index] == key) {
				for (int j = index; j < nElems; j++)
					arr[j] = arr[j + 1];
				nElems--;
				return true;
			}	
			else if (lower > upper)
				return false;
			else {
				if (arr[index] < key)
					lower = index + 1;
				else
					upper = index - 1;
			}
		}
	}

	public void display() {
		for (int i = 0; i < nElems; i++)
			System.out.print(arr[i] + " ");
		System.out.println(" ");
	}

}

class OrderedArrayApp {
	public static void main(String[] args) {
		int maxSize = 100;
		OrderedArray arr = new OrderedArray(maxSize);
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
		long key = 93;
		if (arr.find(key) != arr.size())
			System.out.println("Found :" + key);
		else
			System.out.println("Can't find :" + key);
		arr.display();
		arr.delete(17);
		arr.delete(64);
		arr.delete(77);
		arr.display();
	}
}
