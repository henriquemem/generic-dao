package br.com.generic.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public interface SearchBuilder<T, Q> extends BaseSearchBuilder< SearchBuilder<T, Q>>{
	
	public Q search();
	public Predicate getPredicate(CriteriaBuilder builder, Root<?> root);
	public void setField(String field);
}
