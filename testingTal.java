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


//	public static final String VALID_GENERAL_EXP = "(("+GENERAL_NAME+")|("+SOME_TYPE_VALUE+")|("+VALID_METHOD_CALL+"))( )*;( )*";
	
	
	public static final String METHOD_CALL = "( )*"+GENERAL_NAME+"\\([\\d]*[\\D]*\\)( )*;";
	public static final String inner = "((( )*[^ ,]*( )*)|(( )*([^ ]*,)*[^ ]+( )*))";
	public static final String test1 = "( )*[^ ,]*( )*";
	public static final String test2 = "";
	public void check() {
		int a = 5;
		this.a = a;
	}
	
	public static void main(String[] args) {
		String line = "    foo   (  a c  ,   b  );";
		String callName=line.substring(0, line.indexOf("(")).trim();
		String variablesCall=line.substring(line.indexOf("(")+1,line.indexOf(")")).trim();
		//Need to check valid expression structure
		String[] stringVars=variablesCall.split(",");
		String[] expVars = new String[stringVars.length];
		
		for(int i = 0; i< stringVars.length; i++) {
			expVars[i] = stringVars[i].trim();
			
		}
		for (String str:expVars) {
			if (str.contains(" ")) {
				System.out.println("Var not valid!");
				return;
			}
		}
		if ()
		System.out.println(callName);
		System.out.println(variablesCall);
		for(String str:expVars) {
			System.out.println(str);
		}
	} 
		
//			
//
//	}

		
}

