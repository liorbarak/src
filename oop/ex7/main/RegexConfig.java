package oop.ex7.main;



/**
 * holds static final variables that 
 * @author Lior
 *
 */
public class RegexConfig {
	
	/** Types of Variables - Literal name */
	public static final String TYPE_INT = "( )*int( )*";
	public static final String TYPE_DOUBLE = "( )*double( )*";
	public static final String TYPE_STRING = "( )*String( )*";
	public static final String TYPE_BOOLEAN = "( )*boolean( )*";
	public static final String TYPE_CHAR = "( )*char( )*";
	/** Input Expression that matches a value of a specific type.
	 *  For example: 34 - matches int type, true - matches boolean type. 
	 */
	public static final String INPUT_INT = "( )*(-)?[\\d]+( )*";
	public static final String INPUT_DOUBLE = "("+INPUT_INT+"|( )*(-)?[\\d]+.([\\d]+)?( )*)";
	public static final String INPUT_STRING = "( )*\"[^\"]*\"( )*";
	public static final String INPUT_BOOLEAN = "( )*(true|false)( )*";
	public static final String INPUT_CHAR = "( )*'[^']'( )*";

	/** 
	 * Represent some type input value - matches all the different types of
	 * inputs. for example - 34, true - both match - because they are both valid
	 * inputs for a specific type. 
	 */
	public static final String SOME_TYPE_VALUE = "("+INPUT_INT+"|"+
													INPUT_DOUBLE+"|"+
													INPUT_CHAR+"|"+
													INPUT_STRING+"|"+
													INPUT_BOOLEAN+")";	

	/**
	 * Valid types for a variable declaration. For example: int a - matches - 
	 * int is a valid type. void a - doesn't match - void is not a valid 
	 * variable declaration type.  
	 */
	public static final String VALID_TYPES = "( )*(int|double|String|char|boolean)( )*";
	
	/**
	 * Valid types for a return types of a method. For example: int , void - 
	 * matches - int and void are both valid method return types.  
	 */
	public static final String VALID_TYPES_METHOD = "( )*(int|double|String|char|boolean|void)( )*";
	
	/** 
	 * A legal Variable/Method name according to the naming conventions 
	 * mentioned in the exercise instructions
	 */
	public static final String GENERAL_NAME = "( )*([_][^ ]+|[^\\d_][^ ]*)( )*";
	
	public static final String ENDS_WITH_SEMICOLON = "$( )*;( )*";
	public static final String METHOD_CALL = "( )*"+GENERAL_NAME+"( )*\\(( )*[\\d]*[\\D]*( )*\\)( )*;?";

	public static final String VALID_EXP = "("+GENERAL_NAME+"|"+SOME_TYPE_VALUE+"|"+METHOD_CALL+")";
	public static final String BLANK_LINE = "( )*";
	public static final String COMMENT = "$( )*//";

	public static final String VALID_IF_CALL ="( )*if( )*[(]( )*"+VALID_EXP+"( )*[)]( )*[{]( )*";
	public static final String VALID_WHILE_CALL="( )*while( )*[(]( )*"+VALID_EXP+"( )*[)]( )*[{]( )*";
	
	public static final String TYPE_PLUS_VAR = "( )*"+VALID_TYPES+"( )+"+GENERAL_NAME+"( )*"; 
	public static final String VALID_METHOD_DECLARE="( )*"+VALID_TYPES_METHOD+"( )+"+GENERAL_NAME+"( )*[(]"+"("+TYPE_PLUS_VAR+","+")"+"*"+TYPE_PLUS_VAR+"[)]( )*[{]( )*";
	public static final String VALID_OPERATOR = "[+-/*]";
	
	public enum lineType {
		
		DECLARATION ("( )*"+VALID_TYPES+"[ ]+"+GENERAL_NAME+"[ ]*;[ ]*"),
		ASSIGNMENT ("( )*"+GENERAL_NAME+"[ ]*=[ ]*"+VALID_EXP+"[ ]*;[ ]*"), 
		BOTH ("( )*"+VALID_TYPES+"[ ]+"+GENERAL_NAME+"[ ]*=[ ]*"+VALID_EXP+"[ ]*;[ ]*"),
		RETURN ("( )*return( )*"+VALID_EXP+"( )*;( )*");

		private String regex;

		lineType(String regex) {
			this.regex = regex;
		}
		public String getRegex() {
			return regex;
		}
	}

	
	
}
