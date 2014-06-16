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
 * 
 * @author Lior
 *
 */
public class FileParser {

	public enum expTypes {SOME_TYPE_INPUT, VAR, METHOD, OPERATORS, ARR_VAR, ARRAY_INIT} 
	
	/**
	 * parses original file 
	 * @param origin
	 * @return
	 * @throws FileNotFoundException 
	 * @throws BadLineSyntaxException 
	 * @throws BadEndOfLineException 
	 */
	public static ArrayList<String>  getlinesList(File origin) throws FileNotFoundException, BadLineSyntaxException, BadEndOfLineException{
		
		ArrayList<String> fileLines = new ArrayList<String>();
		Scanner myScan= new Scanner(origin);
		String currentLine;
		int lineNumber=0;
		
		while (myScan.hasNext()){

			currentLine=myScan.nextLine();
			if(!isLineCommentOrBlank(currentLine)) {//if it is commented simply continue and dont add to list
				fileLines.add(currentLine);
			}

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
		return currentLine.matches(RegexConfig.BLANK_LINE) || currentLine.matches(RegexConfig.COMMENT); 
	}
		
	//return 0-scope
	//return 1-variable
	public static int scopeOrVariable(String lineText,int lineNumber) throws BadEndOfLineException{
		String tempString=lineText.trim();
		if(tempString.matches(RegexConfig.ENDS_WITH_OPEN_BRACKET)) {
			return 0;
		}

		else if (tempString.matches(RegexConfig.ENDS_WITH_SEMICOLON)) {
			return 1;
		}
		throw new BadEndOfLineException(lineNumber,lineText);
	}
	
	
	
	
	public static void checkExpression(Type typeToCompare, String expression, 
		ScopeMediator med) throws CompileException {
		
		if (analyze(expression).equals(expTypes.SOME_TYPE_INPUT)) {
			if (!typeToCompare.isExpressionMatch(expression)) {
				throw new BadTypeException(expression);
			}
			return;
		}
		
		if (analyze(expression).equals(expTypes.VAR)) {
			
			ScopeMediator tempScope = med;
			
			while (tempScope != null) {
				for(Variable var:tempScope.getVariables()) {
					if (var.getName().equals(expression)) {
						if(!typeToCompare.sameType(var.getType())) {
							throw new BadTypeException(expression);
						}
						if(!var.isInitialized()) {
							throw new VarNotExistException(expression+" - Uninitialized"); //TODO
						}
						return;
					}
				}
				tempScope = tempScope.getFatherScope();
			}
			throw new VarExistException(expression);
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
						throw new BadTypeException(expression);
					}
					return;	
				}
			}
		}
		
		if (analyze(expression).equals(expTypes.OPERATORS)) {
			String[] expressions = getExpressions(typeToCompare, expression);
			for (String exp:expressions) {
				checkExpression(typeToCompare, exp, med);
			}
		}
		
		if (analyze(expression).equals(expTypes.ARR_VAR)) {
			
			Pattern p = Pattern.compile(RegexConfig.VALID_EXP);
			Matcher m = p.matcher(expression);
			m.find();
			String nameOfArr = expression.substring(m.start(), m.end());
			
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
							throw new VarNotExistException(expression+" - Uninitialized"); //TODO
						}
						checkInnerArrVarPos(expression, med);
						return;
					}
				}
				tempScope = tempScope.getFatherScope();
			}
			throw new VarExistException(expression);
		}
		
		if (analyze(expression).equals(expTypes.ARRAY_INIT)) {
			
			ArrayList<String> exps = new ArrayList<String>();
			Pattern p = Pattern.compile(RegexConfig.VALID_EXP);
			Matcher m = p.matcher(expression);
			
			while (m.find()) {
				exps.add(expression.substring(m.start(), m.end()).trim());
			}
			
//			ScopeMediator tempScope = med;
//			
//			if (tempScope.getFatherScope() == null) {
//				throw new CompileException(); //TODO change Exception
//			}
//			while (tempScope.getFatherScope().getFatherScope() != null) {
//				tempScope = tempScope.getFatherScope();
//			}
//			
//			MethodScope method = (MethodScope) tempScope;
			ArrayType arr = (ArrayType)typeToCompare;
			for (String exp:exps) {
				checkExpression(arr.getInnerType(), exp, med);
			}
			
		}
	}

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
		if (expression.matches(RegexConfig.ARRAY_INIT)) {
			
			return expTypes.ARRAY_INIT;
		}
		throw new BadLineSyntaxException(expression);
	}
	
	public static int  findLastCloser(ArrayList<String> relevantLines, int i) throws EndOfFileException{

		int bracketCounter=1;//initial size is 1 because 
		for (int index=i+1; index<relevantLines.size(); index++){
			String tempString=relevantLines.get(index);
			tempString=tempString.trim();

			if (tempString.matches(RegexConfig.ENDS_WITH_CLOSED_BRACKET)){
				bracketCounter--;
			}
			else if(tempString.matches(RegexConfig.ENDS_WITH_OPEN_BRACKET)){
				bracketCounter++;
			}

			if (bracketCounter==0){
				return index;
			}

		}
		throw new EndOfFileException();
	}

	
	public static String[] getExpressions(Type type, String expression) throws CompileException {
		
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
