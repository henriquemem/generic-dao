package br.com.generic.genericDAO;

import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.generic.WeldJUnit4Runner;

@RunWith(WeldJUnit4Runner.class)
public class SearchPropertyTest extends BaseTest {

	@Test
	public void searchPropertyGenericOn(){
		popularBanco();
		String login = usuarioDAO.<String>searchProperty("login")
				.by("login+", "test%")
				.by("email.email", "1@gmail.com")
				.search();
		
		assertNotNull(login);
	}
	
	@Test
	public void searchPropertyGenericOff(){
		popularBanco();
		String login = (String) usuarioDAO.searchProperty("login")
				.by("login+", "test%")
				.by("email.email", "1@gmail.com")
				.search();
		
		assertNotNull(login);
	}
	

}
