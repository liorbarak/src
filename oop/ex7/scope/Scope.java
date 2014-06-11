package oop.ex7.scope;

import java.util.ArrayList;

import oop.ex7.main.FileParser;
import oop.ex7.main.Variable;
import oop.ex7.type.Type;

public abstract class Scope {
	Scope FatherScope;
	ArrayList<String> relevantLines;
	ArrayList<Scope> innerScopes;
	ArrayList<Variable> innerVariables;
	ArrayList<Integer> validScopes;
	ArrayList<Integer> validVarOperations;
	
	
	//constructor

	public  void compileScope() {

		ArrayList<Integer> variableIndexArray=new ArrayList<Integer>();
		int lineType;
		Variable tempVar;
		Scope tempScope;

		for(int i=0;i<relevantLines.size();i++){
			lineType=FileParser.scopeOrVAriable(relevantLines.get(i));//throws if not valid scope or var declaration

			if (lineType==1){
				int closer = FileParser.findLastCloser(relevantLines,i);
				tempScope = ScopeFactory.createScope(relevantLines,i,closer);

				if (!isScopeValid(tempScope)){
					throw new Exception();//TODO create unique exception
				}
				innerScopes.add(tempScope);
				i=closer;
			}
			else{
				variableIndexArray.add(i);
			}

		}
		for (Integer j : variableIndexArray ){
			tempVar= VariableFactory.createVar(relevantLines.get(j));

			if (!isVarScopeValid(tempVar)){
				throw new Exception();//TODO create unique exception
			}
			if (!isVarExpValid(tempVar)){
				throw new Exception();//TODO create unique exception
			}

			innerVariables.add(tempVar);


		}
		//recursively calls all the crap in the universe. 
		for (Scope inner:innerScopes){
			compileScope(); 
		}



	}



	/**
	 * 
	 * 
	 * @param tempVar
	 * @return
	 */
	private boolean isVarExpValid(Variable tempVar) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 
	 * @param tempScope
	 * @return
	 */
	private boolean isScopeValid(Scope tempScope) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * checks back compatibility of vars 
	 * also checks if right hand side exp matches type of var
	 * 
	 * 
	 * @param tempVar
	 * @return
	 */
	private boolean isVarScopeValid(Variable tempVar) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * checks if var exsits in this scope or the ones above it.recursive.
	 * @return null if not exist
	 */
	private Type checkVarExist(Variable tempVar){
		
	}
	
	
	private boolean checkExpTypecorrect(Type targetType){
		
	}





}
