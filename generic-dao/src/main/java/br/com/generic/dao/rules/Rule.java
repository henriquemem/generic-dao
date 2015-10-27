package br.com.generic.dao.rules;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.generic.dao.Parameter;

public interface Rule {
	public <T> Predicate getPredicate(Class<?> entityClass, CriteriaBuilder builder, Root<T> root, Parameter parameter);
}
