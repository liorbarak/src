package oop.ex7.type;

import oop.ex7.main.RegexConfig;

public class BooleanType extends Type {

	//public static final String INPUT_BOOLEAN = "=( )*(true|false)( )*;";
	
	@Override
	public boolean isExpressionMatch(String check) {
		return check.matches(RegexConfig.INPUT_BOOLEAN);
	}

	@Override
	public String getRegex() {
		return RegexConfig.INPUT_BOOLEAN;
	}

}
