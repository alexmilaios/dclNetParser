package rules;

public class Input extends Rule {

	
	public Input(Predicate _head, Literal_list _body) {
		super(_head, _body);
	}

	@Override
	public String evaluate() {
		String result ="";
		result += head.name.evaluate() +"(Self_," + head.terms.toString() + "Now_) :-";
		result += "\n\t\tnode(Self_),\n\t\ttime(Now_),\n\t\t"+ head.simpleEvaluate();
		return result;
	}

}
