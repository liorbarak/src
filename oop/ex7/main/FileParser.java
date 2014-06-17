package oop.ex7.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import oop.ex7.scope.*;
import oop.ex7.type.*;

/**
 * class holds static methods and is used to parse the document and its
 * String expressions in different ways as needed
 *
 */
public class FileParser {
	
	private static final int SCOPE=0;
	private static final int OPERATION=1;	

	/*
	 * used to differentiate between kinds of lines
	 */
	/**
	 * parses original file into a list of separate lines
	 * @param origin-a file
	 * @return list of separate lines
	 * 
	 * @throws FileNotFoundException 
	 * @throws BadLineSyntaxException 
	 */
	public static ArrayList<String>  getlinesList(File origin) 
			throws FileNotFoundException, BadLineSyntaxException{

		ArrayList<String> fileLines = new ArrayList<String>();
		Scanner myScan= new Scanner(origin);
		String currentLine;

		while (myScan.hasNext()){
			currentLine=myScan.nextLine();

			//if it is commented simply continue and dont add to list
			if(!isLineCommentOrBlank(currentLine)) {

				fileLines.add(currentLine);

			}

		}
		myScan.close();
		return fileLines;
	}

	/*
	 * helper method
	 * checks if line starts with '//' or if it is blank
	 * @param currentLine
	 * @return
	 */
	private static boolean isLineCommentOrBlank(String currentLine)
			throws BadLineSyntaxException  {
		
		if (currentLine.matches(RegexConfig.ILLEGAL_COMMENT)) { 
			throw new  BadLineSyntaxException(currentLine);
		}
		return currentLine.matches(RegexConfig.BLANK_LINE) 
				|| currentLine.matches(RegexConfig.COMMENT); 
	}

	/**
	 * determines if a certain line ends with an open bracket or a semicolon.
	 * is used to check if this line is the beginning of a scope or
	 * a variable decleration/assigment
	 * 
	 * @param lineText
	 * @param lineNumber
	 * @return 0 if ends with an open bracket
	 * 			1 if ends with a semicolon
	 * 
	 * @throws BadEndOfLineException
	 */
	public static int scopeOrVariable(String lineText,int lineNumber)
			throws BadEndOfLineException{
		
		String tempString=lineText.trim();
		
		if(tempString.matches(RegexConfig.ENDS_WITH_OPEN_BRACKET)) {
			return SCOPE;
		}

		else if (tempString.matches(RegexConfig.ENDS_WITH_SEMICOLON)) {
			return OPERATION;
		}
		throw new BadEndOfLineException(lineNumber,lineText);
	}



	/**
	 * Receives an expression and a type and checks if 
	 * they match each other
	 * 
	 * @param typeToCompare
	 * @param expression- a complex string, may contain all the different
	 * 						expressions the program can compile
	 * @param med- this represents the scope and is used as an interface
	 * @throws BadTypeException 
	 * @throws BadLineSyntaxException 
	 * @throws VarNotExistException 
	 * @throws VarExistException 
	 */
	public static void checkExpression(Type typeToCompare, String expression, 
			ScopeMediator med) throws BadLineSyntaxException, BadTypeException,
			VarNotExistException, VarExistException  {

		//represents a basic rhs value. only needs to check if valid
		//if (analyze(expression).equals(expTypes.SOME_TYPE_INPUT)) {
		if (expression.matches(RegexConfig.SOME_TYPE_VALUE)) {
			if (!typeToCompare.isExpressionMatch(expression)) {
				throw new BadTypeException(expression);
			}
			return;
		}

		//in case this is a method, it must be compared to relevant methods
//		if (analyze(expression).equals(expTypes.METHOD)) {
		if (expression.matches(RegexConfig.METHOD_CALL)) {
			ScopeMediator tempScope = med;
			//get up to the Class scope
			
			while (tempScope.getFatherScope() != null) {
				tempScope = tempScope.getFatherScope();
			}

			for(Scope method:tempScope.getScopes()) {
				MethodScope tempMethodScope = (MethodScope) method;
				if (tempMethodScope.compareMethod(expression, med)) {
					if(!typeToCompare.sameType
							(tempMethodScope.getReturnType())) {
						
						throw new BadTypeException(expression);
					}
					return;	
				}
			}
			throw new VarNotExistException(expression);
		}


		//checks if this is a variable and must be compared with other
		//variables in relevant scopes
//		if (analyze(expression).equals(expTypes.VAR)) {
		if (expression.matches(RegexConfig.GENERAL_NAME)) {
			
			ScopeMediator tempScope = med;
			expression = expression.replaceAll("-","").trim();
			while (tempScope != null) {

				for(Variable var:tempScope.getVariables()) {

					if (var.getName().equals(expression)) {

						if(!typeToCompare.sameType(var.getType())) {
							throw new BadTypeException(expression);
						}

						if(!var.isInitialized()) {
							throw new VarNotExistException(expression); 
						}

						return;
					}
				}
				tempScope = tempScope.getFatherScope();
			}
			throw new VarExistException(expression);
		}




		//checks if there is an expression that contains operators
		//if so it dismantles it and checks if it is legal by recursion
//		if (analyze(expression).equals(expTypes.OPERATORS)) {
		if (expression.matches(RegexConfig.OPERATOR_EXP)) {	
			String[] expressions = getExpressions(typeToCompare, expression);
			
			for (String exp:expressions) {
				checkExpression(typeToCompare, exp, med);
			}
			return;
		}


		//checks if this is an assigment pulled out of an array
		//like b=a[5]. checks its validity
//		if (analyze(expression).equals(expTypes.ARR_VAR)) {
		if (expression.matches(RegexConfig.ARR_VAR)) {	

			Pattern p = Pattern.compile(RegexConfig.GENERAL_NAME);
			Matcher m = p.matcher(expression);
			m.find();
			String nameOfArr = expression.substring(m.start(), m.end());
			ScopeMediator tempScope = med;

			while (tempScope != null) {
				
				for(Variable var:tempScope.getVariables()) {
					
					if (var.getName().equals(nameOfArr)) {
						
						if(!new ArrayType().sameType(var.getType())) {
							throw new BadTypeException(expression);
						}
						
						ArrayType varTempArrType = (ArrayType) var.getType();
						if(!typeToCompare.sameType
								(varTempArrType.getInnerType())) {
							throw new BadTypeException(expression);
						}
						
						if(!var.isInitialized()) {
							throw new VarNotExistException(expression);
						}
						
						checkInnerArrVarPos(expression, med);
						return;
					}
				}
				tempScope = tempScope.getFatherScope();
			}
			throw new VarExistException(expression);

		}

//		if (analyze(expression).equals(expTypes.ARRAY_INIT)) {
		if (expression.matches(RegexConfig.ARRAY_INIT)) {					

			ArrayList<String> exps = new ArrayList<String>();
			Pattern p = Pattern.compile(RegexConfig.VALID_EXP);
			Matcher m = p.matcher(expression);

			while (m.find()) {
				exps.add(expression.substring(m.start(), m.end()).trim());
			}

			ArrayType arr = (ArrayType)typeToCompare;
			for (String exp:exps) {
				checkExpression(arr.getInnerType(), exp, med);
			}

			return;
		}
		throw new BadLineSyntaxException(expression);
	}

	/**
	 * used to check if a calling index of an array is legal.
	 * it can be a type int expression, or a positive number
	 * 
	 * @param expression-expression inside calling brackets '[expression]'
	 * 
	 * @param med- an interface used to refer to the current scope
	 * @throws BadTypeException 
	 * @throws VarExistException 
	 * @throws VarNotExistException 
	 * @throws BadLineSyntaxException 
	 */
	public static void checkInnerArrVarPos
		(String expression, ScopeMediator med)
			throws BadTypeException, BadLineSyntaxException, 
			VarNotExistException, VarExistException  {

		String innerExp = expression.substring(expression.
				indexOf("[")+1, expression.lastIndexOf("]"));
		
		if (innerExp.matches(RegexConfig.INPUT_INT)) {
			
			int intExp = Integer.parseInt(innerExp.trim());
			
			if (intExp < 0) {
				throw new BadTypeException(innerExp);
			}
			
		}
		checkExpression(new IntType(), innerExp, med);
	}


	/**
	 * when the program encounters an open bracket '{' this method is called
	 * it finds and returns the location of the end of the scope.
	 * taking into consideration that there may be other scopes inside
	 * 
	 * @param relevantLines- array of relevant lines
	 * @param i-index of open bracket
	 * @return - index of closing bracket
	 * 
	 * @throws EndOfFileException
	 */
	public static int  findLastCloser(ArrayList<String> relevantLines, int i) throws EndOfFileException{

		int bracketCounter=1;
		//initial size is set to 1 to represent the first open bracket
		for (int index=i+1; index<relevantLines.size(); index++){
			String tempString=relevantLines.get(index);
			tempString=tempString.trim();

			if (tempString.matches(RegexConfig.ENDS_WITH_CLOSED_BRACKET)){
				bracketCounter--;
			}
			else if(tempString.matches(RegexConfig.ENDS_WITH_OPEN_BRACKET)){
				bracketCounter++;
			}

			//this means we have reached the end of the scope
			if (bracketCounter==0){
				return index;
			}

		}
		throw new EndOfFileException();
	}

	/**
	 * is used for a complex operator expressions
	 * splits the expression into two different types.
	 * if unmatching types throws exception
	 * 
	 * @param type- the type of the wanted expression
	 * @param expression- a String representing the right side of the '=' sign
	 * @throws BadTypeException 
	 * @return- Strings[] with two String, lhs and rhs
	 */
	public static String[] getExpressions(Type type, String expression) throws BadTypeException
	{

		Pattern p = Pattern.compile(RegexConfig.VALID_EXP_ARRAY_CELL);
		Matcher m = p.matcher(expression);
		String leftExp;
		String rightExp;
		if (m.find()) {
			leftExp = expression.substring(m.start(), m.end()).trim();
		}
		else {
			throw new BadTypeException("a"); 
		}
		if (m.find()) {
			rightExp = expression.substring(m.start(), m.end()).trim();
		}
		else {
			throw new  BadTypeException("a"); 
		}

		String[] expressions = {leftExp, rightExp};
		return expressions;
	}


}
