package br.com.generic.dao.rules;

import java.util.Collection;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.generic.dao.Parameter;

public class IsMemberRule extends BaseRule {

	public IsMemberRule() {
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public <T> Predicate getPredicate(Class<?> entityClass, CriteriaBuilder builder, Root<T> root,
			Parameter parameter) {
		String lasProperty = getLastProperty(parameter.getProperty());
		String navigation = getNavigation(parameter.getProperty());
		
		Path<T> path = this.<T>getPath(entityClass, root, navigation);
		if(isJoin(entityClass, path, parameter.getProperty())){
			return builder.isMember(parameter.getValue(), ((Join)path).<Collection<?>>join(lasProperty));
		}
		return builder.isMember( parameter.getValue(), path.get(lasProperty));
	}

}
