package edu.tacas21.ta;

import org.fmaes.j2uppaal.datastructures.base.CompositeUppaalElement;


public class Query extends CompositeUppaalElement{

	  public Query() {
	    // TODO Auto-generated constructor stub
	    super();
	    this.tagName = "query";
	  }
	  
	 public void setFormula(QueryFormula formula) {
		    // TODO Auto-generated method stub
		    if (formula == null) {
		      return;
		    }
		    childrenUppaalElements.add(formula);
	  }
	 

	 public void setComment(QueryComment comment) {
		    // TODO Auto-generated method stub
		    if (comment == null) {
		      return;
		    }
		    childrenUppaalElements.add(comment);
	  }
}
