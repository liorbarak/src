package oop.ex7.scope;

import java.util.ArrayList;

/**
 * 
 * represents a general class scope
 * every file has a class scope because it is the file itself
 *
 */
public class ClassScope extends Scope {

	/**
	 * constructor
	 * @param lines-relevent lines to scope. 
	 * these are all the lines in the file
	 */
	public ClassScope(ArrayList<String> lines){
		super(lines,-1,lines.size(), null);

	}


}
