package oop.ex7.scope;

import java.util.ArrayList;
import java.util.regex.*;

import oop.ex7.main.*;
import oop.ex7.type.*;

/**
 * The enum lineTypes represent the type of the line in the Scope. lines in 
 * the different scopes have basic possibilities and this enum tells which type
 * of line is it.
 *
 */
enum lineTypes {SCOPE, VARIABLE};

/**
 * This class is a father class for all other scope types in the program. This 
 * class is an abstract class and implements the interface Scopemediator. 
 * This class represents and holds all the joint properties of all scopes in 
 * the program.      
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

	//Contructor for inheriting classes
	Scope (ArrayList<String> lines,int begin,int end, Scope father){
		this.fatherScope = father;
		this.fileLines = lines;
		this.startIndex = begin;
		this.endIndex = end;
		this.innerScopes = new ArrayList<Scope>();
		this.innerVariables = new ArrayList<Variable>();
	}
	
	/**
	 * This method is responsible for compiling all scopes in the file. 
	 * The method represents the compiling process for all scopes besides the 
	 * Class Scope which implements this class also. Throws CompileException 
	 * that is a father class for all Exception classes mentioned.  
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
	public  void compileScope() throws CompileException  {
		//will save the line type returned from the enum.
		int lineType;

		//go over all lines in the file except the first and last line (the
		//declaration of the scope and the "}" closing line of the scope).
		for(int i=this.startIndex+1;i<this.endIndex;i++){
			//throws if not valid scope declaration or Variable type line.
			lineType=FileParser.scopeOrVariable(fileLines.get(i),i);
			//if line handles Variable: 
			if (lineType==lineTypes.VARIABLE.ordinal()){
				//Analyze the line.
				lineAnalizerOp(fileLines.get(i));
			}

			else if (lineType==lineTypes.SCOPE.ordinal()){
				//find the index of the last line. throws if end of file is
				//reached.
				int closer = FileParser.findLastCloser(fileLines,i);
				//Analyze the line.
				lineAnalizerSc(fileLines,i,closer);

				//advance the index of the line to after the scope.
				i=closer;
				
				//run compileScope on the scope added in lineAnalizer.
				this.innerScopes.get(this.innerScopes.size()-1).compileScope();
			}
			
			else{
				throw new BadEndOfLineException(i, fileLines.get(i));
			}	
		}
	}

	/**
	 * Getter - innerVariables
	 */
	public ArrayList<Variable> getVariables(){
		return this.innerVariables;
	}

	/**
	 * Getter - innerScopes
	 */
	public ArrayList<Scope> getScopes(){
		return this.innerScopes;
	}

	/**
	 * Getter - fatherScope
	 */
	public Scope getFatherScope(){
		return this.fatherScope;
	}

	/**
	 * Analyzes the line if it is a operation line that is  Variable related,  
	 * or method call related. this method checks exactly what type of line is
	 * it and checks whether or not the line is legal according to the text.
	 * @param line - the line to analyze
	 * @throws BadReturnException
	 * @throws BadLineSyntaxException
	 * @throws BadTypeException
	 * @throws VarNotExistException
	 * @throws VarExistException
	 * @throws BadInputException 
	 */
	public void lineAnalizerOp(String line) throws  BadReturnException, 
				BadLineSyntaxException, BadTypeException, VarNotExistException, 
										VarExistException, BadInputException  {
		
		/*
		 * 5 cases- return, assign,initialize, declaration and assign (=both),
		 * method call 
		 */
		
		//return ("return 7:")
		if (line.matches(RegexConfig.lineType.RETURN.getRegex())){
			
			Pattern p = Pattern.compile("return");
			Matcher m = p.matcher(line);
			m.find(); //find the appearance of return in the line:
			
			//get only return expression itself. for example, if the line is:
			//"return 7;", return expression will be "7";
			String returnExpression = 
							line.substring(m.end(), line.indexOf(";")).trim();

			//check whether or not the return expression type matches the type
			//of the father method scope. false if no match.
			if(!handleReturn(returnExpression)){
				throw new BadReturnException(line);
			}
		}

		//assign ("a = 7;")
		else if (line.matches(RegexConfig.lineType.ASSIGNMENT.getRegex()) || 
			line.matches(RegexConfig.lineType.ASSIGNMENT_ARRAY.getRegex())){
			
			//continue process the assignment line.
			assignmentLine(line);
		}
		
		//initialize ("int a;")
		else if (line.matches(RegexConfig.lineType.DECLARATION.getRegex()) || 
								line.matches(RegexConfig.ARRAY_DECLARE)){

			//continue process the declaration line.
			declarationLine(line);
		}
		//declaration and assign ("int a = 7;")
		else if (line.matches(RegexConfig.lineType.BOTH.getRegex()) || 
				line.matches(RegexConfig.lineType.BOTH_ARRAY.getRegex())){

			//continue process the declaration and assign line.
			bothLine(line);

		}
		//method call ("foo(7)")
		else if (line.matches(RegexConfig.METHOD_CALL)) {
	
			//continue process the method call line.
			this.checkMethod(line);
		}
		
		//no match!
		else {
			throw new BadLineSyntaxException(line);	
		}
	}

	/*
	 * Checks if the method called in the line exists in the class and if it 
	 * called properly. For example, if the method call is "foo(7)", this 
	 * method checks if there is a method in the name of "foo", and after it 
	 * finds the method in the scopes list of the class, it checks if the 
	 * input variable in the call is the same as the method declaration. in 
	 * the example case, the input variable called is 7, and int, so the method 
	 * will checked if the input variable of the method foo was declared with 1
	 * input variable and that this input variable is of the type int.
	 * @param line - the line of the method call.
	 * @throws BadLineSyntaxException
	 * @throws BadTypeException
	 * @throws VarNotExistException
	 * @throws VarExistException   
	 */
	private void checkMethod(String line) throws VarNotExistException, 
			BadLineSyntaxException, BadTypeException, VarExistException  {
		
		//tempScope to get to the class scope.
		Scope tempScope = this; 
		
		while(tempScope.getFatherScope() != null) {
			tempScope = tempScope.getFatherScope();
		}
		//tempScope is now class scope.
		ClassScope classScope = (ClassScope) tempScope;
		//go over all innerScopes of the class (only methods!)
		for (Scope sc:classScope.innerScopes) {
			//cast is legal always - only methods inside class.
			MethodScope tempMethod = (MethodScope) sc;
			//the call name of the method from the text. "foo(7)" -> "foo".
			String callName = MethodScope.getMethodCallNameFromExp(line);
			//check if there is a method in the class with the same name.
			if (tempMethod.getNameOfMethod().equals(callName)) {
				//save variables in the call . "foo(7, 8)" -> ["7","8"]
				String[] varsCall = MethodScope.getMethodVarsFromCallExp(line);
					
				//case for empty call and empty method in the class - legal.
				if (varsCall.length==1 && varsCall[0].equals("") && 
						tempMethod.inputVars.isEmpty()) {
					
					return;
				}
				//check if num of variables in the call matches the num of 
				//variables in the real method.
				if (varsCall.length != tempMethod.innerVariables.size()) {
					throw new VarNotExistException(callName); //TODO
				}
				//for every variable in the input variables of the method, 
				//check if the variable called matches the type of the input.
				for (int i = 0; i < tempMethod.inputVars.size(); i++) {
					//throws if not matches.
					FileParser.checkExpression
					(tempMethod.inputVars.get(i).getType(), varsCall[i], this);
				}
				
				return;
			}
		}
		throw new VarNotExistException(line); 
	}

	/*
	 * checks if the return type of the mathod call matches the return type
	 * of the method reffered to.
	 * @param returnExpression - only the return expression itself: if the call
	 * is "return 7;", then the returnExpression param will be "7".
	 */
	private boolean handleReturn(String returnExpression) 
		throws BadLineSyntaxException, BadTypeException, VarNotExistException, 
									VarExistException, BadInputException {
		
		//if this scope is class scope:
		if (fatherScope==null){
			return false;
		}
		//if reached method scope:
		if (fatherScope.fatherScope == null) {
			//cast always legal! the if checks this exactly.
			MethodScope method = (MethodScope) this;
			
			//if the return type is void:
			if (method.getReturnType().getRegex().
											equals(RegexConfig.INPUT_VOID)) {
				
				if (returnExpression.matches(RegexConfig.INPUT_VOID)){
					return true;
				}
				throw new BadInputException(new VoidType());
			}

			//if not void, check if matches to other types possible
			FileParser.checkExpression(method.getReturnType(), 
												returnExpression, method);
			
			return true;
		}
		//recursive until reaches method.
		return fatherScope.handleReturn(returnExpression);
	}


	/*
	 * This method executes all action related to an assignment line type 
	 * analysis. checks if the variables to which we are trying to assign 
	 * exists and if the type of the expression on the right side of the assign
	 * matches the type of the variable.
	 * @param line - the assignment line from the file 
	 */
	private void assignmentLine(String line) throws VarExistException, 
			BadTypeException, BadLineSyntaxException, VarNotExistException  {

		Variable varTemp;
		//divide the strings in the expressions to left expression and right 
		//expression.
		String[] stringsInLine = getAssigmentStr(line);		

		//get the full name - if it is an array it will be different from the 
		//name of the variable.
		String fullName = stringsInLine[0];
		//find the name of the variable called in the text.
		Pattern p = Pattern.compile(RegexConfig.GENERAL_NAME);
		Matcher m = p.matcher(fullName);
		m.find();
		//get the name of the var.
		String nameOfVar = fullName.substring(m.start(), m.end());
		
		String inputValue = stringsInLine[1];

		//if the variable exists, somewhere in the code (only relevant scopes
		//will be checked), put it into varTemp, else, put null into varTemp.
		varTemp = this.varExistInAll(nameOfVar);

		//if the variable doesn't exist:
		if (varTemp == null) {
			throw new VarExistException(line);
		}
		//check if the variable called is an array.
		if (varTemp.getType().sameType(new ArrayType())) {
			
			ArrayType type = (ArrayType) varTemp.getType();
			//if the call is an array
			if (!fullName.equals(nameOfVar)) {
				//compare the inner variable of the array.
				FileParser.checkInnerArrVarPos(fullName, this);
				FileParser.checkExpression(type.getInnerType(), inputValue, 
																		this);
				
				return;
			}
			
			FileParser.checkExpression(varTemp.getType(), inputValue, this);
			
			String cleanInputVals = inputValue.replaceAll("[\\{\\}]", "");
			
			if (cleanInputVals.matches(RegexConfig.BLANK_LINE)) {
				return;
			}
			String[] inputValues = cleanInputVals.split(",");

			//check if the inner variables in the call match in type to the
			//type of the array.
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

	/*
	 * This method executes all action related to an declaration line type 
	 * analysis. checks if the variables we are trying to declare already 
	 * exists and what type of variable it will be.
	 * @param line - the declaration line from the file 
	 */
	private void declarationLine(String line) throws VarExistException, 
															BadTypeException {

		
		Variable varTemp;
		//same as assignment
		String fullType = getDecStr(line)[0];
		//finds the type of the declared variable.
		Pattern p = Pattern.compile(RegexConfig.VALID_TYPES);
		Matcher m = p.matcher(line);
		m.find();
		
		String typeOfVar = line.substring(m.start(), m.end());
		int lastEnd = m.end();
		p = Pattern.compile(RegexConfig.GENERAL_NAME);
		m = p.matcher(line);
		m.find(lastEnd);
		String nameOfVar = line.substring(m.start(), m.end()).trim();

		//if the variable exists, put it into varTemp, else, put null into 
		//varTemp.
		varTemp = this.varExist(nameOfVar);
		//if the variable doesn't exist:
		if (varTemp == null) {
			//if the variable is an array:
			if (line.matches(RegexConfig.ARRAY_DECLARE) || 
					line.matches(RegexConfig.ARRAY_DECLARE_WITH_SEMICOLON)) {
				this.innerVariables.add(new Variable(fullType, nameOfVar));
			}
			else {
				this.innerVariables.add(new Variable(typeOfVar, nameOfVar));
			}

			return;
		}
		throw new VarExistException(line);	
	}

	/*
	 * like assingment and daclare, but for both actions.
	 * @param line - the declaration and assign line from the file 
	 */
	private void bothLine(String line) throws VarExistException, 
			BadTypeException, BadLineSyntaxException, VarNotExistException  {

		String linetemp = line.trim();
		String[] stringsInLine = getBothStr(linetemp);
		
		String decLine = stringsInLine[0];
		String assLine = stringsInLine[1];
		
		this.declarationLine(decLine);
		this.assignmentLine(assLine);	

	}

	/*
	 * This method return an array of the left and right expressions from an
	 * assignment line type.
	 * @param line - the line of code.
	 */
	private static String[] getAssigmentStr(String line) {
		//1 - index of input value
		String linetemp = line.trim();
		String[] stringsInLine = linetemp.split("=");
		stringsInLine[1] = stringsInLine[1].trim();
		stringsInLine[1] = stringsInLine[1].replaceAll("( )*;?", "");
		return stringsInLine;
	}

	/*
	 * This method return an array of the type and name of the variable 
	 * declared  from an declared line type.
	 * @param line - the line of code.
	 */
	private static String[] getDecStr(String line) {
		//0 - index of type of var.
		//1 - index of name of var.
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

	/*
	 * This method divides the both line to declaration and assignment lines.
	 * uses the declaration and assign methods. 
	 * @param line - the line of code.
	 */
	private static String[] getBothStr(String line) {

		String declarationLine =  getAssigmentStr(line)[0]+";";
		String[] declaration = getDecStr(declarationLine);

		String inputValue = getAssigmentStr(line)[1];
		
		String decLine = declaration[0]+" "+declaration[1];
		String assLine = declaration[1]+"="+inputValue;
		String[] both = {decLine, assLine};
		return both;

	}

	/**
	 * checks if the variable, that is represented by its name - a string input
	 * value, already exists in the current scope.
	 * @param nameOfVar - the variable name which we want to check if exists 
	 * in the current scope.
	 * @return the variable we searched for if found it, null if variable does 
	 * not exist.
	 */
	 protected Variable varExist(String nameOfVar) {

		Scope tempScope = this; 

		for (Variable varOfScope:tempScope.getVariables()) {
			if (varOfScope.getName().equals(nameOfVar.trim())) {
				return varOfScope;
			}
		}
		return null;
	}

	 /**
	  * like varExist method, but checks in the current scope and all father 
	  * scopes of the current method. uses varExist method.
	  * @param nameOfVar - the variable we want to see if exists or not.
	  * @return the variable we searched for if found it, null if variable does 
	 * not exist.
	  */
	protected Variable varExistInAll(String nameOfVar) {

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


	
	/**
	 * Analyzes the line if it is a scope line that is scope related. this 
	 * method checks exactly what type of line is it and checks whether or not
	 * the line is legal according to the text. if legal, calls other methods
	 * for creating scopes and handles their variables and so on.
	 * @param lines - file lines
	 * @param start - index of the first line of the scope.
	 * @param finish - index of the last line of the scope.
	 * @throws InvalidScopeException
	 * @throws BadLineSyntaxException
	 * @throws BadTypeException
	 * @throws VarNotExistException
	 * @throws VarExistException
	 * @throws DoubleMethodException
	 */
	protected void lineAnalizerSc (ArrayList<String> lines,int start, 
			int finish) throws InvalidScopeException, BadLineSyntaxException, 
			BadTypeException, VarNotExistException, VarExistException, 
													DoubleMethodException {

		String firstline=lines.get(start);


		//method scope
		if (firstline.matches(RegexConfig.VALID_METHOD_DECLARE)){

			if (fatherScope!=null){
				throw new InvalidScopeException(start);
			}
			//creates the method
			methodInput(lines, start, finish);
		}
		
		//while scope
		else if (firstline.matches(RegexConfig.VALID_WHILE_CALL)){
			if (fatherScope==null){
				throw new InvalidScopeException(start);
			}
			//checks if the argument inside the brackets is a boolean.
			FileParser.checkExpression(new BooleanType(), 
										getInsideBrackets(firstline), this);
			innerScopes.add( new WhileScope(lines,start,finish,this));
		}
		//if scope
		else if (firstline.matches(RegexConfig.VALID_IF_CALL)){
			if (fatherScope==null){
				throw new InvalidScopeException(start);
			}
			
			//checks if the argument inside the brackets is a boolean.
			FileParser.checkExpression(new BooleanType(), 
										getInsideBrackets(firstline), this);
			innerScopes.add( new IfScope(lines,start,finish,this));
		}
		//if no matching type line found:
		else {
			throw new BadLineSyntaxException(firstline);	
		}
	}
	
	/*
	 * This method allows you to get a string representation of an expression
	 * that originally was inside of brackets in some expression.
	 * @param line - the line with the brackets expression
	 */
	private static String getInsideBrackets(String line){
		int first = line.indexOf("(")+1;
		int second = line.lastIndexOf(")");
		return line.substring(first, second);
	}



	/*
	 * receives relevant lines for method, takes it apart, and
	 * creates a new methodscope while checking all its variables
	 */
	private void methodInput (ArrayList<String> lines,int start, int finish) 
			throws DoubleMethodException, BadTypeException, VarExistException {

		//get the first line of the scope - the method declaration line
		String tempLine=lines.get(start).trim();
		ArrayList<Variable> inputVars=new ArrayList<Variable>();
		
		//get return type
		String returnType=tempLine.substring(0, tempLine.indexOf(" "));
		//get method name
		String methodName=tempLine.substring(tempLine.indexOf(" "), 
												tempLine.indexOf("(")).trim();
		
		//check if this name is another already existing methods name
		for (Scope i:innerScopes){
			MethodScope method=(MethodScope) i;

			if (method.getNameOfMethod().equals(methodName)){
				throw new DoubleMethodException(lines.get(start));

			}
		}
		//get the expression inside the brackets in the declaration
		String insideBracketsExp=getInsideBrackets(tempLine);
		//if inside brackets not blank line
		if (!insideBracketsExp.matches(RegexConfig.BLANK_LINE)) {
			String[] insideBrackets=getInsideBrackets(tempLine).split(",");
			String type;
			String name;
			//create and add all the input variables to the method.
			for (String i:insideBrackets){

				Pattern p = Pattern.compile(RegexConfig.VALID_TYPES_METHOD);
				Matcher m = p.matcher(i);			
				m.find();
				type = i.substring(m.start(), m.end());
				name = i.substring(m.end());

				Variable tempVar = new Variable(type, name);
				tempVar.setInitialized(true);
				inputVars.add(tempVar);
			}

		}		

		innerScopes.add(new MethodScope (lines,start,finish,
				Type.createType(returnType),methodName,inputVars, this));

	}

}
