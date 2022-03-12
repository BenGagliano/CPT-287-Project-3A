
import java.util.*;

public class binTree {
	
	static final int COUNT = 10; // TESTING PURPOSES ONLY
	
	String val;
	binTree left, right;

	binTree(String data) {
		val = data;
	}
	
	/**
	 * @param expression: passed from main()
	 * @return: head of binary Tree
	 */
	
	public static binTree getTree(String expression) {

		ArrayList<String> expArr = createList(expression);

		return createTree(expArr, 0, expArr.size() - 1);
	} // End getTree()

	/**
	 * Takes an ArrayList<String> and turns it into a Binary Tree
	 * @param expArr: the Created ArrayList
	 * @param start: starting index of ArrayList
	 * @param end: ending index of ArrayList
	 * @return: head of binary Tree
	 */
	
	private static binTree createTree(ArrayList<String> expArr, int start, int end) {

		if (start > end) {
			return null;
		}

		int mid = (start + end) / 2;

		binTree head = new binTree(expArr.get(mid));

		head.left = createTree(expArr, start, mid - 1);
		head.right = createTree(expArr, mid + 1, end);

		return head;
	} // End createTree()
	
	/** 
	 * Takes a String and turns it into a ArrayList<String>
	 * @param expression: passed from main()
	 * @return: ArrayList to create Binary Tree with
	 */
	
	private static ArrayList<String> createList(String expression) {

		ArrayList<String> expArr = new ArrayList<String>();

		String[] temp = expression.split("");

		String toPush = "";

		// Cycles through temp[] and adds to expArr
		// Will add "11" as "11" and not as "1" "1" as per the .split("") method

		for (String elem : temp) {
			if (isDigit(elem)) {
				toPush += elem;
			} else if (!isDigit(elem)) {
				expArr.add(toPush);
				toPush = "";
				expArr.add(elem);
			} else if (elem == " ") {
				continue;
			}
		}
		// Adds the last element to expArr if not already added
		if (toPush != "") {
			expArr.add(toPush);
			toPush = "";
		}

		return expArr;
	} // End createList()

	/**
	 * Tests whether a passed String is a digit
	 * @param sample: passed from createList()
	 * @return: TRUE if digit, FALSE if not a digit
	 */
	
	private static boolean isDigit(String sample) {

		if (sample == null) {
			return false;
		}
		try {
			int x = Integer.parseInt(sample);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	} // End isDigit()
	
	/** TESTING PURPOSES ONLY
	 * Takes Binary Tree and prints it to screen sideways (rotate screen 90 degree clockwise to see full tree)
	 * @param head: Head of Binary Tree
	 * @param space: Arbitrary spacing value
	 */
	
	public static void printBT(binTree head, int space) {

		if (head == null) {
			return;
		}

		space += COUNT;

		printBT(head.right, space);

		System.out.print("\n");

		for (int i = COUNT; i < space; i++) {
			System.out.print(" ");
		}

		System.out.print(head.val + "\n");

		printBT(head.left, space);
	} // End printBT()
}
