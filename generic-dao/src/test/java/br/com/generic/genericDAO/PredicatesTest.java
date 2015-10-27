package br.com.generic.genericDAO;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.generic.WeldJUnit4Runner;
import br.com.generic.entity.Usuario;

@RunWith(WeldJUnit4Runner.class)
public class PredicatesTest extends BaseTest {

	@Test
	public void noEqual(){
		popularBanco();
		List<Usuario> list = usuarioDAO.listEntities()
				.notEqual("login", "test1")
				.list();
		assertEquals(99, list.size());
	}
	
	@Test
	public void greaterThanOrEqualTo(){
		popularBanco();
		List<Usuario> list = usuarioDAO.listEntities()
				.greaterThanOrEqualTo("id", 2)
				.list();
		assertEquals(99, list.size());
	}
	
	@Test
	public void lessThanOrEqualTo(){
		popularBanco();
		List<Usuario> list = usuarioDAO.listEntities()
				.lessThanOrEqualTo("id", 99)
				.list();
		assertEquals(99, list.size());
	}
	
	@Test
	public void greaterThan(){
		popularBanco();
		List<Usuario> list = usuarioDAO.listEntities()
				.greaterThan("id", 1)
				.list();
		assertEquals(99, list.size());
	}
	
	@Test
	public void lessThan(){
		popularBanco();
		List<Usuario> list = usuarioDAO.listEntities()
				.lessThan("id", 100)
				.list();
		assertEquals(99, list.size());
	}
	
	@Test
	public void equal(){
		popularBanco();
		Usuario usuario = usuarioDAO.searchEntity()
				.equal("login", "test1")
				.search();
		
		assertNotNull(usuario);
	}
	
	@Test
	public void like(){
		popularBanco();
		Usuario usuario = usuarioDAO.searchEntity()
				.like("email.email", "11%")
				.search();
		
		assertNotNull(usuario);
	}
	
	

}
