package br.com.generic.entity;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;


public abstract class ChaveValor<E> extends EntityId<E> {
	private static final long serialVersionUID = 4111302334454641705L;

	private Integer codigo; // Chave
	private String nome;    // Valor
	private String abreviatura;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
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

	@PrePersist
	@PreUpdate
	protected void consistir() {
		consistirCodigo();
		consistirNome();
		consistirAbreviatura();
	}

	protected void consistirCodigo() {
		if (this.codigo == null) {
			throw new ModelException("O código é obrigatório!");
		}
	}
	
	protected void consistirNome() {
		if (this.nome == null || this.nome.isEmpty()) {
			throw new ModelException("O nome é obrigatório!");
		}
	}
	
	protected void consistirAbreviatura() {
		if (this.abreviatura == null || this.abreviatura.isEmpty()) {
			throw new ModelException("A abreviatura é obrigatória!");
		}
	}
	
}