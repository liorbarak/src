package oop.ex7.type;

import oop.ex7.main.CompileException;


/**
 * extends CompileException
 * used when there is an illegal assignment.
 * a value is being assigned to a variable that does not exist in 
 * any relevant scope.
 * @author Lior
 *
 */
public class VarNotExistException extends CompileException {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ERROR_MSG="variable does not exist:";
	private String text;
	
	/**
	 * constructor
	 * @param line-line in file that caused the exception
	 */
	public VarNotExistException(String line) {
		text=line;
	}
	
	/**
	 * @Override
	 * @return String relevant to this specific exception
	 */
	public String getLocalizedMessage() {
		return ERROR_MSG+text;
	}

	
	
	
	
	
	
	
	
}
