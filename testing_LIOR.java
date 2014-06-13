
public class testing_LIOR {



	
	public static final String VALID_IF_CALL ="( )*if( )*[(].+[)]( )*[{]";
	
	
	public static final String VALID_TYPES = "( )*(int|double|String|char|boolean)( )*";
	public static final String GENERAL_NAME = "[_]?[^ ]+( )*";
	
	
	public static final String TYPE_PLUS_VAR = "( )*"+VALID_TYPES+"( )+"+GENERAL_NAME+"( )*"; 
	public static final String VALID_METHOD_DECLARE="( )*"+VALID_TYPES+"( )+"+GENERAL_NAME+"( )*[(]"+(TYPE_PLUS_VAR+",")+"*"+TYPE_PLUS_VAR+"[)]( )*[{]( )*";

	public enum vars {INT , STUFF, CHAR };
	/**
	 * @param args
	 */
	
	
	public static void main(String[] args) {

		
		
		//String test="int abc (int a, int b, char zxcv) {";
		String test="char a";
		System.out.println(test.matches(TYPE_PLUS_VAR));

	}

}


