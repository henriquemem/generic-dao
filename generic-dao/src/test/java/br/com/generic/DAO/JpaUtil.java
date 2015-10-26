package br.com.generic.DAO;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

class JpaUtil {
	
	private EntityManagerFactory factory =  Persistence.createEntityManagerFactory("projeto");
	
	
	@Produces
	public EntityManager getEntityManager(){
		return factory.createEntityManager();
	}
	
}
