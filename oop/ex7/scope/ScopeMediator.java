package oop.ex7.scope;

import java.util.ArrayList;

import oop.ex7.main.Variable;

public interface ScopeMediator {

//	Scope FatherScope;
//	ArrayList<String> relevantLines;
//	ArrayList<Scope> innerScopes;
//	ArrayList<Variable> innerVariables;
//	ArrayList<Integer> validScopes;
//	ArrayList<Integer> validVarOperations;
	
	public ArrayList<Variable> getVariables();
	
	public ArrayList<Scope> getScopes();
	
	public Scope getFatherScope();
	
}
