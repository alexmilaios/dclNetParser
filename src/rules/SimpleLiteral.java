package rules;

import java.util.Vector;

public class SimpleLiteral extends Literal {
	
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

	@Override
	public boolean equals(Object object) {
		Literal literal = (Literal) object;
		return (this.evalute().equals(literal.evalute()));
	}
	
	public boolean containsVariavles(Term_List list) {
		
		for(int i = 0; i < list.size(); i++){
			if((list.elementAt(i) instanceof Variable) && predicate.containsVar((Variable)list.elementAt(i))){
				return true;
			}
		}
		return false;
	}
}
