package br.com.generic.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="produto")
public class Produto extends ChaveValor<Produto> {
	private static final long serialVersionUID = 4986454563776608370L;
		
	@ManyToOne
	@JoinColumn(name="fabricante_id")
	private Fabricante fabricante;	
	
	@OneToMany
	@JoinColumn(name="produto_id")
	private Set<Embalagem> embalagens = new HashSet<Embalagem>();	



	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public Set<Embalagem> getEmbalagens() {
		return embalagens = embalagens == null ? new HashSet<Embalagem>() : embalagens;
	}

		
}
