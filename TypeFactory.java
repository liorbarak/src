/**
 * This class creates the different variable types for the different Type 
 * classes implementations. 
 * @author taldovrat
 *
 */
public abstract class TypeFactory {

	/**
	 * The enum type - int, double, String, boolean, char, array or void.
	 */
	public enum VarType {INT, DOUBLE, STRING, BOOLEAN, CHAR, ARRAY, VOID};
	
	public static Type createType(String varTypeRepresentation) 
		throws BadVarDeclarationException {
		
		switch(varTypeRepresentation) {
		
		return "str";
		
		case(VarType.INT):
		
		case(VarType.DOUBLE):
		
		case(VarType.STRING):
		
		case(VarType.BOOLEAN):
		
		case(VarType.CHAR):
		
		case(VarType.ARRAY):
		
		case(VarType.VOID):
		}
		
	}
}
