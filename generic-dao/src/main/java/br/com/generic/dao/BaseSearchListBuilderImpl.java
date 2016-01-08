package br.com.generic.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import br.com.generic.dao.type.Order;

abstract class BaseSearchListBuilderImpl<T, Q, B extends BaseSearchListBuilder<T, Q, B>> 
	extends 
		BaseSearchBuilderImpl<B>
        implements
            BaseSearchListBuilder<T, Q, B>{
        

	private Integer start;
	private Integer end;
	private Order order;
	private String propertyOrder;
	private final Class<Q> queryClass;
	private final Class<T> fromClass;
	private EntityManager manager;
	private String field;
	
	BaseSearchListBuilderImpl(EntityManager manager, Class<T> fromClass, Class<Q> queryClass) {
		this.fromClass = fromClass;
		this.queryClass = queryClass;
		this.manager = manager;
	}
	
	@SuppressWarnings("unchecked")
	public B startWith(Integer start){
		this.start = start;
		return (B) this;
	}
	
	@SuppressWarnings("unchecked")
	public B endsWith(Integer end){
		this.end = end;
		return (B) this;
	}
	
	@SuppressWarnings("unchecked")
	public B sortBy(String property){
		sortBy(property , Order.ASC);
		return (B) this;
	}
	
	@SuppressWarnings("unchecked")
	public B sortBy(String property, Order order){
		this.propertyOrder = property;
		this.order = order;
		return (B) this;
	}
	
	@SuppressWarnings("unchecked")
	public List<Q> list(){
		CriteriaBuilder builder = manager.getCriteriaBuilder() ;
		CriteriaQuery<Q> criteriaQuery = builder.createQuery(queryClass);
		Root<T> root = criteriaQuery.from(fromClass);
		criteriaQuery = criteriaQuery.select((Selection<? extends Q>) root) ;
		
		Predicate predicate = getPredicate(builder, root);
		if(predicate != null){
			criteriaQuery = criteriaQuery.where(predicate);
		}
		if(order != null){
			if(order.equals(Order.ASC)){
				criteriaQuery = criteriaQuery.orderBy(builder.asc(root.get(propertyOrder)));
			}else if(order.equals(Order.DESC)){
				criteriaQuery = criteriaQuery.orderBy(builder.desc(root.get(propertyOrder)));
			}
		}
		
		if(field != null)
			criteriaQuery = criteriaQuery.select(root.<Q>get(field));
		else
			criteriaQuery.distinct(true);
		
		TypedQuery<Q> query = manager.createQuery(criteriaQuery);
		if(start != null && start > 0)
			query.setFirstResult(start);
		if(end != null && end > 0)
			query.setMaxResults(end);
		
		
		return query.getResultList();
	}

	public void setField(String field) {
		this.field = field;
	}

	@Override
	Class<?> getFromClass() {
		return fromClass;
	}
	
	
}
