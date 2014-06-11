package oop.ex7.type;

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
	//public enum VarType {INT, DOUBLE, STRING, BOOLEAN, CHAR, ARRAY, VOID};
	
	public static final String INT = "int";
	public static final String DOUBLE = "double";
	public static final String STRING = "String";
	public static final String BOOLEAN = "boolean";
	public static final String CHAR = "char";
	public static final String VOID = "void";
	
	public static Type createType(String varTypeRepresentation,
			String varName) throws BadVarDeclarationException {
		
		switch(varTypeRepresentation) {
		
		case (INT): return new Int(varName);
		
//		case(DOUBLE):
//			return new Int(varName);
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
