package oop.ex7.type;


/**
 * This interface represents the type of the variable in the code - int,
 * double, String, boolean, char, an array of one of these types and the return
 * type of a method void. This code will be checked and compared to the
 * interface's enums to detect which type of variable is used in the code. 
 * @author taldovrat
 *
 */
public abstract class  Type {
	
//	public static final String INT = "int";
//	public static final String DOUBLE = "double";
//	public static final String STRING = "String";
//	public static final String BOOLEAN = "boolean";
//	public static final String CHAR = "char";
//	public static final String VOID = "void";

	
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
	
	
	//	@Override
	//	/**
	//	 * This method overrides the toString of Object class. 
	//	 * @return The string representation of the Type: "String", "int", "char", 
	//	 * exc.
	//	 */
	//	public String toString();
	//	
	//	/**
	//	 * This method checks if the code contains a correct variable declaration.
	//	 * for example, if the code is: "int a;", the method will check if the word
	//	 * int is correct, and in this case the method should do nothing. for
	//	 * example, if the code is: "inti a;", the varibale declaration is 
	//	 * incorrect (inti instead of int) and the method should throw an Exception
	//	 */
	//	public void checkType(String strToCheck) throws BadVarDeclarationException;
	//	
	//	/**
	//	 * This method checks if the input expression in the code matches the 
	//	 * variable type. For example, if the code has the line: "int a = 3;", the
	//	 * input expression is the number "3", and in this case the method should
	//	 * do nothing. if the code contains the line: "int a = "str";, the method
	//	 * should throw a BadInputException and inform the user about the error. 
	//	 * @throw BadInputException
	//	 * @return
	//	 */

	/**
	 * checks if a String expression is of the correct type for a specific type
	 * @param check
	 * @return
	 */
	public abstract boolean checkExpression(String check);

	/**
	 * returns the regex compatible with a specific type. the actual string
	 * can be used if an external method wants to compare regexes by itself
	 * @return
	 */
	public abstract String getRegex();
	
	public Type createType(String check) throws Exception{//TODO create specific exception
		
		
		if (check.equals(DifferentTypes.INT.toString())){
			return new IntType();
		}
		
		if (check.equals(DifferentTypes.DOUBLE.toString())){
			return new DoubleType();
		}
		if (check.equals (DifferentTypes.STRING.toString())) {
			return new StringType();
		}
		if (check.equals (DifferentTypes.BOOLEAN.toString())) {
			return new BooleanType();
		}	
		
		if (check.equals (DifferentTypes.CHAR.toString())) {
			return new CharType();
		}
		
		if (check.equals (DifferentTypes.VOID.toString())) {// TODO must handle what happens if something is void
			return new VoidType();
		}	
		
		
			throw new Exception();//throw exception because not a valid type string
		
	}
		
//		switch(check){
//		case DifferentTypes.INT.toString():
//			return new IntType();
//		case DifferentTypes.DOUBLE.toString():
//			return new DoubleType();
//		case DifferentTypes.STRING.toString() :
//			return new StringType();
//		case DifferentTypes.BOOLEAN.toString() :
//			return new BooleanType();
//		case DifferentTypes.CHAR.toString() :
//			return new CharType();
//		case DifferentTypes.VOID.toString() :
//			return new VoidType();
//		default:
//			throw new Exception();//throw exception because not a valid type string
//			break;
//		}
//		return null;
//	}

}
