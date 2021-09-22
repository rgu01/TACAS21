package edu.tacas21.ta;

import org.fmaes.j2uppaal.datastructures.base.SimpleUppaalElement;

public class QueryFormula extends SimpleUppaalElement  {

	public QueryFormula() {
		super();
	    this.tagName = "formula";
	}
	
	public QueryFormula(String value) {
		super();
	    this.tagName = "formula";
	    this.value = value;
	}
	

	public void setValue(String value) {
		// TODO Auto-generated method stub

	    if (value != null) {
	      this.value = value;
	    }
	}

	public String getValue() {
		// TODO Auto-generated method stub
	    return this.value;
	}
}
