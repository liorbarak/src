package oop.ex7.scope;



import oop.ex7.main.CompileException;

/**
 * extends CompileException
 * is called when trying to create an illegal scope.
 * for instance creating a method inside an 'if' scope
 *
 */
public class InvalidScopeException extends CompileException {

	private static String ERROR_MSG=
			" was trying to create an illegal kind of scope in line: ";
	private static final long serialVersionUID = 1L;
	
	/**
	 * constructor
	 * @param lineNum- line number where the exception happened
	 */
	public InvalidScopeException(int lineNum){
		super(lineNum);
	}
	
	public String getLocalizedMessage() {
		return ERROR_MSG + lineNumber;
	}
	

}
