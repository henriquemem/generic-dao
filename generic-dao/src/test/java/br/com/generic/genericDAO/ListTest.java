package br.com.generic.genericDAO;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.generic.WeldJUnit4Runner;
import br.com.generic.entity.Usuario;

@RunWith(WeldJUnit4Runner.class)
public class ListTest extends BaseTest{

	@Test
	public void listTest(){
		popularBanco();
		List<Usuario> list = usuarioDAO.list(0, 0, null);
		assertEquals(list.size(), 100);
	}
	
	@Test
	public void listTestLimitInit(){
		popularBanco();
		List<Usuario> list = usuarioDAO.list(0, 50, "id");
		assertEquals(50, list.size());
		assertEquals(1, list.get(0).getId());
		assertEquals(50, list.get(49).getId());
	}
	
	@Test
	public void listTestLimitEnd(){
		popularBanco();
		List<Usuario> list = usuarioDAO.list(50, 100, "id");
		assertEquals(50 , list.size());
		assertEquals(51, list.get(0).getId());
		assertEquals(100, list.get(49).getId());
	}
	
	@Test
	public void listTestW(){
		popularBanco();
		List<Usuario> list = usuarioDAO.listEntities().list();
		assertEquals(100, list.size());
	}
}
