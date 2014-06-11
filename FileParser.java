import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author Lior
 *
 */
public class FileParser {

	/**
	 * parses original file 
	 * @param origin
	 * @return
	 * @throws FileNotFoundException 
	 * @throws BadLineSyntaxException 
	 */
	public static ArrayList<String>  getlinesList(File origin) throws FileNotFoundException, BadLineSyntaxException{
		
		ArrayList<String> fileLines = new ArrayList<String>();
		Scanner myScan= new Scanner(origin);
		String currentLine;
		int lineNumber=0;
		currentLine=myScan.nextLine();

		while (currentLine!=null){
			checkLineLegal(lineNumber, currentLine);

			if(!isLineComment(currentLine)) {//if it is commented simply continue and dont add to list
				fileLines.add(currentLine);
			}

			currentLine=myScan.nextLine();
			lineNumber++;
		}

		myScan.close();
		return fileLines;
	}


	/*
	 * checks if line starts with '//'
	 * @param currentLine
	 * @return
	 */
	private static boolean isLineComment(String currentLine) {
		// TODO Auto-generated method stub
		return false;
	}


	/*
	 * if line is illegal throws exception
	 */
	private static void checkLineLegal(int lineNum ,String lineText) throws BadLineSyntaxException  {

		if(){//TODO is bad input
			throw new BadLineSyntaxException(lineNum,lineText);
		}


	}



}
