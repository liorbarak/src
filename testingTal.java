import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.*;

import javax.print.DocFlavor.READER;

import oop.ex7.main.CompileException;
import oop.ex7.main.FileParser;
import oop.ex7.main.RegexConfig;
import oop.ex7.main.Variable;
import oop.ex7.scope.Scope;
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
	
	public static String[] getExpressions(Type type, String expression) throws CompileException {

		Pattern p = Pattern.compile(type.getRegex());
		Matcher m = p.matcher(expression);
		String leftExp;
		String rightExp;
		if (m.find()) {
			leftExp = expression.substring(m.start(), m.end());
		}
		else {
			throw new CompileException();
		}
		if (m.find()) {
			rightExp = expression.substring(m.start(), m.end());
		}
		else {
			throw new CompileException();
		}
		
		String[] expressions = {leftExp, rightExp};
		return expressions;
		
		//		String[] strArr = new String[expression.length()];
//		for (int i = 0; i<expression.length(); i++) {
//			strArr[i] = expression.substring(i, i+1);
//			
//		}
//		ArrayList<Integer> operIndexArr = new ArrayList<Integer>(); 
//		for (int j = 0; j<strArr.length; j++) {
//			if (strArr[j].matches(VALID_OPERATOR)) {
////				System.out.println(j);
//				operIndexArr.add(j);
//			}
//		}
//		
//		if(operIndexArr.size()<2) {
//			return expression.split(VALID_OPERATOR);
//		}
//		System.out.println("test");
//		String leftExp = expression.substring(operIndexArr.get(0), operIndexArr.get(1));
//		String rightExp = expression.substring(operIndexArr.get(1)+1);
//		String[] expressions = {leftExp, rightExp};
//		return expressions;
	}

	 
	
	public static int getOper(int a) {
		return a;
	}

//	
//	int[] a = {0};
//	int[] foo(){
//		int[] b = {9};
//		a[8] = b[9];
//		int[] c = {a,56} 
//		return c;
//	}
	
	public static void main(String[] args) throws FileNotFoundException, CompileException {
	
		
		
	} 
		
}

