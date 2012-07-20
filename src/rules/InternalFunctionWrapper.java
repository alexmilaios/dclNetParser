package rules;

public class InternalFunctionWrapper extends Term {
	
	
	private InternalFunction fun;
	
	public InternalFunctionWrapper(InternalFunction fun) {
		this.fun = fun;
	}
	

	@Override
	public String evaluate() {
		// TODO Auto-generated method stub
		return fun.evaluate();
	}

}
