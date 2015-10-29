package br.com.generic.dao;

import javax.persistence.EntityManager;

public class WhereEntityListBuilder<T> extends BaseWhereListBuilder<T, T, WhereEntityListBuilder<T>> {

	WhereEntityListBuilder(EntityManager manager, Class<T> fromClass, Class<T> queryClass) {
		super(manager, fromClass, queryClass);
	}

}
