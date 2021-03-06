
public class eval {
	
	/**
	 * Traverses the tree to evaluate the expression.
	 * @param root: root of the tree generated by the expression
	 * @return: integer result of evaluating the expression
	 * @throws ArithmeticException: deals with divide by 0 errors
	 */
	public static final int traverseTree(binTree root) throws ArithmeticException {
		
		//empty tree
		if (root == null) {
			return 0;
		}
		
		//leaf nodes
		if (root.left== null && root.right == null) {
			return Integer.parseInt(root.val);
		}
		
		//branch nodes
		int left = traverseTree(root.left);
		int right = traverseTree(root.right);
		
	
		if ((root.val == "/" || root.val == "%") && right == 0) {// if the operator is '/' or '%' and the right number is 0
			throw new ArithmeticException();//prevents crash
		} else {
			return mathCalc(root.val, left, right);//pass the operator, left tree value and right tree value to the calculations method
		}
	}
	
	/**
	 * Does the arithmetic and logical operator work for the traverseTree method
	 * @param operator: string operator from tree
	 * @param left: integer from left node or calculated from left tree
	 * @param right: integer from right node or calculated from right tree
	 * @return: result of evaluating the left and right numbers with the operator
	 */
	private static final int mathCalc(String operator, int left, int right) {
		boolean bool;
		switch(operator) {
			case "+":
				return left+right;
			case "-":
				return left-right;
			case "*":
				return left*right;
			case "/":
				return left/right;
			case "%":
				return left%right;
			case "&&":
				bool = (left!=0) && (right!=0);
				if (bool == true){
					return 1;
				}else {
					return 0;
				}
			case "||":
				bool = (left!=0) || (right!=0);
				if (bool == true){
					return 1;
				}else {
					return  0;
				}
			case "^":
				return (int) Math.pow(left, right);
			case "<":
				bool = left<right;
				if (bool == true){
					return  1;
				}else {
					return 0;
				}
			case "<=":
				bool = left<=right;
				if (bool == true){
					return 1;
				}else {
					return 0;
				}
			case ">=":
				bool = left>=right;
				if (bool == true){
					return  1;
				}else {
					return 0;
				}
			case ">":
				bool = left>right;
				if (bool == true){
					return 1;
				}else {
					return 0;
				}
			case "!=":
				bool = left!=right;
				if (bool == true){
					return 1;
				}else {
					return 0;
				}
			case "==":
				bool = left==right;
				if (bool == true){
					return 1;
				}else {
					return 0;
				}
			default:
				System.out.println("Unsupported operator" + operator);
				return 0;
		}
	}
	
}
