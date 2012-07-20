package rules;

import parser.Token;

public class Arithmetic extends Term {

	public Token leftPart, operator,rightPart;
	
	public Arithmetic(Token leftPart) {
		this.leftPart = leftPart ;
		this.operator = null;
		this.rightPart = null;
	}
	
	public Arithmetic(Token operator, Token rightPart){
		this.leftPart =  null;
		this.operator = operator;
		this.rightPart = rightPart;
	}
	
	public Arithmetic(Token left,Token operator, Token rightPart){
		this.leftPart =  left;
		this.operator = operator;
		this.rightPart = rightPart;
	}
	
	@Override
	public String evaluate() {
		if (rightPart == null) {
			return leftPart.toString();
		}
		else if (leftPart == null) {
			return operator.toString() + rightPart.toString();
		}
		else{
			return leftPart.toString() + operator.toString() + rightPart.toString();
		}
	}

}
