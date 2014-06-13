package oop.ex7.type;

import oop.ex7.main.RegexConfig;


/**
 * This interface represents the type of the variable in the code - int,
 * double, String, boolean, char, an array of one of these types and the return
 * type of a method void. This code will be checked and compared to the
 * interface's enums to detect which type of variable is used in the code. 
 * @author taldovrat
 *
 */
public abstract class  Type {
	

	//use constants from regexconfig file
	enum DifferentTypes{
		INT("int"),
	    DOUBLE ("double"),
	    STRING ("String"),
	    BOOLEAN ("boolean"),
	    CHAR ("char"), 
	    VOID ("void");
	    
	    public final String innerString;
		
		DifferentTypes(String a){
			innerString=a;
		}
		@Override
		public String toString() {
			return innerString;
		}
	}
	
	


	/**
	 * checks if a String expression is of the correct type for a specific type
	 * @param check
	 * @return
	 */
	public abstract boolean isExpressionMatch(String check);

	/**
	 * returns the regex compatible with a specific type. the actual string
	 * can be used if an external method wants to compare regexes by itself
	 * @return true if type is ok
	 */
	public abstract String getRegex();
	
	
	//change to switch case
	public static Type createType(String check) throws Exception{//TODO create specific exception
		
		
		if (check.matches(RegexConfig.INPUT_INT)){
			return new IntType();
		}
		
		if (check.matches(RegexConfig.INPUT_DOUBLE)){
			return new DoubleType();
		}
		if (check.matches(RegexConfig.INPUT_STRING)){ 
			return new StringType();
		}
		if (check.matches(RegexConfig.INPUT_BOOLEAN)){ 
			return new BooleanType();
		}	
		
		if (check.matches(RegexConfig.INPUT_CHAR)){ 
			return new CharType();
		}
		
//		if (check.matches(RegexConfig.INPUT_VOID)){ { {// TODO must handle what happens if something is void
//			return new VoidType();
//		}	
//		
		
			throw new Exception();//throw exception because not a valid type string
		
	}
	
	public boolean sameType(Type target){
		return this.getClass().equals(target.getClass());
	}
		


}
