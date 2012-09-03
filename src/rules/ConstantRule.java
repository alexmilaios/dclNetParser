package rules;

import parser.Token;

public class ConstantRule extends Rule {

	Token cons;
	public ConstantRule(Predicate _head, Literal_list _body, Token cons) {
		super(_head, _body);
		this.cons = cons;
	}

	@Override
	public String evaluate() {
		return cons.toString();
	}

}
