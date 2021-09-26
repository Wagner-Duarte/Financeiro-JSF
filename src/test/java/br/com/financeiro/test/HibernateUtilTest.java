/**
 * 
 */
package br.com.financeiro.test;

import org.hibernate.Session;
import org.junit.Test;

import br.com.financeiro.util.HibernateUtil;

/**
 * @author Wagner Duarte
 *
 *
 * 25 de set. de 2021
 */
public class HibernateUtilTest {

	@Test
	public void conectar(){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		sessao.close();
		HibernateUtil.getFabricaDeSessoes().close();
	}
}
