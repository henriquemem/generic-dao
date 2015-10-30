package br.com.generic.DAO;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.generic.dao.GenericDAOImpl;
import br.com.generic.entity.Produto;


public class ProdutoDAOImpl extends GenericDAOImpl<Produto> implements ProdutoDAO{


	@Inject
	@Override
	public void setEntityManager(EntityManager manager) {
		super.setEntityManager(manager);
	}
}
