package br.com.generic.dao.rules;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.generic.dao.Parameter;

public class LikeRule extends BaseRule {


	@Override
	public <T> Predicate getPredicate(Class<?> entityClass, CriteriaBuilder builder, Root<T> root,
			Parameter parameter) {
		String property = parameter.getProperty();
		return builder.like(this.<T>getPath(entityClass, root, property)
				.<String>get(getLastProperty(property)), (String) parameter.getValue());
	}

}
