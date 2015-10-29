package br.com.generic.DAO;

import br.com.generic.dao.GenericDAOImpl;
import br.com.generic.entity.Usuario;


public class UsuarioDAOImpl extends GenericDAOImpl<Usuario> implements UsuarioDAO{
	
	@Override
	protected Usuario consist(Usuario entity) {
		String login = this.<String>searchProperty("login")
				.equal("login", entity.getLogin())
				.notEqual("id", entity.getId())
				.search();
		
		
		return super.consist(entity);
	}

}
