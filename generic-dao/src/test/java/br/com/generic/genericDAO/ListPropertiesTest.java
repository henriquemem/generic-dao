package br.com.generic.genericDAO;

import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.generic.WeldJUnit4Runner;

@RunWith(WeldJUnit4Runner.class)
public class ListPropertiesTest extends BaseListTest {

	@Test
	public void listPropertiesGenericOn(){
		/*popularBanco();
		List<String> list = usuarioDAO.<String>listProperties("login")
				.like("login", "test%")
				.equal("email.email", "1@gmail.com")
				.list();
		assertEquals(1, list.size());*/
	}
	
	@Test
	public void listPropertiesGenericOff(){
		/*popularBanco();
		List<String> list = (List) usuarioDAO.listProperties("login")
				.like("login", "test%")
				.equal("email.email", "1@gmail.com")
				.list();
		assertEquals(1, list.size());
		assertEquals(String.class, list.get(0).getClass());*/
	}


}
