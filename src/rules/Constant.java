package rules;

import parser.Token;
public class Constant extends Identifier {
	
	private String value;
	
	public Constant(Token token){
		super(token);
	}
	
	public Constant(String name){
		super(null);
		value = name;
	}
	@Override
	public String evaluate() {
		// TODO Auto-generated method stub
		return (token != null) ? token.toString() : value;
	}

}
