package oop.ex7.scope;

import java.util.ArrayList;

public class IfScope extends Scope {

	
	
	public IfScope(ArrayList<String> lines, Scope father){
		super(lines, father);
		stringRepresentation=Scopetypes.IF.name();
		
		
		validScopes.add(Scopetypes.WHILE);
		validScopes.add(Scopetypes.IF);
	}
	
	
	
}
