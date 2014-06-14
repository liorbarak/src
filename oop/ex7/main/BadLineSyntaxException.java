package oop.ex7.main;

/**
 * 
 * @author Lior
 *
 */
public class BadLineSyntaxException extends CompileException {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String ERROR_MESSAGE = "general structure error in line ";
	
	//private int lineNumber;
	private String lineText;
	
	public BadLineSyntaxException(Integer num,String lineText){
		super(num);
		//lineNumber=num;
		this.lineText=lineText;
	}
	
	@Override
	public String getLocalizedMessage() {
		return ERROR_MESSAGE+super.lineNumber+": "+lineText;
	}

}
