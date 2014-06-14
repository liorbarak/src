package oop.ex7.type;

import oop.ex7.main.RegexConfig;

public class VoidType extends Type {

	@Override
	public boolean isExpressionMatch(String check) {
		if (check.matches(RegexConfig.INPUT_VOID)) {
			return true;
		}
		return false;
	}

	@Override
	public String getRegex() {
		return RegexConfig.INPUT_VOID;
	}

}
