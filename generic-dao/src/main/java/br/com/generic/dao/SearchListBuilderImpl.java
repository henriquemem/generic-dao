package br.com.generic.dao;

import javax.persistence.EntityManager;

public class SearchListBuilderImpl<T, Q> extends BaseSearchListBuilderImpl<T, Q, SearchListBuilder<T, Q>> implements SearchListBuilder<T, Q>{

	public SearchListBuilderImpl(EntityManager manager, Class<T> fromClass,
			Class<Q> queryClass) {
		super(manager, fromClass, queryClass);
	}

}
