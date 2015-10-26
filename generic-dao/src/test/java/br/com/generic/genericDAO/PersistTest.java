package br.com.generic.genericDAO;

import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.generic.WeldJUnit4Runner;
import br.com.generic.entity.Usuario;

@RunWith(WeldJUnit4Runner.class)
public class PersistTest  extends BaseTest{

	@Test
	public void insertTest(){
		popularBanco();
	}
	
	@Test
	public void updateTest(){
		popularBanco();
		Usuario usuario = usuarioDAO.findEntityById(1);
		usuario.setNivel("master");
		usuario = usuarioDAO.update(usuario);
		assertEquals("master", usuario.getNivel());
	}
	
	@Test
	public void deletTest(){
		popularBanco();
		Usuario usuario = usuarioDAO.findEntityById(1);
		usuario = usuarioDAO.delete(usuario);
		usuario = usuarioDAO.findEntityById(1);
		assertNull(usuario);
	}
	
	

}
