package oop.ex7.type;


public class ArrayType extends Type {

	
	/*
	 * array holds an inner type. 
	 * 
	 */
	private Type innerType;
	
	public ArrayType() {
	}
	
	public ArrayType(Type primitiveType){
		innerType=primitiveType;
	}
	
	
	@Override
	public boolean isExpressionMatch(String check) {
		//return check.matches(RegexConfig.ARR_TYPE);
		return innerType.isExpressionMatch(check);
	}

	@Override
	public String getRegex() {
		//return RegexConfig.ARR_TYPE;
		return innerType.getRegex();
	}

	
}
