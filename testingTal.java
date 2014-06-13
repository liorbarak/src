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

	

//	public static final String VALID_GENERAL_EXP = "(("+GENERAL_NAME+")|("+SOME_TYPE_VALUE+")|("+VALID_METHOD_CALL+"))( )*;( )*";
	
	
	public static final String METHOD_CALL = "( )*"+GENERAL_NAME+"\\(( )*((( )*[^ ]( )*)|(( )*[^ ]( )*,)+[^ ]( )*)\\)( )*;";

	public void check() {
		int a = 5;
		this.a = a;
	}
	
	public static void main(String[] args) {
	
		String methodCall = "foooo(a   ,asf);";
		System.out.println(methodCall.matches(METHOD_CALL));
	} 
		
//			
//
//	}

		
}

