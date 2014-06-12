package oop.ex7.scope;

import java.util.ArrayList;

import oop.ex7.main.Variable;
import oop.ex7.type.Type;

public class MethodScope extends Scope {

	private Type returnType;
	private String nameOfMethod;
	//TODO add method's input variables - their type by their order!
	
	public MethodScope(){
		
	}
	


	@Override
	public void compileScope() {
		// TODO Auto-generated method stub
		
	}
	
	public static MethodScope checkMethod(String line) {
		return new MethodScope(); //TODO implement!
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
