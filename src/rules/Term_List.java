package rules;

import java.util.Vector;

public class Term_List {
	private Vector<Term> list;
	
	public Term_List(){
		list = new Vector<Term>();
	}
	
	public void addElement(Term term){
		list.addElement(term);
	}
	
	public Term elementAt(int i){
		return list.elementAt(i);
	}
	
	public int size(){
		return list.size();
	}

	public String toString(){
		String result="";
		for(int i=0;i<list.size();i++){
			result += list.elementAt(list.size()-i-1).evaluate()+",";
		}
		return result;
	}
	
	public Term_List removeVal(Variable var){
		Term_List list = new Term_List();
		
		for(int i=0;i < this.size(); i++){
			if(!this.elementAt(i).evaluate().equals(var.evaluate())){
				list.addElement(this.elementAt(i));
			}
		}
		return list;
	}
	
	public void removeVal2(Variable var){
		
		for(int i=0;i < this.size(); i++){
			if(this.elementAt(i).evaluate().equals(var.evaluate())){
				list.remove(i);
			}
		}
	}
	
	private Vector<Term> getList(){
		return this.list;
	}
	
	public void addAll(Term_List list){
		this.list.addAll(list.getList());
	}
	
	public void remove(int i){
		this.list.remove(i);
	}
	
	public void removeDublicates(){
		for(int i = 0; i < this.size(); i++){
			for(int j = i+1; j < this.size(); j++){
				if(this.elementAt(i).evaluate().equals(this.elementAt(j).evaluate())){
					remove(j);
				}
			}
		}
	}
	
	public void addList(Term_List add_list) { 
		for(int i =0; i < add_list.size(); i++) {
			this.list.add(add_list.elementAt(i));
		}
		removeDublicates();
	}
	
	public void addElementAt(Term item,int index){
		list.add(index, item);
	}
	
	public void reverse() {
		Term tmp;
		for(int i = 0; i < list.size()/2; i++){
			tmp = list.elementAt(i);
			list.set(i, list.elementAt(list.size() - i-1));
			list.set(list.size() - i-1, tmp);
		}
	}
}
