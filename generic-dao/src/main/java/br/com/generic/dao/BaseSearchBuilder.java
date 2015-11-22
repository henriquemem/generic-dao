package br.com.generic.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.generic.dao.type.Predicates;

public abstract class BaseSearchBuilder<B extends BaseSearchBuilder<?>> {

	private List<Parameter> parameters = new ArrayList<Parameter>();
	private List<BaseSearchBuilder<?>> andSearchBuilds = new ArrayList<BaseSearchBuilder<?>>(); 
	private List<BaseSearchBuilder<?>> orSearchBuilds = new ArrayList<BaseSearchBuilder<?>>();
	
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
	public B isMember(String arg0, Object value){
		Parameter parameter = new Parameter();
		parameter.setProperty(arg0);
		parameter.setPredicates(Predicates.IS_MEMBER);
		parameter.setValue(value);
		parameters.add(parameter);
		return (B) this;	
	}
	
	@SuppressWarnings("unchecked")
	public B isNotMember(String arg0, Object value){
		Parameter parameter = new Parameter();
		parameter.setProperty(arg0);
		parameter.setPredicates(Predicates.IS_NOT_MEMBER);
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
	public B or(B searchBuildr){
		orSearchBuilds.add(searchBuildr);
		return ((B) this);
	}
	
	@SuppressWarnings("unchecked")
	public B and(B searchBuildr){
		andSearchBuilds.add(searchBuildr);
		return ((B) this);
	}
	

	public B eq(String arg0, Object value){
		return (B) equal(arg0, value);	
	}
	
	public B ge(String arg0, Comparable<?> value){
		return (B) greaterThanOrEqualTo(arg0, value);	
	}
	
	public B gt(String arg0, Comparable<?> value){
		return (B) greaterThan(arg0, value);	
	}

	
	public B le(String arg0, Comparable<?> value){
		return (B) lessThanOrEqualTo(arg0, value);	
	}
	
	public B lt(String arg0, Comparable<?> value){
		return (B) greaterThan(arg0, value);	
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
		
		if(!andSearchBuilds.isEmpty()){
			Predicate andPredicate = getAndPredicates(builder, root);
			if(thisPredicate != null && andPredicate != null){
				thisPredicate =  builder.and(andPredicate, thisPredicate);
			}else if(andPredicate != null){
				thisPredicate = andPredicate;
			}
		}
		if(!orSearchBuilds.isEmpty()){
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
		Predicate[] predicates = new Predicate[orSearchBuilds.size()];
		Predicate predicate;
		for(int i = 0; i < orSearchBuilds.size() ; i++){
			predicate = orSearchBuilds.get(i).getPredicate(builder, root);
			if(predicate != null){
				predicates[i] = predicate;
			}
		}
		return predicates.length > 0 ? builder.or(predicates) : null;
	}
	
	private Predicate getAndPredicates(CriteriaBuilder builder, Root<?> root){
		Predicate[] predicates = new Predicate[andSearchBuilds.size()];
		Predicate predicate;
		for(int i = 0; i < andSearchBuilds.size() ; i++){
			predicate = andSearchBuilds.get(i).getPredicate(builder, root);
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
