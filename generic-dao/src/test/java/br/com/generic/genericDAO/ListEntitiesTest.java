package br.com.generic.genericDAO;

import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.generic.WeldJUnit4Runner;
import br.com.generic.dao.type.Order;

@RunWith(WeldJUnit4Runner.class)
public class ListEntitiesTest extends BaseTest {

	
	
	/*@Test
	public void listEntitiesLike(){
		popularBanco();
		List<Usuario> list = usuarioDAO.listEntities()
				.by("login+", "test%")
				.by("email.email", "1@gmail.com")
				.list();
		assertEquals(1, list.size());
	}
	
	@Test
	public void listEntitiesEndsWith(){
		popularBanco();
		List<Usuario> list = usuarioDAO.listEntities()
				.endsWith(50)
				.list();
		assertEquals(50, list.size());
	}
	
	@Test
	public void listEntitiesStartWith(){
		popularBanco();
		List<Usuario> list = usuarioDAO.listEntities()
				.startWith(50)
				.list();
		assertEquals(50, list.size());
	}
	
	@Test
	public void listEntitiesSortBy(){
		popularBanco();
		List<Usuario> list = usuarioDAO.listEntities()
				.sortBy("id")
				.list();
		assertEquals(1, list.get(0).getId());
	}
	
	@Test
	public void listEntitiesSortByASC(){
		popularBanco();
		List<Usuario> list = usuarioDAO.listEntities()
				.sortBy("id", Order.ASC)
				.list();
		assertEquals(1, list.get(0).getId());
	}
	
	@Test
	public void listEntitiesSortByASCDESC(){
		popularBanco();
		List<Usuario> list = usuarioDAO.listEntities()
				.sortBy("id", Order.DESC)
				.list();
		assertEquals(100, list.get(0).getId());
	}
	
	@Test
	public void listEntitiesSub(){
		popularBanco();
		List<Usuario> list = usuarioDAO.listEntities()
				.by("privilegios.descricao", "pri1")
				.list();
		System.out.println(list.size());
	}
	
	@Test
	public void listEntitiesSub1(){
		popularBanco();
		Usuario usuario = usuarioDAO.searchEntity()
				.by("login+", "test%")
				.by("email.email", "1@gmail.com")
				.search();
		
		List<Usuario> list = usuarioDAO.listEntities()
				.by("fucionario.privilegios.descricao", "pri1")
				.list();
		System.out.println(list.size());
	}*/
	
	@Test
	public void test(){
		Order o1 = Order.ASC;
		Order o2 = Order.ASC;
		
		assertEquals(o1, o2);
		System.out.println( o1 == o2);
	}

}
