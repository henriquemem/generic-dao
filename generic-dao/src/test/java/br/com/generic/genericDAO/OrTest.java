package br.com.generic.genericDAO;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.generic.WeldJUnit4Runner;
import br.com.generic.dao.WhereEntityListBuilder;
import br.com.generic.entity.Usuario;

@RunWith(WeldJUnit4Runner.class)
public class OrTest extends BaseTest {

	@Test
	public void listTestW(){
		popularBanco();
	
		
		WhereEntityListBuilder<Usuario> entityListBuild2 = usuarioDAO.listEntities()
				.like("login", "%1%");
		
		WhereEntityListBuilder<Usuario> entityListBuild3 = usuarioDAO.listEntities()
				.equal("login", "test87");
		
		List<Usuario> list = usuarioDAO.listEntities()
				.and(entityListBuild2)
				.or(entityListBuild3)
				.list();
		assertEquals(21, list.size());
	}
}
