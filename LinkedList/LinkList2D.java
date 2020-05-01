
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

	public LinkList2D(int maxRows, int maxCols) {
		for (int i = 0; i < maxRows; i++) {
			Link2D newLink = new Link2D(10 - i, 0, 0);
			newLink.down = first.down;
			first.down = newLink;
		}
		for (int i = 0; i < maxCols; i++) {
			Link2D newLink = new Link2D(0, 10 - i, 0);
			newLink.right = first.right;
			first.right = newLink;
		}
	}

	public void insert(int row, int col, int key) {
		Link2D top = first, left = first;
		if (row == 0 || col == 0) {
			System.out.println("Can't insert at this index");
			return;
		}
		while (top.col < col)
			top = top.right;
		while (left.row < row)
			left = left.down;
		Link2D topCurr = top, leftCurr = left;
		Link2D topPrev = null, leftPrev = null;
		Link2D newLink = new Link2D(row, col, key);

		while (topCurr.down != null && row > topCurr.row) {
			topPrev = topCurr;
			topCurr = topCurr.down;
		}

		while (leftCurr.right != null && col > leftCurr.col) {
			leftPrev = leftCurr;
			leftCurr = leftCurr.right;
		}

		if (topCurr == leftCurr) {
			newLink.down = topCurr.down;
			newLink.right = leftCurr.right;
			topPrev.down = newLink;
			leftPrev.right = newLink;
			return;
		}

		if (leftPrev == null)
			left.right = newLink;
		else {
			if (leftCurr.col < col)
				leftCurr.right = newLink;
			else {
				leftPrev.right = newLink;
				newLink.right = leftCurr;
			}
		}

		if (topPrev == null)
			top.down = newLink;
		else {
			if (topCurr.row < row)
				topCurr.down = newLink;
			else {
				topPrev.down = newLink;
				newLink.down = topCurr;
			}
		}
	}

	public void delete(int key) {
		Link2D topPrev = first, rowPrev = first;
		Link2D keyLink = this.find(key);
		if (keyLink != null) {
			for (int i = 0; i < keyLink.row; i++) {
				rowPrev = rowPrev.down;
			}
			for (int i = 0; i < keyLink.col; i++) {
				topPrev = topPrev.right;
			}
			while (keyLink.row > topPrev.down.row) {
				topPrev = topPrev.down;
			}
			while (keyLink.col > rowPrev.right.col) {
				rowPrev = rowPrev.right;
			}
			rowPrev.right = keyLink.right;
			topPrev.down = keyLink.down;
		} else {
			System.out.println("Cannot find " + key + " in the 2D array");
		}
	}

	public Link2D find(int key) {
		Link2D top = first.right, row = top.down;
		Link2D link = null;
		if(this.isEmpty()) return null;
		while (top != null) {
			while (row != null) {
				if (row.dData == key)
					return row;
				row = row.down;
			}
			if (row == null) {
				top = top.right;
				row = top.down;
			}
		}
		return null;
	}

	public void display() {    // displays all content row wise
		Link2D top = first.right, row = top.down;
		while (top != null) {
			while (row != null) {
				row.displayLink();
				row = row.down;
			}
			if (row == null) {
				top = top.right;
				if (top != null)
					row = top.down;
			}
		}
	}
	
	public boolean isEmpty() {
		Link2D top = first.right;
		while(top != null) {
			if(top.down != null) {
				top = top.right;
				return false;
			}else top = top.right;
		}
		return true;
	}
}

class LinkList2DApp {
	public static void main(String[] args) {
		LinkList2D theList = new LinkList2D(10, 10); // make list

		theList.delete(3);
		theList.insert(1, 1, 1);// insert 4 items
		theList.insert(1, 3, 2);
		theList.insert(1, 2, 3);
		theList.insert(3, 1, 4);
		theList.insert(2, 1, 5);
		theList.insert(2, 2, 6);
		theList.insert(2, 3, 7);
		theList.insert(3, 2, 8);
		theList.insert(3, 3, 9);
		theList.insert(7, 0, 2); // will not allow to insert at row number 0 or column number 0
		theList.insert(0, 8, 6); // will not allow to insert at row number 0 or column number 0
		theList.insert(0, 0, 0); // will not allow to insert at row number 0 or column number 0
		theList.display();
		System.out.println("");
		theList.delete(3);
		theList.display();
	} // end main()
}
