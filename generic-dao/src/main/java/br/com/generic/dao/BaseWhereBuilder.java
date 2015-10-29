package br.com.generic.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.generic.dao.type.Predicates;

abstract class BaseWhereBuilder<B extends BaseWhereBuilder<?>> {

	private List<Parameter> parameters = new ArrayList<Parameter>();
	private List<BaseWhereBuilder<?>> andWhereBuilds = new ArrayList<BaseWhereBuilder<?>>();
	private List<BaseWhereBuilder<?>> orWhereBuilds = new ArrayList<BaseWhereBuilder<?>>();
	
	@SuppressWarnings("unchecked")
	public B equal(String arg0, Object value){
		Parameter parameter = new Parameter();
		parameter.setProperty(arg0);
		parameter.setPredicates(Predicates.EQUAL);
		parameter.setValue(value);
		parameters.add(parameter);
		return (B) this;	
	}
	
	@SuppressWarnings("unchecked")
	public B greaterThanOrEqualTo(String arg0, Comparable<?> value){
		Parameter parameter = new Parameter();
		parameter.setProperty(arg0);
		parameter.setPredicates(Predicates.GREATER_THAN_OR_EQUAL_TO);
		parameter.setValue(value);
		parameters.add(parameter);
		return (B) this;	
	}
	
	@SuppressWarnings("unchecked")
	public B greaterThan(String arg0, Comparable<?> value){
		Parameter parameter = new Parameter();
		parameter.setProperty(arg0);
		parameter.setPredicates(Predicates.GREATER_THAN);
		parameter.setValue(value);
		parameters.add(parameter);
		return (B) this;	
	}
	
	@SuppressWarnings("unchecked")
	public B in(String arg0, Collection<?> value){
		Parameter parameter = new Parameter();
		parameter.setProperty(arg0);
		parameter.setPredicates(Predicates.IN);
		parameter.setValue(value);
		parameters.add(parameter);
		return (B) this;	
	}
	
	@SuppressWarnings("unchecked")
	public B in(String arg0, Object... value){
		Parameter parameter = new Parameter();
		parameter.setProperty(arg0);
		parameter.setPredicates(Predicates.IN);
		parameter.setValue(value);
		parameters.add(parameter);
		return (B) this;	
	}
	
	@SuppressWarnings("unchecked")
	public B lessThanOrEqualTo(String arg0, Comparable<?> value){
		Parameter parameter = new Parameter();
		parameter.setProperty(arg0);
		parameter.setPredicates(Predicates.LESS_THAN_OR_EQUAL_TO);
		parameter.setValue(value);
		parameters.add(parameter);
		return (B) this;	
	}
	
	@SuppressWarnings("unchecked")
	public B lessThan(String arg0, Comparable<?> value){
		Parameter parameter = new Parameter();
		parameter.setProperty(arg0);
		parameter.setPredicates(Predicates.LESS_THAN);
		parameter.setValue(value);
		parameters.add(parameter);
		return (B) this;	
	}
	
	@SuppressWarnings("unchecked")
	public B like(String arg0, String value){
		Parameter parameter = new Parameter();
		parameter.setProperty(arg0);
		parameter.setPredicates(Predicates.LIKE);
		parameter.setValue(value);
		parameters.add(parameter);
		return (B) this;	
	}
	
	@SuppressWarnings("unchecked")
	public B notLike(String arg0, String value){
		Parameter parameter = new Parameter();
		parameter.setProperty(arg0);
		parameter.setPredicates(Predicates.NOT_LIKE);
		parameter.setValue(value);
		parameters.add(parameter);
		return (B) this;	
	}
	
	@SuppressWarnings("unchecked")
	public B notEqual(String arg0, Object value){
		Parameter parameter = new Parameter();
		parameter.setProperty(arg0);
		parameter.setPredicates(Predicates.NOT_EQUAL);
		parameter.setValue(value);
		parameters.add(parameter);
		return (B) this;	
	}
	
	@SuppressWarnings("unchecked")
	public B isNull(String arg0){
		Parameter parameter = new Parameter();
		parameter.setProperty(arg0);
		parameter.setPredicates(Predicates.IS_NULL);
		parameters.add(parameter);
		return (B) this;	
	}
	
	@SuppressWarnings("unchecked")
	public B isNotNull(String arg0){
		Parameter parameter = new Parameter();
		parameter.setProperty(arg0);
		parameter.setPredicates(Predicates.IS_NOT_NULL);
		parameters.add(parameter);
		return (B) this;	
	}
	
	@SuppressWarnings("unchecked")
	public B between(String arg0, Comparable<?> value1, Comparable<?> value2){
		Parameter parameter = new Parameter();
		parameter.setProperty(arg0);
		Comparable<?>[] values = {value1, value2};
		parameter.setValue(values);
		parameter.setPredicates(Predicates.BETWEEN);
		parameters.add(parameter);
		return (B) this;	
	}
	
	@SuppressWarnings("unchecked")
	public B or(B whereBuild){
		orWhereBuilds.add(whereBuild);
		return ((B) this);
	}
	
	@SuppressWarnings("unchecked")
	public B and(B whereBuild){
		andWhereBuilds.add(whereBuild);
		return ((B) this);
	}
	
	Predicate getPredicate(CriteriaBuilder builder, Root<?> root){
		Predicate[] predicates = new Predicate[parameters.size()];
		
		for(int i = 0; i < parameters.size(); i++){
			predicates[i] = parameters.get(i).getPredicates().getPredicate(getFromClass(), builder, root, parameters.get(i));
		}
		
		Predicate thisPredicate = null;
		if(predicates.length > 0){
			thisPredicate = builder.and(predicates);
		}
		
		if(!andWhereBuilds.isEmpty()){
			Predicate andPredicate = getAndPredicates(builder, root);
			if(thisPredicate != null && andPredicate != null){
				thisPredicate =  builder.and(andPredicate, thisPredicate);
			}else if(andPredicate != null){
				thisPredicate = andPredicate;
			}
		}
		if(!orWhereBuilds.isEmpty()){
			Predicate orPredicate = getOrPredicates(builder, root);
			
			if(thisPredicate != null && orPredicate != null){
				thisPredicate =  builder.or(orPredicate, thisPredicate);
			}else if(orPredicate != null){
				thisPredicate = orPredicate;
			}
		}
		return thisPredicate;
	}
	
	private Predicate getOrPredicates(CriteriaBuilder builder, Root<?> root){
		Predicate[] predicates = new Predicate[orWhereBuilds.size()];
		Predicate predicate;
		for(int i = 0; i < orWhereBuilds.size() ; i++){
			predicate = orWhereBuilds.get(i).getPredicate(builder, root);
			if(predicate != null){
				predicates[i] = predicate;
			}
		}
		return predicates.length > 0 ? builder.or(predicates) : null;
	}
	
	private Predicate getAndPredicates(CriteriaBuilder builder, Root<?> root){
		Predicate[] predicates = new Predicate[andWhereBuilds.size()];
		Predicate predicate;
		for(int i = 0; i < andWhereBuilds.size() ; i++){
			predicate = andWhereBuilds.get(i).getPredicate(builder, root);
			if(predicate != null){
				predicates[i] = predicate;
			}
		}
		return predicates.length > 0 ? builder.and(predicates) : null;
	}
	
	abstract Class<?> getFromClass();
	
	List<Parameter> getParameters() {
		return parameters;
	}

}
