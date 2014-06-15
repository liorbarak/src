package oop.ex7.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import oop.ex7.scope.*;


public class Sjavac {

	enum returnValues{NO_ERRORS,INPUT_ERRORS,CRITICAL_IO_ERRORS};

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try{
			
			File origin = new File(args[0]);	//debug mode if is commented.TODO change bach for junit
//			File origin = new File("C://Users//Lior//Desktop//ex7stuff//tests//test253.sjava");
			ArrayList<String> fileLines= FileParser.getlinesList(origin);
			
			ClassScope mainClass= new ClassScope(fileLines);
			mainClass.compileScope();

		}
		catch(EndOfFileException |FileNotFoundException e){
			
			//System.out.println(returnValues.CRITICAL_IO_ERRORS.ordinal());//prints 2

			
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
//			System.out.println("general error. not one of ours");

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
