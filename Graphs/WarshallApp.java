// mst.java
// demonstrates minimum spanning tree
// to run this program: C>java MSTApp
////////////////////////////////////////////////////////////////
class StackX {
	private final int SIZE = 20;
	private int[] st;
	private int top;

// -------------------------------------------------------------
	public StackX() // constructor
	{
		st = new int[SIZE]; // make array
		top = -1;
	}

// -------------------------------------------------------------
	public void push(int j) // put item on stack
	{
		st[++top] = j;
	}

// -------------------------------------------------------------
	public int pop() // take item off stack
	{
		return st[top--];
	}

// -------------------------------------------------------------
	public int peek() // peek at top of stack
	{
		return st[top];
	}

// -------------------------------------------------------------
	public boolean isEmpty() // true if nothing on stack
	{
		return (top == -1);
	}
// -------------------------------------------------------------
} // end class StackX
////////////////////////////////////////////////////////////////

class Vertex {
	public char label; // label (e.g. 'A')
	public boolean wasVisited;

// -------------------------------------------------------------
	public Vertex(char lab) // constructor
	{
		label = lab;
		wasVisited = false;
	}
// -------------------------------------------------------------
} // end class Vertex
////////////////////////////////////////////////////////////////

class Graph {
	private final int MAX_VERTS = 20;
	private Vertex vertexList[]; // list of vertices
	private int adjMat[][]; // adjacency matrix
	private int nVerts; // current number of vertices
	private StackX theStack;

// -------------------------------------------------------------
	public Graph() // constructor
	{
		vertexList = new Vertex[MAX_VERTS];
		// adjacency matrix
		adjMat = new int[MAX_VERTS][MAX_VERTS];
		nVerts = 0;
		for (int j = 0; j < MAX_VERTS; j++) // set adjacency
			for (int k = 0; k < MAX_VERTS; k++) // matrix to 0
				adjMat[j][k] = 0;
		theStack = new StackX();
	} // end constructor
// -------------------------------------------------------------

	public void addVertex(char lab) {
		vertexList[nVerts++] = new Vertex(lab);
	}

// -------------------------------------------------------------
	public void addEdge(int start, int end) {
		adjMat[start][end] = 1;
	}

// -------------------------------------------------------------
	public void displayVertex(int v) {
		System.out.print(vertexList[v].label);
	}

	public void makeWarshall() {
		for(int i = 0; i < nVerts; i++) {
			for(int j = 0; j< nVerts; j++) {
				if(adjMat[i][j] == 1) {
//					warshallMatrix[i][j] = 1;
					for(int k = 0; k< nVerts; k++) {
						if(adjMat[k][i] == 1)
							adjMat[k][j] = 1;
					}
				}
			}
		}
	}
	
	public void displayAdj() {
		for(int i = 0; i<nVerts; i++) {
			for(int j = 0; j<nVerts; j++) {
				System.out.print(" " + adjMat[i][j] + " ");
			}
			System.out.println("");
		}
	}

} // end class Graph
////////////////////////////////////////////////////////////////

class WarshallApp {
	public static void main(String[] args) {
		Graph theGraph = new Graph();
		theGraph.addVertex('A'); // 0 (start for mst)
		theGraph.addVertex('B'); // 1
		theGraph.addVertex('C'); // 2
		theGraph.addVertex('D'); // 3
		theGraph.addVertex('E'); // 4

		theGraph.addEdge(0, 1); // AB
		theGraph.addEdge(0, 2); // AC
		theGraph.addEdge(0, 3); // AD
		theGraph.addEdge(0, 4); // AE
		theGraph.addEdge(1, 2); // BC
		theGraph.addEdge(1, 3); // BD
		theGraph.addEdge(1, 4); // BE
		theGraph.addEdge(2, 3); // CD
		theGraph.addEdge(2, 4); // CE
		theGraph.addEdge(3, 4); // DE
		theGraph.displayAdj();
		System.out.println("");
		theGraph.makeWarshall();
		theGraph.displayAdj();
//      System.out.print("Minimum spanning tree: ");
//      theGraph.mst();             // minimum spanning tree
//      System.out.println();
	} // end main()
} // end class MSTApp
////////////////////////////////////////////////////////////////
