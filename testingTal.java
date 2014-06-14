import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.*;

import oop.ex7.main.RegexConfig;
import oop.ex7.type.*;
public class testingTal {
	
	/** Types of Variables - Literal name */
	public static final String TYPE_INT = "( )*int( )*";
	public static final String TYPE_DOUBLE = "( )*double( )*";
	public static final String TYPE_STRING = "( )*String( )*";
	public static final String TYPE_BOOLEAN = "( )*boolean( )*";
	public static final String TYPE_CHAR = "( )*char( )*";
	public static final String TYPE_VOID = "( )*void( )*";
	/** Input Expression that matches a value of a specific type.
	 *  For example: 34 - matches int type, true - matches boolean type. 
	 */
	public static final String INPUT_INT = "( )*(-)?[\\d]+( )*";
	public static final String INPUT_DOUBLE = "("+INPUT_INT+"|( )*(-)?[\\d]+(\\.)([\\d]+)?( )*)";
	public static final String INPUT_STRING = "( )*\"[^\"]*\"( )*";
	public static final String INPUT_BOOLEAN = "( )*(true|false)( )*";
	public static final String INPUT_CHAR = "( )*'[^']'( )*";
	public static final String INPUT_VOID = "( )*";

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
	
	public static final String VALID_OPERATOR = "( )*[\\+\\/\\-\\*]( )*";
	public static final String GENERAL_NAME = "( )*([_][^ ]+|[^\\d_\\+-\\/\\*][^ ,]*)( )*";
	
	public static final String ENDS_WITH_SEMICOLON = "(.*;[ ]*)$";
	public static final String ENDS_WITH_OPEN_BRACKET = "(.*\\{[ ]*)$";
	public static final String ENDS_WITH_CLOSED_BRACKET = "(.*\\}[ ]*)$";
	
	public static final String METHOD_CALL = "( )*"+GENERAL_NAME+"( )*\\(( )*[\\d]*[\\D]*( )*\\)( )*;?";
	
	public static final String VALID_EXP = "("+GENERAL_NAME+"|"+SOME_TYPE_VALUE+"|"+METHOD_CALL+")";
	public static final String BLANK_LINE = "( )*";
	public static final String COMMENT = "$( )*//";

	public static final String VALID_IF_CALL ="( )*if( )*[(]( )*"+VALID_EXP+"( )*[)]( )*[{]( )*";
	public static final String VALID_WHILE_CALL="( )*while( )*[(]( )*"+VALID_EXP+"( )*[)]( )*[{]( )*";
	
	public static final String TYPE_PLUS_VAR = "( )*"+VALID_TYPES+"( )+"+GENERAL_NAME+"( )*"; 
	public static final String VALID_METHOD_DECLARE = "( )*"+VALID_TYPES_METHOD+"( )+"+GENERAL_NAME+"( )*[(]"+"(( )*("+TYPE_PLUS_VAR+")?( )*|(("+TYPE_PLUS_VAR+",)+"+TYPE_PLUS_VAR+"))[)]( )*[{]( )*";
	public static final String ARR_TYPE = null;
	
	public static final String OPERATOR_EXP = VALID_EXP+VALID_OPERATOR+VALID_EXP;;
	
	public static String[] getExpressions(String expression) {
		String[] strArr = new String[expression.length()];
		for (int i = 0; i<expression.length(); i++) {
			strArr[i] = expression.substring(i, i+1);
			
		}
		ArrayList<Integer> operIndexArr = new ArrayList<Integer>(); 
		for (int j = 0; j<strArr.length; j++) {
			if (strArr[j].matches(VALID_OPERATOR)) {
//				System.out.println(j);
				operIndexArr.add(j);
			}
		}
		
		if(operIndexArr.size()<2) {
			return expression.split(VALID_OPERATOR);
		}
		System.out.println("test");
		String leftExp = expression.substring(operIndexArr.get(0), operIndexArr.get(1));
		String rightExp = expression.substring(operIndexArr.get(1)+1);
		String[] expressions = {leftExp, rightExp};
		return expressions;
	}

	
	public static String getOper(Character ch) {
		
		switch(ch) {
		case('-'): return "-";
		case('+'): return "+";
		case('/'): return "/";
		case('*'): return "*";
		default: return null;
		}
	}

	
	public static void main(String[] args) throws FileNotFoundException {
	
//		String tempRegex = "( )*(-)?[\\d]+(\\.)([\\d]+)?( )*";
		
//		String test = "9.2-3.4";
//		String[] expressions = getExpressions(test);
//		for (String s:expressions){
//			System.out.println(s);
//		}
//		String dot = ".";
//		System.out.println(dot.matches(VALID_OPERATOR));
//		 
		
		
//		System.out.println(-9+-3);
//		ArrayList<String> operArr = new ArrayList<String>();
//		char[] test1 = test.toCharArray();
//		for (char ch:test1) {
//			String oper = getOper(ch);
//			if (oper != null) {
//				operArr.add(oper);
//			}
//		}
//		System.out.println(operArr.size());
//		Pattern p = Pattern.compile(RegexConfig.VALID_OPERATOR);
//		Matcher m = p.matcher(test);
////		int numOfGroups = m.groupCount();
//		m.find();
//		m.find();
//		int indexOfOper1 = m.start();
//		int indexOfOper2 = m.end();
//		
//		System.out.println(indexOfOper1);
//		System.out.println(indexOfOper2);
////		System.out.println(numOfGroups);
//		System.out.println(test.matches(VALID_OPERATOR));
//		
		
		File _19 = new File("/Users/taldovrat/Desktop/19.txt");
		File _25 = new File("/Users/taldovrat/Desktop/25.txt");
		Scanner pdf19 = new Scanner(_19);
		Scanner pdf25 = new Scanner(_25);
		
		
		ArrayList<String> pdf19lines = new ArrayList<String>();
		while (pdf19.hasNext()) {
			pdf19lines.add(pdf19.next());
		}
		ArrayList<String> pdf25lines = new ArrayList<String>();
		while (pdf25.hasNext()) {
			pdf25lines.add(pdf25.next());
		}
		
		String line = "ds";
		for (int i = 0;i<pdf19lines.size(); i++) {
			if (!pdf19lines.get(i).equals(pdf25lines.get(i))) {
				
				line = pdf19lines.get(i);
			}
		}
		
		System.out.println(line);
		
		
	} 
		
}

