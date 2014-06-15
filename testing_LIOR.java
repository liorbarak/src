import oop.ex7.type.ArrayType;

import org.junit.*;
public class testing_LIOR {

	
	public static final String GENERAL_NAME = "( )*([_][^ ]+|[^\\d_\\+-\\/\\*][^ ,]*)( )*";

	public static final String INPUT_INT = "( )*(-)?[\\d]+( )*";
	public static final String INPUT_DOUBLE = "("+INPUT_INT+"|( )*(-)?[\\d]+.([\\d]+)?( )*)";
	public static final String INPUT_STRING = "( )*\"[^\"]*\"( )*";
	public static final String INPUT_BOOLEAN = "( )*(true|false)( )*";
	public static final String INPUT_CHAR = "( )*'[^']'( )*";
	public static final String INPUT_VOID = "( )*";

	public static final String METHOD_CALL = "( )*"+GENERAL_NAME+"( )*\\(( )*[\\d]*[\\D]*( )*\\)( )*;?";
	
	

	public static final String SOME_TYPE_VALUE = "("+INPUT_INT+"|"+
			INPUT_DOUBLE+"|"+
			INPUT_CHAR+"|"+
			INPUT_STRING+"|"+
			INPUT_BOOLEAN+")";	

	

	
	public static final String VALID_EXP = "("+GENERAL_NAME+"|"+SOME_TYPE_VALUE+"|"+METHOD_CALL+")";
	public static String ARRAY_INPUT="[{]("+VALID_EXP+",)*("+VALID_EXP+")?[}]";

	public static String RETURN_VAL =("( )*return( )*"+VALID_EXP+"( )*;( )*");

	
	public static void main(String[] args) {

		
		//return new ArrayType(createType(check.replaceAll("[]", "")));
		
		String test= "return1;";
		//System.out.println(test.replaceAll("( )*\\[\\]", ""));
		
		
		System.out.println(test.matches(RETURN_VAL));
		

	}

	

}


