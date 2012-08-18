package rules;

public class Persistent extends Rule {

	AddRule inertiaRule;
	
	public Persistent(Predicate _head, Literal_list _body) {
		super(_head, _body);
		
		Literal_list inertiaBody = new Literal_list(); 
		SimpleLiteral posPart = new SimpleLiteral(head);
		Constant negName = new Constant("forget_" + head.name.evaluate());
		SimplePredicate negation = new SimplePredicate(negName,head.terms);
		NegativeLiteral negPart = new NegativeLiteral(negation);
		inertiaBody.addElement(negPart);
		inertiaBody.addElement(posPart);
		
		inertiaRule = new AddRule(head, inertiaBody);
	}
	
	@Override
	public String evaluate() {
		return inertiaRule.evaluate();
	}

}
