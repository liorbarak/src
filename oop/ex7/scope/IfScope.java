package oop.ex7.scope;

import java.util.ArrayList;

/**
 * extends scope
 * represents an 'if' scope
 * 
 */
public class IfScope extends Scope {

	
	/**
	 * constrctor
	 * 
	 * @param lines- array of lines for all the file
	 * @param begin- first relevant line for this scope
	 * @param finish- last relevant line for this scope
	 * @param father-the scope above this one
	 */
	public IfScope(ArrayList<String> lines,int begin,int finish, Scope father){
		super(lines,begin,finish, father);

	}
	
	
	
}
