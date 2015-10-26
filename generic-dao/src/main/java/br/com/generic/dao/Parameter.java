package br.com.generic.dao;

import br.com.generic.dao.type.Predicates;

public class Parameter {

	private String property;
	private Predicates predicates;
	private Object value;
	
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public Predicates getPredicates() {
		return predicates;
	}
	public void setPredicates(Predicates predicates) {
		this.predicates = predicates;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	
	
	
}
