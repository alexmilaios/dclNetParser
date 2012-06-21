package rules;

import java.util.Vector;

public class SimpleLiteral extends Literal {

	protected Predicate predicate;
	
	public SimpleLiteral(Predicate predicate) {
		this.predicate = predicate;
	}
	
	public String evalute() {
		return predicate.evaluate(false, false);
		// TODO Auto-generated method stub

	}

	@Override
	public String simpleEvaluate() {
		// TODO Auto-generated method stub
		return predicate.simpleEvaluate();
	}

	public Vector<Variable> getVariables(){
		return predicate.getVariables();
	}
}
