package oop.ex7.scope;

import oop.ex7.main.CompileException;

public class DoubleMethodDecleration extends CompileException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String text;
	private static final String ERROR_MSG= "two methods declared with the same name. line: ";
	
	public DoubleMethodDecleration(int lineNum,String line){
		super(lineNum);
		text=line;
	}
	
	@Override
	public String getLocalizedMessage() {
		return ERROR_MSG+lineNumber+"/n"+text;
	}
	
	
}
