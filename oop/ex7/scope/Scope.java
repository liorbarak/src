package oop.ex7.scope;

import java.util.ArrayList;

import oop.ex7.main.EndOfFileException;
import oop.ex7.main.FileParser;
import oop.ex7.main.Variable;
import oop.ex7.type.Type;
import oop.ex7.type.VariableFactory;
import oop.ex7.type.badEndOfLineException;



enum Scopetypes {CLASS,METHOD,IF,WHILE};
enum lineTypes {SCOPE, VARIABLE};


public abstract class Scope implements ScopeMediator{
	String stringRepresentation;
	Scope fatherScope;
	ArrayList<String> relevantLines;
	ArrayList<Scope> innerScopes;
	ArrayList<Variable> innerVariables;
	ArrayList<Scopetypes> validScopes;
	ArrayList<Integer> validVarOperations;


	//no constructor at the moment
	Scope (ArrayList<String> lines, Scope father){
		fatherScope=father;
		relevantLines=lines;
	}

	public  void compileScope() throws InvalidScopeException, EndOfFileException, badEndOfLineException,Exception  {

		ArrayList<Integer> variableIndexArray=new ArrayList<Integer>();
		int lineType;
		Variable tempVar;
		Scope tempScope;

		for(int i=0;i<relevantLines.size();i++){
			lineType=FileParser.scopeOrVariable(relevantLines.get(i),i);//throws if not valid scope or var declaration

			if (lineType==lineTypes.SCOPE.ordinal()){
				int closer = FileParser.findLastCloser(relevantLines,i);
				//replace to lineAnalizerSc
				tempScope = ScopeFactory.createScope(relevantLines,i,closer, this);

				//if scope is legal in that specific scope. for example, if/while in scope class is invalid.
				if (!isScopeValid(tempScope)){
					throw new InvalidScopeException(tempScope);//TODO create unique exception
				}
				innerScopes.add(tempScope);
				i=closer;
			}
			else{
				variableIndexArray.add(i);
			}

		}
		for (Integer j : variableIndexArray ){
			//replace to LineAnalizerOp
			tempVar= VariableFactory.createVar(relevantLines.get(j), this);

			if (tempVar!=null){
				innerVariables.add(tempVar);
			}

		}
		//recursively calls all the crap in the universe. 
		for (Scope inner:innerScopes){
			inner.compileScope(); 
		}



	}




	/**
	 * checks if this is a legal kind of scope that could be initialized in the
	 * current scope.
	 * example- class scope can only contain method scopes
	 * example2-method scope can only contain if/while
	 * @param tempScope
	 * @return
	 */
	private boolean isScopeValid(Scope tempScope) {
		return validScopes.contains(tempScope.toString());	//does this even work? needs checking
	}


	public String toString(){
		return stringRepresentation;
	}


	public ArrayList<Variable> getVariables(){
		return innerVariables;
	}

	public ArrayList<Scope> getScopes(){
		return innerScopes;
	}

	public Scope getFatherScope(){
		return fatherScope;
	}
	
	/**
	 * overridden in methodScope 
	 * all other scopes do not have return type
	 * @return
	 */
	public Type getReturnType() {
		return null;
	}


/////////////////////////////////////////////////////////////////////////////////////////////////operations
	public void lineAnalizerOp(String line) throws Exception {
		//4 cases- return, assign,initialize, both

		//return
		if (line.matches(RegexConfig.)){
			String returnExpression=line.substring(line.indexOf(" "), line.indexOf(";")).trim();
			if(!handleReturn(returnExpression)){
				throw new Exception();//return in incorrect location
			}
		}
		//assign
		else if (line.matches(RegexConfig.)){

		}
		//initialize
		else if (line.matches(RegexConfig.)){

		}
		//both
		else if (line.matches(RegexConfig.)){

		}

		//TODO create specific error. happens if doesnt match any of the known operations
		throw new Exception();

	}

	private boolean handleReturn(String returnExpression){
		if (fatherScope==null){
			return false;
		}
		
		return fatherScope.handleReturn(returnExpression);
	}

	private void handleAssign(String assignExp){
		//VariableFactory.assignment line 
	}

	private void handleInitialize(String initializeExp) throws Exception{//TODO change exception type to specific
		 if (VariableFactory.createVar(initializeExp, this)!=null){
			 
		 }
	}

//maybe combine assign and init	
//	private void handleBoth(){
//
//	}

	
///////////////////////////////////////////////////////////////////////////////////////////scopes
	
	public void lineAnalizerSc(String line) {
		//TODO check if scope declaration valid inside the specific scope.
	}





}
