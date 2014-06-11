package oop.ex7.type;

/**
 * This class represents the int variable type. 
 * @author taldovrat
 *
 */
public class Int implements Type {

	private String varName;
	private boolean isInitialized;
	
	public Int(String varName) { //, boolean isInitialized
		this.varName = varName;
		//this.isInitialized = isInitialized;
	}
	
	@Override
	public void checkType(String strToCheck) throws BadVarDeclarationException {
		if (!strToCheck.equals(TypeFactory.VarType.INT.name())) {
			throw new BadVarDeclarationException(strToCheck);
		}
	}

	@Override
	public void checkExpression() throws BadInputException {

		
	}
	
}
