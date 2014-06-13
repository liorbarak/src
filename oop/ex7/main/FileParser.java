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
import oop.ex7.type.badEndOfLineException;


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
	
	public static final String METHOD_CALL = "( )*"+GENERAL_NAME+"\\([\\d]*[\\D]*\\)( )*;";
	
	
	public enum expTypes {SOME_TYPE_INPUT, VAR, METHOD} 
	
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
	
	public static void checkExpression(Type typeToCompare, String expression, ScopeMediator med) throws Exception {
		
		if (analyze(expression).equals(expTypes.SOME_TYPE_INPUT)) {
			Type expType = Type.createType(expression);
			if (!typeToCompare.sameType(expType)) {
				throw new Exception();
			}
			return;
		}
		
		if (analyze(expression).equals(expTypes.VAR)) {
			
			ScopeMediator tempScope = med;
			
			while (tempScope != null) {
				for(Variable var:tempScope.getVariables()) {
					if (var.getName().equals(expression)) {
						if(!typeToCompare.sameType(var.getType())) {
							throw new Exception();
						}
						return;
					}
				}
				tempScope = tempScope.getFatherScope();
			}
		}
		
		if (analyze(expression).equals(expTypes.METHOD)) {
			
			ScopeMediator tempScope = med;
			//get to the Class scope
			while (tempScope.getFatherScope() != null) {
				tempScope = tempScope.getFatherScope();
			}
			
			for(Scope method:tempScope.getScopes()) {
				MethodScope tempMethodScope = (MethodScope) method;
				if (tempMethodScope.compareMethod(expression, med)) {
					if(!typeToCompare.sameType(tempMethodScope.getReturnType())) {
						throw new Exception();
					}
					return;	
				}
			}
		}
	}

	private static expTypes analyze(String expression) throws Exception {
		
		if(expression.matches(SOME_TYPE_VALUE)) {
			return expTypes.SOME_TYPE_INPUT;
		}
		
		if(expression.matches(GENERAL_NAME)) {
			return expTypes.VAR;
		}
		
		if (expression.matches(METHOD_CALL)) {
			return expTypes.METHOD;
		}
		throw new Exception();
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
