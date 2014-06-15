package oop.ex7.type;

import oop.ex7.main.RegexConfig;

/**
 * this class represents a variable CharType
 * @author Lior
 *
 */
public class CharType extends Type {

	
	/**@Override
	 * checks if a String expression is of the correct kind for CharType
	 * @param check-input expression to be tested
	 * @return true if the expression matches DoubleType
	 */
	public boolean isExpressionMatch(String check) {
		return check.matches(RegexConfig.INPUT_CHAR);
	}


	/**@Override
	 * returns the regex compatible with a CharType
	 * can be used if an external method wants to compare regexes by itself
	 * @return a regex value
	 */
	public String getRegex() {
		return RegexConfig.INPUT_CHAR;
	}

	
	/**@Override
	 * checks if a String expression is of the correct kind for __________
	 * @param check-input expression to be tested
	 * @return true if the expression matches DoubleType
	 */


	/**@Override
	 * returns the regex compatible with a ___________
	 * can be used if an external method wants to compare regexes by itself
	 * @return a regex value
	 */
}
