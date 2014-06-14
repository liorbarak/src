package oop.ex7.main;

public class CompileException extends Exception{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String ERROR_MESSAGE = "compile error. line number: ";
	
	protected Integer lineNumber=null;
	//private String lineText;
	
	public CompileException(){
	}
	
	public CompileException(Integer num){
		lineNumber=num;
		}
	
	@Override
	public String getLocalizedMessage() {
		return ERROR_MESSAGE+lineNumber;
	}
	
	public void setLineNum(int num){
		lineNumber=num;
	}

}

