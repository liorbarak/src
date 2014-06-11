package oop.ex7.scope;

import java.util.ArrayList;

public abstract class Scope {
	ArrayList<String> relevantLines;
	ArrayList<Scope> innerScopes;
	ArrayList<Variable> innerVariables;
	
	
	
	public abstract void compileScope();
		
	
	
	
	
}
