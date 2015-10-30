package br.com.generic.dao.rules;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.generic.dao.Parameter;

public class IsNotNullRule extends BaseRule {

	@Override
	@SuppressWarnings("rawtypes")
	public <T> Predicate getPredicate(Class<?> entityClass,
			CriteriaBuilder builder, Root<T> root, Parameter parameter) {
		String lasProperty = getLastProperty(parameter.getProperty());
		String navigation = getNavigation(parameter.getProperty());
		
		Path<T> path = this.<T>getPath(entityClass, root, navigation);
		if(isJoin(entityClass, path, parameter.getProperty())){
			return builder.isNotNull(((Join)path).join(lasProperty));
		}
		return builder.isNotNull(path.get(lasProperty));
	}

}
