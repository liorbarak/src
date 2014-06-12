package oop.ex7.scope;
import java.util.ArrayList;

import oop.ex7.type.*;

public abstract class ScopeFactory {

	public static final String WHILE_LINE ="";
	public static final String IF_LINE="";
	public static final String METHOD_LINE=""; 
//	public static final String 
//	public static final String 
//	public static final String 

	
	
	
	public static Scope createScope(ArrayList<String> lines,int start, int finish,ScopeMediator myScope){//throws exceptions
		//TODO split line to words
		String firstWord="";


		//creats class that has different rules
		if (myScope==null){
			return new ClassScope(lines);
		}
		
		
		// all other scopes
		BooleanType boolTemp=new BooleanType();
		if (isWhile(line)){
			
			
			
			//check if inside brackets is boolean, or method
			//return new initialized scope
		}

		else if (isIf(line)){
			//check if inside brackets is boolean, or method
			//return new initialized scope
			
		}
		else if (isMethod(line)){
			//if method already exists throw error
			//return new initialized method 
		}

		//implicit default
		//throw exception because line is not compatible with if/method/while

	}



}
