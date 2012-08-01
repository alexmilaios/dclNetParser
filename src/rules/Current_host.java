package rules;


public class Current_host extends Literal {

	private Variable var;
	
	public Current_host(Variable var){
		this.var = var;
	}
	
	public Variable getVariable(){
		return var;
	}
	
	@Override
	public String simpleEvaluate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String evalute() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean equals(Object object) {
		Literal literal = (Literal) object;
		return (this.evalute().equals(literal.evalute()));
	}

}
