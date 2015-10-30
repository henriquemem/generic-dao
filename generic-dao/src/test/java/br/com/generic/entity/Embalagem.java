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
@Table(name="embalagem")
public class Embalagem extends EntityId<Embalagem> {
	private static final long serialVersionUID = -2533440098025652094L;

	@ManyToOne
	@JoinColumn(name="produto_id")
	private Produto produto;
	@Column(name="nome")
	private String nome;
	@Column(name="abreviatura")
	private String abreviatura;
	@Column(name="codigo_barras")
	private String codigoBarras;

	@Column(name="e_embalagem_padrao")
	private Boolean eEmbalagemPadrao = false;
	@Column(name="codigo_ms")
	private String codigoMs;
	
	@OneToMany
	@JoinColumn(name="embalagem_id")
	private Set<EmbalagemCodigoBarras> codigosBarras;
	
	public Produto getProduto() {
		return produto;
	}

	protected void setProduto(Produto produto) {
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