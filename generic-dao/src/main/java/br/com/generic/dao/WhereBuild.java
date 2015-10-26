package br.com.generic.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import com.mchange.util.DuplicateElementException;

import br.com.generic.dao.type.Predicates;

public class WhereBuild<T, Q> {
	private EntityManager manager;
	private List<Parameter> parameters = new ArrayList<Parameter>();
	private final Class<Q> queryClass;
	private final Class<T> fromClass;
	private String field;

	WhereBuild(EntityManager manager, Class<T> fromClass, Class<Q> queryClass) {
		this.fromClass = fromClass;
		this.queryClass = queryClass;
		this.manager = manager;
	}
	
	public WhereBuild<T, Q> by(String arg0, Object value){
		Parameter parameter = new Parameter();
		Predicates predicate = getPredicate(arg0.trim());
		predicate.setEntityClass(fromClass);
		parameter.setProperty( arg0.replace(predicate.getValue(), "").trim());
		parameter.setPredicates(predicate);
		parameter.setValue(value);
		parameters.add(parameter);
		return this;	
	}
	
	@SuppressWarnings("unchecked")
	public Q search(){
		CriteriaBuilder builder = manager.getCriteriaBuilder() ;
		CriteriaQuery<Q> criteriaQuery = builder.createQuery(queryClass);
		Root<T> root = criteriaQuery.from(fromClass);
		
		criteriaQuery = criteriaQuery.select((Selection<? extends Q>) root) ;
		if(!getParameters().isEmpty())
			criteriaQuery = criteriaQuery.where(mountWhere(builder, root, getParameters()));
		
		if(field != null)
			criteriaQuery = criteriaQuery.select(root.<Q>get(field));
		
		TypedQuery<Q> query = manager.createQuery(criteriaQuery);
		
		query.setFirstResult(0);
		query.setMaxResults(2);
		
		List<Q> list = query.getResultList();
		
		if(list.size() > 1){
			throw new DuplicateElementException("more than one " + queryClass.getSimpleName() + " has been found.");
		}
		
		if(list.isEmpty()){
			return null;
		}else{
			return list.get(0);
		}
	}
	
	private Predicates getPredicate(String property){
		for(Predicates predicate : Predicates.values()){
			if(property.contains(predicate.getValue())){
				return predicate;
			}
		}
		return Predicates.EQUAL;
	}

	List<Parameter> getParameters() {
		return parameters;
	}

	
	Predicate[] mountWhere(CriteriaBuilder builder, Root<T> root, List<Parameter> parameters){
		Predicate[] predicates = new Predicate[parameters.size()];
		
		for(int i = 0; i < parameters.size(); i++){
			predicates[i] = parameters.get(i).getPredicates().getPredicate(builder, root, parameters.get(i));
		}
		
		return predicates;
	}
	
	void setField(String field) {
		this.field = field;
	}

}
