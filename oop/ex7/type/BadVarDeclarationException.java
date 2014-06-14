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
	
	private static final String ERROR_MESSAGE = "temp error messsage with: ";
	private String badDeclaration;
	
	public BadVarDeclarationException(Integer num,String badDeclaration) {
		super(num);
		this.badDeclaration = badDeclaration;
	}
	
	@Override
	public String getLocalizedMessage() {
		return ERROR_MESSAGE+this.badDeclaration;
	}
	
}
