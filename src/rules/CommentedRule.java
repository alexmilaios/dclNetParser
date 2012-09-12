package rules;

import parser.Token;

public class CommentedRule extends Rule {

	private Token commetedPart;
	
	public CommentedRule(Predicate _head, Literal_list _body, Token commentedPart) {
		super(_head, _body);
		this.commetedPart = commentedPart;
	}

	@Override
	public String evaluate() {
		String tmr =commetedPart.toString();
		return tmr.substring(3, tmr.length()-4);
	}

}
