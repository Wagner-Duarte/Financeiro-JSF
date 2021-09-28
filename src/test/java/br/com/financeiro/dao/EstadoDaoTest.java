/**
 * 
 */
package br.com.financeiro.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.financeiro.domain.Estado;


/**
 * @author Wagner Duarte
 *
 *
 *         26 de set. de 2021 11:11:32
 */
public class EstadoDaoTest {
	@Test
	@Ignore
	public void salvar() {
		Estado estado = new Estado();
		estado.setNome("Rio de Janeiro");
		estado.setSigla("RJ");

		Estado estado2 = new Estado();
		estado2.setNome("Minas Gerais");
		estado2.setSigla("MG");

		EstadoDao estadoDao = new EstadoDao();
		estadoDao.salvar(estado);
		estadoDao.salvar(estado2);
		
	}

	@Test
	@Ignore
	public void merge() {
		Estado estado = new Estado();
		estado.setNome("Goiás");
		estado.setSigla("GO");

		Estado estado2 = new Estado();
		estado2.setNome("Tocantins");
		estado2.setSigla("TO");

		EstadoDao estadoDAO = new EstadoDao();
		estadoDAO.merge(estado);
		estadoDAO.merge(estado2);
	}

	@Test
	
	public void listar() {
		EstadoDao estadoDAO = new EstadoDao();
		List<Estado> resultado = estadoDAO.listar();

		System.out.println("Total de Registros Encontrados: " + resultado.size());

		for (Estado estado : resultado) {
			System.out.println(estado.getCodigo() + " - " + estado.getSigla() + " - " + estado.getNome());
		}
	}

	@Test
	@Ignore
	public void buscar() {
		Long codigo = 1L;

		EstadoDao estadoDAO = new EstadoDao();
		Estado estado = estadoDAO.buscar(codigo);

		if (estado == null) {
			System.out.println("Nenhum registro encontrado");
		} else {
			System.out.println("Registro encontrado:");
			System.out.println(estado.getCodigo() + " - " + estado.getSigla() + " - " + estado.getNome());
		}
	}

	@Test
	@Ignore
	public void excluir() {
		Long codigo = 2L;
		EstadoDao estadoDAO = new EstadoDao();
		Estado estado = estadoDAO.buscar(codigo);

		if (estado == null) {
			System.out.println("Nenhum registro encontrado");
		} else {
			estadoDAO.excluir(estado);
			System.out.println("Registro removido:");
			System.out.println(estado.getCodigo() + " - " + estado.getSigla() + " - " + estado.getNome());
		}
	}

	@Test
	@Ignore
	public void editar() {
		Long codigo = 1L;
		EstadoDao estadoDAO = new EstadoDao();
		Estado estado = estadoDAO.buscar(codigo);

		if (estado == null) {
			System.out.println("Nenhum registro encontrado");
		} else {
			System.out.println("Registro editado - Antes:");
			System.out.println(estado.getCodigo() + " - " + estado.getSigla() + " - " + estado.getNome());

			estado.setNome("Santa Catarina");
			estado.setSigla("SC");
			estadoDAO.editar(estado);

			System.out.println("Registro editado - Depois:");
			System.out.println(estado.getCodigo() + " - " + estado.getSigla() + " - " + estado.getNome());
		}
	}

}