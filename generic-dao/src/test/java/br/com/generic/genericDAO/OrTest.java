package br.com.generic.genericDAO;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.generic.WeldJUnit4Runner;
import br.com.generic.dao.WhereEntityListBuild;
import br.com.generic.entity.Usuario;

@RunWith(WeldJUnit4Runner.class)
public class OrTest extends BaseTest {

	@Test
	public void listTestW(){
		popularBanco();
		WhereEntityListBuild<Usuario> entityListBuild = usuarioDAO.listEntities()
				.notLike("login", "%1%");
		List<Usuario> list = usuarioDAO.listEntities()
				.or(entityListBuild)
				.list();
		assertEquals(100, list.size());
	}
}
