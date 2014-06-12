package oop.ex7.scope;

import java.util.ArrayList;

import oop.ex7.main.Variable;
import oop.ex7.type.Type;

public class MethodScope extends Scope {

	private Type returnType;
	private String nameOfMethod;
	private ArrayList<Variable> inputVars; 
	//TODO add method's input variables - their type by their order!



	public ArrayList<Variable> getInputVars() {
		return inputVars;
	}



	public MethodScope(ArrayList<String> lines, Scope father){
		super(lines, father);
		stringRepresentation=Scopetypes.METHOD.name();

		validScopes.add(Scopetypes.WHILE);
		validScopes.add(Scopetypes.IF);
	}



	public static MethodScope checkMethod(String line,Scope myScope) {

		String callName=line.substring(0, line.indexOf("("));
		String varCall=line.substring(line.indexOf("("),line.indexOf(")"));
		String[] stringVars=line.split(",");
		ArrayList<Type> callVarsType;

		for (String i: stringVars){
			//push into callvars the type of i. need to implement a method
		}


		//go to main class
		Scope tempScope= myScope;
		while(tempScope.getFatherScope()!=null){
			tempScope=tempScope.getFatherScope();
		}

		for (Scope i:tempScope.getScopes()){
			MethodScope myMethod=(MethodScope)(i);//casting scope to method. there are only method in main class
			ArrayList<Variable> currentVars= myMethod.getInputVars();

			if (myMethod.getNameOfMethod().equals(callName)){
				if (callVarsType.size()!=currentVars.size()){
					return null;
				}
				for (int j=0; j<callVarsType.size();j++){
					if (!callVarsType.get(j).equals(currentVars.get(j).getType())){//TODO check if this shit even works
						return null;
					}
				}
				return myMethod;
			}

		}

		return null;
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
