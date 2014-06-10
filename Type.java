/**
 * This interface represents the type of the variable in the code - int,
 * double, String, boolean, char, an array of one of these types and the return
 * type of a method void. This code will be checked and compared to the
 * interface's enums to detect which type of variable is used in the code. 
 * @author taldovrat
 *
 */
public interface Type {
	
//	/**
//	 * The enum type - int, double, String, boolean, char, array or void.
//	 */
//	public enum VarType {INT, DOUBLE, STRING, BOOLEAN, CHAR, ARRAY, VOID};
	
	@Override
	/**
	 * This method overrides the toString of Object class. 
	 * @return The string representation of the Type: "String", "int", "char", 
	 * exc.
	 */
	public String toString();
	
	/**
	 * This method checks if the input expression in the code matches the 
	 * variable type. For example, if the code has the line: "int a = 3;", the
	 * input expression is the number "3", and in this case the method should
	 * do nothing. if the code contains the line: "int a = "str";, the method
	 * should throw a BadInputException and inform the user about the error. 
	 * @throw BadInputException
	 * @return
	 */
	public void checkExpression() throws BadInputException; 
}
