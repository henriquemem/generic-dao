package br.com.generic.dao;

import javax.persistence.EntityManager;

public class WhereListBuilder<T, Q> extends BaseWhereListBuilder<T, Q, WhereListBuilder<T, Q>> {

	WhereListBuilder(EntityManager manager, Class<T> fromClass,
			Class<Q> queryClass) {
		super(manager, fromClass, queryClass);
	}

}
