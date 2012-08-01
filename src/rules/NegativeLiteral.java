package rules;

import java.util.Vector;

public class NegativeLiteral extends Literal {

	public NegativeLiteral(Predicate predicate) {
		this.predicate = predicate;  
	}
	@Override
	public String evalute() {
		String result ="";
		result = "not ";
		return (result+predicate.evaluate(false,false));
	}
	// Should not be called
	@Override
	public String simpleEvaluate() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Predicate getPredicate(){
		return predicate;
	}
	
	public Vector<Variable> getVariables() {
		return predicate.getVariables();
	}

	public boolean containsVar(Variable var){
		return predicate.containsVar(var);
	}
	
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
