package br.com.generic.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import com.mchange.util.DuplicateElementException;

public class WhereBuild<T, Q> extends BaseWhereBuild< WhereBuild<T, Q>>{
	private EntityManager manager;
	private final Class<Q> queryClass;
	private final Class<T> fromClass;
	private String field;

	WhereBuild(EntityManager manager, Class<T> fromClass, Class<Q> queryClass) {
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
	
	
	void setField(String field) {
		this.field = field;
	}



	@Override
	Class<?> getFromClass() {
		return fromClass;
	}

}
