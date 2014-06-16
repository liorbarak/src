package oop.ex7.scope;

import oop.ex7.main.CompileException;

/**
 * extends CompileException
 * is called when when two methods are declared with the same name
 * 
 */
public class DoubleMethodException extends CompileException{

	private static final long serialVersionUID = 1L;
	private String text;
	private static final String ERROR_MSG= "two methods declared with the same name. line: ";
	
	/**
	 * constructor 
	 * 
	 * @param line- line that caused the exception
	 */
	public DoubleMethodException(String line){
		text=line;
	}
	
	/**
	 * @Override
	 * return String- message unique for this exception
	 */
	public String getLocalizedMessage() {
		return ERROR_MSG+lineNumber+"/n"+text;
	}
	
	
}
