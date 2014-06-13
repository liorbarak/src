import java.util.ArrayList;


public class testing_LIOR {


	public static final String TYPE_INT = "( )*int( )*";
	public static final String INPUT_INT = "=( )*(-)?[\\d]+( )*;";

	public static final String TYPE_DOUBLE = "( )*double( )*";
	public static final String INPUT_DOUBLE = "("+INPUT_INT+"|=( )*(-)?[\\d]+.[\\d]+( )*;)";

	
	


	public static final String VALID_IF_CALL ="( )*if( )*[(].+[)]( )*[{]";


	public static final String VALID_TYPES = "( )*(int|double|String|char|boolean)( )*";
	public static final String GENERAL_NAME = "[_]?[^ ]+( )*";


	public static final String TYPE_PLUS_VAR = "( )*"+VALID_TYPES+"( )+"+GENERAL_NAME+"( )*"; 
	public static final String VALID_METHOD_DECLARE="( )*"+VALID_TYPES+"( )+"+GENERAL_NAME+"( )*[(]"+"("+TYPE_PLUS_VAR+","+")"+"*"+TYPE_PLUS_VAR+"[)]( )*[{]( )*";
	public static final String VALID_OPERATOR = "[+-/*]";

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


		ArrayList<Integer> abc=new ArrayList<>();
		abc.add(10);
		abc.add(11);
		abc.add(12);
		abc.add(13);
		System.out.println(abc.size());
		for (int i=0;i<abc.size(); i++){
			System.out.println(i);
		}



	}

	

}


