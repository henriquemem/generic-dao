package br.com.generic.dao.rules;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.generic.dao.Parameter;

public class BetweenRule extends BaseRule {

	@Override
	@SuppressWarnings({"unchecked", "rawtypes"})
	public <T> Predicate getPredicate(Class<?> entityClass,
			CriteriaBuilder builder, Root<T> root, Parameter parameter) {
		String lasProperty = getLastProperty(parameter.getProperty());
		String navigation = getNavigation(parameter.getProperty());
		
		Path<T> path = this.<T>getPath(entityClass, root, navigation);
		Comparable[] values = (Comparable[]) parameter.getValue();
		return builder.<Comparable>between(path.get(lasProperty), 
				values[0], values[1]);
	}

}
