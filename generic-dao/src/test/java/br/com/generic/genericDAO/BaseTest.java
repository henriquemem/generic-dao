package br.com.generic.genericDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;

import br.com.generic.DAO.FuncionarioDAO;
import br.com.generic.DAO.PrivilegioDAO;
import br.com.generic.DAO.UsuarioDAO;
import br.com.generic.entity.Email;
import br.com.generic.entity.Funcionario;
import br.com.generic.entity.Privilegio;
import br.com.generic.entity.Usuario;
import junit.framework.TestCase;

@Ignore
public class BaseTest extends TestCase{

	@Inject
	protected UsuarioDAO usuarioDAO; 
	
	@Inject
	protected EntityManager entityManager;
	
	@Inject
	protected FuncionarioDAO funcionarioDao; 
	
	@Inject
	protected PrivilegioDAO privilegioDAO; 
	
	@Before
	public void beforeTest(){
		entityManager.getTransaction().begin();
		usuarioDAO.setEntityManager(entityManager);
		funcionarioDao.setEntityManager(entityManager);
		privilegioDAO.setEntityManager(entityManager);
	}
	
	@After
	public void afterTest(){
		entityManager.getTransaction().commit();
	}

	public void popularBanco(){
		Usuario usuario;
		Email email;
		for (int i = 1; i <= 100; i++) {
			usuario = new Usuario();
			usuario.setLogin("test" + i);
			usuario.setSenha(i+"");
			email = new Email();
			email.setEmail(i + "@gmail.com");
			usuario.setEmail(email);
			usuario.setFucionario(criarFuncionario(usuario, i));
			usuario = usuarioDAO.insert(usuario);
			usuario.setPrivilegios(criarPrivilegios(usuario));
			usuario.getFucionario().setUsuario(usuario);
			usuario.getFucionario().setPrivilegios(usuario.getPrivilegios());
			usuario.setFucionario(funcionarioDao.update(usuario.getFucionario()));
			usuario = usuarioDAO.update(usuario);
		}
	}
	
	private Funcionario criarFuncionario(Usuario usuario, int i){
		Funcionario funcionario = new Funcionario();
		funcionario.setNome("func" + i);
		return funcionarioDao.insert(funcionario);
	}
	
	private List<Privilegio> criarPrivilegios(Usuario usuario){
		List<Privilegio> list = new ArrayList<Privilegio>();
		Random gerador = new Random();
        int numero = gerador.nextInt(10);
        Privilegio privilegio;
        for (int i = 1; i <= numero; i++) {
        	privilegio = new Privilegio();
        	privilegio.setDescricao("pri" + i);
        	privilegio.setUsuario(usuario);
        	list.add(privilegioDAO.insert(privilegio));
		}
        return list;
	}
}
