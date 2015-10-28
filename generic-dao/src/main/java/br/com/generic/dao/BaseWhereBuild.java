package br.com.generic.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.generic.dao.type.Predicates;

abstract class BaseWhereBuild<T extends BaseWhereBuild<?>> {

	private List<Parameter> parameters = new ArrayList<Parameter>();
	
	@SuppressWarnings("unchecked")
	public T equal(String arg0, Object value){
		Parameter parameter = new Parameter();
		parameter.setProperty(arg0);
		parameter.setPredicates(Predicates.EQUAL);
		parameter.setValue(value);
		parameters.add(parameter);
		return (T) this;	
	}
	
	@SuppressWarnings("unchecked")
	public T greaterThanOrEqualTo(String arg0, Comparable<?> value){
		Parameter parameter = new Parameter();
		parameter.setProperty(arg0);
		parameter.setPredicates(Predicates.GREATER_THAN_OR_EQUAL_TO);
		parameter.setValue(value);
		parameters.add(parameter);
		return (T) this;	
	}
	
	@SuppressWarnings("unchecked")
	public T greaterThan(String arg0, Comparable<?> value){
		Parameter parameter = new Parameter();
		parameter.setProperty(arg0);
		parameter.setPredicates(Predicates.GREATER_THAN);
		parameter.setValue(value);
		parameters.add(parameter);
		return (T) this;	
	}
	
	@SuppressWarnings("unchecked")
	public T in(String arg0, Collection<?> value){
		Parameter parameter = new Parameter();
		parameter.setProperty(arg0);
		parameter.setPredicates(Predicates.IN);
		parameter.setValue(value);
		parameters.add(parameter);
		return (T) this;	
	}
	
	@SuppressWarnings("unchecked")
	public T in(String arg0, Object... value){
		Parameter parameter = new Parameter();
		parameter.setProperty(arg0);
		parameter.setPredicates(Predicates.IN);
		parameter.setValue(value);
		parameters.add(parameter);
		return (T) this;	
	}
	
	@SuppressWarnings("unchecked")
	public T lessThanOrEqualTo(String arg0, Comparable<?> value){
		Parameter parameter = new Parameter();
		parameter.setProperty(arg0);
		parameter.setPredicates(Predicates.LESS_THAN_OR_EQUAL_TO);
		parameter.setValue(value);
		parameters.add(parameter);
		return (T) this;	
	}
	
	@SuppressWarnings("unchecked")
	public T lessThan(String arg0, Comparable<?> value){
		Parameter parameter = new Parameter();
		parameter.setProperty(arg0);
		parameter.setPredicates(Predicates.LESS_THAN);
		parameter.setValue(value);
		parameters.add(parameter);
		return (T) this;	
	}
	
	@SuppressWarnings("unchecked")
	public T like(String arg0, String value){
		Parameter parameter = new Parameter();
		parameter.setProperty(arg0);
		parameter.setPredicates(Predicates.LIKE);
		parameter.setValue(value);
		parameters.add(parameter);
		return (T) this;	
	}
	
	@SuppressWarnings("unchecked")
	public T notEqual(String arg0, Object value){
		Parameter parameter = new Parameter();
		parameter.setProperty(arg0);
		parameter.setPredicates(Predicates.NOT_EQUAL);
		parameter.setValue(value);
		parameters.add(parameter);
		return (T) this;	
	}
	
	@SuppressWarnings("unchecked")
	public T isNull(String arg0){
		Parameter parameter = new Parameter();
		parameter.setProperty(arg0);
		parameter.setPredicates(Predicates.IS_NULL);
		parameters.add(parameter);
		return (T) this;	
	}
	
	@SuppressWarnings("unchecked")
	public T isNotNull(String arg0){
		Parameter parameter = new Parameter();
		parameter.setProperty(arg0);
		parameter.setPredicates(Predicates.IS_NOT_NULL);
		parameters.add(parameter);
		return (T) this;	
	}
	
	@SuppressWarnings("unchecked")
	public T between(String arg0, Comparable<?> value1, Comparable<?> value2){
		Parameter parameter = new Parameter();
		parameter.setProperty(arg0);
		Comparable<?>[] values = {value1, value2};
		parameter.setValue(values);
		parameter.setPredicates(Predicates.BETWEEN);
		parameters.add(parameter);
		return (T) this;	
	}
	
	Predicate[] mountWhere(CriteriaBuilder builder, Root<?> root, List<Parameter> parameters){
		Predicate[] predicates = new Predicate[parameters.size()];
		
		for(int i = 0; i < parameters.size(); i++){
			predicates[i] = parameters.get(i).getPredicates().getPredicate(getFromClass(), builder, root, parameters.get(i));
		}
		
		return predicates;
	}
	
	abstract Class<?> getFromClass();
	
	List<Parameter> getParameters() {
		return parameters;
	}

}
