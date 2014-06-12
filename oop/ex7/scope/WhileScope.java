package oop.ex7.scope;

import java.util.ArrayList;

public class WhileScope extends Scope {
	
	
	public WhileScope(ArrayList<String> lines, Scope father){
		super( lines, father);
		stringRepresentation=Scopetypes.WHILE.name();
		
		validScopes.add(Scopetypes.WHILE);
		validScopes.add(Scopetypes.IF);
		
	}
	
}
