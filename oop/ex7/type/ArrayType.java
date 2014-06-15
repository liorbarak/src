package oop.ex7.type;

import oop.ex7.main.CompileException;

/**
 * extends Type.
 * represents an array of a certain type
 * @author Lior
 *
 */
public class ArrayType extends Type {

	
	/*
	 * array must hold an inner type that determines it's behavior
	 */
	private Type innerType;
	
	/**
	 * empty constructor
	 * used for internal checks of the program
	 */
	public ArrayType() {
		//empty constructor
	}
	
	
	/**
	 * constructor, receives an inner type that the array will hold
	 * @param primitiveType - the type the array contains
	 * @throws CompileException
	 */
	public ArrayType(String primitiveType) throws CompileException{
		innerType = Type.createType(primitiveType);
	}
	
	
	/**@Override
	 * checks if a String expression is of the correct kind for ArrayType
	 * @param check-input expression to be tested
	 * @return true if the expression matches DoubleType
	 */
	public boolean isExpressionMatch(String check) {
		//return check.matches(RegexConfig.ARR_TYPE);
		return innerType.isExpressionMatch(check);
	}

	/**@Override
	 * returns the regex compatible with a ArrayType
	 * can be used if an external method wants to compare regexes by itself
	 * @return a regex value
	 */
	public String getRegex() {
		return innerType.getRegex();
	}

	/**
	 * 
	 * @return inner type used by array
	 */
	public Type getInnerType() {
		return innerType;
	}
	
	

}
