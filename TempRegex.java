import oop.ex7.type.*;
import java.util.regex.*;

import oop.ex7.type.BadVarDeclarationException;

public class TempRegex {
	
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
	
	
//	public static final String IGNORE_VARS = "[^int][^String]^[double][^char]"
//										+ "[^boolean][^void][^return]";
	
	public void intDeclaration(String lineToCheck) throws BadVarDeclarationException{
		
		Pattern declarationLine = Pattern.compile(INT_REGEX_EXP);
		
		Matcher matcher = declarationLine.matcher(lineToCheck);
		
		if (!matcher.matches()) {
			throw new BadVarDeclarationException(lineToCheck);
		}
	}
	
	public void doubleDeclaration(String lineToCheck) throws BadVarDeclarationException{
		
		Pattern declarationLine = Pattern.compile(DOUBLE_REGEX_EXP);
		
		Matcher matcher = declarationLine.matcher(lineToCheck);
		
		if (!matcher.matches()) {
			throw new BadVarDeclarationException(lineToCheck);
		}
	}
	
	public void stringDeclaration(String lineToCheck) throws BadVarDeclarationException{
		
		Pattern declarationLine = Pattern.compile(STRING_REGEX_EXP);
		
		Matcher matcher = declarationLine.matcher(lineToCheck);
		
		if (!matcher.matches()) {
			throw new BadVarDeclarationException(lineToCheck);
		}	
	}
	
	public void booleanDeclaration(String lineToCheck) throws BadVarDeclarationException{
		
		Pattern declarationLine = Pattern.compile(BOOLEAN_REGEX_EXP);
		
		Matcher matcher = declarationLine.matcher(lineToCheck);
		
		if (!matcher.matches()) {
			throw new BadVarDeclarationException(lineToCheck);
		}
	}
	
	public void charDeclaration(String lineToCheck) throws BadVarDeclarationException{
		
		Pattern declarationLine = Pattern.compile(CHAR_REGEX_EXP);
		
		Matcher matcher = declarationLine.matcher(lineToCheck);
		
		if (!matcher.matches()) {
			throw new BadVarDeclarationException(lineToCheck);
		}
	}
	
	public Type getType(String lineToCheck) {
		
		return new IntType(lineToCheck);
	}
	
	public void arrDeclaration(String lineToCheck) throws BadVarDeclarationException{
		
		
		
		Pattern declarationLine = Pattern.compile(DOUBLE_REGEX_EXP);
		
		Matcher matcher = declarationLine.matcher(lineToCheck);
		
		if (!matcher.matches()) {
			throw new BadVarDeclarationException(lineToCheck);
		}
	}
	
	public static void main(String[] args) {
		TempRegex a = new TempRegex();
		try {
			a.charDeclaration("char _ai    ;");
		}
		catch(BadVarDeclarationException ex) {
			System.out.println(ex.getLocalizedMessage());
		}
		
//		Pattern declarationLine = Pattern.compile("a|b");
//		
//		Matcher matcher = declarationLine.matcher("ab");
//		System.out.println(matcher.matches());
	}
}
