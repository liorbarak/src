package oop.ex7.type;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.CheckedInputStream;

import oop.ex7.main.Variable;
import oop.ex7.scope.Method;
import oop.ex7.scope.ScopeMediator;

/**
 * This class creates the different variable types for the different Type 
 * classes implementations. 
 * @author taldovrat
 *
 */
public abstract class VariableFactory {

	public enum lineType{DECLARATION, ASSIGNMENT, BOTH};
	
	public static final String validTypes = "( )*(int|double|String|char|boolean)( )*";
	public static final String GENERAL_NAME = "[_]?[^ ]+( )*";
	public static final String UNINITIALIZED = ";";
	
	public static final String TYPE_INT = "( )*int( )*";
	public static final String INPUT_INT = "=( )*(-)?[\\d]+( )*;";
	
	public static final String TYPE_DOUBLE = "( )*double( )*";
	public static final String INPUT_DOUBLE = "("+INPUT_INT+"|=( )*(-)?[\\d]+.[\\d]+( )*;)";
	
	public static final String TYPE_STRING = "( )*String( )*";
	public static final String INPUT_STRING = "=( )*\"[^\"]*\"( )*;";
	
	public static final String TYPE_BOOLEAN = "( )*boolean( )*";
	public static final String INPUT_BOOLEAN = "=( )*(true|false)( )*;";
	
	public static final String TYPE_CHAR = "( )*char( )*";
	public static final String INPUT_CHAR = "=( )*'[^']?'( )*;";
	
	public static final String INT_REGEX_EXP = TYPE_INT+GENERAL_NAME+"("+UNINITIALIZED+"|"+INPUT_INT+")";
	public static final String DOUBLE_REGEX_EXP = TYPE_DOUBLE+GENERAL_NAME+"("+UNINITIALIZED+"|"+INPUT_DOUBLE+")";
	public static final String STRING_REGEX_EXP = TYPE_STRING+GENERAL_NAME+"("+UNINITIALIZED+"|"+INPUT_STRING+")";
	public static final String BOOLEAN_REGEX_EXP = TYPE_BOOLEAN+GENERAL_NAME+"("+UNINITIALIZED+"|"+INPUT_BOOLEAN+")";
	public static final String CHAR_REGEX_EXP = TYPE_CHAR+GENERAL_NAME+"("+UNINITIALIZED+"|"+INPUT_CHAR+")";
	
	
	/**
	 * The enum type - int, double, String, boolean, char, array or void.
	 */
	//public enum VarType {INT, DOUBLE, STRING, BOOLEAN, CHAR, ARRAY, VOID};
	
	public static final String INT = "int";
	public static final String DOUBLE = "double";
	public static final String STRING = "String";
	public static final String BOOLEAN = "boolean";
	public static final String CHAR = "char";
	public static final String VOID = "void";
	
	public static Variable createVar(String line, ScopeMediator currScope)
			throws Exception{
		
		
		//if the line is an assignment of the form a an assignment:
		if (checkLine(line) == lineType.ASSIGNMENT.ordinal()) {
			return assignmentLine(line, currScope);
		}
		
		if (checkLine(line) == lineType.DECLARATION.ordinal()) {
			return declarationLine(line, currScope);
		}
		
		if (checkLine(line) == lineType.BOTH.ordinal()) {
			return bothLine(line, currScope);
		}
		return null;
	}
	
	private static Variable bothLine(String line, ScopeMediator currScope) {
		
		Variable varTemp;
		//save the left expression as the name of the variable
		//save the right expression as the input value
		String linetemp = line.trim();
		String[] stringsInLine = linetemp.split("[ ]+");
		String typeOfVar = stringsInLine[0];
		String nameOfVar = stringsInLine[1];
		String inputValue = stringsInLine[3];
		
		return null;
	}

	private static int checkLine(String line) {
		// TODO Auto-generated method stub
		return 0;
	}

	private static Variable varExist(String nameOfVar, ScopeMediator currScope) {
		
		ScopeMediator tempScope = currScope; 
		
		while (tempScope != null) {
			for (Variable varOfScope:currScope.getVariables()) {
				if (varOfScope.getName().equals(nameOfVar)) {
					return varOfScope;
				}
			}
			tempScope = tempScope.getFatherScope();
		}
		return null;
	}

	private static Variable assignmentLine(String line, ScopeMediator currScope) throws Exception {
		
		Variable varTemp;
		
		String[] stringsInLine = getAssigmentStr(line);		
		String nameOfVar = stringsInLine[0];
		String inputValue = stringsInLine[1];
		
		//if the variable exists, put it into varTemp, else, put null into varTemp.
		varTemp = varExist(nameOfVar, currScope);
		
		//if the variable doesn't exist:
		if (varTemp == null) {
			throw new VarExistException();
		}
		
		//check if the right expression is of the same type.
		if (varTemp.getType().checkExpression(inputValue)) {
			varTemp.setInitialized(true);
			return varTemp;
		}
		
		//check if the right expression is a variable in itself
		Variable tempExpVar = varExist(inputValue, currScope);
		//if it is a variable, check if its type matches the type of the leftside variable.
		if (tempExpVar != null) {
			if (varTemp.getType().checkExpression(tempExpVar.getType().toString())) {
				varTemp.setInitialized(true);
				return varTemp;
			}
			else {
				throw new Exception();
			}
		}
		
		Method tempMethod = Method.checkMethod(inputValue);
		if (tempMethod != null) {
			if (varTemp.getType().toString().equals(tempMethod.getReturnType().toString())) {
				varTemp.setInitialized(true);
				return varTemp;
			}
			else {
				throw new Exception();
			}
		}
			
		return null;
	}
	

	private static Variable declarationLine(String line, ScopeMediator currScope) {
		
		Variable varTemp;
		//save the left expression as the name of the variable
		//save the right expression as the input value
		String[] stringsInLine = getDecStr(line);
		String typeOfVar = stringsInLine[0];
		String nameOfVar = stringsInLine[1];
		
		Type newVarType = Type.createType(typeOfVar);
		//if the variable exists, put it into varTemp, else, put null into varTemp.
		varTemp = varExist(nameOfVar, currScope);
		//if the variable doesn't exist:
		if (varTemp == null) {
			return new Variable(newVarType, nameOfVar);
		}
	
	return null;
	}

	private static String[] getAssigmentStr(String line) {
		
		String linetemp = line.trim();
		String[] stringsInLine = linetemp.split("=");
		stringsInLine[1] = stringsInLine[1].trim();
		stringsInLine[1] = stringsInLine[1].replaceAll("( )*;?", "");
		return stringsInLine;
	}
	
	private static String[] getDecStr(String line) {
		
		String linetemp = line.trim();
		String[] stringsInLine = linetemp.split("[ ]+");
		stringsInLine[1] = stringsInLine[1].replaceAll("( )*;?", "");
		return stringsInLine;
	}
}
