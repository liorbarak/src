package oop.ex7.scope;

import java.util.ArrayList;

import oop.ex7.main.FileParser;


public class ClassScope extends Scope {

	public ClassScope(ArrayList<String> lines){
		super(lines, null);
		stringRepresentation=Scopetypes.CLASS.name();
		
		validScopes.add(Scopetypes.METHOD);
		
	}


}
