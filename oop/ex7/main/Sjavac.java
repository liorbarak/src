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
			File origin = new File(args[0]);//todo handle throws
			ArrayList<String> fileLines= FileParser.getlinesList(origin);
			for(String line:fileLines) {
				System.out.println(line);
			}
			ClassScope mainClass= new ClassScope(fileLines);
			mainClass.compileScope();

		}
		catch(EndOfFileException |FileNotFoundException e){
			//print to err?
			System.out.println(returnValues.CRITICAL_IO_ERRORS.ordinal());//prints 2

			//for debug
			System.out.println(e.getStackTrace());
			System.out.println(e.toString());
			//for debug

			return;
		}
		catch(CompileException e1){//make sure all kinds of errors are cought here
			System.out.println(returnValues.INPUT_ERRORS.ordinal());//prints 1


			//for debug
			System.out.println(e1.getStackTrace());
			System.out.println(e1.toString());
			//for debug

		}
		catch(Exception e2){	//TODO change type of exception
			System.out.println("general error. not one of ours");

			//for debug
			System.out.println(e2.getStackTrace());
			System.out.println(e2.toString());
			//for debug

			return;

		}


		//if i got this far in the program it means compilation found no errors. print 0
		System.out.println(returnValues.NO_ERRORS.ordinal());
	}





}
