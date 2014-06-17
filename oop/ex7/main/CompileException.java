package oop.ex7.main;

/**
 * extens Exception
 * this exception is the main one in this exercise
 * all compile exception exceptions extend it
 * the olny ones that don't inherit from it are the ones
 * that handle IO errors or file opening errors
 *
 */
public class CompileException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String ERROR_MESSAGE = "general compile error";
	protected Integer lineNumber=null;

	/**
	 * empty default constructor
	 */
	public CompileException(){
	}
	
	/**
	 * COnstructor
	 * @param num- when the line number for the exception is 
	 * known it is passed here
	 */
	public CompileException(Integer num){
		lineNumber=num;
		}
	
	/**
	 * @Override
	 * return String-localized message for this specific exception
	 */
	public String getLocalizedMessage() {
		return ERROR_MESSAGE+lineNumber;
	}
	
	/**
	 * allows to set line number
	 * @param num-input line number
	 */
	public void setLineNum(int num){
		lineNumber=num;
	}

}

