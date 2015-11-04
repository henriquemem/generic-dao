package br.com.generic.dao;

import javax.persistence.EntityManager;

public class SearchEntityBuilder<T> extends SearchBuilder<T, T> {

	public SearchEntityBuilder(EntityManager manager, Class<T> fromClass) {
		super(manager, fromClass, fromClass);
	}

}
