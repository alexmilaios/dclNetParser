package rules;

import parser.Token;

public abstract class Identifier extends Term {

	public Token token;
	
	public Identifier(Token token){
		this.token = token;
	}
	
	@Override
	public abstract String evaluate();

}
