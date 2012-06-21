package rules;

import parser.Token;

public class Num extends Term {

	private Token token;
	
	public Num(Token token){
		this.token = token;
	}
	
	@Override
	public String evaluate() {
		return token.toString();
	}

}
