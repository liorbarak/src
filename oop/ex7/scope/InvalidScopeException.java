package oop.ex7.scope;

import java.io.ObjectInputStream.GetField;

public class InvalidScopeException extends Exception {

	private static String ERROR_MSG=" was trying to create an illegal kind of scope in line: ";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int lineNum;
	
	public InvalidScopeException(int lineNum){
		this.lineNum=lineNum;
	}
	
	public String getLocalizedMessage() {
		return ERROR_MSG + lineNum;
	}
	

}
