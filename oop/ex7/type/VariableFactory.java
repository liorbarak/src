package oop.ex7.type;

import javax.print.DocFlavor.INPUT_STREAM;

import org.omg.PortableInterceptor.INACTIVE;

import oop.ex7.main.Variable;
import oop.ex7.scope.MethodScope;
import oop.ex7.scope.ScopeMediator;

/**
 * This class creates the different variable types for the different Type 
 * classes implementations. 
 * @author taldovrat
 *
 */
public abstract class VariableFactory {
	
	public enum lineType {
		DECLARATION (validTypes+"[ ]+"+GENERAL_NAME+"[ ]*;[ ]*"),
		ASSIGNMENT (GENERAL_NAME+"[ ]*=[ ]*"+VALID_EXP+"[ ]*;[ ]*"), 
		BOTH ();
		
		private static final String regex;
		
		lineType(String regex) {
			this.regex = regex;
		}
		public String getRegex() {
			return regex;
		}
	}
	
	
	
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
	
	public static final String SOME_TYPE_VALUE = "("+INPUT_INT+"|"+
													INPUT_DOUBLE+"|"+
													INPUT_CHAR+"|"+
													INPUT_STRING+"|"+
													INPUT_BOOLEAN+")";
	
	public static final String VALID_METHOD_CALL = GENERAL_NAME+"((("+GENERAL_NAME+")?|(("+GENERAL_NAME+"),)+))";

	
	public static final String VALID_EXP = "(GENERAL_NAME|SOME_TYPE_VALUE|VALID_METHOD_CALL)";
	
	public static final String INT_REGEX_EXP = TYPE_INT+GENERAL_NAME+"("+UNINITIALIZED+"|"+INPUT_INT+")";
	public static final String DOUBLE_REGEX_EXP = TYPE_DOUBLE+GENERAL_NAME+"("+UNINITIALIZED+"|"+INPUT_DOUBLE+")";
	public static final String STRING_REGEX_EXP = TYPE_STRING+GENERAL_NAME+"("+UNINITIALIZED+"|"+INPUT_STRING+")";
	public static final String BOOLEAN_REGEX_EXP = TYPE_BOOLEAN+GENERAL_NAME+"("+UNINITIALIZED+"|"+INPUT_BOOLEAN+")";
	public static final String CHAR_REGEX_EXP = TYPE_CHAR+GENERAL_NAME+"("+UNINITIALIZED+"|"+INPUT_CHAR+")";
		
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
	

	private static int checkLine(String line) {
		switch(line) {
		
		case
		}
		
	}

	private static Variable varExist(String nameOfVar, ScopeMediator currScope) {
		
		ScopeMediator tempScope = currScope; 
		
		for (Variable varOfScope:currScope.getVariables()) {
			if (varOfScope.getName().equals(nameOfVar)) {
				return varOfScope;
			}
		}
		return null;
	}
	
	private static Variable varExistInAll(String nameOfVar, ScopeMediator currScope) {
		
		ScopeMediator tempScope = currScope;
		Variable tempVar;
		while(tempScope != null) {
			tempVar = varExist(nameOfVar, tempScope);
			if (tempVar != null) {
				return tempVar;
			}
			tempScope = tempScope.getFatherScope();
		}
		return null;
	}
	
	private static Variable bothLine(String line, ScopeMediator currScope) {
		
		Variable varTemp;
		//save the left expression as the name of the variable
		//save the right expression as the input value
		String linetemp = line.trim();
		String[] stringsInLine = getBothStr(linetemp);
		String typeOfVar = stringsInLine[0];
		String nameOfVar = stringsInLine[1];
		String inputValue = stringsInLine[2];
		
		try {
			Type varType = Type.createType(typeOfVar);
			Variable tempVar = varExist(nameOfVar, currScope);
			if (tempVar == null) {
				throw new VarExistException();
			}
			if (varType.checkExpression(inputValue)); {
				tempVar.setInitialized(true);
				return tempVar;
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getLocalizedMessage());
		}
		
		return null;
	}
	
	
	private static Variable assignmentLine(String line, ScopeMediator currScope) throws Exception {
		
		Variable varTemp;
		
		String[] stringsInLine = getAssigmentStr(line);		
		String nameOfVar = stringsInLine[0];
		String inputValue = stringsInLine[1];
		
		//if the variable exists, somewhere in the code, put it into varTemp, else, put null into varTemp.
		varTemp = varExistInAll(nameOfVar, currScope);
		
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
		Variable tempExpVar = varExistInAll(inputValue, currScope);
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
		
		MethodScope tempMethod = MethodScope.checkMethod(inputValue);
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
		try {
			Type newVarType = Type.createType(typeOfVar);
			//if the variable exists, put it into varTemp, else, put null into varTemp.
			varTemp = varExist(nameOfVar, currScope);
			//if the variable doesn't exist:
			if (varTemp == null) {
				return new Variable(newVarType, nameOfVar);
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getLocalizedMessage());
		}
	
		return null;
	}

	private static String[] getAssigmentStr(String line) {
		//1 - index of input value
		String linetemp = line.trim();
		String[] stringsInLine = linetemp.split("=");
		stringsInLine[1] = stringsInLine[1].trim();
		stringsInLine[1] = stringsInLine[1].replaceAll("( )*;?", "");
		return stringsInLine;
	}
	
	private static String[] getDecStr(String line) {
		//1 - index of name of var
		String linetemp = line.trim();
		String[] stringsInLine = linetemp.split("[ ]+");
		stringsInLine[1] = stringsInLine[1].replaceAll("( )*;?", "");
		return stringsInLine;
	}
	
	public static String[] getBothStr(String line) {
		
		String declarationLine =  getAssigmentStr(line)[0];
		String[] declaration = getDecStr(declarationLine);
		String inputValue = getAssigmentStr(line)[1];
		String[] bothStrings = {declaration[0], declaration[1], inputValue};
		return bothStrings;
		
	}
}
