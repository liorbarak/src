package oop.ex7.scope;

import java.util.ArrayList;

import oop.ex7.main.Variable;

public abstract class Scope {
	ArrayList<String> relevantLines;
	ArrayList<Scope> innerScopes;
	ArrayList<Variable> innerVariables;
	
	//constructor
	
	public abstract void compileScope();
	
	
	public abstract boolean isScope(String line);
	

	
	
	
	
}
