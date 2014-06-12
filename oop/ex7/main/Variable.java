package oop.ex7.main;
import oop.ex7.type.*;
public class Variable {
	
	private Type type;
	private String name;
	private boolean isInitialized;
	
	public Variable() {
		
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isInitialized() {
		return isInitialized;
	}

	public void setInitialized(boolean isInitialized) {
		this.isInitialized = isInitialized;
	}
	
	
}
