import java.io.File;
import java.util.Scanner;

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

	
	public static final String ARR_TYPE = VALID_TYPES+"\\[\\][ \t]*";
	
	
//public static String ARRAY_ASSIGN=GENERAL_NAME+"\\["+VALID_EXP + ;

	
	public static void main(String[] args) {
		
//		try{
//		String location= "C://Users//Lior//Desktop//ex7stuff//sjavac_tests.txt";//location of tester
//		File myFile= new File(location);
//		Scanner scan=new Scanner(myFile);
//		while (scan.hasNext()){
//			System.out.println(scan.nextLine());
//		}
//		
//		}
//		catch(Exception e){
//			System.out.println("exception u idiot");
//		}
		
		System.out.println("askdhasd");
		System.err.println("bla");
		boolean b= true;
		// {1,3,5};
		int[] q ;

		//return new ArrayType(createType(check.replaceAll("[]", "")));
		
		String test= "int[]  " ;
		//System.out.println(test.replaceAll("( )*\\[\\]", ""));
		
		
		System.out.println(test.matches(ARR_TYPE));
		

		
		
	}

	

}


