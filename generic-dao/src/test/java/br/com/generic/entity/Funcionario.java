package br.com.generic.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Funcionario extends EntityId {

	private static final long serialVersionUID = 2340487246123920919L;

	private String nome;
	
	@OneToOne(mappedBy="fucionario")
	private Usuario usuario; 
	
	@OneToMany(mappedBy = "funcionario", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Privilegio> privilegios = new ArrayList<Privilegio>();

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Privilegio> getPrivilegios() {
		return privilegios;
	}

	public void setPrivilegios(List<Privilegio> privilegios) {
		this.privilegios = privilegios;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	
}
