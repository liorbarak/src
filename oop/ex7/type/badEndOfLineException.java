package oop.ex7.type;

import oop.ex7.main.CompileException;

public class BadEndOfLineException extends CompileException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String ERROR_MSG= "line does not end with '{' or ';'. line number: ";
	//private int lineNumber;
	private String text;
	
	public BadEndOfLineException(int line, String lineText){
		super(line);
		text=lineText;
	}
	
	public String getLocalizedString(){
		return ERROR_MSG+lineNumber+" .line text: "+text;
	}
}
