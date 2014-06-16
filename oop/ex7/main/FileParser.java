package oop.ex7.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import oop.ex7.scope.MethodScope;
import oop.ex7.scope.Scope;
import oop.ex7.scope.ScopeMediator;
import oop.ex7.type.ArrayType;
import oop.ex7.type.BadTypeException;
import oop.ex7.type.IntType;
import oop.ex7.type.Type;
import oop.ex7.type.BadEndOfLineException;
import oop.ex7.type.VarExistException;
import oop.ex7.type.VarNotExistException;


/**
 * class holds static methods and is used to parse the document and its
 * String expressions in different ways as needed
 * @author Lior
 *
 */
public class FileParser {

	/*
	 * used to differentiate between kinds of lines
	 */
	private enum expTypes {SOME_TYPE_INPUT, VAR, METHOD, OPERATORS, ARR_VAR} 


	/**
	 * parses original file into a list of separate lines
	 * @param origin-a file
	 * @return list of separate lines
	 * 
	 * @throws FileNotFoundException 
	 * @throws BadLineSyntaxException 
	 * @throws BadEndOfLineException 
	 */
	public static ArrayList<String>  getlinesList(File origin) throws FileNotFoundException, BadLineSyntaxException, BadEndOfLineException{

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
	private static boolean isLineCommentOrBlank(String currentLine) {
		return currentLine.matches(RegexConfig.BLANK_LINE) || currentLine.matches(RegexConfig.COMMENT); 
	}

	
	//return 0-scope
	//return 1-variable
	
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
	public static int scopeOrVariable(String lineText,int lineNumber) throws BadEndOfLineException{
		String tempString=lineText.trim();
		if(tempString.matches(RegexConfig.ENDS_WITH_OPEN_BRACKET)) {
			return 0;		//TODO make const
		}

		else if (tempString.matches(RegexConfig.ENDS_WITH_SEMICOLON)) {
			return 1;		//TODO make const
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
	 * 
	 * @throws CompileException
	 */
	public static void checkExpression(Type typeToCompare, String expression, 
			ScopeMediator med) throws CompileException {
		
		//represents a basic rhs value. only needs to check if valid
		if (analyze(expression).equals(expTypes.SOME_TYPE_INPUT)) {
			if (!typeToCompare.isExpressionMatch(expression)) {
				throw new BadTypeException(expression);
			}
			return;
		}

		//checks if this is a variable and must be compared with other
		//variables in relevant scopes
		if (analyze(expression).equals(expTypes.VAR)) {
			ScopeMediator tempScope = med;
			
			while (tempScope != null) {
				
				for(Variable var:tempScope.getVariables()) {
					
					if (var.getName().equals(expression)) {
						
						if(!typeToCompare.sameType(var.getType())) {
							throw new BadTypeException(expression);
						}
						
						if(!var.isInitialized()) {
							throw new VarNotExistException(expression+" - Uninitialized"); //TODO correct exception
						}
						
						return;
					}
				}
				tempScope = tempScope.getFatherScope();
			}
			throw new VarExistException(expression);
		}

		//in case this is a method, it must be compared to relevant methods
		if (analyze(expression).equals(expTypes.METHOD)) {

			ScopeMediator tempScope = med;
			//get up to the Class scope
			while (tempScope.getFatherScope() != null) {
				tempScope = tempScope.getFatherScope();
			}
			
			for(Scope method:tempScope.getScopes()) {
				MethodScope tempMethodScope = (MethodScope) method;
				if (tempMethodScope.compareMethod(expression, med)) {
					if(!typeToCompare.sameType(tempMethodScope.getReturnType())) {
						throw new BadTypeException(expression);
					}
					return;	
				}
			}
		}
		
		//checks if there is an expression that contains operators
		//if so it dismantles it and checks if it is legal by recursion
		if (analyze(expression).equals(expTypes.OPERATORS)) {
			String[] expressions = getExpressions(typeToCompare, expression);
			for (String exp:expressions) {
				checkExpression(typeToCompare, exp, med);
			}
		}

		//checks if this is an assigment pulled out of an array
		//like b=a[5]. checks its validity
		if (analyze(expression).equals(expTypes.ARR_VAR)) {

			Pattern p = Pattern.compile(RegexConfig.GENERAL_NAME);
			Matcher m = p.matcher(expression);
			m.find();
			String nameOfArr = expression.substring(m.start(), m.end()); // TODO delete this if not needed

			ScopeMediator tempScope = med;

			while (tempScope != null) {
				for(Variable var:tempScope.getVariables()) {
					if (var.getName().equals(expression)) {
						if(!new ArrayType().sameType(var.getType())) {
							throw new BadTypeException(expression);
						}
						ArrayType varTempArrType = (ArrayType) var.getType();
						if(!typeToCompare.sameType(varTempArrType.getInnerType())) {
							throw new BadTypeException(expression);
						}
						if(!var.isInitialized()) {
							throw new VarNotExistException(expression+" - Uninitialized"); //TODO fix exception
						}
						checkInnerArrVarPos(expression, med);
						return;
					}
				}
				tempScope = tempScope.getFatherScope();
			}
			throw new VarExistException(expression);

		}
	}

	/**
	 * used to check if a calling index of an array is legal.
	 * it can be a type int expression, or a positive number
	 * 
	 * @param expression-expression inside calling brackets '[expression]'
	 * 
	 * @param med- an interface used to refer to the current scope
	 * @throws CompileException
	 */
	public static void checkInnerArrVarPos(String expression, ScopeMediator med) throws CompileException {

		String[] stringsInLine = Scope.getAssigmentStr(expression);		
		String nameOfVar = stringsInLine[0];
		String expToCheck = nameOfVar.substring(expression.indexOf("[")+1, expression.lastIndexOf("]")).trim();

		FileParser.checkExpression(new IntType(), expToCheck, med);
		if (expToCheck.matches(RegexConfig.INPUT_INT)) {
			int intExp = Integer.parseInt(expToCheck);
			if (intExp < 0) {
				throw new CompileException(); //TODO change exception type
			}
		}
	}

	/*
	 * used to differentiate between kinds of line structures
	 */
	private static expTypes analyze(String expression) throws BadLineSyntaxException {

		if(expression.matches(RegexConfig.SOME_TYPE_VALUE)) {
			return expTypes.SOME_TYPE_INPUT;
		}

		if(expression.matches(RegexConfig.GENERAL_NAME)) {
			return expTypes.VAR;
		}

		if (expression.matches(RegexConfig.METHOD_CALL)) {
			return expTypes.METHOD;
		}
		if (expression.matches(RegexConfig.OPERATOR_EXP)) {
			return expTypes.OPERATORS;
		}

		if (expression.matches(RegexConfig.ARR_VAR)) {
			return expTypes.ARR_VAR;
		}
		throw new BadLineSyntaxException(expression);
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
	 * @return- Strings[] with two String, lhs and rhs
	 * 
	 * @throws CompileException
	 */
	public static String[] getExpressions(Type type, String expression)
			throws CompileException {

		Pattern p = Pattern.compile(type.getRegex());
		Matcher m = p.matcher(expression);
		String leftExp;
		String rightExp;
		if (m.find()) {
			leftExp = expression.substring(m.start(), m.end());
		}
		else {
			throw new CompileException(); //TODO change exception type
		}
		if (m.find()) {
			rightExp = expression.substring(m.start(), m.end());
		}
		else {
			throw new CompileException(); //TODO change exception type
		}

		String[] expressions = {leftExp, rightExp};
		return expressions;
	}


}
