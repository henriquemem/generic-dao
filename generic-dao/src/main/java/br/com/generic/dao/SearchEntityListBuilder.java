package br.com.generic.dao;

import javax.persistence.EntityManager;

public class SearchEntityListBuilder<T> extends BaseSearchListBuilder<T, T, SearchEntityListBuilder<T>> {

	SearchEntityListBuilder(EntityManager manager, Class<T> fromClass, Class<T> queryClass) {
		super(manager, fromClass, queryClass);
	}

}
