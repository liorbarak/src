package oop.ex7.type;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import oop.ex7.main.Variable;
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
	
	public static Variable createVariable(String line, ScopeMediator currScope)
			throws BadVarDeclarationException {
		
		Variable varTemp;
		if (checkLine(line) == lineType.ASSIGNMENT.ordinal()) {
			varTemp = varExist(line, currScope);
				
			
		}
	
//		switch(varTypeRepresentation) {
//		
//		case (INT): return new Int(varName);
//		
//		case(DOUBLE):
//			return new Int(varName);
//		case(VarType.STRING.name()):
//		
//		case(VarType.BOOLEAN.name()):
//		
//		case(VarType.CHAR.name()):
//		
//		case(VarType.ARRAY.name()):
//		
//		case(VarType.VOID.name()):
		
		default:
			throw new BadVarDeclarationException(varTypeRepresentation);
		}
		
	}
	
	private static int checkLine(String line) {
		// TODO Auto-generated method stub
		return 0;
	}

	private static Variable varExist(String line, ScopeMediator currScope) {
		
		ScopeMediator tempScope = currScope; 
		String linetemp = line.trim();
		String[] expInLine = linetemp.split("[ ]+");
		String nameOfVar = expInLine[0];
		
		while (tempScope != null) {
			for (Variable varOfScope:currScope.getVariables()) {
				if (varOfScope.getName().equals(nameOfVar)) {
					return varOfScope;
				}
			}
			tempScope = tempScope.getFatherScope();
		}
		
		
	}
	
	
	private boolean checkExpTypecorrect(Type targetType){
		
	}
}
