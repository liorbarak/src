import java.util.regex.*;

import oop.ex7.type.*;
public class testingTal {

	

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
	 
	
	public static boolean check(Type one, Type two){
		return one.getClass().equals(two.getClass());
	}
	
	public static void main(String[] args) {
//
//		String testStr = "a_a";
//		Pattern p = Pattern.compile(GENERAL_NAME);
//		System.out.println(testStr);
//		Matcher m = p.matcher(testStr);
//		System.out.println(m.matches());

		IntType in1 = new IntType();
		IntType in2 = new IntType();
		BooleanType test = new BooleanType();
		
		System.out.println(check(in1, in2));
		System.out.println(check(in1, test));
		
	} 
		
//			
//
//	}

		
}

