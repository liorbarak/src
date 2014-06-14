package oop.ex7.scope;

import java.io.ObjectInputStream.GetField;

import oop.ex7.main.CompileException;

public class InvalidScopeException extends CompileException {

	private static String ERROR_MSG=" was trying to create an illegal kind of scope in line: ";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//private int lineNum;
	
	public InvalidScopeException(int lineNum){
		super(lineNum);
	}
	
	public String getLocalizedMessage() {
		return ERROR_MSG + lineNumber;
	}
	

}
