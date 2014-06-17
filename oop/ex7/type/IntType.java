package oop.ex7.type;

import oop.ex7.main.RegexConfig;


/**
 * This class represents the integer  type. 
 * @author taldovrat
 *
 */
public class IntType extends Type {
	
	

	/**@Override
	 * checks if a String expression is of the correct kind for IntType
	 * @param check-input expression to be tested
	 * @return true if the expression matches DoubleType
	 */
	public boolean isExpressionMatch(String check) {
		
		return check.matches(RegexConfig.INPUT_INT);
	}

	/**@Override
	 * returns the regex compatible with a IntType
	 * can be used if an external method wants to compare regexes by itself
	 * @return a regex value
	 */
	public String getRegex() {
		return RegexConfig.INPUT_INT;
	}

	

}
