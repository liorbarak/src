package oop.ex7.type;

public class CharType implements Type {

	
	public static final String INPUT_CHAR = "=( )*'[^']?'( )*;";
	
	
	@Override
	public boolean checkExpression(String check) {
		return check.matches(INPUT_CHAR);
	}


	@Override
	public String getRegex() {
		return INPUT_CHAR;
	}

}
