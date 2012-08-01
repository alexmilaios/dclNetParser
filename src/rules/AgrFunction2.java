package rules;



import parser.Token;

public class AgrFunction2 extends Function {

	public AgrFunction2(Token name, Term_List arguments) {
		super(name, arguments);
	}

	public String evaluateAgrFun(Predicate head,Literal_list body, int position, Rule rule) {
		
		Term_List head_arguments = head.getTerms();
		
		head_arguments.remove(position);
		
		Literal_list body_without_domains = removeDomains(arguments.size(), body); 
		
		Literal_list list_with_vars = body_without_domains.getLiteralsWithVariable(arguments);
		Literal_list list_without_vars = body_without_domains.getLiteralsWithoutVariable(arguments);
		
		TransientRule aux_rule = auxTransientRule(list_with_vars, head);
		Literal_list aux_list = null;
		String result = "";
		
		if(aux_rule != null){
			result += aux_rule.evaluate();
			aux_list = new Literal_list();
			aux_list.addElement(new SimpleLiteral(aux_rule.head));
		}
		
		Term_List newHeadTerms = head.terms;
		newHeadTerms.addElementAt(new Variable("ArgRes"),newHeadTerms.size());
		SimplePredicate newHead = new SimplePredicate(head.name,newHeadTerms);

		Literal_list initBody = modifiyVariable((aux_rule == null) ? list_with_vars : aux_list);
		list_without_vars.concatenate(initBody);
		
		if(rule instanceof SendRule) {
			result += "send ";
		}
		
		if(rule instanceof DeleteRule) {
			result += "forget ";
		}
		
		result += (rule instanceof AddRule || rule instanceof SendRule) ? 
				newHead.evaluate(true, true) : 
				newHead.evaluate(false, false);

		result += " :- \n\t\tnode(Self_),\n\t\ttime(Now_)"+ list_without_vars.toString();
		result += "\n\t\tArgRes := #" + name.toString() +
				((name.toString().equals("count")) ? " {\n\t\t\t" : " [\n\t\t\t");

		list_with_vars = unModifiyVariable(list_with_vars);

		result += (aux_rule == null) ? 
					list_with_vars.elementAt(0).evalute() 
					: aux_list.elementAt(0).evalute();
					
		for(int i=0; i < arguments.size() ;i++) {
			result += "\n\t\t\t: " + body.elementAt(i + body.size()-arguments.size()).evalute();
		}
		result +=  (name.toString().equals("count")) ? "\n\t\t}." : "\n\t\t\t= Var\n\t\t].";
		return result;

	}
	
	private Literal_list modifiyVariable(Literal_list list){
		Literal_list newList = new Literal_list();
		
		for(int i = 0; i < list.size(); i++) {
			Literal tmpLiteral = list.elementAt(i);
			if(tmpLiteral instanceof SimpleLiteral || tmpLiteral instanceof NegativeLiteral){
				SimplePredicate predicate = (SimplePredicate) tmpLiteral.predicate;
				Term_List tmpTerms = predicate.terms;
				for(int j = 0; j < arguments.size(); j++) {
					Variable tmpVar = (Variable) arguments.elementAt(j);
					tmpTerms.removeVal2(tmpVar);
					tmpTerms.addElementAt(new Variable("_"+tmpVar.evaluate()),j);
				}
			}
			newList.addElement(tmpLiteral);
		}
		return newList;
	}
	
	private Literal_list unModifiyVariable(Literal_list list){
		Literal_list newList = new Literal_list();
		
		for(int i = 0; i < list.size(); i++) {
			Literal tmpLiteral = list.elementAt(i);
			if(tmpLiteral instanceof SimpleLiteral || tmpLiteral instanceof NegativeLiteral){
				SimplePredicate predicate = (SimplePredicate) tmpLiteral.predicate;
				Term_List tmpTerms = predicate.terms;
				for(int j = 0; j < arguments.size(); j++) {
					Variable tmpVar = new Variable("_"+arguments.elementAt(j).evaluate());
					tmpTerms.removeVal2(tmpVar);
					tmpTerms.addElementAt(new Variable(arguments.elementAt(j).evaluate()),j);
				}
			}
			newList.addElement(tmpLiteral);
		}
		return newList;
	}
	
	private TransientRule auxTransientRule(Literal_list aux_body, Predicate head) {
		if(aux_body.size() > 1) {
			SimplePredicate aux_head = new SimplePredicate(new Constant("aux_" + head.name.evaluate()),arguments);
			return new TransientRule(aux_head, aux_body);
		}else
			return null;
	}
	
	private Literal_list removeDomains(int index, Literal_list body) {
		Literal_list list = new Literal_list();
		for(int i = 0; i < ((body.size() - index)) ; i++) {
			list.addElement(body.elementAt(i));
		}
		return list;
	}
}
