package br.com.generic.dao;

import javax.persistence.EntityManager;

public class WhereEntityBuild<T> extends WhereBuild<T, T> {

	WhereEntityBuild(EntityManager manager, Class<T> fromClass, Class<T> queryClass) {
		super(manager, fromClass, queryClass);
	}

}
