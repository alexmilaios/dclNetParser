package rules;

import java.util.Vector;

public class NegativeLiteral extends Literal {

	Predicate predicate;
	
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
	
}
