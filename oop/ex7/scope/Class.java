package oop.ex7.scope;

import java.util.ArrayList;

public class Class extends Scope {


	
	public Class(ArrayList<String> lines){
		this.relevantLines=lines;
	}
	
	@Override
	public void compileScope() {
		
		for(String line: relevantLines){
			if (isScope(line)){
				
			}
			
		}
		
		
	}

}
