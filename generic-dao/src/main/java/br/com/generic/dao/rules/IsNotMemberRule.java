package br.com.generic.dao.rules;

import java.util.Collection;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.generic.dao.Parameter;

public class IsNotMemberRule extends BaseRule {


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public <T> Predicate getPredicate(Class<?> entityClass, CriteriaBuilder builder, Root<T> root,
			Parameter parameter) {
		String lasProperty = getLastProperty(parameter.getProperty());
		String navigation = getNavigation(parameter.getProperty());
		Path<T> path = this.<T>getPath(entityClass, root, navigation);

		return builder.isNotMember( parameter.getValue(), path.<Collection>get(lasProperty));
	}

}
