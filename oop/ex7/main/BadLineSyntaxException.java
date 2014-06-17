package oop.ex7.main;

/**
 * extends CompileException
 * used when there is problem with the general syntax of the line
 * for example it isnt a legal "if" statements, or not a legal array
 * declaration
 * @author Lior
 *
 */
public class BadLineSyntaxException extends CompileException {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String ERROR_MESSAGE = "general structure error in line ";	
	private String lineText;
	
	/**
	 * Constructor
	 * @param lineText- the text of the bad line
	 */
	public BadLineSyntaxException(String lineText){
		this.lineText=lineText;
	}
	
	/**
	 * @Override
	 * return- localized message specific for this exception
	 */
	public String getLocalizedMessage() {
		return ERROR_MESSAGE+super.lineNumber+": "+lineText;
	}

}
