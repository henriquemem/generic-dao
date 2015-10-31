package br.com.generic.DAO;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.generic.dao.GenericDAOImpl;
import br.com.generic.entity.Fabricante;


public class FabricanteDAOImpl extends GenericDAOImpl<Fabricante> implements FabricanteDAO{


	@Inject
	@Override
	public void setEntityManager(EntityManager manager) {
		super.setEntityManager(manager);
	}
}
