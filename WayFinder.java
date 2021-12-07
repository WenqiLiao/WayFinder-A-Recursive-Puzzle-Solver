package project4;

import java.util.ArrayList;

/**
 * 
 * This class is to implement a program that finds all solutions to a number
 * puzzle.
 * 
 * @author Wenqi Liao
 * @version 04/10/2021
 *
 */
public class WayFinder {

	public static int size;
	public static int[] nums;
	public static String[] str;
	public static ArrayList<String[]> finalVer = new ArrayList<String[]>();
	public static ArrayList<String> visit = new ArrayList<String>();
	public static int counter;

	public static void main(String[] args) {

		size = args.length;
		str = args.clone();

		if (args.length <= 2) {
			System.err.println("It expects two command line arguments");
			System.exit(1);
		}

		try {
			for (int i = 0; i < args.length; i++) {
				if (Integer.valueOf(args[i]) < 0) {
					System.err.println("ERROR: the puzzle values have to be positive integers.\n"
							+ "(Some error print stating negative numbers not allowed)");
					System.exit(1);
				}
			}
		} catch (NumberFormatException e) {
			System.err.println("ERROR: the puzzle value have to be integer.");
			System.exit(1);

		}

		for (int i = 0; i < args.length; i++) {
			if (Integer.valueOf(args[i]) < 0) {
				System.err.println("ERROR: the puzzle values have to be positive integers.\n"
						+ "(Some error print stating negative numbers not allowed)");
				System.exit(1);
			}
		}

		for (int i = 0; i < args.length; i++) {
			if (Integer.valueOf(args[i]) > 99) {
				System.err.println("ERROR: Values out of range.\n"
						+ "(Some error print stating numbers greater than 99 are not allowed)");
				System.exit(1);
			}
		}

		if (!args[size - 1].equals("0")) {
			System.err.println("ERROR: the last value in the puzzle has to be zero.\n"
					+ "(Some error print stating the last number to be 0)");
			System.exit(1);
		}

		nums = new int[size];
		for (int i = 0; i < nums.length; i++) {
			nums[i] = Integer.parseInt(args[i]);
		}

		helper(0);

		if (counter == 0) {
			System.out.println("No way through this puzzle.");
		} else {
			System.out.println("\n" + "There are " + counter + " ways through this puzzle.");
		}

	}

	/**
	 * 
	 * A recursion helper method which can find out possible ways to escape the
	 * puzzle and print it
	 * 
	 * @param the current index.
	 * 
	 */
	public static void helper(int index) {

		if (index == size - 1) {
			System.out.print("\n");
			for (int i = 0; i < finalVer.size(); i++) {
				printArray(finalVer.get(i));
			}
			counter++;
			return;
		}

		if (checkRight(index, nums[index])) {
			visit.add(index + "R");
			String[] currPath = str.clone();
			currPath[index] = currPath[index] + "R";
			finalVer.add(currPath);
			helper(index + nums[index]);
			// CONFUSED
			finalVer.remove(finalVer.size() - 1);
			visit.remove(visit.size() - 1);
		}

		if (checkLeft(index, nums[index])) {
			visit.add(index + "L");
			String[] currPath = str.clone();
			currPath[index] = currPath[index] + "L";
			finalVer.add(currPath);
			helper(index - nums[index]);
			finalVer.remove(finalVer.size() - 1);
			visit.remove(visit.size() - 1);
		}

	}

	/**
	 * 
	 * check whether we can go right at current index.
	 * 
	 * @param index: the current index num: the number at current index
	 * @return Returns true if can go right Return False if cannot
	 */
	public static boolean checkRight(int index, int num) {

		if (index + num >= size) {
			return false;
		}

		if (visit.contains(index + "R") || visit.contains(index + "L")) {
			return false;
		}

		return true;
	}

	/**
	 * 
	 * check whether we can go left at current index.
	 * 
	 * @param index: the current index num: the number at current index
	 * @return Returns true if can go left Return False if cannot
	 */
	public static boolean checkLeft(int index, int num) {

		if (index - num < 0) {
			return false;
		}

		if (visit.contains(index + "L") || visit.contains(index + "R")) {
			return false;
		}

		return true;
	}

	/**
	 * 
	 * print the array in a standardized version.
	 * 
	 * @param the array needed to be printed
	 */
	public static void printArray(String[] ary) {

		System.out.print("[");
		for (int i = 0; i < ary.length - 1; i++) {
			if (nums[i] < 10 && (ary[i].contains("R") || ary[i].contains("L"))) {
				System.out.print(" " + ary[i] + ", ");
			} else if (nums[i] < 10 && !(ary[i].contains("R") || ary[i].contains("L"))) {
				System.out.print(" " + ary[i] + " , ");
			} else if (nums[i] >= 10 && (ary[i].contains("R") || ary[i].contains("L"))) {
				System.out.print(ary[i] + ", ");
			} else {
				System.out.print(ary[i] + " , ");
			}

		}
		System.out.print(" " + ary[ary.length - 1]);
		System.out.println(" ]");
	}
}
