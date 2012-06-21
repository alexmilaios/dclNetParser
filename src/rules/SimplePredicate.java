package rules;



public class SimplePredicate extends Predicate {

	
	public SimplePredicate(Constant name, Term_List terms) {
		super(name, terms);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String evaluate(boolean timeplus,boolean head) {
		String result="";
		result += this.name.evaluate();
		result += "(Self_,";
		result += this.terms.toString();
		result += (timeplus == true) ? "Now_+1)": "Now_)";
		return result;
		// TODO Auto-generated method stub

	}

	@Override
	public String simpleEvaluate() {
		String result="";
		result += this.name.evaluate();
		result += "(";
		String term = this.terms.toString();
		result += term.substring(0,term.length()-1);
		result += ")";
		return result;
	}

}
