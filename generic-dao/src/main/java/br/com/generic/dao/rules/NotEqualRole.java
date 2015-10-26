package br.com.generic.dao.rules;

import java.lang.reflect.Field;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.generic.dao.Parameter;

public class NotEqualRole extends RuleBase{

	public NotEqualRole(Class<?> entityClass) {
		super(entityClass);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public <T> Predicate getPredicate(CriteriaBuilder builder, Root<T> root,
			Parameter parameter) {
		String property = parameter.getProperty();
		Path<T> path = this.<T>getPath(root, property);
		Field field = getField(property, getLastProperty(property));
		if((annotedEntity(field.getType()) || isCollectionEntity(field)) && this.<T>responderJoin(path)){
			return builder.notEqual(((Join)path).join(getLastProperty(property)), parameter.getValue());
		}
		return builder.notEqual(path.get(getLastProperty(property)), parameter.getValue());
	}

}
