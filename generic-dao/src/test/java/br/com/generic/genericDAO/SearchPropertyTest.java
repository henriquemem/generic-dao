package br.com.generic.genericDAO;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.generic.WeldJUnit4Runner;

@RunWith(WeldJUnit4Runner.class)
public class SearchPropertyTest extends BaseTest {

	@Test
	public void searchPropertyGenericOn(){
		popularBanco();
		
		Long stat = new Date().getTime();
		for(int i = 1; i <= 1000; i++){
			String login = usuarioDAO.<String>searchProperty("login")
					.like("login", "test%")
					.equal("email.email", "1@gmail.com")
					.search();
		}
		System.out.println(new Date().getTime() - stat);
		//assertNotNull(login);
	}
	
	/*@Test
	public void searchPropertyGenericOff(){
		popularBanco();
		String login = (String) usuarioDAO.searchProperty("login")
				.like("login", "test%")
				.equal("email.email", "1@gmail.com")
				.search();
		
		assertNotNull(login);
	}*/
	

}
