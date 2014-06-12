package oop.ex7.type;


/**
 * This class represents the int variable type. 
 * @author taldovrat
 *
 */
public class IntType extends Type {
	
	public static final String INPUT_INT = "=( )*(-)?[\\d]+( )*;";

	@Override
	public boolean checkExpression(String check) {
		return check.matches(INPUT_INT);
	}

	@Override
	public String getRegex() {
		return INPUT_INT;
	}

	
	
}
