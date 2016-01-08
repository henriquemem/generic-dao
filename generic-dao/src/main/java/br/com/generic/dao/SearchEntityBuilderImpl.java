package br.com.generic.dao;

import javax.persistence.EntityManager;

public class SearchEntityBuilderImpl<T> extends SearchBuilderImpl<T, T> implements SearchEntityBuilder<T>{

	public SearchEntityBuilderImpl(EntityManager manager, Class<T> fromClass) {
		super(manager, fromClass, fromClass);
	}

}
