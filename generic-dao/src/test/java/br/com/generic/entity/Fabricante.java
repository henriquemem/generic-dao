
package br.com.generic.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="fabricante")
public class Fabricante extends ChaveValor<Fabricante> {

	private static final long serialVersionUID = -2164920745225937856L;
	
}
