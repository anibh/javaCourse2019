import java.io.*;

class KnapSack {
	private int set[];

	public KnapSack(int arr[], int maxSize) {
		set = new int[maxSize];
		set = arr;
	}

	public void solve(int target) {
		String solution = knapsack(target, 0);
		if (solution.equals(""))
			System.out.println("No solution");
		else
			System.out.println(solution);
	}

	private String knapsack(int target, int index) {
		if (index == set.length)
			return "";
		String solution = "";
		int elem = set[index];

		if (elem < target) {
			System.out.println(target + ", " + elem + " is too small");
			solution += knapsack(target - elem, index + 1);
		} else if (elem > target) {
			System.out.println(target + ", " + elem + " is too big");
		} else if (elem == target) {
			System.out.println(target + ", " + elem + " is just right");
			solution += elem;
			return solution;
		}
		if (solution.equals("")) {
			solution += knapsack(target, index + 1);
		} else {
			solution = elem + " " + solution;
		}

		return solution;
	}
}

class KnapSackApp {

	public static void main(String[] args) {
		int arr[] = { 11, 8, 7, 6, 5 };
		KnapSack problem = new KnapSack(arr, arr.length);
		problem.solve(20);
	}
}
