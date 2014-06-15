package oop.ex7.type;

import oop.ex7.main.RegexConfig;

/**
 * represents void type.
 * relevant for methods
 * extends Type
 * @author Lior
 *
 */
public class VoidType extends Type {

	/**@Override
	 * checks if a String expression is of the correct kind for VoidType
	 * @param check-input expression to be tested
	 * @return true if the expression matches DoubleType
	 */
	public boolean isExpressionMatch(String check) {
		if (check.matches(RegexConfig.INPUT_VOID)) {
			return true;
		}
		return false;
	}

	/**@Override
	 * returns the regex compatible with a VoidType
	 * can be used if an external method wants to compare regexes by itself
	 * @return a regex value
	 */
	public String getRegex() {
		return RegexConfig.INPUT_VOID;
	}

}
