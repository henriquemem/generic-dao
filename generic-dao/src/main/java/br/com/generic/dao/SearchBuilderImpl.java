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


public class SearchBuilderImpl<T, Q> extends BaseSearchBuilderImpl< SearchBuilder<T, Q>> implements SearchBuilder<T, Q>{
	private EntityManager manager;
	private final Class<Q> queryClass;
	private final Class<T> fromClass;
	private String field;

	public SearchBuilderImpl(EntityManager manager, Class<T> fromClass, Class<Q> queryClass) {
		this.fromClass = fromClass;
		this.queryClass = queryClass;
		this.manager = manager;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public Q search(){
		CriteriaBuilder builder = getManager().getCriteriaBuilder() ;
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
		
		TypedQuery<Q> query = getManager().createQuery(criteriaQuery);
		
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



    protected EntityManager getManager() {
        return this.manager;
    }



    public void setManager(EntityManager manager) {
        this.manager = manager;
    }



    public String getField() {
        return this.field;
    }



    public void setField(String field) {
        this.field = field;
    }



    public Class<Q> getQueryClass() {
        return this.queryClass;
    }



    public Class<T> getFromClass() {
        return this.fromClass;
    }
	
	
}
