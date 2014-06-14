import java.beans.Expression;
import java.util.ArrayList;

import oop.ex7.type.BadEndOfLineException;


public class testing_LIOR {


	public static final String TYPE_INT = "( )*int( )*";
	public static final String INPUT_INT = "=( )*(-)?[\\d]+( )*;";

	public static final String TYPE_DOUBLE = "( )*double( )*";
	public static final String INPUT_DOUBLE = "("+INPUT_INT+"|=( )*(-)?[\\d]+.[\\d]+( )*;)";

	
	



	public static final String VALID_TYPES = "( )*(int|double|String|char|boolean)( )*";
	public static final String GENERAL_NAME = "[_]?[^ ]+( )*";


	public static final String TYPE_PLUS_VAR = "( )*"+VALID_TYPES+"( )+"+GENERAL_NAME+"( )*"; 
	public static final String VALID_METHOD_DECLARE="( )*"+VALID_TYPES+"( )+"+GENERAL_NAME+"( )*[(]"+"("+TYPE_PLUS_VAR+","+")"+"*"+TYPE_PLUS_VAR+"[)]( )*[{]( )*";
	public static final String VALID_OPERATOR = "[+-/*]";
	//public static final String VALID_
	
	public enum vars {INT , STUFF, CHAR };
	/**
	 * @param args
	 */


	public static void main(String[] args) {


		//String test="String bli_id (String a, char b, boolean zxcv) {";
		//String test="int abc (int a, int b, char zxcv) {";
		//String test="char a   String b";
		String test= "*+";

		System.out.println(test.matches(VALID_OPERATOR));

		
		
		Integer av;
		System.out.println(vars.CHAR);

		BadEndOfLineException aaa=new BadEndOfLineException(1, "bla");
		System.out.println(aaa.getLocalizedMessage());

	}

	

}


