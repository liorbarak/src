package oop.ex7.scope;

import java.util.ArrayList;

import oop.ex7.main.Variable;

public interface ScopeMediator {

	public ArrayList<Variable> getVariables();
	
	public ArrayList<Scope> getScopes();
	
	public Scope getFatherScope();
	
}
