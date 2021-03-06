package br.com.financeiro.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.financeiro.dao.FornecedorDao;
import br.com.financeiro.domain.Fornecedor;

/**
 * @author Wagner Duarte
 *
 *
 * 26 de set. de 2021 20:57:45
 */
@ManagedBean
@ViewScoped
public class FornecedorBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Fornecedor fornecedor;
	private List<Fornecedor>fornecedores;
	
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}
	
	public void setFornecedores(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}
	

	public void novo() {
		fornecedor = new Fornecedor();
	}

	public void salvar() {
		try {
			FornecedorDao fornecedorDao = new FornecedorDao();
			fornecedorDao.merge(fornecedor);

			novo();
			fornecedores = fornecedorDao.listar();
			
			Messages.addGlobalInfo("Fornecedor salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o estado");
			erro.printStackTrace();
		}
	}
	
	

@PostConstruct
	public void listar(){
		try{
			FornecedorDao fornecedorDao = new FornecedorDao();
			fornecedores = fornecedorDao.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os fornecedores");
			erro.printStackTrace();
		}
	}


public void excluir(ActionEvent evento) {
	try {
		fornecedor = (Fornecedor) evento.getComponent().getAttributes().get("estadoSelecionado");

		FornecedorDao fornecedorDao = new FornecedorDao();
		fornecedorDao.excluir(fornecedor);
		
		fornecedores = fornecedorDao.listar();

		Messages.addGlobalInfo("Forneedor removido com sucesso");
	} catch (RuntimeException erro) {
		Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o Fornecedor");
		erro.printStackTrace();
	}
}

public void editar(ActionEvent evento){
	fornecedor = (Fornecedor) evento.getComponent().getAttributes().get("fornecedorSelecionado");
}

	
	

}
