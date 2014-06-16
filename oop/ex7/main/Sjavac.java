package oop.ex7.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import oop.ex7.scope.*;

/**
 * ex7 solution
 * a basic compiler for a 'simple java' language.
 * this language supports basic java operations like building functions,
 * using arrays, assigning variables and so forth
 * more details in the README 
 * 
 *
 */
public class Sjavac {

	/*
	 * internal enum used to represent the different outcomes possible for
	 * running the main method
	 */
	private enum returnValues{NO_ERRORS,INPUT_ERRORS,CRITICAL_IO_ERRORS};

	/**
	 * main method
	 * gets a pathname to a file and 'compiles' it. in the end it prints
	 * out : 
	 * 0- no errors
	 * 1- compilation errors
	 * 2- IO errors
	 * also if there was an error additional information 
	 * is printed to  System.err  
	 * @param args pathname for a file to a sjava text file 'compile'
	 */
	public static void main(String[] args) {

		try{

			File origin = new File(args[0]);	//debug mode if is commented.TODO change bach for junit

//			origin = new File("C://Users//Lior//Desktop//ex7stuff//tests//test253.sjava");
			origin = new File("/Users/taldovrat/Downloads/tests/test433.sjava");


			ArrayList<String> fileLines= FileParser.getlinesList(origin);
//			System.out.println(origin.isDirectory());
			ClassScope mainClass= new ClassScope(fileLines);
			mainClass.compileScope();

		}
		catch(FileNotFoundException e){
			
			System.out.println(returnValues.CRITICAL_IO_ERRORS.ordinal());//prints 2

			
			//for debug
			System.out.println(e.toString());
			e.printStackTrace(System.err);
			//for debug

			return;
		}
		catch(CompileException e1){//make sure all kinds of errors are cought here
			System.out.println(returnValues.INPUT_ERRORS.ordinal());//prints 1


			//for debug
			System.out.println(e1.toString());

			e1.printStackTrace(System.err);
			//for debug
			return;

		}
		catch(Exception e2){	//TODO change type of exception

			//System.out.println("general error. not one of ours");

			e2.printStackTrace(System.err);
			
			//for debug
//			System.out.println(e2.toString());
			//e2.printStackTrace();
			//for debug

			return;

		}


		//if i got this far in the program it means compilation found no errors. print 0
		System.out.println(returnValues.NO_ERRORS.ordinal());
	}
}
