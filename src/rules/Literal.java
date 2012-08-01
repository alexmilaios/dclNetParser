package rules;

public abstract class Literal {

	public Predicate predicate;
	
	public abstract String simpleEvaluate();
	
	public abstract String evalute();
	
	public abstract boolean equals(Object object); 
}
