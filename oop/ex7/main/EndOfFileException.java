package oop.ex7.main;


/**
 * Extens CompileException
 * 
 * this error happens when running over the unexpectedly hits the EOF
 * usually this happens when there is a problem with scope brackets{}  
 * @author Lior
 *
 */
public class EndOfFileException extends CompileException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String ERROR_MSG=
			"end of file reached. probably problem with scope brackets";
	
	/**
	 * empty constructor
	 */
	public EndOfFileException(){
	}
	
	
	/**
	 * return- Stringlocalized message for the eXception
	 */
	public String getLocalizedMessage() {
		return ERROR_MSG;
	}
	
	
	
}
