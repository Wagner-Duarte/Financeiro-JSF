package br.com.financeiro.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.financeiro.dao.FornecedorDao;
import br.com.financeiro.dao.ProdutoDao;
import br.com.financeiro.domain.Fornecedor;
import br.com.financeiro.domain.Produto;

/**
 * @author Wagner Duarte
 *
 *
 * 26 de set. de 2021 20:57:45
 */
@ManagedBean
@ViewScoped
public class ProdutoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Produto  produto;
	private List<Produto> produtos;
	private List<Fornecedor> fornecedor;
	
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<Fornecedor> getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(List<Fornecedor> fornecedor) {
		this.fornecedor = fornecedor;
	}

	@PostConstruct
	public void listar() {
		try {
			ProdutoDao produtoDao = new ProdutoDao();
			produtos = produtoDao.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os produtos");
			erro.printStackTrace();
		}
	}
	
	public void novo() {
		try {
			produto = new Produto();

			FornecedorDao fabricanteDAO = new FornecedorDao();
			fornecedor = fabricanteDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar gerar um novo produto");
			erro.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento){
		try {
			produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");

			FornecedorDao fornecedorDao = new FornecedorDao();
			fornecedor = fornecedorDao.listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar um produto");
			erro.printStackTrace();
		}	
	}
	
	public void salvar() {
		try {
			ProdutoDao produtoDao = new ProdutoDao();
			produtoDao.merge(produto);

			produto = new Produto();

			FornecedorDao fornecedorDao = new FornecedorDao();
			fornecedor = fornecedorDao.listar();

			produtos = produtoDao.listar();

			Messages.addGlobalInfo("Produto salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar o produto");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");

			ProdutoDao produtoDao = new ProdutoDao();
			produtoDao.excluir(produto);

			produtos = produtoDao.listar();

			Messages.addGlobalInfo("Produto removido com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o produto");
			erro.printStackTrace();
		}
	}
}