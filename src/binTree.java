
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

		List<String> expArr = createList(expression);

		return createTree(expArr);
	} // End getTree()

	/**
	 * Takes an ArrayList<String> and turns it into a Binary Tree
	 * @param expArr: the Created ArrayList
	 * @param start: starting index of ArrayList
	 * @param end: ending index of ArrayList
	 * @return: head of binary Tree
	 */
	
	private static binTree createTree(List<String> expArr) {
		
		int index = 0;
		int prec = 9;
		
		if (expArr.size() == 1) {
			return new binTree(expArr.get(0));
		}
		
		List<Integer> pars = findParen(expArr);
		
		if (pars.size() == expArr.size()) {
			expArr = expArr.subList(1, expArr.size() - 1);
			pars.clear();
		}
		
		for (int i = expArr.size() - 1; i > -1; i--) {
			if (!pars.contains(i)) {
				int temp = 9;
				if (!isDigit(expArr.get(i))) {
					temp = precedence(expArr.get(i));
				}
				if (temp < prec) {
					prec = temp;
					index = i;
				}
			}
		}
		
		binTree head = new binTree(expArr.get(index));
		
		head.right = createTree(expArr.subList(index + 1, expArr.size()));	
		head.left = createTree(expArr.subList(0, index));
		
		return head;
		
	} // End createTree()
	
	/**
	 * Finds Parenthesis in expression
	 * @param exp: section of whole array
	 * @return: List of indices between Parenthesis
	 */
	
	private static List<Integer> findParen(List<String> exp) {
		
		List<Integer> pars = new ArrayList<Integer>();
		
		boolean inPars = false;
		
		for (int i = 0; i < exp.size(); i++) {
			if (exp.get(i).equals("(")) {
				inPars = true;
				pars.add(i);
			} else if (exp.get(i).equals(")")) {
				pars.add(i);
				inPars = !inPars;
			} else if (inPars) {
				pars.add(i);
			} 
		}
		
		return pars;
	}
	
	/**
	 * Returns in what order should passed operand be executed
	 * @param str: to be tested
	 * @return: arbitrary precedence value
	 */
	
	private static int precedence(String str) {
		
		String prec6 = "*/%";
		String prec5 = "+-";
		String prec4 = ">=<=";
		String prec3 = "!==";
		
		if (str.equals("^")) {
			return 7;
		}
		if (prec6.contains(str)) {
			return 6;
		}
		if (prec5.contains(str)) {
			return 5;
		} 
		if (prec4.contains(str)) {
			return 4;
		} 
		if (prec3.contains(str)) {
			return 3;
		} 
		if (str.equals("&&")) {
			return 2;
		}
		
		return 1; // Only if str == "||"
		
	}
	
	/** 
	 * Takes a String and turns it into a ArrayList<String>
	 * @param expression: passed from main()
	 * @return: ArrayList to create Binary Tree with
	 */
	
	private static List<String> createList(String expression) {

		List<String> expArr = new ArrayList<String>();

		String[] temp = expression.split("");

		String digit = "";
		String nonDigit = "";
		String addPar = "";
		
		// Cycles through temp[] and adds to expArr
		// Will add "11" as "11" and not as "1" "1" as per the .split("") method

		for (String elem : temp) {
			if (isDigit(elem)) {
				digit += elem;
				if (nonDigit != "") {
					expArr.add(nonDigit);
					nonDigit = "";
				}
				if (addPar != "") {
					expArr.add(addPar);
					addPar = "";
				}
			} else if (!isDigit(elem)) {
				if (elem.equals("(") || elem.equals(")")) {
					addPar = elem;
				} else {
					nonDigit += elem;
					if (digit != "") {
						expArr.add(digit);
						digit = "";
					}
					if (addPar != "") {
						expArr.add(addPar);
						addPar = "";
					}
				}
			} 
		}
		
		// Adds the last digit to expArr if not already added
		if (digit != "") {
			expArr.add(digit);
			digit = "";
		}
		
		// Adds the last nonDigit to expArr if not already added
		// Will result in INVALID equation
		if (nonDigit != "") {
			expArr.add(nonDigit);
			nonDigit = "";
		}
		
		// Adds the last parenthesis to expArr if not already added
		if (addPar != "") {
			expArr.add(addPar);
			addPar = "";
		}
		
		//System.out.println("Size: " + expArr.size());
		
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
