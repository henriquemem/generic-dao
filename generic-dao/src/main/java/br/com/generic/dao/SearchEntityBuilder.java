package br.com.generic.dao;

import javax.persistence.EntityManager;

public class SearchEntityBuilder<T> extends SearchBuilder<T, T> {

	SearchEntityBuilder(EntityManager manager, Class<T> fromClass, Class<T> queryClass) {
		super(manager, fromClass, queryClass);
	}

}
