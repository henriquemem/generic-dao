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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((embalagens == null) ? 0 : embalagens.hashCode());
		result = prime * result + ((fabricante == null) ? 0 : fabricante.hashCode());
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
		Produto other = (Produto) obj;
		if (embalagens == null) {
			if (other.embalagens != null)
				return false;
		} else if (!embalagens.equals(other.embalagens))
			return false;
		if (fabricante == null) {
			if (other.fabricante != null)
				return false;
		} else if (!fabricante.equals(other.fabricante))
			return false;
		return true;
	}

	
		
}
