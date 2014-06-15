package oop.ex7.type;

import oop.ex7.main.CompileException;

/**
 * This class represents an Exception of type BadVarDeclaration. This class is
 * thrown when a variable declaration is bad.
 * @author taldovrat
 *
 */
public class BadVarDeclarationException extends CompileException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String ERROR_MESSAGE =
			"error declaring a variable. expression: ";
	private String badDeclaration;
	
	/**
	 * constructor
	 * @param badDeclaration- the incorrect string representing a 
	 * bad attempt to declare a variable
	 */
	public BadVarDeclarationException(String badDeclaration) {
		this.badDeclaration = badDeclaration;
	}
	
	/**
	 * @Override
	 * returns a specific error message
	 */
	public String getLocalizedMessage() {
		return ERROR_MESSAGE+this.badDeclaration;
	}
	
}
