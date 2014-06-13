package oop.ex7.type;

import oop.ex7.main.RegexConfig;


/**
 * This class represents the int variable type. 
 * @author taldovrat
 *
 */
public class IntType extends Type {
	
	//public static final String INPUT_INT = "=( )*(-)?[\\d]+( )*;";

	@Override
	public boolean isExpressionMatch(String check) {
		return check.matches(RegexConfig.INPUT_INT);
	}

	@Override
	public String getRegex() {
		return RegexConfig.INPUT_INT;
	}

	
	
}
