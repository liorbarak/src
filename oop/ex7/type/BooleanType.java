package oop.ex7.type;

public class BooleanType extends Type {

	public static final String INPUT_BOOLEAN = "=( )*(true|false)( )*;";
	
	@Override
	public boolean isExpressionMatch(String check) {
		return check.matches(INPUT_BOOLEAN);
	}

	@Override
	public String getRegex() {
		return INPUT_BOOLEAN;
	}

}
