package oop.ex7.type;

import oop.ex7.main.CompileException;

public class BadTypeException extends CompileException{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final String ERROR_MSG ="bad type comparison. line text: "; 
	private String text;
	public BadTypeException(String expression) {
		super(null);
		text=expression;
	}
	
	
	@Override
	public String getLocalizedMessage() {
		return ERROR_MSG+text;
	}
	

}
