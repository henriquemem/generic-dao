package br.com.generic.dao;

import javax.persistence.EntityManager;

public class SearchEntityListBuilder<T> extends BaseSearchListBuilder<T, T, SearchEntityListBuilder<T>> {

	public SearchEntityListBuilder(EntityManager manager, Class<T> fromClass) {
		super(manager, fromClass, fromClass);
	}

}
