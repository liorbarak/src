package oop.ex7.type;

import oop.ex7.main.CompileException;

/**
 * extends CompileException
 * represents an incorrect input for type.
 * i.e. this type was spelled incorrectly or doesn't exist
 * @author Lior
 *
 */
public class BadTypeException extends CompileException{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String ERROR_MSG ="bad type comparison. line text: "; 
	private String text;
	
	/**
	 * constructor
	 * @param expression-the expression tested, was supposed to be a
	 * correct type 
	 */
	public BadTypeException(String expression) {
		text=expression;
	}
	
	
	/**
	 * @Override
	 * return specific error message
	 */
	public String getLocalizedMessage() {
		return ERROR_MSG+text;
	}
	

}
