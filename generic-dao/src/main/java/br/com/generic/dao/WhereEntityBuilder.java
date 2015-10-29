package br.com.generic.dao;

import javax.persistence.EntityManager;

public class WhereEntityBuilder<T> extends WhereBuilder<T, T> {

	WhereEntityBuilder(EntityManager manager, Class<T> fromClass, Class<T> queryClass) {
		super(manager, fromClass, queryClass);
	}

}
