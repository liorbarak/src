package oop.ex7.main;

import java.awt.geom.GeneralPath;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import oop.ex7.scope.MethodScope;
import oop.ex7.scope.Scope;
import oop.ex7.scope.ScopeMediator;
import oop.ex7.type.Type;


/**
 * 
 * @author Lior
 *
 */
public class FileParser {

	public static final String GENERAL_NAME = "( )*([_][^ ]+|[^\\d_][^ ]*)( )*";
	public static final String TYPE_INT = "( )*int( )*";
	public static final String INPUT_INT = "( )*(-)?[\\d]+( )*";
	
	public static final String TYPE_DOUBLE = "( )*double( )*";
	public static final String INPUT_DOUBLE = "("+INPUT_INT+"|( )*(-)?[\\d]+.([\\d]+)?( )*)";
	
	
	public static final String TYPE_STRING = "( )*String( )*";
	public static final String INPUT_STRING = "( )*\"[^\"]+\"( )*";
	
	public static final String TYPE_BOOLEAN = "( )*boolean( )*";
	public static final String INPUT_BOOLEAN = "( )*(true|false)( )*";
	
	public static final String TYPE_CHAR = "( )*char( )*";
	public static final String INPUT_CHAR = "( )*'[^']'( )*";
	
	public static final String SOME_TYPE_VALUE = "("+INPUT_INT+"|"+
													INPUT_DOUBLE+"|"+
													INPUT_CHAR+"|"+
													INPUT_STRING+"|"+
													INPUT_BOOLEAN+")";
	
	public static final String VALID_METHOD_CALL = GENERAL_NAME+"\\((|("+GENERAL_NAME+")|("+SOME_TYPE_VALUE+")|(\\)";
	
	
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
	
	public static void checkExpression(Type typeToCompare, String expression, ScopeMediator med) {
		
		if (analize(expression) == SOME_TYPE_VALUE) {
			Type expType = Type.createType(expression);
			if (!typeToCompare.sameType(expType)) {
				throw new Exception();
			}
		}
		
		if (analize(expression) == GENERAL_NAME) {
			
			ScopeMediator tempScope = med;
			
			while (tempScope != null) {
				for(Variable var:tempScope.getVariables()) {
					if (var.getName().equals(expression)) {
						if(!typeToCompare.sameType(var.getType())) {
							throw new Exception();
						}
					}
				}
				tempScope = tempScope.getFatherScope();
			}
		}
		
		if (analize(expression) == VALID_METHOD_CALL) {
			
			ScopeMediator tempScope = med;
			//get to the Class
			while (tempScope.getFatherScope() != null) {
				tempScope = tempScope.getFatherScope();
			}
			
			for(Scope method:tempScope.getScopes()) {
				MethodScope tempMethodScope = (MethodScope) method;
				tempMethodScope.
			}
			
		}
		
		
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
