package oop.ex7.scope;

import java.util.ArrayList;

import oop.ex7.main.Variable;
import oop.ex7.type.Type;

public class Method extends Scope {

	private Type returnType;
	private String nameOfMethod;
	//TODO add method's input variables - their type by their order!
	
	public Method(){
		
	}
	


	@Override
	public void compileScope() {
		// TODO Auto-generated method stub
		
	}
	
	public static Method checkMethod(String line) {
		return new Method(); //TODO implement!
	}
		
		
	

	@Override
	public ArrayList<Variable> getVariables() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public ArrayList<Scope> getScopes() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Scope getFatherScope() {
		// TODO Auto-generated method stub
		return null;
	}



	public Type getReturnType() {
		return returnType;
	}



	public void setReturnType(Type returnType) {
		this.returnType = returnType;
	}



	public String getNameOfMethod() {
		return nameOfMethod;
	}



	public void setNameOfMethod(String nameOfMethod) {
		this.nameOfMethod = nameOfMethod;
	}



	
}
