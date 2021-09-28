package br.com.financeiro.bean;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.financeiro.dao.CidadeDao;
import br.com.financeiro.dao.EstadoDao;
import br.com.financeiro.dao.PessoaDao;
import br.com.financeiro.domain.Cidade;
import br.com.financeiro.domain.Estado;
import br.com.financeiro.domain.Pessoa;

/**
 * @author Wagner Duarte
 *
 *
 * 26 de set. de 2021 20:57:45
 */
@ManagedBean
@ViewScoped
public class PessoaBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Pessoa pessoa;
	private List<Pessoa> pessoas;
	private Estado estado;
	private List<Estado> estados;
	private List<Cidade> cidades;

	
	
	public Estado getEstado() {
		return estado;
	}
	
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	@PostConstruct
	public void listar() {
		try {
			PessoaDao pessoaDao = new PessoaDao();
			pessoas = pessoaDao.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar as pessoas");
			erro.printStackTrace();
		}
	}

	public void novo() {
		try {
			pessoa = new Pessoa();

			EstadoDao estadoDao = new EstadoDao();
			estados = estadoDao.listar("nome");

			cidades = new ArrayList<Cidade>();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar gerar uma nova pessoa");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try{
			pessoa = (Pessoa) evento.getComponent().getAttributes().get("pessoaSelecionada");
			
			estado = pessoa.getCidade().getEstado();
			
			EstadoDao estadoDao = new EstadoDao();
			estados = estadoDao.listar("nome");
			
			CidadeDao cidadeDao = new CidadeDao();
			cidades = cidadeDao.buscarPorEstado(estado.getCodigo());
		}catch(RuntimeException erro){
			Messages.addGlobalError("Ocorreu um erro ao tentar selecionar uma pessoa");
		}
	}

	public void salvar() {
		try {
			PessoaDao pessoaDao = new PessoaDao();
			pessoaDao.merge(pessoa);
			
			pessoas = pessoaDao.listar("nome");
			
			pessoa = new Pessoa();
			
			estado = new Estado();

			EstadoDao estadoDao = new EstadoDao();
			estados = estadoDao.listar("nome");

			cidades = new ArrayList<>();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar a pessoa");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {

	}
	
	

public void popular() {
		try {
			if (estado != null) {
				CidadeDao cidadeDao = new CidadeDao();
				cidades = cidadeDao.buscarPorEstado(estado.getCodigo());
			} else {
				cidades = new ArrayList<>();
			}
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar filtrar as cidades");
			erro.printStackTrace();
		}
	}
	
	
}