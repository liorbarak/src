package oop.ex7.type;

import oop.ex7.main.CompileException;

/**
 * extends CompileException
 * is used whan a variable with the same name it declared twice
 * @author Lior
 *
 */
public class VarExistException extends CompileException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String ERROR_MSG=
			"two declerations of the same variable name:";
	private String text;
	
	/**
	 * constructor
	 * @param line- the line that led to the exception
	 */
	public VarExistException(String line) {
		text=line;
	}
	
	/**
	 * @Override
	 * retuens String- a specific error message for this exception
	 */
	public String getLocalizedMessage() {
		return ERROR_MSG+text;
	}

}
