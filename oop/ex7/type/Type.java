package oop.ex7.type;

import oop.ex7.main.CompileException;
import oop.ex7.main.RegexConfig;


/**
 * This interface represents the type of the variable in the code - int,
 * double, String, boolean, char, an array of one of these types and the return
 * type of a method void. This code will be checked and compared to the
 * constants in RegexConfig to detect which type of variable is used in the code. 
 * @author taldovrat
 *
 */
public abstract class  Type {



	/**
	 * checks if a String expression is of the correct kind for
	 * a specific type
	 * @param check-input expression to be tested
	 * @return true if the expression matches
	 */
	public abstract boolean isExpressionMatch(String check);

	
	
	/**
	 * returns the regex compatible with a specific type. the actual string
	 * can be used if an external method wants to compare regexes by itself
	 * @return a regex value
	 */
	public abstract String getRegex();


/**
 * static method.
 * checks which type an expression matches and return the initialized
 * type
 * @param check- expression to examine
 * @return initialized sub type
 * @throws 
 */
	public static Type createType(String check) throws CompileException{//TODO create specific exception


		if (check.matches(RegexConfig.TYPE_INT)){
			return new IntType();
		}

		if (check.matches(RegexConfig.TYPE_DOUBLE)){
			return new DoubleType();
		}
		if (check.matches(RegexConfig.TYPE_STRING)){ 
			return new StringType();
		}
		if (check.matches(RegexConfig.TYPE_BOOLEAN)){ 
			return new BooleanType();
		}	

		if (check.matches(RegexConfig.TYPE_CHAR)){ 
			return new CharType();
		}

		if (check.matches(RegexConfig.TYPE_VOID)){
			return new VoidType();
		}	

		if (check.matches(RegexConfig.TYPE_ARRAY)) {
			return new ArrayType(check.replaceAll("\\[\\]", ""));
		}

		throw new CompileException();//throw exception because not a valid type string//TODO make more specific

	}

	public boolean sameType(Type target){
		return this.getClass().equals(target.getClass());
	}



}
