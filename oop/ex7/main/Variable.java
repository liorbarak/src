package oop.ex7.main;
import oop.ex7.type.*;

public class Variable {
	
	private Type type;
	private String name;
	private boolean isInitialized;
	
	public Variable(String type, String name) {
		try {
			this.type = Type.createType(type);
		}
		catch(Exception ex) {
			System.out.println(ex.getLocalizedMessage());
		}
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
