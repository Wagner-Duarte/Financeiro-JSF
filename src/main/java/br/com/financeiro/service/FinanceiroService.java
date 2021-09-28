/**
 * 
 */
package br.com.financeiro.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * @author Wagner Duarte
 *
 *
 * 28 de set. de 2021 17:30:15
 */
//http://localhost:8080/Financeiro/rest/financeiro
@Path("financeiro")
public class FinanceiroService {
	@GET
	public String exibir(){
		return "Sistema Financeiro by Wagner Duarte";
	}
}
