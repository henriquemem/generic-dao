package br.com.generic.dao.rules;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.generic.dao.Parameter;

public class NotLikeRule extends BaseRule {

	@Override
	public <T> Predicate getPredicate(Class<?> entityClass,
			CriteriaBuilder builder, Root<T> root, Parameter parameter) {
		String lasProperty = getLastProperty(parameter.getProperty());
		String navigation = getNavigation(parameter.getProperty());
		return builder.notLike(this.<T>getPath(entityClass, root, navigation)
				.<String>get(lasProperty), (String) parameter.getValue());
	}

}
