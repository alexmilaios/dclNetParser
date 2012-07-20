package rules;

import parser.Token;

public class InternalFunction extends Function {

	public InternalFunction(Token name, Term_List arguments) {
		super(name, arguments);
		// TODO Auto-generated constructor stub
	}

	public String evaluate() {

		String output = "@";
		output += this.name;
		output += "(" + arguments.toString();
		output = output.substring(0, output.length()-1);
		output += ")";
		return output;
	}

}
