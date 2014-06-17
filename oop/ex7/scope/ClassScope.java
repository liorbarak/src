package oop.ex7.scope;

import java.util.ArrayList;

import oop.ex7.main.BadLineSyntaxException;
import oop.ex7.main.CompileException;
import oop.ex7.main.EndOfFileException;
import oop.ex7.main.FileParser;
import oop.ex7.type.BadEndOfLineException;
import oop.ex7.type.BadTypeException;
import oop.ex7.type.VarExistException;
import oop.ex7.type.VarNotExistException;

/**
 * 
 * represents a general class scope
 * every file has a class scope because it is the file itself
 *
 */
public class ClassScope extends Scope {

	/**
	 * constructor
	 * @param lines-relevent lines to scope. 
	 * these are all the lines in the file
	 */
	public ClassScope(ArrayList<String> lines){
		super(lines,-1,lines.size(), null);

	}

	/**
	 * @throws CompileException 
	 * 
	 */
	public  void compileScope() throws CompileException   {

		ArrayList<Integer> opIndexArray=new ArrayList<Integer>();
		int lineType;
		//		Variable tempVar;
		//		Scope tempScope;

		for(int i=this.startIndex+1;i<this.endIndex;i++){
			lineType=FileParser.scopeOrVariable(fileLines.get(i),i);//throws if not valid scope or var declaration

			if (lineType==lineTypes.SCOPE.ordinal()){

				int closer = FileParser.findLastCloser(fileLines,i);

				lineAnalizerSc(fileLines,i,closer);

				i=closer;
			}
			else{
				opIndexArray.add(i);
			}

		}
		for (Integer j : opIndexArray ){

			lineAnalizerOp(fileLines.get(j));
		}

		//recursively calls all the crap in the universe. 
		for (Scope inner:innerScopes){
			inner.compileScope(); 
		}



	}


}
