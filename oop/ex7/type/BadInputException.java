package oop.ex7.type;

import oop.ex7.main.CompileException;

/**
 * This class represents an Exception of type BadInputException. This class is
 * thrown when a non-matching input expression is inserted.
 * @author taldovrat
 *
 */
public class BadInputException extends CompileException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String ERROR_MESSAGE = "temp error messsage with: ";
	//the type variable that the inserted input was bad.
	private Type varType;
	
	
	public BadInputException(Type varType) {	
		this.varType = varType;
	}
	
	@Override
	public String getLocalizedMessage() {
		return ERROR_MESSAGE+varType.toString();
	}
	
}
