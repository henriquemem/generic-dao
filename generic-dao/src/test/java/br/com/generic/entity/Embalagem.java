package br.com.generic.entity;

import java.util.HashSet;
import java.util.Set;

public class Embalagem extends EntityId<Embalagem> {
	private static final long serialVersionUID = -2533440098025652094L;

	private Produto<?> produto;
	private String nome;
	private String abreviatura;
	
	private String codigoBarras;

	private Boolean eEmbalagemPadrao = false;
	private String codigoMs;
	
	private Set<EmbalagemCodigoBarras> codigosBarras;
	
	public Produto<?> getProduto() {
		return produto;
	}

	protected void setProduto(Produto<?> produto) {
		this.produto = produto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getAbreviatura() {
		return abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}


	public Boolean geteEmbalagemPadrao() {
		return eEmbalagemPadrao;
	}

	public void seteEmbalagemPadrao(Boolean eEmbalagemPadrao) {
		this.eEmbalagemPadrao = eEmbalagemPadrao;
	}

	public Set<EmbalagemCodigoBarras> getCodigosBarras() {
		return this.codigosBarras = this.codigosBarras == null ? new HashSet<EmbalagemCodigoBarras>() : this.codigosBarras;
	}
	
	public String getCodigoMs() {
		return codigoMs;
	}

	public void setCodigoMs(String codigoMs) {
		this.codigoMs = codigoMs;
	}


	protected void setCodigosBarras(Set<EmbalagemCodigoBarras> embalagensCodigosBarras) {
		if (! embalagensCodigosBarras.equals(getCodigosBarras())) {
			for (EmbalagemCodigoBarras embalagemCodigoBarras : getCodigosBarras()) {
				embalagemCodigoBarras.setEmbalagem(null);
			}
			this.codigosBarras.clear();
		}
		this.codigosBarras = embalagensCodigosBarras;
	}

	public boolean addEmbalagemCodigoBarras(EmbalagemCodigoBarras embalagemCodigoBarras) {
		embalagemCodigoBarras.setEmbalagem(this);
		return this.getCodigosBarras().add(embalagemCodigoBarras);
	}	

	

	
	
	
	public boolean contemEsseCodigoBarras(String codigoBarras){
		for(EmbalagemCodigoBarras embalagemCodigoBarras : this.codigosBarras){
			if(embalagemCodigoBarras.getCodigoBarras().equals(codigoBarras))
				return true;
		}
		return false;
	}
	
	public EmbalagemCodigoBarras obterEmbalagemCodigoBarrasPorCodigoBarras(String CodigoBarras){
		for(EmbalagemCodigoBarras embalagemCodigoBarras : this.codigosBarras){
			if(embalagemCodigoBarras.getCodigoBarras().equals(codigoBarras))
				return embalagemCodigoBarras;
		}
		return null;
	}
	
}