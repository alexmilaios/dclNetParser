package rules;

import java.util.Vector;

public class SendRule extends Rule {

	public SendRule(Predicate _head, Literal_list _body) {
		super(_head, _body);
		
	}

	public String evaluate() {
		
		Vector<TransientRule> aux = this.checkValidity();
		String output = "";
		
		if(body.curent_host() != -1){
			Current_host host = (Current_host) body.elementAt(body.curent_host());
			Variable var = host.getVariable();
			head.changeVariable(var, head.containsVarIndex(var));
			body.removeAt(body.curent_host());
		}
		
		for(int i = 0; i < aux.size(); i++) { 
			output += aux.elementAt(i).evaluate();
		}
		output += head.evaluate(true,true);
		output += " :- \n\t\tnode(Self_),\n\t\ttime(Now_)";
		output += body.toString();
		output += ".";
		return output;
	}
}
