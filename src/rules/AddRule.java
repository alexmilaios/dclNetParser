package rules;

import java.util.Vector;

public class AddRule extends Rule{

	public AddRule(Predicate _head, Literal_list _body) {
		super(_head, _body);
	}
	
	public String evaluate(){
		
		Vector<TransientRule> aux = this.checkValidity();
		String output = "";
		
		for(int i = 0; i < aux.size(); i++) { 
			output += aux.elementAt(i).evaluate()+"\n";
		}
		
		output += head.evaluate(true,true);
		output += " :- node(Self_),time(Now_)";
		output += body.toString();
		output += ".";
		return output;
		
	}
}
