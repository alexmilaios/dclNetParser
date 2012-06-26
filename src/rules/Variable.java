package rules;

import parser.Token;

public class Variable extends Identifier {

	String var;
	
	public Variable(Token token){
		super(token);
	}
	
	public Variable(String var){
		super(null);
		this.var = var;
	}
	
	
	@Override
	public String evaluate() {
		
		return (token != null) ? token.toString() : var;
	}
}
