package edu.tacas21.ta;

import org.fmaes.j2uppaal.datastructures.base.CompositeUppaalElement;


public class QueryGenerator extends CompositeUppaalElement{
	public static Query produce(String sFormula, String sComment)
	{
		Query query = new Query();
		QueryFormula formula = new QueryFormula(sFormula);
		QueryComment comment = new QueryComment(sComment);
		query.setFormula(formula);
		query.setComment(comment);
		
		return query;
	}
}
