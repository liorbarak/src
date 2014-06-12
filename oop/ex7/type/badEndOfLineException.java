package oop.ex7.type;

public class badEndOfLineException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String ERROR_MSG= "line does not end with '{' or ';'. line number: ";
	private int lineNumber;
	
	public badEndOfLineException(int line){
		lineNumber=line;
	}
	
	public String getLocalizedString(){
		return ERROR_MSG+lineNumber;
	}
}
