package oop.ex7.scope;
import oop.ex7.type.*;

/**
 * 
 * class represents a variable declared by our 'compiler'.
 * it is used to compare between existing 
 *
 */
public class Variable {

	private Type type;
	private String name;
	private boolean isInitialized;

	public Variable(String type, String name) throws BadTypeException {

		this.type = Type.createType(type);
		this.name = name;
		this.isInitialized = false;
	}

	public Type getType() {
		return type;
	}


	public String getName() {
		return name;
	}

	public boolean isInitialized() {
		return isInitialized;
	}

	public void setInitialized(boolean isInitialized) {
		this.isInitialized = isInitialized;
	}

	public boolean compareType(Type target) {
		return this.type.sameType(target);
	}


}
