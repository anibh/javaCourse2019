
class Deque {

	private int maxSize;
	private long[] dequeArray;
	private int read;
	private int write;
	private int nItems;
	
	public Deque(int s) {
		maxSize = s;
		dequeArray = new long[maxSize];
		read = 0;
		write = 0;
		nItems = 0;
	}
	
	public boolean isFull() {
		if((write+1)% maxSize == read) return true;
		else return false;
	}
	
	public boolean isEmpty() {
		if(write == read) return true;
		else return false;
	}
	
	public void insertRight(long data) {
		if(!this.isFull()) {
			dequeArray[write] = data;
			write = (write+1)%maxSize;
			nItems++;
		}
		else {
			System.out.println("Queue is full. Can't insert "+data);
		}
	}
	
	public long removeLeft() {
		long delItem = 0;
		if(!this.isEmpty()) {
			dequeArray[read] = delItem;
			read = (read+1)% maxSize;
			nItems--;
		}
		else {
			System.out.println("Queue is empty. No items to delete");
		}
		return delItem;
	}
	
	public void insertLeft(long data) {
		if(!this.isFull() && read > 0){
			read = (read-1) % maxSize;
			dequeArray[read] = data;
			nItems++;
		}
		else if(!this.isFull() && read == 0) {
			read = maxSize-1;
			dequeArray[read] = data;
			nItems++;
		}
		else {
			System.out.println("Queue is full. Can't insert "+data);
		}
	}
	
	public long removeRight() {
		long delItem = 0;
		if(!this.isEmpty()) {
			dequeArray[write] = delItem;
			write = (write-1) % maxSize;
			nItems--;
		}
		else {
			System.out.println("Queue is empty. No items to delete");
		}
		return delItem;
	}
	
	public void display() {
		for (int i = 0; i < nItems; i++)
			System.out.print(dequeArray[(read+i) % maxSize] + " ");
		System.out.println(" ");
	}
}

class DequeApp{
	
	public static void main(String[] args) {
		Deque theDeque = new Deque(10);
		
		theDeque.removeLeft();
		theDeque.removeRight();
		
		theDeque.insertRight(10);
		theDeque.insertLeft(20);
		theDeque.insertLeft(30);
		theDeque.insertRight(40);
		theDeque.insertRight(50);
		theDeque.insertLeft(60);
		theDeque.insertRight(70);
		theDeque.insertRight(80);
		theDeque.insertLeft(90);
		theDeque.insertLeft(81);
		theDeque.insertRight(58);
		theDeque.display();
		theDeque.removeLeft();
		theDeque.display();
		theDeque.removeRight();
		
		theDeque.display();
	}
}
