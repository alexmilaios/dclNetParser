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
	
	public boolean equals(Object obj) {
		return this.evaluate().equals(((Variable) obj).evaluate());
	}
	
	@Override
	public String evaluate() {
		
		return (token != null) ? token.toString() : var;
	}
}
