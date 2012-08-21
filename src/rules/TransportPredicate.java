package rules;


public class TransportPredicate extends Predicate {

	private Identifier src_dest;
	
	public TransportPredicate(Constant name, Term_List terms, Identifier src_dest) {
		super(name, terms);
		// TODO Auto-generated constructor stub
		this.src_dest = src_dest;
	}
	
	public Identifier getSrcDest(){
		return src_dest;
	}

	@Override
	public String evaluate(boolean timeplus,boolean head) {
		String result="";
		result += (head) ? "send_" : "receive_";
		result += this.name.evaluate();
		result += "(Self_,";
		result += this.terms.toString();
		result += src_dest.evaluate();
		//result += (!head) ? ",_," : ",";
		result += (timeplus == true) ? ",Now_+1)": ",Now_)";
		return result;
	}

	// Not called
	@Override
	public String  simpleEvaluate() {
		// TODO Auto-generated method stub
		return null;
	}

}
