package oop.ex7.type;

/**
 * This interface represents the type of the variable in the code - int,
 * double, String, boolean, char, an array of one of these types and the return
 * type of a method void. This code will be checked and compared to the
 * interface's enums to detect which type of variable is used in the code. 
 * @author taldovrat
 *
 */
public interface Type {
	
	
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
	public boolean checkExpression(String check);
	
}
