package oop.ex7.scope;

import java.util.ArrayList;

import oop.ex7.main.FileParser;
import oop.ex7.main.Variable;
import oop.ex7.type.BadTypeException;
import oop.ex7.type.Type;

public class MethodScope extends Scope {

	private Type returnType;
	private String nameOfMethod;
	private ArrayList<Variable> inputVars; 
	//TODO add method's input variables - their type by their order!



	public ArrayList<Variable> getInputVars() {
		return inputVars;
	}


	//innerScopes.add(new MethodScope (lines,start,finish,Type.createType(returnType),methodName,inputVars, this)   );
	public MethodScope(ArrayList<String> lines, int start,int finish,
			Type returnType, String methodName, ArrayList<Variable> inputVars,
			Scope father){

		super(lines,start,finish, father);
		this.inputVars = new ArrayList<Variable>(); 
		//stringRepresentation=Scopetypes.METHOD.name();

		//		validScopes.add(Scopetypes.WHILE);
		//		validScopes.add(Scopetypes.IF);
		this.returnType=returnType;
		this.nameOfMethod=methodName;

		//handleinput vars
		for(Variable var:inputVars){
			var.setInitialized(true);
			inputVars.add(var);
			innerVariables.add(var);

		}


	}



	public boolean compareMethod(String line, ScopeMediator med) throws BadTypeException {

		String callName = getMethodCallNameFromExp(line);
		if (!this.getNameOfMethod().equals(callName)) {
			return false;
		}

		String[] varsCall = getMethodVarsFromCallExp(line);

		for (int i = 0; i < this.innerVariables.size(); i++) {

			FileParser.checkExpression(this.innerVariables.get(i).getType(), varsCall[i], med);

		}
		return true;
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


	public boolean handleReturn(String returnExpression) throws BadTypeException{

		FileParser.checkExpression(this.returnType,returnExpression,this);

		return true;
	}

	private static String getMethodCallNameFromExp(String call) {
		return call.substring(0, call.indexOf("(")).trim();
	}

	private static String[] getMethodVarsFromCallExp(String call) {
		String variablesCall = call.substring(call.indexOf("(")+1,call.indexOf(")")).trim();
		//Need to check valid expression structure
		String[] stringVars=variablesCall.split(",");
		String[] expVars = new String[stringVars.length];

		for(int i = 0; i< stringVars.length; i++) {
			expVars[i] = stringVars[i].trim();
		}
		return expVars;
	}


}
