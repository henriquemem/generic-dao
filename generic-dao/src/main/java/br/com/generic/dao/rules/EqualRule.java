package br.com.generic.dao.rules;

import java.lang.reflect.Field;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.generic.dao.Parameter;

public class EqualRule extends BaseRule {


	@SuppressWarnings("rawtypes")
	@Override
	public <T> Predicate getPredicate(Class<?> entityClass, CriteriaBuilder builder, Root<T> root,
			Parameter parameter) {
		String property = parameter.getProperty();
		Path<T> path = this.<T>getPath(entityClass, root, property);
		Field field = getField(entityClass, property, getLastProperty(property));
		if((annotedEntity(field.getType()) || isCollectionEntity(field)) && this.<T>responderJoin(path)){
			return builder.equal(((Join)path).join(getLastProperty(property)), parameter.getValue());
		}
		return builder.equal(path.get(getLastProperty(property)), parameter.getValue());
	}

}
