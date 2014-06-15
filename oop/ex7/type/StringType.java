package oop.ex7.type;

import oop.ex7.main.RegexConfig;

/**
 * class represents String 
 * extends Type
 * @author Lior
 *
 */
public class StringType extends Type {

	
	
	/**@Override
	 * checks if a String expression is of the correct kind for StringType
	 * @param check-input expression to be tested
	 * @return true if the expression matches DoubleType
	 */
	public boolean isExpressionMatch(String check) {
		return check.matches(RegexConfig.INPUT_STRING);
	}


	/**@Override
	 * returns the regex compatible with a StringType
	 * can be used if an external method wants to compare regexes by itself
	 * @return a regex value
	 */
	public String getRegex() {
		return RegexConfig.INPUT_STRING;
	}

}
