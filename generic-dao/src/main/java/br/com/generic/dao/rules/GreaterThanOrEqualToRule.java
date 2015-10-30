package br.com.generic.dao.rules;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.generic.dao.Parameter;

public class GreaterThanOrEqualToRule extends BaseRule {


	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public <T> Predicate getPredicate(Class<?> entityClass, CriteriaBuilder builder, Root<T> root,
			Parameter parameter) {
		String lasProperty = getLastProperty(parameter.getProperty());
		String navigation = getNavigation(parameter.getProperty());
		Path<T> path = this.<T>getPath(entityClass, root, navigation);
		if(isJoin(entityClass, path, parameter.getProperty())){
			return builder.greaterThanOrEqualTo(((Join)path).<Comparable>join(lasProperty), (Comparable) parameter.getValue());
		}
		return builder.greaterThanOrEqualTo(path.<Comparable>get(lasProperty), (Comparable) parameter.getValue());
	}

}
