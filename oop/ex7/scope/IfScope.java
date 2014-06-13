package oop.ex7.scope;

import java.util.ArrayList;

public class IfScope extends Scope {

	
	
	public IfScope(ArrayList<String> lines,int begin,int finish, Scope father){
		super(lines,begin,finish, father);
		//stringRepresentation=Scopetypes.IF.name();
		
		
//		validScopes.add(Scopetypes.WHILE);
//		validScopes.add(Scopetypes.IF);
	}
	
	
	
}
