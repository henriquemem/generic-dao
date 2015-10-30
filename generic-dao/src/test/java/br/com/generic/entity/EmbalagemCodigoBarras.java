package br.com.generic.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="embalagem_codigo_barras")
public class EmbalagemCodigoBarras extends EntityId<EmbalagemCodigoBarras>{
	private static final long serialVersionUID = 8004373109491022084L;
	
	@ManyToOne
	@JoinColumn(name="embalagem_id")
	private Embalagem embalagem;
	@Column(name="codigo_barras")
	private String codigoBarras;
	@Column(name="principal")
	private Boolean principal = false;
	
	public Embalagem getEmbalagem() {
		return embalagem;
	}
	public void setEmbalagem(Embalagem embalagem) {
		this.embalagem = embalagem;
	}
	public String getCodigoBarras() {
		return codigoBarras;
	}
	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}
	public Boolean getPrincipal() {
		return principal;
	}
	public void setPrincipal(Boolean principal) {
		this.principal = principal;
	}	
	
}
