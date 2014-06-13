package oop.ex7.type;

import oop.ex7.main.RegexConfig;

public class CharType extends Type {

	
	//public static final String INPUT_CHAR = "=( )*'[^']?'( )*;";
	
	
	@Override
	public boolean isExpressionMatch(String check) {
		return check.matches(RegexConfig.INPUT_CHAR);
	}


	@Override
	public String getRegex() {
		return RegexConfig.INPUT_CHAR;
	}

}
