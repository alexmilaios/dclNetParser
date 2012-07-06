package rules;

import parser.Token;

public class AgrFunction extends Function {

	public AgrFunction(Token name, Term_List arguments) {
		super(name, arguments);
		// TODO Auto-generated constructor stub
	}
	
	public String forAgrFunction(Predicate head,Literal_list body, int position){
		
		String auxHead = "";
		auxHead = head.getName().evaluate()+"_"+this.getToken().toString();
		Term_List arguments = head.getTerms();
		
		arguments.remove(position);
		arguments.addElementAt(this.getVariable(), arguments.size());
		//System.out.println(arguments);
		
		SimplePredicate aux_head = new SimplePredicate(new Constant(auxHead), arguments);
		
		Literal_list aux_body = body.getLiteralsWithVariable(this.getVariable());
		aux_body.removeAt(aux_body.size()-1);
		TransientRule aux_rule = new TransientRule(aux_head, aux_body);
		String result = aux_rule.evaluate();
		
		
		result += head.getName().evaluate();
		head.terms.remove(head.terms.size()-1);
		// System.out.println(position);
		result += "(Self_," + this.evaluate() + "," + head.terms + "Now_)";
		result += " :- node(Self_),time(Now_),";
		aux_head.terms.removeVal(this.getVariable());
		aux_head.terms.addElement(new Variable(this.getVariable().evaluate() + "_plus"));
		result += aux_head.evaluate(false, false);
		result += "," + this.evaluate()+ " := #"; 
		result += this.getToken().toString();
		result += (this.getToken().toString().equals("count")) ? " { " : " [ ";
		aux_head.terms = aux_head.terms.removeVal(new Variable(this.getVariable().evaluate() + "_plus"));
		aux_head.terms.addElement(new Variable(this.evaluate()));
		result += aux_head.evaluate(false, false);
		result += " : " + body.elementAt(body.size()-1).evalute();
		result += (this.getToken().toString().equals("count")) ? " }." : " ].";
		return result;
	}
	
	@Override
	public String evaluate() {
		// TODO Auto-generated method stub
		String val = name.toString();
		String newVal = Character.toUpperCase(val.charAt(0))+val.substring(1);
		String arg = arguments.toString();
		newVal +=  arg.substring(0, arg.length()-1);
		return newVal;
	}
}
