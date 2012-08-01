package rules;

import java.util.Vector;


public abstract class Predicate {

	protected Constant name;
	protected Term_List terms;
	
	public Predicate(Constant name,Term_List terms) {
		this.name = name;
		this.terms = terms;
	}
	
	public Constant getName(){
		return name;
	}
	
	public Term_List getTerms(){
		return terms;
	}
	
	public int testforAgrFunction(){
		for(int i = 0; i < terms.size(); i++){
			if( terms.elementAt(i) instanceof AgrFunction2)
				return i;
		}
		return -1;
	}
	
	public AgrFunction2 getArgFunction(int index){
		return (AgrFunction2) terms.elementAt(index);
	}
	
	public Vector<Variable> getVariables(){
		Vector<Variable> list = new Vector<Variable>();
		for(int i = 0; i < terms.size(); i++){
			if(terms.elementAt(i) instanceof Variable){
				list.add((Variable)terms.elementAt(i));
			}
		}
		if(this instanceof TransportPredicate){
			TransportPredicate temp = (TransportPredicate) this;
			if(temp.getSrcDest() instanceof Variable){
				list.add((Variable) temp.getSrcDest());
			}
		}
		return list;
	}
	
	public boolean containsVar(Variable var){
		for(int i = 0; i < terms.size(); i++){
			if(terms.elementAt(i).evaluate().equals(var.evaluate())){
				return true;
			}
		}
		return false;
	}
	
	public int containsVarIndex (Variable var){
		for(int i = 0; i < terms.size(); i++){
			if(terms.elementAt(i).evaluate().equals(var.evaluate())){
				return i;
			}
		}
		// never be here
		return -1;
	}
	public void changeVariable(Variable var, int index){
		terms = terms.removeVal(var);
		terms.addElementAt(new Variable("Self_"), index);
	}
	
	abstract public String simpleEvaluate();
	
	abstract public String evaluate(boolean timeplus,boolean head);
}
