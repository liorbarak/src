package oop.ex7.main;

import java.io.File;
import java.util.ArrayList;

import oop.ex7.scope.*;
import oop.ex7.scope.ClassScope;


public class Sjavac {

	enum returnValues{NO_ERRORS,INPUT_ERRORS,CRITICAL_IO_ERRORS};
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		
	try{
		File origin = new File(args[0]);//todo handle throws
		ArrayList<String> fileLines= FileParser.getlinesList(origin);
		for(String line:fileLines) {
			System.out.println(line);
		}
		ClassScope mainClass= new ClassScope(fileLines);
		mainClass.compileScope();
		
	}
	catch(Exception e){	//TODO change type of exception
		System.out.println(e.getLocalizedMessage());//some must print 2.
		return;
		//all others print 1
	}
		
	
	//if i got this far in the program it means compilation found no errors
	System.out.println(returnValues.NO_ERRORS.ordinal());
	}
	
	
	
	
	
}
