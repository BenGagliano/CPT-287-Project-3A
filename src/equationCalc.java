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
			Stack stringStack =	equationParser();
			
			if (!stringStack.isEmpty()) {
	            int x = eval(stringStack);
	            if (x != Integer.MAX_VALUE) {
	            	System.out.println(x);
	            }
	            System.out.println();
	        }
		} 
		fScan.close();

	} // end main

}
