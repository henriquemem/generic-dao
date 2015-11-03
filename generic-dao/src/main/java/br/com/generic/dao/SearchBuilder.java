package br.com.generic.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import br.com.generic.dao.exception.DuplicateResultException;


public class SearchBuilder<T, Q> extends BaseSearchBuilder< SearchBuilder<T, Q>>{
	private EntityManager manager;
	private final Class<Q> queryClass;
	private final Class<T> fromClass;
	private String field;

	SearchBuilder(EntityManager manager, Class<T> fromClass, Class<Q> queryClass) {
		this.fromClass = fromClass;
		this.queryClass = queryClass;
		this.manager = manager;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public Q search(){
		CriteriaBuilder builder = manager.getCriteriaBuilder() ;
		CriteriaQuery<Q> criteriaQuery = builder.createQuery(queryClass);
		Root<T> root = criteriaQuery.from(fromClass);
		
		criteriaQuery = criteriaQuery.select((Selection<? extends Q>) root) ;
		criteriaQuery.distinct(true);
		Predicate predicate = getPredicate(builder, root);
		if(predicate != null){
			criteriaQuery = criteriaQuery.where(predicate);
		}
		
		if(field != null)
			criteriaQuery = criteriaQuery.select(root.<Q>get(field));
		
		TypedQuery<Q> query = manager.createQuery(criteriaQuery);
		
		query.setFirstResult(0);
		query.setMaxResults(2);
		
		List<Q> list = query.getResultList();
		
		if(list.size() > 1){
			throw new DuplicateResultException("more than one result has been found.");
		}
		
		if(list.isEmpty()){
			return null;
		}else{
			return list.get(0);
		}
	}
	
	
	void setField(String field) {
		this.field = field;
	}



	@Override
	Class<?> getFromClass() {
		return fromClass;
	}

}
