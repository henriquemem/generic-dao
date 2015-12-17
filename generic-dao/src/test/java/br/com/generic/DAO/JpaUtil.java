package br.com.generic.DAO;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

class JpaUtil {
	
	private static EntityManagerFactory factory;
	
	
	
	public JpaUtil() {
		super();
		if(factory == null){
			factory =  Persistence.createEntityManagerFactory("projeto");
		}
	}



	@Produces
	public EntityManager getEntityManager(){
		return factory.createEntityManager();
	}
	
}
