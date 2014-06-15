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
	
	private static final String ERROR_MESSAGE =
			"expression tested does not match the specified type : ";
	private Type varType;
	
	/**
	 * constructor
	 * 
	 * @param varType-a type of variable that failed the program
	 */
	public BadInputException(Type varType) {	
		this.varType = varType;
	}
	
	/**
	 * @Override
	 * @returns a message specific for this error
	 */
	public String getLocalizedMessage() {
		return ERROR_MESSAGE+varType.toString();
	}
	
}
