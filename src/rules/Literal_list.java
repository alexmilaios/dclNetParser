package rules;

import java.util.Vector;


public class Literal_list {
	
	private Vector<Literal> list;
	
	public Literal_list(){
		list = new Vector<Literal>();
	}
	
	public boolean contains(Literal literal) {
		return list.contains(literal);
	}
	
	public void addElement(Literal item, int index){
		list.add(index, item);
	}
	public void addElement(Literal literal) {
		list.addElement(literal);
	}
	
	public Literal elementAt(int i) {
		return list.elementAt(i);
	}
	
	public int size(){
		return list.size();
	}

	public String toString(){
		String result="";
		for(int i=0;i<list.size();i++){
			result += ",\n\t\t"+ list.elementAt(list.size()-i-1).evalute();
		}
		return result;
	}
	
	private Vector<Variable> removeDublicates (Vector<Variable> list){
		
		for(int i = 0; i < list.size(); i++){
			for(int j = i+1; j < list.size(); j++){
				if(list.elementAt(i).evaluate().equals(list.elementAt(j).evaluate())){
					list.remove(j);
				}
			}
		}
		return list;
	}
	
	public Vector<Variable> getPositiveVariables(){
		Vector<Variable> list = new Vector<Variable>();
		for(int i = 0; i < this.size(); i++){
			if(this.elementAt(i) instanceof SimpleLiteral){
				SimpleLiteral simple = (SimpleLiteral) this.elementAt(i);
				list.addAll(simple.getVariables());
			}
		}
		return this.removeDublicates(list);
	}
	
	public Vector<Variable> getNegationVariables(){
		Vector<Variable> list = new Vector<Variable>();
		for(int i = 0; i < this.size(); i++){
			if(this.elementAt(i) instanceof NegativeLiteral){
				NegativeLiteral neg = (NegativeLiteral) this.elementAt(i);
				list.addAll(neg.getVariables());
			}
		}
		return this.removeDublicates(list);
	}
	
	public Literal_list getNegLiteralWithUnsedVariables(Variable var){
		Literal_list list = new Literal_list();
		for(int i = 0; i < this.size(); i++){
			if(this.elementAt(i) instanceof NegativeLiteral){
				NegativeLiteral item = (NegativeLiteral) this.elementAt(i);
				if(item.containsVar(var))
					list.addElement(item);
			}
		}
		return list;
	}
	
	public String toString(int index){
		String result="";
		for(int i=index;i<list.size();i++){
			result += ","+ list.elementAt(list.size()-i-1).evalute();
		}
		return result;
	}
	
	public Literal_list getPosLiteral(){
		Literal_list list =  new Literal_list();
		
		for(int i = 0; i < this.size(); i++){
			NegativeLiteral lit = (NegativeLiteral) this.elementAt(i);
			list.addElement(new SimpleLiteral(lit.getPredicate()));
		}
		
		return list;
	}
	
	private int findLiteral(Literal item){
		for(int i = 0; i < this.size(); i++)
			if(this.elementAt(i).evalute().equals(item.evalute()))
				return i;
		return -1;
	}
	
	public void removeNegLiteras(Literal_list list){
		for(int i=0; i<list.size(); i++){
			int index = findLiteral(list.elementAt(i));
			this.list.remove(index);
		}
	}
	
	public int curent_host(){
		for(int i = 0; i < this.size(); i++){
			if(this.elementAt(i) instanceof Current_host)
				return i;
		}
		return -1;
	}
	
	public void removeAt(int i){
		list.remove(i);
	}
	
	public void concatenate(Literal_list second) {
		list.addAll(list.size(), second.list);
	}
	
	public Literal_list getLiteralsWithVariable(Variable variable) {
		Literal_list newList = new Literal_list();
		for(int i = 0; i < this.size(); i++) {
			if(this.elementAt(i) instanceof SimpleLiteral){
				SimpleLiteral tmp = (SimpleLiteral) this.elementAt(i);
				if(tmp.predicate.containsVar(variable)){
					newList.addElement(tmp);
				}
			}
			else if(this.elementAt(i) instanceof NegativeLiteral){
				NegativeLiteral tmp = (NegativeLiteral) this.elementAt(i);
				if(tmp.predicate.containsVar(variable)){
					newList.addElement(tmp);
				}
			}
		}
		return newList;
	}
	
	public Literal_list getLiteralsWithVariable(Term_List arguments) {
		Literal_list newList = new Literal_list();
		for(int k = 0; k <  arguments.size(); k++) {
			for(int i = 0; i < this.size(); i++) {
				if(this.elementAt(i) instanceof SimpleLiteral){
					SimpleLiteral tmp = (SimpleLiteral) this.elementAt(i);
					if(tmp.predicate.containsVar((Variable) arguments.elementAt(k)) &&  !newList.contains(tmp)){
						newList.addElement(tmp);
					}
				}
				else if(this.elementAt(i) instanceof NegativeLiteral){
					NegativeLiteral tmp = (NegativeLiteral) this.elementAt(i);
					if(tmp.predicate.containsVar((Variable) arguments.elementAt(k)) &&  !newList.contains(tmp)){
						newList.addElement(tmp);
					}
				}
			}
		}
		return newList;
	}
	
	public Literal_list getLiteralsWithoutVariable(Variable variable) {
		Literal_list newList = new Literal_list();
		for(int i = 0; i < this.size(); i++) {
			if(this.elementAt(i) instanceof SimpleLiteral){
				SimpleLiteral tmp = (SimpleLiteral) this.elementAt(i);
				if(!tmp.predicate.containsVar(variable)){
					newList.addElement(tmp);
				}
			}
			else if(this.elementAt(i) instanceof NegativeLiteral){
				NegativeLiteral tmp = (NegativeLiteral) this.elementAt(i);
				if(!tmp.predicate.containsVar(variable)){
					newList.addElement(tmp);
				}
			}
		}
		return newList;
	}
	
	public Literal_list getLiteralsWithoutVariable(Term_List arguments) {
		Literal_list newList = new Literal_list();
		for(int k = 0; k < arguments.size(); k++) {
			for(int i = 0; i < this.size(); i++) {
				if(this.elementAt(i) instanceof SimpleLiteral){
					SimpleLiteral tmp = (SimpleLiteral) this.elementAt(i);
					if(!tmp.containsVariavles(arguments) && !newList.contains(tmp)){
						newList.addElement(tmp);
					}
				}
				else if(this.elementAt(i) instanceof NegativeLiteral){
					NegativeLiteral tmp = (NegativeLiteral) this.elementAt(i);
					if(!tmp.containsVariavles(arguments) && !newList.contains(tmp)){
						newList.addElement(tmp);
					}
				}
			}
		}
		return newList;
	}
}
