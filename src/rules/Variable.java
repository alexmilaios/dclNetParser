package rules;

import parser.Token;

public class Variable extends Identifier {

	
	public Variable(Token token){
		super(token);
	}
	
	@Override
	public String evaluate() {
		return token.toString();
	}
}
