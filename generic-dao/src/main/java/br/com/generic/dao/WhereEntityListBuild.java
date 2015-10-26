package br.com.generic.dao;

import javax.persistence.EntityManager;

public class WhereEntityListBuild<T> extends WhereListBuild<T, T> {

	WhereEntityListBuild(EntityManager manager, Class<T> fromClass, Class<T> queryClass) {
		super(manager, fromClass, queryClass);
	}

}
