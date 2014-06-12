package oop.ex7.main;

public class EndOfFileException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String ERROR_MSG=
			"end of file reached. problem with scope brackets";
	
	public EndOfFileException(){
	}
	
	public String getLocalizedMessage() {
		return ERROR_MSG;
	}
	
	
	
}
