package br.com.generic.genericDAO;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.generic.WeldJUnit4Runner;
import br.com.generic.DAO.ProdutoDAO;

@RunWith(WeldJUnit4Runner.class)
public class OrTest extends BaseTest {
	
	@Inject
	ProdutoDAO produtoDao;

	@Test
	public void listTestW(){
		
		System.out.println(produtoDao.listEntities()
				.like("nome", "A%")
				.list().size());
	
		
	}
}
