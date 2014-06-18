package oop.ex7.scope;

import java.util.ArrayList;
import oop.ex7.main.CompileException;
import oop.ex7.main.FileParser;


/**
 * represents a general class scope
 * every file has a class scope because it is the file itself
 */
public class ClassScope extends Scope {

	/**
	 * constructor
	 * @param lines-relevent lines to scope. 
	 * these are all the lines in the file
	 */
	public ClassScope(ArrayList<String> lines){
		//-1 because of the implementation of compile scope.
		super(lines,-1,lines.size(), null);

	}

	/**
	 * This method compiles the class, which means handling all the compilation
	 * process. Calls other relevant methods to analyze each line of code in 
	 * the input file.
	 * @throws CompileException 
	 */
	public  void compileScope() throws CompileException   {
		
		//will be used to save all variable related lines for later processing
		ArrayList<Integer> opIndexArray=new ArrayList<Integer>();
		
		int lineType;

		//go over all the lines of the file and analyze them according to their
		//type.
		for(int i=this.startIndex+1;i<this.endIndex;i++){
			//check if the line is scope or variable related line 
			//throws if not valid scope or var declaration
			lineType=FileParser.scopeOrVariable(fileLines.get(i),i);

			if (lineType==lineTypes.SCOPE.ordinal()){
				//find the last line of the class.
				int closer = FileParser.findLastCloser(fileLines,i);
				//analyze the line of the type of scope - add methods to class
				lineAnalizerSc(fileLines,i,closer);
				//advance index of index line
				i=closer;
			}
			//if Variable line
			else{
				//add the line for later process.
				opIndexArray.add(i);
			}

		}
		//analyze all variable relaed lines after finishing processing and 
		//adding all methods to the class
		for (Integer j : opIndexArray ){
			//analyze members of class
			lineAnalizerOp(fileLines.get(j));
		}

		//recursively compile all inner scopes of the class.
		for (Scope inner:innerScopes){
			inner.compileScope(); 
		}
	}
}
