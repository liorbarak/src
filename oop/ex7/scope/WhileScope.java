package oop.ex7.scope;

import java.util.ArrayList;

/**
 * represents a 'while' scope
 *
 */
public class WhileScope extends Scope {
	
	/**
	 * constructor
	 * @param lines-list af all file lines
	 * @param begin- first relevant line
	 * @param finish- last relevant line
	 * @param father- the scope encapsulating this scope
	 */
	public WhileScope(ArrayList<String> lines,int begin,int finish, Scope father){
		super(lines,begin,finish, father);
		
	}
	
}
