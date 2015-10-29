package br.com.generic.entity;


public class EmbalagemCodigoBarras extends EntityId<EmbalagemCodigoBarras>{
	private static final long serialVersionUID = 8004373109491022084L;
	
	private Embalagem embalagem;
	private String codigoBarras;
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
