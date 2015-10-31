package br.com.generic.genericDAO;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;

import br.com.generic.DAO.FabricanteDAO;
import br.com.generic.DAO.ProdutoDAO;
import junit.framework.TestCase;

@Ignore
public abstract class BaseListTest extends TestCase{

	@Inject
	protected EntityManager entityManager;
	
	
	@Inject
	protected ProdutoDAO produtoDao;
	
	@Inject
	protected FabricanteDAO fabricanteDao;
	
	@Before
	public void beforeTest(){
		entityManager.getTransaction().begin();
	}
	
	@After
	public void afterTest(){
		entityManager.getTransaction().commit();
	}

	
	//Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    //validator.validate(null);
}
