import java.io.File;
import java.util.ArrayList;


public class mainCompiler {

	enum returnValues{NO_ERRORS,INPUT_ERRORS,CRITICAL_IO_ERRORS};
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		
	try{
		File origin = new File(args[0]);//todo handle throws
		ArrayList<String> fileLines= FileParser.getlinesList(origin);
		
		Class mainClass= new Class(fileLines);
		
		
	}
	catch(Exception e){	//TODO change type of exception
		//some must print 2.
		//all others print 1
	}
		
	
	//if i got this far in the program it means compilation found no errors
	System.out.println(returnValues.NO_ERRORS.ordinal());
	}
	
	
	
	
	
}
