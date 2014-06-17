package oop.ex7.scope;

import java.util.ArrayList;



/**
 * interface.
 * all scopes implement this.
 * enables other classes to access only part of the available methods
 *
 */
public interface ScopeMediator {

	/**
	 * 
	 * @return-an array all inner variables inside this scope.
	 * 
	 */
	public ArrayList<Variable> getVariables();
	
	
	/**
	 * 
	 * @return an array of all inner scopes inside this scope
	 */
	public ArrayList<Scope> getScopes();
	
	/**
	 * 
	 * @return- father scope.
	 * in case this is a 'class' scope the default return type is null
	 */
	public Scope getFatherScope();
	
}
