package oop.ex7.main;



/**
 * holds static final variables that 
 * @author Lior
 *
 */
public class RegexConfig {
	
	/** Types of Variables - Literal name */
	public static final String TYPE_INT = "[ \t]*int[ \t]*";
	public static final String TYPE_DOUBLE = "[ \t]*double[ \t]*";
	public static final String TYPE_STRING = "[ \t]*String[ \t]*";
	public static final String TYPE_BOOLEAN = "[ \t]*boolean[ \t]*";
	public static final String TYPE_CHAR = "[ \t]*char[ \t]*";
	public static final String TYPE_VOID = "[ \t]*void[ \t]*";
	/** Input Expression that matches a value of a specific type.
	 *  For example: 34 - matches int type, true - matches boolean type. 
	 */
	public static final String INPUT_INT = "[ \t]*(-)?[\\d]+[ \t]*";
	public static final String INPUT_DOUBLE = "([ \t]*(-)?[\\d]+(\\.)([\\d]+)?[ \t]*|"+INPUT_INT+")";
	public static final String INPUT_STRING = "[ \t]*\"[^\"]*\"[ \t]*";
	public static final String INPUT_BOOLEAN = "[ \t]*(true|false)[ \t]*";
	public static final String INPUT_CHAR = "[ \t]*'[^']'[ \t]*";
	public static final String INPUT_VOID = "[ \t]*";

	/** 
	 * Represent some type input value - matches all the different types of
	 * inputs. for example - 34, true - both match - because they are both valid
	 * inputs for a specific type. 
	 */
	public static final String SOME_TYPE_VALUE = "("+INPUT_DOUBLE+"|"+
													INPUT_INT+"|"+
													INPUT_CHAR+"|"+
													INPUT_STRING+"|"+
													INPUT_BOOLEAN+")";	

	/**
	 * Valid types for a variable declaration. For example: int a - matches - 
	 * int is a valid type. void a - doesn't match - void is not a valid 
	 * variable declaration type.  
	 */
	public static final String VALID_TYPES = "[ \t]*(int|double|String|char|boolean)[ \t]*";
	public static final String VALID_TYPES_IN_METHOD = "[ \t]*(int|double|String|char|boolean)[ \t]*(\\[\\])?[ \t]*";
	
	/**
	 * Valid types for a return types of a method. For example: int , void - 
	 * matches - int and void are both valid method return types.  
	 */
	public static final String VALID_TYPES_METHOD = "[ \t]*(int|double|String|char|boolean|void)[ \t]*(\\[\\])?[ \t]*";
	
	/** 
	 * A legal Variable/Method name according to the naming conventions 
	 * mentioned in the exercise instructions
	 */
	
	public static final String VALID_OPERATOR = "[ \t]*[\\+\\/\\-\\*][ \t]*";
	public static final String GENERAL_NAME = "[ \t]*([_][^ \\(\\)\\{\\}\\[\\];]+|[^\\d_\\+-\\/\\*{}\\[\\]; ][^ ,\\[\\]\\{\\};]*)[ \t]*";
	
	public static final String ENDS_WITH_SEMICOLON = "(.*;[ \t]*)$";
	public static final String ENDS_WITH_OPEN_BRACKET = "(.*\\{[ \t]*)$";
	public static final String ENDS_WITH_CLOSED_BRACKET = "(.*\\}[ \t]*)$";
	
	public static final String METHOD_CALL = "[ \t]*"+GENERAL_NAME+"[ \t]*\\([ \t]*[\\d]*[\\D]*[ \t]*\\)[ \t]*;?";
	
	
	
	public static final String OPERATOR_EXP ="("+GENERAL_NAME+"|"+SOME_TYPE_VALUE+"|"+METHOD_CALL+")"+VALID_OPERATOR+"("+GENERAL_NAME+"|"+SOME_TYPE_VALUE+"|"+METHOD_CALL+")";
	public static final String VALID_EXP = "("+GENERAL_NAME+"|"+SOME_TYPE_VALUE+"|"+METHOD_CALL+"|"+OPERATOR_EXP+")";
	
	public static final String ARRAY_INIT="[ \t]*[{](("+VALID_EXP+",[ \t]*)*("+VALID_EXP+")[ \t]*|"+VALID_EXP+"?)*[}][ \t]*";//right of equals-only brackets {}
	
	public static final String VALID_EXP_WITH_ARRAY = "("+VALID_EXP+"|[ \t]*[{][ \t]*("+VALID_EXP+")?[}]|[ \t]*[{][ \t]*["+VALID_EXP+",]+[ \t]*"+VALID_EXP+"[}])";
	public static final String VALID_EXP_JUST_ARRAY = "("+GENERAL_NAME+"|"+METHOD_CALL+"|"+ARRAY_INIT+")";
	//public static final String VALID_EXP3 = "("+VALID_EXP2+"|[{][}])";
	public static final String BLANK_LINE = "[ \t]*";
	public static final String COMMENT = "$[ \t]*//";

	public static final String VALID_IF_CALL ="[ \t]*if[ \t]*[(][ \t]*"+VALID_EXP+"[ \t]*[)][ \t]*[{][ \t]*";
	public static final String VALID_WHILE_CALL="[ \t]*while[ \t]*[(][ \t]*"+VALID_EXP+"[ \t]*[)][ \t]*[{][ \t]*";
	
	public static final String TYPE_PLUS_VAR = "[ \t]*"+VALID_TYPES_IN_METHOD+"( )+"+GENERAL_NAME+"[ \t]*"; 
	public static final String VALID_METHOD_DECLARE = "[ \t]*"+VALID_TYPES_METHOD+"[ ]+"+GENERAL_NAME+"[ \t]*[(]([ \t]*("+TYPE_PLUS_VAR+")?[ \t]*|("+TYPE_PLUS_VAR+",)+"+TYPE_PLUS_VAR+")[)][ \t]*[{][ \t]*";
	public static final String ARR_TYPE = VALID_TYPES+"\\[\\][ \t]*";
	
	
	
	
	
	//array shits
//	public static String ARRAY_INIT="[ \t]*[{](("+VALID_EXP+",[ \t]*)*("+VALID_EXP+")[ \t]*|"+VALID_EXP+"?)*[}][ \t]*";//right of equals-only brackets {}
	public static String ARRAY_DECLARE= VALID_TYPES+"(\\[\\])[ \t]+"+GENERAL_NAME+"[ \t]*";//left of equals 
	public static String ARRAY_DECLARE_WITH_SEMICOLON=ARRAY_DECLARE+";[ \t]*";//just declare
//	public static String ARRAY_DECLARE_AND_ASSIGN= ARRAY_DECLARE+"[ \t]*=[ \t]*"+ARRAY_INIT+"[ \t]*;[ \t]*" ;//complete "int[]	 q={1,4,a};"
	public static String ARRAY_DECLARE_AND_ASSIGN= ARRAY_DECLARE+"[ \t]*=[ \t]*"+VALID_EXP_JUST_ARRAY+"[ \t]*;[ \t]*" ;//complete "int[]	 q={1,4,a};"
	public static final String TYPE_ARRAY = VALID_TYPES+"\\[\\][ \t]*";
	public static final String ARR_VAR = GENERAL_NAME+"\\["+VALID_EXP+"\\][ \t]*;[ \t]*";
	public static final String RETURN_METHOD = VALID_EXP_WITH_ARRAY+"[ \t]*";

	public static final String ARRAY_DECLARE_BLANK=ARRAY_DECLARE+"[ \t]*=[ \t]*[{][ \t]*[}][ \t]*;[ \t]*";
	
	public enum lineType {
		
		DECLARATION ("([ \t]*"+VALID_TYPES+"[ ]+"+GENERAL_NAME+"[ \t]*;[ \t]*|"+ARRAY_DECLARE_WITH_SEMICOLON+")"),
		ASSIGNMENT ("[ \t]*"+GENERAL_NAME+"[ \t]*=[ \t]*"+VALID_EXP+"[ \t]*;[ \t]*"), 
		ASSIGNMENT_ARRAY ("[ \t]*"+GENERAL_NAME+"[ \t]*\\["+VALID_EXP_WITH_ARRAY+"\\][ \t]*=[ \t]*"+VALID_EXP_WITH_ARRAY+"[ \t]*;[ \t]*"),
		BOTH ("[ \t]*"+VALID_TYPES+"[ ]+"+GENERAL_NAME+"[ \t]*=[ \t]*"+VALID_EXP+"[ \t]*;[ \t]*"),
		BOTH_ARRAY (ARRAY_DECLARE_AND_ASSIGN),
		RETURN ("([ \t]*return( )+"+VALID_EXP_WITH_ARRAY+"[ \t]*;[ \t]*|[ \t]*return[ \t]*;[ \t]*)"); 
		

		private String regex;

		lineType(String regex) {
			this.regex = regex;
		}
		public String getRegex() {
			return this.regex;
		}
	}
	
	//TODO remove before sending
	//used for testing
	
	public static void main(String[] args) {
		

		DEBUGRegex(VALID_EXP_WITH_ARRAY,"{b}");
//		DEBUGRegex(lineType.RETURN.getRegex(),"return  {b} ; ");



		
	}
	
	
	public static void DEBUGRegex(String regex,String expressionToCheck){
		System.out.println(expressionToCheck.matches(regex));
		System.out.println("regex: "+regex);
		System.out.println("String: "+expressionToCheck);
	}
}
