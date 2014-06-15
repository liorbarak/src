package oop.ex7.type;

import oop.ex7.main.CompileException;


public class ArrayType extends Type {

	
	/*
	 * array holds an inner type. 
	 * 
	 */
	private Type innerType;
	
	public ArrayType() {
	}
	
	public ArrayType(String primitiveType) throws CompileException{
		innerType = Type.createType(primitiveType);
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

	public Type getInnerType() {
		return innerType;
	}
	
}
