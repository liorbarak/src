package oop.ex7.type;
/**
 * This class represents an Exception of type BadVarDeclaration. This class is
 * thrown when a variable declaration is bad.
 * @author taldovrat
 *
 */
public class BadVarDeclarationException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String ERROR_MESSAGE = "temp error messsage with: ";
	private String badDeclaration;
	
	public BadVarDeclarationException(String badDeclaration) {
		this.badDeclaration = badDeclaration;
	}
	
	@Override
	public String getLocalizedMessage() {
		return ERROR_MESSAGE+this.badDeclaration;
	}
	
	//for testing
	public static void main(String[] args) {
		
//		Exception a = new Exception();
	
	}
}
