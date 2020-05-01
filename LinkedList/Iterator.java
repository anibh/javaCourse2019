
class Link2D {

	public int row;
	public int col;
	public int dData;
	public Link2D right;
	public Link2D down;

	public Link2D(int r, int c, int dd) {
		row = r;
		col = c;
		dData = dd;
	}

	public void displayLink() {
		System.out.println("{" + row + "," + col + "," + dData + "}");
	}
}

class LinkList2D {

	private Link2D first = new Link2D(0, 0, 0);
	int rows, cols;

	public LinkList2D(int maxRows, int maxCols) {
		rows = maxRows;
		cols = maxCols;
		for (int i = 0; i < maxRows; i++) {
			Link2D newLink = new Link2D(maxRows - i, 0, 0);
			newLink.down = first.down;
			first.down = newLink;
		}
		for (int i = 0; i < maxCols; i++) {
			Link2D newLink = new Link2D(0, maxCols - i, 0);
			newLink.right = first.right;
			first.right = newLink;
		}
	}

	public int getRows() {
		return rows;
	}

	public int getCols() {
		return cols;
	}

	public Link2D getFirst() {
		return first;
	}

	public ListIterator getIterator() {
		return new ListIterator(this);
	}
}

class ListIterator {
	private Link2D top, left;
	int row, col;
	private LinkList2D theList;

	public ListIterator(LinkList2D list) {
		theList = list;
		reset();
	}

	public void reset() {
		row = 1;
		col = 1;
		top = theList.getFirst().right;
		left = theList.getFirst().down;
	}

	public void moveRight() {
		col += 1;
		if (col > theList.getCols()) {
			col -= 1;
			return;
		}
		top = theList.getFirst();
		left = theList.getFirst();
		for (int i = 0; i < row; i++) {
			left = left.down;
		}
		for (int i = 0; i < col; i++) {
			top = top.right;
		}
		if (top != null) {
			col = top.col;
			while (top.down != null && row > top.down.row) {
				top = top.down;
				if (top.down == null)
					break;
			}
			while (left.right != null && col > left.right.col) {
				left = left.right;
				if (left.right == null)
					break;
			}
		}
	}

	public void moveDown() {
		row += 1;
		if (row > theList.getRows()) {
			row -= 1;
			return;
		}
		top = theList.getFirst();
		left = theList.getFirst();
		for (int i = 0; i < row; i++) {
			left = left.down;
		}
		for (int i = 0; i < col; i++) {
			top = top.right;
		}
		if (left != null) {
			row = left.row;
			while (top.down != null && row > top.down.row) {
				top = top.down;
				if (top.down == null)
					break;
			}
			while (left.right != null && col > left.right.col) {
				left = left.right;
				if (left.right == null)
					break;
			}
		}

	}

	public void moveLeft() {
		if (col > 1)
			col -= 1;
		top = theList.getFirst();
		left = theList.getFirst();
		for (int i = 0; i < row; i++) {
			left = left.down;
		}
		for (int i = 0; i < col; i++) {
			top = top.right;
		}
		while (top.down != null && row > top.down.row) {
			top = top.down;
		}
		while (left.right != null && col > left.right.col) {
			left = left.right;
		}
	}

	public void moveUp() {
		if (row > 1)
			row -= 1;
		top = theList.getFirst();
		left = theList.getFirst();
		for (int i = 0; i < row; i++) {
			left = left.down;
		}
		for (int i = 0; i < col; i++) {
			top = top.right;
		}
		while (top.down != null && row > top.down.row) {
			top = top.down;
		}
		while (left.right != null && col > left.right.col) {
			left = left.right;
		}
	}

	public Link2D getCurrent() {
		if (isHole()) {
			System.out.println("The node is empty");
			return null;
		} else {
			if (top.down != null) {
				top.down.displayLink();
				return top.down;
			} else {
				return null;
			}
		}
	}

	public void insert(int key) {
		Link2D newLink = new Link2D(row, col, key);
		if (isHole()) {
			newLink.right = left.right;
			newLink.down = top.down;
			left.right = newLink;
			top.down = newLink;
		} else {
			newLink.down = top.down.down;
			newLink.right = left.right.right;
			top.down = newLink;
			left.right = newLink;
		}
	}

	public void delete() {
		if (isHole())
			System.out.println("Cannot delete non existent node");
		else {
			top.down = top.down.down;
			left.right = left.right.right;
		}
	}

	public boolean isHole() {
		if (top.down != null && left.right != null) {
			if (top.down.row != row || left.right.col != col)
				return true;
			else
				return false;
		} else {
			return true;
		}
	}

	public void display() {
		Link2D rowPoint = left, colPoint = top;
		int rowNo = row, colNo = col;
		reset();
		for (int i = 0; i < theList.getRows(); i++) {
			for (int j = 0; j < theList.getCols(); j++) {
				if (isHole()) {
					System.out.print("   X   ");
					moveRight();
				} else {
					System.out.print("{" + left.right.row + "," + left.right.col + "," + left.right.dData + "}");
					moveRight();
				}
			}
			col = col - 1;
			moveDown();
			for (int k = 0; k < theList.getCols(); k++) {
				moveLeft();
			}
			System.out.println("");
		}
		left = rowPoint;
		top = colPoint;
		row = rowNo;
		col = colNo;
	}
}

class IteratorApp {
	public static void main(String[] args) {
		LinkList2D theList = new LinkList2D(10, 10); // make list


		ListIterator iterator = theList.getIterator();
		iterator.getCurrent();
		iterator.moveLeft();
		iterator.moveUp();
		iterator.moveRight();
		iterator.moveRight();
		iterator.moveRight();
		iterator.moveRight();

		iterator.getCurrent();
		iterator.moveRight();
		iterator.moveDown();
		iterator.getCurrent();
		iterator.moveLeft();
		iterator.getCurrent();
		iterator.moveUp();
		iterator.getCurrent();
		iterator.delete();
		iterator.insert(3);
		iterator.getCurrent();
//		iterator.delete();
//		iterator.getCurrent();
		iterator.moveDown();
		iterator.moveDown();
		iterator.getCurrent();
//		iterator.delete();
		iterator.moveDown();
		iterator.getCurrent();
		iterator.moveRight();
		iterator.getCurrent();
		iterator.delete();
		iterator.getCurrent();
		iterator.moveRight();
		iterator.getCurrent();
		iterator.moveDown();
		iterator.getCurrent();
		iterator.moveRight();
		iterator.moveRight();
		iterator.moveRight();
		iterator.moveRight();
		iterator.moveRight();
		iterator.moveDown();
		iterator.moveDown();
		iterator.moveDown();
		iterator.moveDown();
		iterator.moveDown();
		iterator.moveDown();
		iterator.display();

	} // end main()
}
