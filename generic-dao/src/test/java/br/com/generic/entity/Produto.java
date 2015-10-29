package br.com.generic.entity;

import java.util.HashSet;
import java.util.Set;

public abstract class Produto<E extends Produto<?>> extends ChaveValor<E> {
	private static final long serialVersionUID = 4986454563776608370L;
		
	private Double quantidadeMinima;
	private String observacao;
	private Fabricante fabricante;	
	
	
	private Set<Embalagem> embalagens = new HashSet<Embalagem>();	
	//private Set<ProdutoArmazem> armazems = new HashSet<ProdutoArmazem>();	

	public Double getQuantidadeMinima() {
		return quantidadeMinima;
	}

	public void setQuantidadeMinima(Double quantidadeMinima) {
		this.quantidadeMinima = quantidadeMinima;
	}
	
	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

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
