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

	
	public static final String VALID_TYPES = "[ \t]*(int|double|String|char|boolean)[ \t]*";
	public static final String VALID_EXP = "("+GENERAL_NAME+"|"+SOME_TYPE_VALUE+"|"+METHOD_CALL+")";
	
	
	public static String ARRAY_INPUT="[ \t]*[{]("+VALID_EXP+",)*("+VALID_EXP+")?[}][ \t]*";//without ;
	public static String ARRAY_DECLARE= VALID_TYPES+"(\\[\\])[ \t]+"+GENERAL_NAME+"[ \t]*";//
	public static String ARRAY_REALY_DECLARE=ARRAY_DECLARE+";[ \t]*";//
	public static String ARRAY_DECLARE_AND_ASSIGN= ARRAY_DECLARE+"[ \t]*=[ \t]*"+ARRAY_INPUT+"[ \t]*;[ \t]*" ;//complete "int[]	 q={1,4,a};"

	
//public static String ARRAY_ASSIGN=GENERAL_NAME+"\\["+VALID_EXP + ;

	
	public static void main(String[] args) {

//		boolean b= true;
		// {1,3,5};
		int[] q ;

		//return new ArrayType(createType(check.replaceAll("[]", "")));
		
		String test= "int[]	 q={1,4,a};";
		//System.out.println(test.replaceAll("( )*\\[\\]", ""));
		
		
		System.out.println(test.matches(ARRAY_DECLARE_AND_ASSIGN));
		

	}

	

}


