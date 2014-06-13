package oop.ex7.type;

import oop.ex7.main.RegexConfig;

public class DoubleType extends Type {

	
//	public static final String INPUT_INT = "=( )*(-)?[\\d]+( )*;";
//	public static final String INPUT_DOUBLE = "("+INPUT_INT+"|=( )*(-)?[\\d]+.[\\d]+( )*;)";
	
	@Override
	public boolean isExpressionMatch(String check) {
		return check.matches(RegexConfig.INPUT_DOUBLE);
		
	}

	@Override
	public String getRegex() {
		return RegexConfig.INPUT_DOUBLE;
	}

}
