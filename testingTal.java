import java.util.regex.*;

import oop.ex7.type.*;
public class testingTal {

	int a;

	public static final String validTypes = "( )*(int|double|String|char|boolean)( )*";
	public static final String GENERAL_NAME = "( )*([_][^ ]+|[^\\d_][^ ]*)( )*";
	public static final String UNINITIALIZED = ";";
	
	
	
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
	
	
	public static final String METHOD_CALL = GENERAL_NAME+"\\((|("+GENERAL_NAME+")|("+SOME_TYPE_VALUE+")\\)";
	public static final String VALID_METHOD_CALL = GENERAL_NAME+"\\((|("+GENERAL_NAME+")|("+SOME_TYPE_VALUE+")|(\\)";
	

	public static final String VALID_GENERAL_EXP = "(("+GENERAL_NAME+")|("+SOME_TYPE_VALUE+")|("+VALID_METHOD_CALL+"))( )*;( )*";
	
	public static final String test1 = "( )*("+GENERAL_NAME+"|"+SOME_TYPE_VALUE+"|"+VALID_METHOD_CALL+")( )*";
	
	
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
		String linetemp = line.trim().replaceAll(";", "");
		String[] stringsInLine = linetemp.split("[ ]+");
		stringsInLine[1] = stringsInLine[1].replaceAll("( )*;?", "");
		return stringsInLine;
	}
	
	private static String[] getBothStr(String line) {
		
		String declarationLine =  getAssigmentStr(line)[0];
		String[] declaration = getDecStr(declarationLine);
		String inputValue = getAssigmentStr(line)[1];
		String decLine = declaration[0]+" "+declaration[1];
		String assLine = declaration[1]+"="+inputValue;
		String[] both = {decLine, assLine};
		return both;
		
	}	
	
	public void check() {
		int a = 5;
		this.a = a;
	}
	
	public static void main(String[] args) {
	
		String both = " int     a = foooo(foo( dafdf  ),asd)     ;";
		String[] testArr = getBothStr(both);
		for (String str:testArr) {
			System.out.println(str);
		}
	} 
		
//			
//
//	}

		
}

