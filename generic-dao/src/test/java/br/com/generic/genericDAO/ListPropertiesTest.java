package br.com.generic.genericDAO;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.generic.WeldJUnit4Runner;

@RunWith(WeldJUnit4Runner.class)
public class ListPropertiesTest extends BaseTest {

	@Test
	public void listPropertiesGenericOn(){
		popularBanco();
		List<String> list = usuarioDAO.<String>listProperties("login")
				.by("login+", "test%")
				.by("email.email", "1@gmail.com")
				.list();
		assertEquals(1, list.size());
	}
	
	@Test
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void listPropertiesGenericOff(){
		popularBanco();
		List<String> list = (List) usuarioDAO.listProperties("login")
				.by("login+", "test%")
				.by("email.email", "1@gmail.com")
				.list();
		assertEquals(1, list.size());
		assertEquals(String.class, list.get(0).getClass());
	}


}
