package oop.ex7.scope;

import java.util.ArrayList;

import oop.ex7.main.BadLineSyntaxException;
import oop.ex7.main.FileParser;
import oop.ex7.type.*;

/**
 * extends Scope
 * represents a method scope. this scope has more information inside it than
 * Scope. it has a name, input variables and a return type.
 *
 */
public class MethodScope extends Scope {

	private Type returnType;
	private String nameOfMethod;
	ArrayList<Variable> inputVars; 
	
	/**
	 * Getter input variables
	 * @return this method's input variables.
	 */
	public ArrayList<Variable> getInputVars() {
		return inputVars;
	}
	/**
	 * constructor
	 * @param lines - file lines
	 * @param start - first line of the scope
	 * @param finish - last line of the scope
	 * @param returnType - the return type of this method.
	 * @param methodName - this method's name.
	 * @param inputVars - input variables of this method
	 * @param father - class that holds this method.
	 * @throws VarExistException
	 */
	public MethodScope(ArrayList<String> lines, int start,int finish,
			Type returnType, String methodName, ArrayList<Variable> inputVars,
			Scope father) throws VarExistException{

		super(lines,start,finish, father);
		this.inputVars = new ArrayList<Variable>(); 		
		this.returnType=returnType;
		this.nameOfMethod=methodName;

		//handleinput vars
		for(Variable var:inputVars){
			//if Var already exists!
			if (this.varExist(var.getName()) != null) {
				throw new VarExistException(var.getName());
			}
			var.setInitialized(true);
			this.inputVars.add(var);
			this.innerVariables.add(var);

		}


	}

	/**
	 * This method checks if a method equals to another, represented by a line 
	 * of code. This method breaks down the line and check if every element in
	 * the method represented in the line of code matches the "this" method.
	 * @param line - the line of code.
	 * @param med - a scopeMediator obj for accessing relevant info.
	 * @return true if same method, false otherwise.
	 * @throws BadLineSyntaxException
	 * @throws BadTypeException
	 * @throws VarNotExistException
	 * @throws VarExistException
	 */
	public boolean compareMethod(String line, ScopeMediator med) 
					throws BadLineSyntaxException, BadTypeException, 
									VarNotExistException, VarExistException  {
		
		//extract call name from the line of code.
		String callName = getMethodCallNameFromExp(line);
		//if method name doesn't equal to the call name:
		if (!this.getNameOfMethod().equals(callName)) {
			return false;
		}

		String[] varsCall = getMethodVarsFromCallExp(line);
		//go over all the input variables of the method and compare them to the
		//variables in the line of code
		for (int i = 0; i < this.innerVariables.size(); i++) {

			FileParser.checkExpression(this.innerVariables.get(i).getType(), 
														varsCall[i], med);

		}
		return true;
	}
	/**
	 * Getter - return type of method
	 * @return  the return type of the method
	 */
	public Type getReturnType() {
		return returnType;
	}

	/**
	 * Getter - name of method
	 * @return - returns the name of the method
	 */
	public String getNameOfMethod() {
		return nameOfMethod;
	}

	/*
	 * This method checks if the return of the method matches to the return
	 * type input string
	 * @param return expression to be checked.
	 * @return true if matches, false otherwise.
	 */
	private boolean handleReturn(String returnExpression) 
		throws BadLineSyntaxException, BadTypeException, VarNotExistException, 
														VarExistException {

		FileParser.checkExpression(this.returnType,returnExpression,this);

		return true;
	}

	/**
	 * This method returns the call name of a method from a given line of code
	 * that represent a method call.
	 * @param call - the line of code of the method call.
	 * @return The call name of the method.
	 */
	protected static String getMethodCallNameFromExp(String call) {
		return call.substring(0, call.indexOf("(")).trim();
	}

	/**
	 * This method returns an array of strings representing the input variables
	 * call in a method call line.
	 * @param call - the method call
	 * @return an array of strings of the call of the input variables of the
	 * method
	 */
	protected static String[] getMethodVarsFromCallExp(String call) {
		//get the expression inside the brackets in the call
		String variablesCall = call.substring(call.indexOf("(")+1, 
													call.indexOf(")")).trim();
		
		//organize each variable from the call in an array.
		String[] stringVars=variablesCall.split(",");
		String[] expVars = new String[stringVars.length];

		for(int i = 0; i< stringVars.length; i++) {
			expVars[i] = stringVars[i].trim();
		}
		return expVars;
	}


}
