/**
 * 
 */
package br.com.financeiro.util;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

/**
 * @author Wagner Duarte
 *
 *
 * 28 de set. de 2021 17:27:31
 */

	//http://localhost:8080/Financeiro/rest
	@ApplicationPath("rest")
	public class FinanceiroResourceConfig extends ResourceConfig {
		public FinanceiroResourceConfig(){
			packages("br.com.financeiro.service");
		}
	}


