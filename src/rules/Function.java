package rules;

import parser.Token;

public class Function extends Term {

	protected Token name;
	protected Term_List arguments; 
	
	public Function(Token name, Term_List arguments){
		this.name = name;
		this.arguments = arguments;
	}
	
	public Token getToken(){
		return name;
	}
	
	public Term_List getTerm_List(){
		return arguments;
	}
	
	public Variable getVariable() {
		return (Variable) arguments.elementAt(0);
	}
	
	@Override
	public String evaluate(){
		return null;
	}

}
