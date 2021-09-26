/**
 * 
 */
package br.com.financeiro.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author Wagner Duarte
 *
 *
 * 26 de set. de 2021 09:02:42
 */


@Entity
public class Fornecedor extends GenericDomain {
	@Column(length = 50, nullable = false)
	private String descricao;
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}