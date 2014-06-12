package oop.ex7.scope;

import java.util.ArrayList;

import oop.ex7.main.FileParser;
import oop.ex7.main.Variable;



public class ClassScope extends Scope {

	public ClassScope(ArrayList<String> lines){
		FatherScope=null;
		relevantLines=lines;
		validScopes.add(Scopetypes.METHOD);
	}



}
