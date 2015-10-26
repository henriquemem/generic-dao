package br.com.generic.DAO;

import javax.persistence.EntityManager;

import br.com.generic.dao.GenericDAO;
import br.com.generic.entity.Privilegio;

public interface PrivilegioDAO  extends GenericDAO<Privilegio>{

	public void setEntityManager(EntityManager entityManager);
}
