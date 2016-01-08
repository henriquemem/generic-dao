package br.com.generic.dao;

import java.util.List;

import br.com.generic.dao.type.Order;

public interface BaseSearchListBuilder<T, Q, B extends BaseSearchListBuilder<T, Q, B>> 
	extends 
		BaseSearchBuilder<B>{

	public B startWith(Integer start);
	public B endsWith(Integer end);
	public B sortBy(String property);
	public B sortBy(String property, Order order);
	public List<Q> list();
	public void setField(String field);
	
}
