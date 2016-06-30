package br.com.generic.dao;

import java.util.Collection;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public interface BaseSearchBuilder<B extends BaseSearchBuilder<?>> {

	
	
	public B equal(String arg0, Object value);
	public B isMember(String arg0, Object value);
	public B isNotMember(String arg0, Object value);
	public B greaterThanOrEqualTo(String arg0, Comparable<?> value);
	public B greaterThan(String arg0, Comparable<?> value);
	public B in(String arg0, Collection<?> value);
	public B in(String arg0, Object... value);
	public B notIn(String arg0, Collection<?> value);
	public B notIn(String arg0, Object... value);
	public B lessThanOrEqualTo(String arg0, Comparable<?> value);
	public B lessThan(String arg0, Comparable<?> value);
	public B like(String arg0, String value);
	public B notLike(String arg0, String value);
	public B notEqual(String arg0, Object value);
	public B isNull(String arg0);
	public B isNotNull(String arg0);
	public B between(String arg0, Comparable<?> value1, Comparable<?> value2);
	public B or(B searchBuildr);
	public B and(B searchBuildr);
	public B eq(String arg0, Object value);
	public B ge(String arg0, Comparable<?> value);
	public B gt(String arg0, Comparable<?> value);
	public B le(String arg0, Comparable<?> value);
	public B lt(String arg0, Comparable<?> value);
	public Predicate getPredicate(CriteriaBuilder builder, Root<?> root);
}
