package oop.ex7.scope;

import java.util.ArrayList;

public class WhileScope extends Scope {
	
	
	public WhileScope(ArrayList<String> lines,int begin,int finish, Scope father){
		super(lines,begin,finish, father);
//		stringRepresentation=Scopetypes.WHILE.name();
//		
//		validScopes.add(Scopetypes.WHILE);
//		validScopes.add(Scopetypes.IF);
		
	}
	
}
