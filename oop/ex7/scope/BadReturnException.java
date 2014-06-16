package oop.ex7.scope;

import oop.ex7.main.CompileException;

/**
 * 
 * extends CompileException
 * is called when a return deceleration is in a bad location
 * or the return type and the return expression don't match
 *
 */
public class BadReturnException extends CompileException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ERROR_MSG="return type in bad location:";
	private String text;
	
	/**
	 * constructor
	 * @param lineText-line that caused the error
	 */
	public BadReturnException(String lineText){
		text=lineText;
	}
	
	/**
	 * @Override
	 * return String- error message
	 */
	public String getLocalizedMessage() {
		return ERROR_MSG+text;
	}

	

}
