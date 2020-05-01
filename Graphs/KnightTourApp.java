import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// dfs.java
// demonstrates depth-first search
// to run this program: C>java DFSApp
////////////////////////////////////////////////////////////////
class StackX {
	private int SIZE;
	private int[] st;
	private int top;

// ------------------------------------------------------------
	public StackX(int maxSize) // constructor
	{
		st = new int[maxSize]; // make array
		top = -1;
		SIZE = maxSize;
	}

// ------------------------------------------------------------
	public void push(int j) // put item on stack
	{
		st[++top] = j;
	}

// ------------------------------------------------------------
	public int pop() // take item off stack
	{
		return st[top--];
	}

// ------------------------------------------------------------
	public int peek() // peek at top of stack
	{
		return st[top];
	}

// ------------------------------------------------------------
	public boolean isEmpty() // true if nothing on stack
	{
		return (top == -1);
	}

	public boolean isFull() {
		return top == SIZE - 1;
	}
	
	public void displayStack() {
		for(int i = 0; i<SIZE; i++) {
			System.out.print(st[i]+1 + " ");
		}
	}
// ------------------------------------------------------------
} // end class StackX
////////////////////////////////////////////////////////////////

class Vertex {
	public int label; // label (e.g. 'A')
	public boolean wasVisited;

// ------------------------------------------------------------
	public Vertex(int lab) // constructor
	{
		label = lab;
		wasVisited = false;
	}
// ------------------------------------------------------------
} // end class Vertex
////////////////////////////////////////////////////////////////

class Graph {
	private final int MAX_VERTS;
	private Vertex vertexList[]; // list of vertices
	private int adjMat[][]; // adjacency matrix
	private int nVerts; // current number of vertices
	private StackX theStack;

// ------------------------------------------------------------
	public Graph(int size) // constructor
	{
		MAX_VERTS = size * size;
		vertexList = new Vertex[MAX_VERTS];
		// adjacency matrix
		adjMat = new int[MAX_VERTS][MAX_VERTS];
		nVerts = 0;
		for (int y = 0; y < MAX_VERTS; y++) // set adjacency
			for (int x = 0; x < MAX_VERTS; x++) // matrix to 0
				adjMat[x][y] = 0;
		theStack = new StackX(MAX_VERTS);
		int n = 1;
		for (int i = 0; i < MAX_VERTS; i++) {
			addVertex(n);
			n++;
		}
		for (int i = 0; i < MAX_VERTS; i++) {
			int a = i / 5;
			int b = i % 5;
			if (a + 2 < 5) {
				if (b + 1 < 5)
					addEdge(i, 5 * (a + 2) + b + 1);
				if (b - 1 >= 0)
					addEdge(i, 5 * (a + 2) + b - 1);
			}
			if (a - 2 >= 0) {
				if (b + 1 < 5)
					addEdge(i, 5 * (a - 2) + b + 1);
				if (b - 1 >= 0)
					addEdge(i, 5 * (a - 2) + b - 1);
			}
			if (a + 1 < 5) {
				if (b + 2 < 5)
					addEdge(i, 5 * (a + 1) + b + 2);
				if (b - 2 >= 0)
					addEdge(i, 5 * (a + 1) + b - 2);
			}
			if (a - 1 >= 0) {
				if (b + 2 < 5)
					addEdge(i, 5 * (a - 1) + b + 2);
				if (b - 2 >= 0)
					addEdge(i, 5 * (a - 1) + b - 2);
			}

		}
	} // end constructor
// ------------------------------------------------------------

	public void addVertex(int lab) {
		vertexList[nVerts++] = new Vertex(lab);
	}

// ------------------------------------------------------------
	public void addEdge(int start, int end) {
		adjMat[start][end] = 1;
	}

// ------------------------------------------------------------
	public void displayVertex(int v) {
		System.out.print(vertexList[v].label);
	}

	public void findSol(int vertex) {
		if (move(vertex - 1)) {
			System.out.println("Done");
			theStack.displayStack();
		}
		else
			System.out.println("No Solution");
		for (int j = 0; j < nVerts; j++) // reset flags
			vertexList[j].wasVisited = false;
	}

// ------------------------------------------------------------
	private boolean move(int v) { // begin at vertex 0
		vertexList[v].wasVisited = true; // mark it
		theStack.push(v); // push it
		StackX adjVertices = new StackX(8);
		adjVertices = getAdjVertex(v);
		while (!adjVertices.isEmpty()) {
			int x = adjVertices.pop();
			if (move(x) == false) {
				vertexList[x].wasVisited = false;
				theStack.pop();
				if (adjVertices.isEmpty())
					return false;
			}
			if (theStack.isFull())
				break;
		}
		if (theStack.isFull())
			return true;
		else
			return false;

	} // end dfs
// ------------------------------------------------------------
	// returns an unvisited vertex adj to v

	private StackX getAdjVertex(int v) {
		StackX vertices = new StackX(8);
		for (int j = 0; j < nVerts; j++)
			if (adjMat[v][j] == 1 && vertexList[j].wasVisited == false)
				vertices.push(j);
		return vertices;
	} // end getAdjUnvisitedVertex()

	public void displayAdj() {
		for (int i = 0; i < nVerts; i++) {
			for (int j = 0; j < nVerts; j++) {
				System.out.print(" " + adjMat[i][j] + " ");
			}
			System.out.println("");
		}
	}
// ------------------------------------------------------------
} // end class Graph
////////////////////////////////////////////////////////////////

class KnightTourApp {
	public static void main(String[] args) throws IOException {
		Graph theGraph = new Graph(5);
		theGraph.displayAdj();
		System.out.println("The block numbers start from 1 to maximum value");
		System.out.println("Enter the block number to start from : ");
		int n = getInt();
		theGraph.findSol(n);


	} // end main()
	
	public static String getString() throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}

//-------------------------------------------------------------
	public static int getInt() throws IOException {
		String s = getString();
		return Integer.parseInt(s);
	}
} // end class DFSApp
////////////////////////////////////////////////////////////////
