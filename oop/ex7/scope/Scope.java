package oop.ex7.scope;

import java.util.ArrayList;

import oop.ex7.main.EndOfFileException;
import oop.ex7.main.FileParser;
import oop.ex7.main.Variable;
import oop.ex7.type.Type;
import oop.ex7.type.VarExistException;
import oop.ex7.type.VariableFactory;
import oop.ex7.type.badEndOfLineException;
import oop.ex7.type.VariableFactory.lineType;



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
//
//		if (checkLine(line) == lineType.ASSIGNMENT.ordinal()) {
//			return assignmentLine(line, currScope);
//		}
//		
//		if (checkLine(line) == lineType.RETURN.ordinal())
//		
//		if (checkLine(line) == lineType.DECLARATION.ordinal()) {
//			return declarationLine(line, currScope);
//		}
//		
//		if (checkLine(line) == lineType.BOTH.ordinal()) {
//			return bothLine(line, currScope);
//		}
//		
//		return null;
//		
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

//______________________________________________________________________________

	private void assignmentLine(String line) throws Exception {
		
		Variable varTemp;
		
		String[] stringsInLine = getAssigmentStr(line);		
		String nameOfVar = stringsInLine[0];
		String inputValue = stringsInLine[1];
		
		//if the variable exists, somewhere in the code, put it into varTemp, else, put null into varTemp.
		varTemp = this.varExistInAll(nameOfVar);
		
		
		
		//if the variable doesn't exist:
		if (varTemp == null) {
			throw new VarExistException();
		}
		
		//check if the right expression is of the same type.
		if (varTemp.getType().checkExpression(inputValue)) {
			varTemp.setInitialized(true);
			return;
		}
		throw new Exception(); //TODO
	}
	
	private void declarationLine(String line) throws Exception {
		
		Variable varTemp;
		//save the left expression as the name of the variable
		//save the right expression as the input value
		String[] stringsInLine = getDecStr(line);
		String typeOfVar = stringsInLine[0];
		String nameOfVar = stringsInLine[1];
	
		//if the variable exists, put it into varTemp, else, put null into varTemp.
		varTemp = this.varExist(nameOfVar);
		//if the variable doesn't exist:
		if (varTemp == null) {
			this.innerVariables.add(new Variable(typeOfVar, nameOfVar));
		}
		throw new Exception();	
	}
	
	private void bothLine(String line) {
		
		Variable varTemp;
		//save the left expression as the name of the variable
		//save the right expression as the input value
		String linetemp = line.trim();
		String[] stringsInLine = getBothStr(linetemp);
		String decLine = stringsInLine[0];
		String assLine = stringsInLine[1];
		
		try {
			this.declarationLine(decLine);
			this.assignmentLine(assLine);	
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		
	}


	

	private static String[] getAssigmentStr(String line) {
		//1 - index of input value
		String linetemp = line.trim();
		String[] stringsInLine = linetemp.split("=");
		stringsInLine[1] = stringsInLine[1].trim();
		stringsInLine[1] = stringsInLine[1].replaceAll("( )*;?", "");
		return stringsInLine;
	}
	
	private static String[] getDecStr(String line) {
		//1 - index of name of var
		String linetemp = line.trim();
		String[] stringsInLine = linetemp.split("[ ]+");
		stringsInLine[1] = stringsInLine[1].replaceAll("( )*;?", "");
		return stringsInLine;
	}
	
	private static String[] getBothStr(String line) {
		
		String declarationLine =  getAssigmentStr(line)[0];
		String[] declaration = getDecStr(declarationLine);
		String inputValue = getAssigmentStr(line)[1];
		String decLine = declaration[0]+" "+declaration[1];
		String assLine = declaration[1]+"="+inputValue;
		String[] both = {decLine, assLine};
		return both;
		
	}



	private Variable varExist(String nameOfVar) {
		
		Scope tempScope = this; 
		
		for (Variable varOfScope:tempScope.getVariables()) {
			if (varOfScope.getName().equals(nameOfVar)) {
				return varOfScope;
			}
		}
		return null;
	}
	
	private Variable varExistInAll(String nameOfVar) {
		
		Scope tempScope = this;
		Variable tempVar;
		while(tempScope != null) {
			tempVar = tempScope.varExist(nameOfVar);
			if (tempVar != null) {
				return tempVar;
			}
			tempScope = tempScope.getFatherScope();
		}
		return null;
	}
	
	

}
