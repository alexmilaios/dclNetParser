package rules;

import parser.Token;

public class AgrFunction extends Function {

	public AgrFunction(Token name, Term_List arguments) {
		super(name, arguments);
		// TODO Auto-generated constructor stub
	}
	
	public String forAgrFunction(Predicate head,Literal_list body, int position, Rule rule){
		boolean control = false;
		
		String auxHead = "";
		auxHead = head.getName().evaluate()+"_"+this.getToken().toString();
		Term_List arguments = head.getTerms();

		arguments.remove(position);
		arguments.addElementAt(this.getVariable(), arguments.size());

		SimplePredicate aux_head = new SimplePredicate(new Constant(auxHead), arguments);

		Literal_list aux_body = body.getLiteralsWithVariable(this.getVariable());
		Literal_list literals_without = body.getLiteralsWithoutVariable(this.getVariable());
		
		//aux_body.removeAt(aux_body.size()-1);
		if(aux_body.size() > 1) {
			TransientRule aux_rule = new TransientRule(aux_head, aux_body);
			String result = aux_rule.evaluate();

			if(rule instanceof DeleteRule)
				result += "forget_";
			if(rule instanceof SendRule)
				result += "send_";
			
			result += head.getName().evaluate();
			head.terms.remove(head.terms.size()-1);
			
			result += "(Self_," + this.evaluate() + "," + head.terms; 
			
			if(rule instanceof AddRule || rule instanceof SendRule) 
				result += "Now_+1)";
			else
				result += "Now_)";
			
			result += " :- node(Self_),time(Now_),";
			aux_head.terms = aux_head.terms.removeVal(this.getVariable());
			aux_head.terms.addElement(new Variable(this.getVariable().evaluate() + "_plus"));
			result += aux_head.evaluate(false, false);
			result += literals_without.toString();
			result += "," + this.evaluate()+ " := #"; 
			result += this.getToken().toString();
			result += (this.getToken().toString().equals("count")) ? " { " : " [ ";
			aux_head.terms = aux_head.terms.removeVal(new Variable(this.getVariable().evaluate() + "_plus"));
			aux_head.terms.addElement(new Variable(this.getVariable().evaluate()));
			result += aux_head.evaluate(false, false);
			result += " : " + body.elementAt(body.size()-1).evalute();
			result += (this.getToken().toString().equals("count")) ? " }." : " ].";
			return result;
		}
		else { 
			String result = "";
			
			if(rule instanceof DeleteRule)
				result += "forget_";
			if(rule instanceof SendRule)
				result += "send_";
			
			result +=  head.getName().evaluate();
			
			head.terms.remove(head.terms.size()-1);
			
			result += "(Self_," + this.evaluate() + "," + head.terms;
			
			if(rule instanceof AddRule || rule instanceof SendRule) 
				result += "Now_+1)";
			else
				result += "Now_)";
			
			result += " :- node(Self_),time(Now_)";
			
			SimpleLiteral literal = (SimpleLiteral) aux_body.elementAt(0);
			
			if(literal.predicate.getVariables().size() > 1) {
				literal.predicate.terms = literal.predicate.terms.removeVal(this.getVariable());
				literal.predicate.terms.addElement(new Variable(this.getVariable().evaluate() + "_plus"));
				result += aux_body.toString();
				control = true;
			}
			result += literals_without.toString();
			
			result += "," + this.evaluate()+ " := #"; 
			result += this.getToken().toString();
			result += (this.getToken().toString().equals("count")) ? " { " : " [ ";
			
			if(control)
				literal.predicate.terms = literal.predicate.terms.removeVal(new Variable(this.getVariable().evaluate() + "_plus"));
			else
				literal.predicate.terms = literal.predicate.terms.removeVal(new Variable(this.getVariable().evaluate()));
			
			literal.predicate.terms.addElement(new Variable(this.getVariable().evaluate()));
			
			result += aux_body.elementAt(0).evalute();
			result += " : " + body.elementAt(body.size()-1).evalute();
			result += (this.getToken().toString().equals("count")) ? " }." : " ].";
			
			return result;
		}
	}
	
	@Override
	public String evaluate() {
		String val = name.toString();
		String newVal = Character.toUpperCase(val.charAt(0))+val.substring(1);
		String arg = arguments.toString();
		newVal +=  arg.substring(0, arg.length()-1);
		return newVal;
	}
}
