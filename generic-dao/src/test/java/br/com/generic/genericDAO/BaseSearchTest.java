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
public abstract class BaseSearchTest extends TestCase{

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
	
	
	public abstract void listPropertiesTest();
	public abstract void searchPropertyTest();
	public abstract void listEntitiesTest();
	public abstract void searchEntityTest();
	
	public abstract void listPropertiesMultTest();
	public abstract void searchPropertyMultTest();
	public abstract void listEntitiesMultTest();
	public abstract void searchEntityMultTest();
	
	
	//Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    //validator.validate(null);
}
