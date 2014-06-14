package oop.ex7.scope;

import oop.ex7.main.CompileException;

public class BadReturnException extends CompileException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ERROR_MSG="return type in bad location:";
	private String text;
	
	public BadReturnException(String lineText){
		text=lineText;
	}
	
	@Override
	public String getLocalizedMessage() {
		return ERROR_MSG+text;
	}

	

}
