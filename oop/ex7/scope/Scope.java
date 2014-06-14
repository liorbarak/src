package oop.ex7.scope;

import java.util.ArrayList;

import oop.ex7.main.BadLineSyntaxException;
import oop.ex7.main.CompileException;
import oop.ex7.main.EndOfFileException;
import oop.ex7.main.FileParser;
import oop.ex7.main.RegexConfig;
import oop.ex7.main.Variable;
import oop.ex7.type.BadTypeException;
import oop.ex7.type.BooleanType;
import oop.ex7.type.Type;
import oop.ex7.type.VarExistException;
import oop.ex7.type.BadEndOfLineException;




enum Scopetypes {CLASS,METHOD,IF,WHILE};
enum lineTypes {SCOPE, VARIABLE};


public abstract class Scope implements ScopeMediator{
	//String stringRepresentation;
	Scope fatherScope;
	ArrayList<String> relevantLines;
	int startIndex;
	int endIndex;
	ArrayList<Scope> innerScopes;
	ArrayList<Variable> innerVariables;
//	ArrayList<Scopetypes> validScopes;
//	ArrayList<Integer> validVarOperations;


	//no constructor at the moment
	Scope (ArrayList<String> lines,int begin,int end, Scope father){
		fatherScope=father;
		relevantLines=lines;
		startIndex=begin;
		endIndex=end;
		innerScopes = new ArrayList<Scope>();
		innerVariables = new ArrayList<Variable>();
	}

	public  void compileScope() throws InvalidScopeException, EndOfFileException, BadEndOfLineException,Exception  {

		ArrayList<Integer> opIndexArray=new ArrayList<Integer>();
		int lineType;
		Variable tempVar;
		Scope tempScope;

		for(int i=0;i<relevantLines.size();i++){
			lineType=FileParser.scopeOrVariable(relevantLines.get(i),i);//throws if not valid scope or var declaration

			if (lineType==lineTypes.SCOPE.ordinal()){
				
				int closer = FileParser.findLastCloser(relevantLines,i);
				
				lineAnalizerSc(relevantLines,i,closer);
				
				i=closer;
			}
			else{
				opIndexArray.add(i);
			}

		}
		for (Integer j : opIndexArray ){

			lineAnalizerOp(relevantLines.get(j));
		}

		//recursively calls all the crap in the universe. 
		for (Scope inner:innerScopes){
			inner.compileScope(); 
		}



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
		if (line.matches(RegexConfig.lineType.RETURN.getRegex())){
			String returnExpression=line.substring(line.indexOf(" "), line.indexOf(";")).trim();
			if(!handleReturn(returnExpression)){
				throw new BadReturnException(line);//return in case of incorrect location
			}
		}

		//assign
		else if (line.matches(RegexConfig.lineType.ASSIGNMENT.getRegex())){
			assignmentLine(line);
			return;
		}
		//initialize
		else if (line.matches(RegexConfig.lineType.DECLARATION.getRegex())){
			declarationLine(line);
			return;
		}
		//both
		else if (line.matches(RegexConfig.lineType.BOTH.getRegex())){
			bothLine(line);
			return;
		}

		//TODO create specific error. happens if doesnt match any of the known operations
		throw new BadLineSyntaxException(line);

	}

	private boolean handleReturn(String returnExpression){
		if (fatherScope==null){
			return false;
		}

		return fatherScope.handleReturn(returnExpression);
	}




	private void assignmentLine(String line) throws VarExistException, BadTypeException {

		Variable varTemp;

		String[] stringsInLine = getAssigmentStr(line);		
		String nameOfVar = stringsInLine[0];
		String inputValue = stringsInLine[1];

		//if the variable exists, somewhere in the code, put it into varTemp, else, put null into varTemp.
		varTemp = this.varExistInAll(nameOfVar);



		//if the variable doesn't exist:
		if (varTemp == null) {
			throw new VarExistException(line);
		}

		//check if the right expression is of the same type.
		FileParser.checkExpression(varTemp.getType(), inputValue, this);
		varTemp.setInitialized(true);
		return;
		
	}

	private void declarationLine(String line)  throws VarExistException {

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
			return;
		}
		throw new VarExistException(line);	
	}

	private void bothLine(String line) throws VarExistException, BadTypeException {

		//Variable varTemp;
		//save the left expression as the name of the variable
		//save the right expression as the input value
		String linetemp = line.trim();
		String[] stringsInLine = getBothStr(linetemp);
		String decLine = stringsInLine[0];
		String assLine = stringsInLine[1];


			this.declarationLine(decLine);
			this.assignmentLine(assLine);	


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


	///////////////////////////////////////////////////////////////////////////////////////////scopes

	//public void lineAnalizerSc(String line) {
	public void lineAnalizerSc (ArrayList<String> lines,int start, int finish) throws CompileException  {
		
		String firstline=lines.get(start);
		ArrayList<String> subScopeLines=(ArrayList<String>) (lines.subList(start, finish));
		
		
		//method
		if (firstline.matches(RegexConfig.VALID_METHOD_DECLARE)){
			
			if (fatherScope!=null){
				throw new InvalidScopeException(start);
			}
			
			methodInput(lines, start, finish);
		}
		//while
		else if (firstline.matches(RegexConfig.VALID_WHILE_CALL)){
			if (fatherScope==null){
				throw new InvalidScopeException(start);
			}
			
			FileParser.checkExpression(new BooleanType(), getInsideBrackets(firstline), this);
			innerScopes.add( new WhileScope(subScopeLines,start,finish,this));
		}
		//if
		else if (firstline.matches(RegexConfig.VALID_IF_CALL)){
			if (fatherScope==null){
				throw new InvalidScopeException(start);
			}
			
			FileParser.checkExpression(new BooleanType(), getInsideBrackets(firstline), this);
			innerScopes.add( new IfScope(subScopeLines,start,finish,this));
		}
		
		throw new BadLineSyntaxException(firstline);
		
}

	
	
	
	
	private static String getInsideBrackets(String line){
		return line=line.substring(line.indexOf("("),line.lastIndexOf(")")).trim();
	}
	
	
	
	/*
	 * recives relevant lines for method, takes it apart, and
	 * creates a new methodscope while checking all its variables
	 * 
	 */
	private void methodInput (ArrayList<String> lines,int start, int finish) throws CompileException {
		String tempLine=lines.get(start);
		ArrayList<Variable> inputVars=new ArrayList<Variable>();
		
		String returnType=tempLine.substring(0, tempLine.indexOf(" "));
		String methodName=tempLine.substring(tempLine.indexOf(" "), tempLine.indexOf("("));
		String[] insideBrackets=getInsideBrackets(tempLine).split(",");
		 
		for (Scope i:innerScopes){
			MethodScope method=(MethodScope) i;
			if (!method.getNameOfMethod().equals(methodName)){
				throw new DoubleMethodDecleration(start,lines.get(start));
			}
		}
		
		for (String i:insideBrackets){
			String[] TypeAndName = i.split(" ");
			inputVars.add(new Variable(TypeAndName[0], TypeAndName[1]));
		}
		
		innerScopes.add(new MethodScope (lines,start,finish,
				Type.createType(returnType),methodName,inputVars, this));
		
	}






}
