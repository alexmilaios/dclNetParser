package rules;

import java.util.Vector;

public class DeleteRule extends Rule {

	public DeleteRule(Predicate _head, Literal_list _body) {
		super(_head, _body);		
	}

	public String evaluate() {
		
		Vector<TransientRule> aux = this.checkValidity();
		String output = "";
		
		for(int i = 0; i < aux.size(); i++) { 
			output += aux.elementAt(i).evaluate()+"\n";
		}
		output += "forget_";
		output += head.evaluate(false,true);
		output += " :- node(Self_),time(Now_)";
		output += body.toString();
		output += ".";
		return output;
	}
}
