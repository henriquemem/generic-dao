package br.com.generic.dao.rules;

import java.lang.reflect.Field;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.generic.dao.Parameter;

public class LessThanOrEqualToRule extends BaseRule {


	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public <T> Predicate getPredicate(Class<?> entityClass, CriteriaBuilder builder, Root<T> root,
			Parameter parameter) {
		String property = parameter.getProperty();
		Path<T> path = this.<T>getPath(entityClass, root, property);
		Field field = getField(entityClass, property, getLastProperty(property));
		if((annotedEntity(field.getType()) || isCollectionEntity(field)) && this.<T>responderJoin(path)){
			return builder.lessThanOrEqualTo(((Join)path).<Comparable>join(getLastProperty(property)), (Comparable) parameter.getValue());
		}
		return builder.lessThanOrEqualTo(path.<Comparable>get(getLastProperty(property)), (Comparable) parameter.getValue());
	}

}
