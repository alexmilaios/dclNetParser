package rules;

import parser.Token;

public class Expresion extends Literal {
	
	private Term left_operand, right_operand;
	private Token operator;
	
	public Expresion(Term left, Token oper, Term right) {
		left_operand = left;
		operator = oper;
		right_operand = right;
	}
	
	@Override
	public  String evalute() {
		String result = "";
		result += left_operand.evaluate();
		result += operator.toString();
		result += right_operand.evaluate();
		return result;
		// TODO Auto-generated method stub

	}

	// Should not be called
	@Override
	public String simpleEvaluate() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean equals(Object object) {
		Literal literal = (Literal) object;
		return (this.evalute().equals(literal.evalute()));
	}
}
