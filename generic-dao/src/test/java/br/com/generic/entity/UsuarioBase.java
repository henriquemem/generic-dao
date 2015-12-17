package br.com.generic.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.FetchType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PrimaryKeyJoinColumn;


@MappedSuperclass
public class UsuarioBase extends EntityId<UsuarioBase>{

	private static final long serialVersionUID = -3327573295169448466L;
	

	private String login;
	
	private String senha;
	
	@OneToOne
	@PrimaryKeyJoinColumn
	private Funcionario fucionario;
	
	@Embedded
	private Email email;
	
	private String nivel;
	
	@OneToMany(mappedBy = "usuario", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Privilegio> privilegios = new ArrayList<Privilegio>();
	
	
	public UsuarioBase() {
		super();
	}
	

	public Funcionario getFucionario() {
		return fucionario;
	}


	public void setFucionario(Funcionario fucionario) {
		this.fucionario = fucionario;
	}


	public String getNivel() {
		return nivel;
	}
	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
	
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Privilegio> getPrivilegios() {
		return privilegios;
	}


	public void setPrivilegios(List<Privilegio> privilegios) {
		this.privilegios = privilegios;
	}


	public Email getEmail() {
		return email;
	}


	public void setEmail(Email email) {
		this.email = email;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((nivel == null) ? 0 : nivel.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
		return result;
	}





	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioBase other = (UsuarioBase) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (nivel == null) {
			if (other.nivel != null)
				return false;
		} else if (!nivel.equals(other.nivel))
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		return true;
	}
	

	@PrePersist
	public void prePersist() {
		System.out.println("Entity");
		
	}
	
	
	
	

}
