package oop.ex7.type;

import oop.ex7.main.CompileException;

public class VarNotExistException extends CompileException {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ERROR_MSG="variable does not exist:";
	private String text;
	
	
	public VarNotExistException(String line) {
		text=line;
	}
	
	@Override
	public String getLocalizedMessage() {
		return ERROR_MSG+text;
	}

	
	
	
	
	
	
	
	
}
