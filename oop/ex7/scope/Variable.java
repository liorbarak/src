package oop.ex7.scope;
import oop.ex7.type.*;

/**
 * class represents a variable declared by our 'compiler'.
 * only scopes hold and manage variables
 */
public class Variable {

	//the type of variable - int, boolean, exc.
	private Type type;
	private String name;
	//is this variable has been initialized or not.
	private boolean isInitialized;

	/**
	 * constructor
	 * notice - every variable starts as uninitialized
	 * @param type - the type of variable
	 * @param name - the name of the variable
	 * @throws BadTypeException
	 */
	public Variable(String type, String name) throws BadTypeException {

		this.type = Type.createType(type);
		this.name = name;
		this.isInitialized = false;
	}
	/**
	 * Getter
	 * @return the type of this variable
	 */
	public Type getType() {
		return type;
	}

	/**
	 * Getter
	 * @return the name of this variable
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter
	 * @return if this variable is initialized or not.
	 */
	public boolean isInitialized() {
		return isInitialized;
	}

	/**
	 * set the is initialized condition.
	 * @param isInitialized
	 */
	public void setInitialized(boolean isInitialized) {
		this.isInitialized = isInitialized;
	}

	/**
	 * check if the type of the variable is of the same input type.
	 * @param target - the type to compare to.
	 * @return
	 */
	public boolean compareType(Type target) {
		return this.type.sameType(target);
	}
}
