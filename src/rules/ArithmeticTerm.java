package rules;

import parser.Token;

public class ArithmeticTerm extends Term {

	Token left, right, operator;
	
	public ArithmeticTerm(Token left, Token operator, Token right) {
		this.left = left;
		this.operator = operator;
		this.right = right;
	}
	
	@Override
	public String evaluate() {
		return left.toString() + " " + operator.toString() + " " +right.toString();
	}

}
