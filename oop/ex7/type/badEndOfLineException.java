package oop.ex7.type;

import oop.ex7.main.CompileException;

/**
 * extends CompileException
 * used when a line does not end with a { or a ; as it should
 * 
 * @author Lior
 *
 */
public class BadEndOfLineException extends CompileException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String ERROR_MSG= 
			"line does not end with '{' or ';'. line number: ";
	private String text;
	
	/**
	 * constructor
	 * @param line-number of line
	 * @param lineText-String representing a line in the file
	 */
	public BadEndOfLineException(int line, String lineText){
		super(line);
		text=lineText;
	}
	
	/**
	 * 
	 * @return String- a specialized message for this exception
	 */
	public String getLocalizedString(){
		return ERROR_MSG+lineNumber+" .line text: "+text;
	}
}
