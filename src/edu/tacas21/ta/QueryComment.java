package edu.tacas21.ta;

import org.fmaes.j2uppaal.datastructures.base.SimpleUppaalElement;

public class QueryComment extends SimpleUppaalElement  {

	public QueryComment() {
		super();
	    this.tagName = "comment";
	}
	
	public QueryComment(String value) {
		super();
	    this.tagName = "comment";
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
