package br.com.financeiro.bean;



/**
* @author Wagner Duarte
*
*
* 26 de set. de 2021 20:57:45
*/
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.financeiro.dao.ClienteDao;
import br.com.financeiro.dao.PessoaDao;
import br.com.financeiro.domain.Cliente;
import br.com.financeiro.domain.Pessoa;


@ManagedBean
@ViewScoped
public class ClienteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Cliente cliente;

	private List<Cliente> clientes;
	private List<Pessoa> pessoas;

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	@PostConstruct
	public void listar() {
		try {
			ClienteDao clienteDao = new ClienteDao();
			clientes = clienteDao.listar("dataCadastro");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os clientes");
			erro.printStackTrace();
		}
	}

	public void novo() {
		try {
			cliente = new Cliente();

			PessoaDao pessoaDao = new PessoaDao();
			pessoas = pessoaDao.listar("nome");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar criar um novo cliente");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			ClienteDao clienteDao = new ClienteDao();
			clienteDao.merge(cliente);

			cliente = new Cliente();
			
			clientes = clienteDao.listar("dataCadastro");

			PessoaDao pessoaDao = new PessoaDao();
			pessoas = pessoaDao.listar("nome");
			
			Messages.addGlobalInfo("Cliente salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o cliente");
			erro.printStackTrace();
		}
	}
}
