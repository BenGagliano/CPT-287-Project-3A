import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class equationCalc {
	static final String INPUT_FILE_TXT = "src/inputFile.txt";
	
	public static void main(String[] args)throws FileNotFoundException {
		String infixEquation;// holds the equation read from one line of the .txt file
		Scanner fScan = new Scanner(new File(INPUT_FILE_TXT));
		
		while (fScan.hasNext()) { //loops through each line of the .txt file
			infixEquation = (fScan.nextLine().replaceAll(" ", ""));
			binTree equationTree = new binTree(infixEquation);
			binTree.printBT(equationTree, 1);//prints the equation for clarity
			try {
				int x = eval.traverseTree(binTree.getTree(infixEquation));
				if (x != Integer.MAX_VALUE) {
					System.out.println(x);
				}
				System.out.println();
			}
			catch (ArithmeticException e){//only runs if traverseTree throws ArithmeticException
				System.out.println(e.getMessage());
			}
	  
		}//end scan loop
		fScan.close();

	} // end main

}
