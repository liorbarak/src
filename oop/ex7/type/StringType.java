package oop.ex7.type;

import oop.ex7.main.RegexConfig;

public class StringType extends Type {

	
	//public static final String INPUT_STRING = "=( )*\"[^\"]*\"( )*;";
	
	@Override
	public boolean isExpressionMatch(String check) {
		return check.matches(RegexConfig.INPUT_STRING);
	}

	@Override
	public String getRegex() {
		return RegexConfig.INPUT_STRING;
	}

}
