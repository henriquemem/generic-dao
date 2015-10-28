package br.com.generic.dao;

import javax.persistence.EntityManager;

public class WhereListBuild<T, Q> extends BaseWhereListBuild<T, Q, WhereListBuild<T, Q>> {

	WhereListBuild(EntityManager manager, Class<T> fromClass,
			Class<Q> queryClass) {
		super(manager, fromClass, queryClass);
	}

}
