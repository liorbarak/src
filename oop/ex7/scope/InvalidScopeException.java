package oop.ex7.scope;

import java.io.ObjectInputStream.GetField;

public class InvalidScopeException extends Exception {

	private static String ERROR_MSG=" was trying to create an illegal kind of scope: ";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Scope innerScope;
	
	public InvalidScopeException(Scope triedToCreate){
		innerScope=triedToCreate;
	}
	
	public String getLocalizedMessage() {
		return ERROR_MSG + innerScope.toString();//what is the tostring here?
	}
	

}
