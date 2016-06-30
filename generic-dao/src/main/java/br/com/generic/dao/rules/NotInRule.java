package br.com.generic.dao.rules;

import java.util.Arrays;
import java.util.Collection;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.generic.dao.Parameter;

public class NotInRule extends BaseRule {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public <T> Predicate getPredicate(Class<?> entityClass, CriteriaBuilder builder, Root<T> root,
			Parameter parameter) {
		String lasProperty = getLastProperty(parameter.getProperty());
		String navigation = getNavigation(parameter.getProperty());
		Collection value = parameter.getValue() instanceof Collection ? (Collection) parameter.getValue() : Arrays.asList((Object[])parameter.getValue()); 
			
		Path<T> path = this.<T>getPath(entityClass, root, navigation);
		if(isFrom(entityClass, path, parameter.getProperty())){
			return builder.not(((From)path).join(lasProperty)).in(value);
		}
		return builder.not(((From)path).get(lasProperty)).in(value);
	}

}
