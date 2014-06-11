
import java.util.regex.*;

public class TempRegex {
	
	public static final String TYPE_INT = "( )*int( )*";
//	public static final String IGNORE_VARS = "[^int][^String]^[double][^char]"
//											+ "[^boolean][^void][^return]";
	
	public static final String IGNORE_VARS = "[^int]";
	public static final String GENERAL_NAME = "[_]?[^_ ]+( )*";
	public static final String INPUT_INT = "(;|=( )*(-)?[\\d]+;)";
	
	public static final String intRegexExp = TYPE_INT+IGNORE_VARS+
											GENERAL_NAME+INPUT_INT;
	
	public void intDeclaration(String lineToCheck) throws BadVarDeclarationException{
		
		Pattern declarationLine = Pattern.compile(intRegexExp);
		
		Matcher matcher = declarationLine.matcher(lineToCheck);
		
		if (!matcher.matches()) {
			throw new BadVarDeclarationException(lineToCheck);
		}
		
	}
	
	
	
	public static void main(String[] args) {
//		TempRegex a = new TempRegex();
//		try {
//			a.intDeclaration("int  int= 3;");
//		}
//		catch(BadVarDeclarationException ex) {
//			System.out.println(ex.getLocalizedMessage());
//		}
		
		String regexTest = TYPE_INT+GENERAL_NAME+INPUT_INT;//+"|"+TYPE_INT+GENERAL_NAME+INPUT_INT;
		Pattern declarationLine = Pattern.compile(regexTest);
		
		Matcher matcher = declarationLine.matcher("int int = 3;");
		
		if (!matcher.matches()) {
			System.out.println("a");
		}
	}
}
