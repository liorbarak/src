package oop.ex7.scope;

import java.util.ArrayList;
import java.util.regex.*;
import oop.ex7.main.*;
import oop.ex7.type.*;

/**
 * The enum lineTypes represent the type of the line in the Scope. lines in 
 * the different scopes have basic possibilities and this enum tells which type
 * of line is it.
 * @author taldovrat
 *
 */
enum lineTypes {SCOPE, VARIABLE};

/**
 * This class is a father class for all other scope types in the program. This 
 * class is an abstract class and implements the interface Scopemediator. 
 * This class represents and holds all the joint properties of all scopes in 
 * the program.      
 * @author taldovrat
 */
/*
 * This class holds and handles all the data that is shared by all inheriting 
 * classes. For example, this class holds all inner scopes that the scope has,
 * because it is a property relevant to all inheriting classes. Also, like in
 * fields, This class implements methods that are shared by its inheriting 
 * classes like compileScope which is a very important method that is used in 
 * a certain way in all classes that are not from the type of Class Scope. 
 */
public abstract class Scope implements ScopeMediator{
	//The scope in which this scope is nested in the code. null if the scope
	//is the class scope.
	Scope fatherScope;
	//the lines of code that the file has.
	ArrayList<String> fileLines;
	//start index of line that is relevant to this scope. 
	int startIndex;
	//end index of line that is relevant to this scope.
	int endIndex;
	/*
	 * holds all nested scopes of this scope. Holds only the first level of
	 * nested scopes. for example, if this scope is a method, that has an if
	 * scope inside it, and the if scope has a while scope inside of it,
	 * scope will have the while scope as its inner scope.
	 * the method scope will have the if scope as its inner scope and the if 
	 */
	ArrayList<Scope> innerScopes;
	/*
	 * holds all nested variables of this scope. Holds only the first level of
	 * nested Variables. for example, if this scope is a method, that has a 
	 * Variable "int a" inside it, and inside the method there is an if scope 
	 * that has an "int b" inside of it, then the int a is the innerVariables
	 * of the method and the "int b" is in the if scope innerVariables field.  
	 * Important - Input Variables for Method scopes are put inside of this 
	 * ArrayList! 
	 */
	ArrayList<Variable> innerVariables;

	//Contructor for 
	Scope (ArrayList<String> lines,int begin,int end, Scope father){
		fatherScope=father;
		fileLines=lines;
		startIndex=begin;
		endIndex=end;
		innerScopes = new ArrayList<Scope>();
		innerVariables = new ArrayList<Variable>();
	}

	
	/**
	 * 
	 * @throws InvalidScopeException
	 * @throws EndOfFileException
	 * @throws BadEndOfLineException
	 * @throws BadReturnException 
	 * @throws VarExistException 
	 * @throws VarNotExistException 
	 * @throws BadTypeException 
	 * @throws BadLineSyntaxException 
	 * @throws DoubleMethodException 
	 */
//	public  void compileScope() throws 
//	InvalidScopeException, EndOfFileException, BadEndOfLineException, 
//	BadReturnException, BadLineSyntaxException, BadTypeException, 
//	VarNotExistException, VarExistException, DoubleMethodException  {
		
	public  void compileScope() throws CompileException  {

		ArrayList<Integer> opIndexArray=new ArrayList<Integer>();
		int lineType;

		for(int i=this.startIndex+1;i<this.endIndex;i++){
			lineType=FileParser.scopeOrVariable(fileLines.get(i),i);//throws if not valid scope or var declaration

			if (lineType==lineTypes.VARIABLE.ordinal()){
				lineAnalizerOp(fileLines.get(i));
			}

			else if (lineType==lineTypes.SCOPE.ordinal()){

				int closer = FileParser.findLastCloser(fileLines,i);

				lineAnalizerSc(fileLines,i,closer);

				i=closer;

				this.innerScopes.get(this.innerScopes.size()-1).compileScope();
			}
			else{
				throw new BadEndOfLineException(i, fileLines.get(i));
			}
			
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
	public void lineAnalizerOp(String line) throws  BadReturnException, BadLineSyntaxException, BadTypeException, VarNotExistException, VarExistException  {
		//4 cases- return, assign,initialize, both

		//return
		if (line.matches(RegexConfig.lineType.RETURN.getRegex())){

			Pattern p = Pattern.compile("return");
			Matcher m = p.matcher(line);
			m.find();
			String returnExpression=line.substring(m.end(), line.indexOf(";")).trim();

			if(!handleReturn(returnExpression)){
				throw new BadReturnException(line);//return in case of incorrect location
			}
			return;
		}

		//assign
		else if (line.matches(RegexConfig.lineType.ASSIGNMENT.getRegex()) || line.matches(RegexConfig.lineType.ASSIGNMENT_ARRAY.getRegex())){
			assignmentLine(line);
			return;
		}
		//		//assign array
		//		else if (line.matches(RegexConfig.lineType.ASSIGNMENT_ARRAY.getRegex())){
		//			assignmentLineArr(line);
		//			return;
		//		}
		//initialize
		else if (line.matches(RegexConfig.lineType.DECLARATION.getRegex()) || line.matches(RegexConfig.ARRAY_DECLARE)){
			declarationLine(line);
			return;
		}
		//both
		else if (line.matches(RegexConfig.lineType.BOTH.getRegex()) || line.matches(RegexConfig.lineType.BOTH_ARRAY.getRegex())){
			bothLine(line);
			return;
		}

		else if (line.matches(RegexConfig.METHOD_CALL)) {
			this.checkMethod(line);
			return;
		}
		//
		//		else if (line.matches(RegexConfig.lineType.BOTH_ARRAY.getRegex())){
		//			bothLineArr(line);
		//			return;
		//		}
		throw new BadLineSyntaxException(line);

	}


	private void checkMethod(String line) throws VarNotExistException, BadLineSyntaxException, BadTypeException, VarExistException  {
		// TODO Auto-generated method stub
		Scope tempScope = this; 
		while(tempScope.getFatherScope() != null) {
			tempScope = tempScope.getFatherScope();
		}
		ClassScope classScope = (ClassScope) tempScope;
		for (Scope sc:classScope.innerScopes) {
			MethodScope tempMethod = (MethodScope) sc;
			String callName = MethodScope.getMethodCallNameFromExp(line);
			if (tempMethod.getNameOfMethod().equals(callName)) {
				String[] varsCall = MethodScope.getMethodVarsFromCallExp(line);
				
				//TODO check num of args!!!
				
				if (varsCall.length==1 && varsCall[0].equals("") && tempMethod.inputVars.isEmpty())
					return;
				if (varsCall.length != tempMethod.innerVariables.size()) {
					throw new VarNotExistException(callName); //TODO
				}
				for (int i = 0; i < tempMethod.inputVars.size(); i++) {

					FileParser.checkExpression(tempMethod.inputVars.get(i).getType(), varsCall[i], this);
					
				}
				return;
			}
		}
		throw new VarNotExistException(line); 
	}

	private boolean handleReturn(String returnExpression) throws BadLineSyntaxException, BadTypeException, VarNotExistException, VarExistException {
		if (fatherScope==null){
			return false;
		}
		if (fatherScope.fatherScope == null) {
			MethodScope method = (MethodScope) this;

			if (method.getReturnType().getRegex().equals(RegexConfig.INPUT_VOID)) {

				if ( returnExpression.matches(RegexConfig.INPUT_VOID)){
					return true;
				}
			}


			FileParser.checkExpression(method.getReturnType(), returnExpression, method);
			return true;
		}

		return fatherScope.handleReturn(returnExpression);
	}

	//
	//	private void assignmentLineArr(String line) throws CompileException {
	//
	//		Variable varTemp;
	//
	//		String[] stringsInLine = Scope.getAssigmentStr(line);		
	//		String nameOfVar = stringsInLine[0];
	//		String inputValue = stringsInLine[1];
	//		String nameOfArr = nameOfVar.split("\\[")[0].trim();
	////		String expToCheck = nameOfVar.substring(line.indexOf("[")+1, line.lastIndexOf("]")).trim();
	//
	//		//if the variable exists, somewhere in the code, put it into varTemp, else, put null into varTemp.
	//		varTemp = this.varExistInAll(nameOfArr);
	//		
	//		//if the variable doesn't exist:
	//		if (varTemp == null || varTemp.getType().sameType(new ArrayType())) {
	//			throw new VarExistException(line);
	//		}
	//		//check if the expression of the index in the array call is valid:
	////		FileParser.checkExpression(new IntType(), expToCheck, this);
	////		if (expToCheck.matches(RegexConfig.INPUT_INT)) {
	////			int intExp = Integer.parseInt(expToCheck);
	////			if (intExp < 0) {
	////				throw new CompileException(); //TODO change exception type
	////			}
	////		}
	//		FileParser.checkInnerArrVarPos(line, this);
	//		
	//		//check if the right expression is of the same type.
	//		FileParser.checkExpression(varTemp.getType(), inputValue, this);
	//		varTemp.setInitialized(true);
	//		return;
	//
	//	}

	private void assignmentLine(String line) throws VarExistException, BadTypeException, BadLineSyntaxException, VarNotExistException  {

		Variable varTemp;
		///
		String[] stringsInLine = getAssigmentStr(line);		

		String fullName = stringsInLine[0];
		Pattern p = Pattern.compile(RegexConfig.GENERAL_NAME);
		Matcher m = p.matcher(fullName);
		m.find();
		String nameOfVar = fullName.substring(m.start(), m.end());

		String inputValue = stringsInLine[1];

		//if the variable exists, somewhere in the code, put it into varTemp, else, put null into varTemp.
		varTemp = this.varExistInAll(nameOfVar);

		//if the variable doesn't exist:
		if (varTemp == null) {
			throw new VarExistException(line);
		}

		if (varTemp.getType().sameType(new ArrayType())) {

			ArrayType type = (ArrayType) varTemp.getType();
			if (!fullName.equals(nameOfVar)) {
				FileParser.checkInnerArrVarPos(fullName, this);
				FileParser.checkExpression(type.getInnerType(), inputValue, this);
				return;
			}
			FileParser.checkExpression(varTemp.getType(), inputValue, this);
			String cleanInputVals = inputValue.replaceAll("[\\{\\}]", "");
			if (cleanInputVals.matches(RegexConfig.BLANK_LINE)) {
				return;
			}
			String[] inputValues = cleanInputVals.split(",");

			for (String exp:inputValues) {
				FileParser.checkExpression(type.getInnerType(), exp, this);
			}
			varTemp.setInitialized(true);
			return;
		}


		//check if the right expression is of the same type.
		FileParser.checkExpression(varTemp.getType(), inputValue, this);
		varTemp.setInitialized(true);
		return;

	}

	private void declarationLine(String line) throws VarExistException, BadTypeException {

		Variable varTemp;
		//save the left expression as the name of the variable
		//save the right expression as the input value
		String fullType = getDecStr(line)[0];
		Pattern p = Pattern.compile(RegexConfig.VALID_TYPES);
		Matcher m = p.matcher(line);
		m.find();
		String typeOfVar = line.substring(m.start(), m.end());
		int lastEnd = m.end();
		p = Pattern.compile(RegexConfig.GENERAL_NAME);
		m = p.matcher(line);
		m.find(lastEnd);
		String nameOfVar = line.substring(m.start(), m.end()).trim();

		//if the variable exists, put it into varTemp, else, put null into varTemp.
		varTemp = this.varExist(nameOfVar);
		//if the variable doesn't exist:
		if (varTemp == null) {
			if (line.matches(RegexConfig.ARRAY_DECLARE)||line.matches(RegexConfig.ARRAY_DECLARE_WITH_SEMICOLON)) {
				this.innerVariables.add(new Variable(fullType, nameOfVar));
			}
			else {
				this.innerVariables.add(new Variable(typeOfVar, nameOfVar));
			}

			return;
		}
		throw new VarExistException(line);	
	}

	private void bothLine(String line) throws VarExistException, BadTypeException, BadLineSyntaxException, VarNotExistException  {

		//Variable varTemp;
		//save the left expression as the name of the variable
		//save the right expression as the input value
		String linetemp = line.trim();
		String[] stringsInLine = getBothStr(linetemp);
		String decLine = stringsInLine[0];
		String assLine = stringsInLine[1];
		//		System.out.println(decLine);
		//		System.out.println(assLine);

		this.declarationLine(decLine);
		this.assignmentLine(assLine);	

	}
	//
	//	private void bothLineArr(String line) throws CompileException {
	//		
	//		String fullAss = getAssigmentStr(line)[0].trim();
	//		String fullType = getAssigmentStr(fullAss)[0];
	//		String nameOfVar = getAssigmentStr(fullAss)[1];
	//		String lineTemp = line.trim();
	//		Pattern p = Pattern.compile(RegexConfig.VALID_TYPES);
	//		Matcher m = p.matcher(lineTemp);
	//		m.find();
	//		String typeOfExps = lineTemp.substring(m.start(), m.end());
	//		
	//		if (line.matches(RegexConfig.ARRAY_DECLARE_BLANK)) {
	//			this.innerVariables.add(new Variable(fullType, nameOfVar));
	//		}
	//		String[] exps = getAssigmentStr(lineTemp)[1].split("\\,");
	//		//TODO lior added
	//		//checks if the expression we get on the right side of the = is '{}'
	//		if (exps[0].matches(RegexConfig.BLANK_LINE)){
	//			return;
	//		}
	//		//TODO lior added
	//		
	//		for (String exp:exps) {
	//			FileParser.checkExpression(Type.createType(typeOfExps), exp, this);
	//		}
	//		
	//	}

	public static String[] getAssigmentStr(String line) {
		//1 - index of input value
		String linetemp = line.trim();
		String[] stringsInLine = linetemp.split("=");
		stringsInLine[1] = stringsInLine[1].trim();
		stringsInLine[1] = stringsInLine[1].replaceAll("( )*;?", "");
		return stringsInLine;
	}

	public static String[] getDecStr(String line) {
		//1 - index of name of var
//		String linetemp = line.trim();
//		String[] stringsInLine = linetemp.split("[ ]+");
		Pattern p = Pattern.compile(RegexConfig.VALID_TYPES+"(\\[[\\s]*\\])?");
		Matcher m = p.matcher(line);
		m.find();
		String[] stringsInLine = new String[2];
		stringsInLine[0] = line.substring(0, m.end());
		stringsInLine[0] = stringsInLine[0].replaceAll("[\\s]*;?", "");
		
		stringsInLine[1] = line.substring(m.end());
		stringsInLine[1] = stringsInLine[1].replaceAll("[\\s]*;?", "");
		return stringsInLine;
	}

	public static String[] getBothStr(String line) {

		//String declarationLine =  getAssigmentStr(line)[0];
		String declarationLine =  getAssigmentStr(line)[0]+";";
		String[] declaration = getDecStr(declarationLine);

		String inputValue = getAssigmentStr(line)[1];
		
		String decLine = declaration[0]+" "+declaration[1];
		String assLine = declaration[1]+"="+inputValue;
		String[] both = {decLine, assLine};
		return both;

	}


	public Variable varExist(String nameOfVar) {

		Scope tempScope = this; 

		for (Variable varOfScope:tempScope.getVariables()) {
			if (varOfScope.getName().equals(nameOfVar.trim())) {
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

	public void lineAnalizerSc (ArrayList<String> lines,int start, int finish) throws InvalidScopeException, BadLineSyntaxException, BadTypeException, VarNotExistException, VarExistException, DoubleMethodException   {

		String firstline=lines.get(start);


		//method
		if (firstline.matches(RegexConfig.VALID_METHOD_DECLARE)){

			if (fatherScope!=null){
				throw new InvalidScopeException(start);
			}

			methodInput(lines, start, finish);
			return;
		}
		//while
		else if (firstline.matches(RegexConfig.VALID_WHILE_CALL)){
			if (fatherScope==null){
				throw new InvalidScopeException(start);
			}

			FileParser.checkExpression(new BooleanType(), getInsideBrackets(firstline), this);
			innerScopes.add( new WhileScope(lines,start,finish,this));
			return;
		}
		//if
		else if (firstline.matches(RegexConfig.VALID_IF_CALL)){
			if (fatherScope==null){
				throw new InvalidScopeException(start);
			}

			FileParser.checkExpression(new BooleanType(), getInsideBrackets(firstline), this);
			innerScopes.add( new IfScope(lines,start,finish,this));
			return;
		}

		throw new BadLineSyntaxException(firstline);

	}

	private static String getInsideBrackets(String line){
		int first = line.indexOf("(")+1;
		int second = line.lastIndexOf(")");
		return line.substring(first, second);
//		return line=line.trim().substring(line.indexOf("(")+1,line.lastIndexOf(")")).trim();
//		return line=line.trim().substring(line.indexOf("("),line.lastIndexOf(")")-1).trim();	//TODO CHECK BRACKETs
	}



	/*
	 * recives relevant lines for method, takes it apart, and
	 * creates a new methodscope while checking all its variables
	 * 
	 */
	private void methodInput (ArrayList<String> lines,int start, int finish) throws DoubleMethodException, BadTypeException, VarExistException  {

		String tempLine=lines.get(start).trim();
		ArrayList<Variable> inputVars=new ArrayList<Variable>();

		String returnType=tempLine.substring(0, tempLine.indexOf(" "));
		String methodName=tempLine.substring(tempLine.indexOf(" "), tempLine.indexOf("(")).trim();
		//String[] insideBrackets=getInsideBrackets(tempLine).split(",");

		for (Scope i:innerScopes){
			MethodScope method=(MethodScope) i;

			if (method.getNameOfMethod().equals(methodName)){
				throw new DoubleMethodException(lines.get(start));

			}
		}
		String insideBracketsExp=getInsideBrackets(tempLine);
		if (!insideBracketsExp.matches(RegexConfig.BLANK_LINE)) {
			String[] insideBrackets=getInsideBrackets(tempLine).split(",");
			String type;
			String name;
			for (String i:insideBrackets){

				Pattern p = Pattern.compile(RegexConfig.VALID_TYPES_METHOD);
				Matcher m = p.matcher(i);			
				m.find();
				type = i.substring(m.start(), m.end());
				name = i.substring(m.end());

				//				String[] TypeAndName = i.trim().split(" ");
				Variable tempVar = new Variable(type, name);
				tempVar.setInitialized(true);
				inputVars.add(tempVar);
			}

		}		

		innerScopes.add(new MethodScope (lines,start,finish,
				Type.createType(returnType),methodName,inputVars, this));

	}

}
