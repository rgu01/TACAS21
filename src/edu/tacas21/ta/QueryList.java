package edu.tacas21.ta;

import org.fmaes.j2uppaal.datastructures.base.CompositeUppaalElement;

public class QueryList extends CompositeUppaalElement{
	public QueryList() {
	    // TODO Auto-generated constructor stub
	    super();
	    this.tagName = "queries";
	  }
	
	public void addQuery(Query query) {
	    // TODO Auto-generated method stub
	    if (query == null) {
	      return;
	    }
	    childrenUppaalElements.add(query);
  }
}
