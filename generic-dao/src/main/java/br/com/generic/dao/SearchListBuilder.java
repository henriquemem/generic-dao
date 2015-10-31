package br.com.generic.dao;

import javax.persistence.EntityManager;

public class SearchListBuilder<T, Q> extends BaseSearchListBuilder<T, Q, SearchListBuilder<T, Q>> {

	SearchListBuilder(EntityManager manager, Class<T> fromClass,
			Class<Q> queryClass) {
		super(manager, fromClass, queryClass);
	}

}
