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
	
	public static Type createType(VarType type,String varTypeRepresentation,
			String varName) throws BadVarDeclarationException {
		
		switch(type) {
		
		case INT:
			return new Int(varName);
//		case(VarType.DOUBLE.name()):
//		
//		case(VarType.STRING.name()):
//		
//		case(VarType.BOOLEAN.name()):
//		
//		case(VarType.CHAR.name()):
//		
//		case(VarType.ARRAY.name()):
//		
//		case(VarType.VOID.name()):
		default:
			throw new BadVarDeclarationException(varTypeRepresentation);
		}
		
	}
}
