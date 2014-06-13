package oop.ex7.main;



/**
 * holds static final variables that 
 * @author Lior
 *
 */
public class RegexConfig {

	//	public static final String INT = "int";
	//	public static final String DOUBLE = "double";
	//	public static final String STRING = "String";
	//	public static final String BOOLEAN = "boolean";
	//	public static final String CHAR = "char";
	//	public static final String VOID = "void";


	//not necessarily needed. 
//	public enum lineType {
//		DECLARATION (validTypes+"[ ]+"+GENERAL_NAME+"[ ]*;[ ]*"),
//		ASSIGNMENT (GENERAL_NAME+"[ ]*=[ ]*"+VALID_EXP+"[ ]*;[ ]*"), 
//		BOTH (),
//		RETURN ();
//
//		private static final String regex;
//
//		lineType(String regex) {
//			this.regex = regex;
//		}
//		public String getRegex() {
//			return regex;
//		}
//	}



	public static final String VALID_TYPES = "( )*(int|double|String|char|boolean)( )*";
	public static final String GENERAL_NAME = "( )*([_][^ ]+|[^\\d_][^ ]*)( )*";
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

	public static final String METHOD_CALL = "( )*"+GENERAL_NAME+"( )*\\(( )*[\\d]*[\\D]*( )*\\)( )*;?";


	public static final String VALID_EXP = "("+GENERAL_NAME+"|"+SOME_TYPE_VALUE+"|"+METHOD_CALL+")";

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


	public static final String VALID_IF_CALL ="( )*if( )*[(]( )*"+VALID_EXP+"( )*[)]( )*[{]( )*";
	public static final String VALID_WHILE_CALL="( )*while( )*[(]( )*"+VALID_EXP+"( )*[)]( )*[{]( )*";
//	public static final String TYPE_PLUS_VAR = "( )*"+VALID_TYPES+"( )+"+GENERAL_NAME+"( )*"; 
//	public static final String VALID_METHOD_DECLARE="( )*"+VALID_TYPES+"( )+"+GENERAL_NAME+"( )*[(]"+(TYPE_PLUS_VAR+",")+"*"+TYPE_PLUS_VAR+"[)]( )*[{]( )*";

	
	public static final String TYPE_PLUS_VAR = "( )*"+VALID_TYPES+"( )+"+GENERAL_NAME+"( )*"; 
	public static final String VALID_METHOD_DECLARE="( )*"+VALID_TYPES+"( )+"+GENERAL_NAME+"( )*[(]"+"("+TYPE_PLUS_VAR+","+")"+"*"+TYPE_PLUS_VAR+"[)]( )*[{]( )*";
	public static final String VALID_OPERATOR = "[+-/*]";
	
	



	public static void main(String[] args) {
//		String test = "aff (as);";
//		String test = "int  (){";
		
		System.out.println(test.matches(VALID_WHILE_CALL));
	}
}
