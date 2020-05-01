import java.io.*;

class Tree {
	private char arr[][];

	public Tree(int size) {
		arr = new char[(int) ((Math.log(size) / Math.log(2)) + 1)][size];
	}

	public void display() {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				System.out.print(arr[i][j]);
			}
			System.out.println();
		}
	}

	private void makeBranches(int left, int right, int row) {
		if(row == arr.length) return;
		int mid = (right + left) / 2;
		for (int i = left; i < mid; i++) {
			arr[row][i] = '-';
		}
		arr[row][mid] = 'X';
		for (int i = mid + 1; i < right; i++) {
			arr[row][i] = '-';
		}
		makeBranches(left, mid, row + 1);
		makeBranches(mid, right, row + 1);
	}
	
	public void makeTree(int size) {
		makeBranches(0, size, 0);
	}
}

class TreeApp{
	
	public static void main(String [] args) throws IOException {
		int size;
		System.out.print("Enter a number: ");
	    size = getInt();
		Tree tree= new Tree(size);
		tree.makeTree(size);
		tree.display();
		
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
