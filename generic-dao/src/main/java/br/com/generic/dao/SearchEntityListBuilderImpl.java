package br.com.generic.dao;

import javax.persistence.EntityManager;

public class SearchEntityListBuilderImpl<T> extends BaseSearchListBuilderImpl<T, T, SearchEntityListBuilder<T>> implements SearchEntityListBuilder<T>{

	public SearchEntityListBuilderImpl(EntityManager manager, Class<T> fromClass) {
		super(manager, fromClass, fromClass);
	}

}
