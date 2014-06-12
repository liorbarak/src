package oop.ex7.type;

public class StringType implements Type {

	
	public static final String INPUT_STRING = "=( )*\"[^\"]*\"( )*;";
	
	@Override
	public boolean checkExpression(String check) {
		return check.matches(INPUT_STRING);
	}

	@Override
	public String getRegex() {
		return INPUT_STRING;
	}

}
