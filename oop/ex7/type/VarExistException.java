package oop.ex7.type;

import oop.ex7.main.CompileException;

public class VarExistException extends CompileException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String ERROR_MSG="two declerations of the same variable name:";
	private String text;
	public VarExistException(String line) {
		//super(line);
		text=line;
	}
	
	@Override
	public String getLocalizedMessage() {
		return ERROR_MSG+text;//add more details?
	}

}
