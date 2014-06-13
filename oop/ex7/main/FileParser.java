package oop.ex7.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import oop.ex7.scope.ScopeMediator;
import oop.ex7.type.Type;
import oop.ex7.type.badEndOfLineException;

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
	 * @throws badEndOfLineException 
	 */
	public static ArrayList<String>  getlinesList(File origin) throws FileNotFoundException, BadLineSyntaxException, badEndOfLineException{
		
		ArrayList<String> fileLines = new ArrayList<String>();
		Scanner myScan= new Scanner(origin);
		String currentLine;
		int lineNumber=0;
		currentLine=myScan.nextLine();

		while (currentLine!=null){
			scopeOrVariable(currentLine,lineNumber);

			if(!isLineCommentOrBlank(currentLine)) {//if it is commented simply continue and dont add to list
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
	private static boolean isLineCommentOrBlank(String currentLine) {
		// TODO Auto-generated method stub
		return false;
	}


//	/*
//	 * if line is illegal throws exception
//	 */
//	private static void checkLineLegal(int lineNum ,String lineText) throws BadLineSyntaxException  {
//
//		if(){//TODO is bad input
//			throw new BadLineSyntaxException(lineNum,lineText);
//		}
//
//
//	}
	
	
	//return 1-scope
	//return 2-variable
	public static int scopeOrVariable(String lineText,int lineNumber) throws badEndOfLineException{
		String tempString=lineText.trim();
		if(tempString.endsWith("{")){
			return 1;
		}
		else if (tempString.endsWith(";")){
			return 2;
		}
		throw new badEndOfLineException(lineNumber);
	}
	
	public static void checkExpression(Type type, String expression, ScopeMediator med) {
		//TODO implement
	}



	public static int  findLastCloser(ArrayList<String> relevantLines, int i) throws EndOfFileException{

		int bracketCounter=1;//initial size is 1 because 
		for (int index=i+1; index<relevantLines.size(); index++){
			String tempString=relevantLines.get(index);
			tempString=tempString.trim();

			if (tempString.endsWith("}")){
				bracketCounter--;
			}
			else if(tempString.endsWith("{")){
				bracketCounter++;
			}

			if (bracketCounter==0){
				return index;
			}

		}
		throw new EndOfFileException();
	}


}
