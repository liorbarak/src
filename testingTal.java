import java.io.File;
import java.util.regex.*;

import oop.ex7.main.RegexConfig;
import oop.ex7.type.*;
public class testingTal {
	
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

	
	
	
	public static void main(String[] args) {
	
		String tempRegex = "( )*(-)?[\\d]+(\\.)([\\d]+)?( )*"; 
		String test = "-9+0";
		System.out.println(test);
		System.out.println(test.matches(SOME_TYPE_VALUE));
		
	} 
		
}

