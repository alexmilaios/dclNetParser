package rules;

import java.util.Vector;

public class TransientRule extends Rule{

	public TransientRule(Predicate _head, Literal_list _body) {
		super(_head, _body);

	}

	
	public String evaluate() {
		Vector<TransientRule> aux = this.checkValidity();
		String output = "";
		if(head.testforAgrFunction())
			if(body.size()>1)
				output = head.getArgFunction().forAgrFunction(head,body);
			else
				output = "Error one argument at the body. The type of the variable should be given";
		else{
			for(int i = 0; i < aux.size(); i++) { 
				output += aux.elementAt(i).evaluate()+"\n";
			}
			output += head.evaluate(false,true);
			output += " :- node(Self_), time(Now_)";
			output += body.toString();
			output += ".";
		}
		return output;
	}
}
