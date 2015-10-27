package br.com.generic.genericDAO;

import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.generic.WeldJUnit4Runner;
import br.com.generic.entity.Usuario;

@RunWith(WeldJUnit4Runner.class)
public class SearchEntityTest extends BaseTest {

	@Test
	public void searchEntityTest(){
		popularBanco();
		Usuario usuario = usuarioDAO.searchEntity()
				.equal("login", "test1")
				.search();
		
		assertNotNull(usuario);
	}

}
