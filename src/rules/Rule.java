package rules;

import java.util.Vector;


public abstract class Rule {

	protected Predicate head; 
	protected Literal_list body;
	protected Vector<Variable> bodyPosVariables ;
	protected Vector<Variable> bodyNegVariables ;
	protected Vector<Variable> headVariables ;
	
	
	Rule(Predicate _head, Literal_list _body){
		head =  _head;
		body = _body;
		this.getVariables();
	}
	
	private void getVariables(){
		bodyPosVariables = body.getPositiveVariables();
		bodyNegVariables = body.getNegationVariables();
		headVariables = head.getVariables();
	}
	
	private boolean contains(Vector<Variable> list, Variable item){
		for(int i = 0; i < list.size(); i++){
			if(list.elementAt(i).evaluate().equals(item.evaluate())){
				return true;
			}
		}
		return false;
	}
	
	private boolean chechUnbuntNegVariables(){
		for(int i = 0; i < headVariables.size(); i++){
			Variable var = headVariables.elementAt(i);
			if(!contains(bodyPosVariables,var) && contains(bodyNegVariables,var)){
				System.out.print("Error: the variable "+ var.evaluate() + " in the head " + head.simpleEvaluate()); 
				System.out.print(" appears only in head and in negative Predicate(s)");
				return true;
			}
		}
		
		return false;
	}
	
	private Variable chechforUnSafty(){
		for(int i = 0; i < bodyNegVariables.size(); i++){
			Variable var = bodyNegVariables.elementAt(i);
			if(!contains(headVariables, var) && !contains(bodyPosVariables, var)){
				return var;
			}
		}
		return null;
	}
	
	
	protected Vector<TransientRule> checkValidity(){
		if (chechUnbuntNegVariables())
			 	return null;
		Variable var;
		Vector<TransientRule> auxRules = new Vector<TransientRule>();
		while((var = chechforUnSafty()) != null){
				Literal_list list =  body.getNegLiteralWithUnsedVariables(var);
				String tmpName = "";
				Term_List auxArg = new Term_List();
				for(int i = 0; i < list.size(); i++){
					NegativeLiteral first = (NegativeLiteral) list.elementAt(i);
					Predicate predicate = first.getPredicate();
					Constant name = predicate.getName();
					tmpName += name.evaluate() + "_";
					Term_List tmpArg = predicate.getTerms().removeVal(var);
					auxArg.addAll(tmpArg);
					auxArg.removeDublicates();
				}
				Constant helpName = new Constant(tmpName+"aux");
				Predicate helpHead = new SimplePredicate(helpName,auxArg);
				body.removeNegLiteras(list);
				body.addElement(new NegativeLiteral(helpHead),0);
				auxRules.addElement(new TransientRule(helpHead, list.getPosLiteral()));
				getVariables();
		}
		return auxRules;
	}
	
	public abstract String evaluate();
}

