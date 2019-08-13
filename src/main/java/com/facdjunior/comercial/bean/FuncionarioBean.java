package com.facdjunior.comercial.bean;

import com.facdjunior.comercial.dao.FuncionarioDAO;
import com.facdjunior.comercial.dao.PessoaDAO;
import com.facdjunior.comercial.domain.Funcionario;
import com.facdjunior.comercial.domain.Pessoa;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.omnifaces.util.Messages;

/**
 *
 * @author Francisco Junior 2019-08-13
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class FuncionarioBean implements Serializable {

    private Funcionario funcionario;
    private List<Pessoa> pessoas;
    private List<Funcionario> funcionarios;

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    @PostConstruct
    public void listar() {

        try {

            FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
            funcionarios = funcionarioDAO.listar();

        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu erro ao listar Registros");
            erro.printStackTrace();
        }
    }

    public void novo() {
        try {

            funcionario = new Funcionario();

            PessoaDAO pessoaDAO = new PessoaDAO();
            pessoas = pessoaDAO.listar("nome");

        } catch (RuntimeException erro) {
            Messages.addGlobalError("Erro ao tentar criar novo Registros");
            erro.printStackTrace();
        }
    }

    public void salvar() {
        try {

            FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
            funcionarioDAO.merge(funcionario);

            novo();
            listar();

            PessoaDAO pessoaDAO = new PessoaDAO();
            pessoas = pessoaDAO.listar("nome");

            Messages.addGlobalInfo("Registro salvo com Sucesso!");

        } catch (RuntimeException erro) {
            Messages.addGlobalError("Erro ao tentar Salvar Registro");
            erro.printStackTrace();
        }
    }

    public void excluir(ActionEvent evento) {
        try {

            funcionario = (Funcionario) evento.getComponent().getAttributes().get("funcionarioSelecionado");

            FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
            funcionarioDAO.excluir(funcionario);

            listar();
            PessoaDAO pessoaDAO = new PessoaDAO();
            pessoas = pessoaDAO.listar("nome");

            Messages.addGlobalInfo("Registro removido com sucesso!");
        } catch (RuntimeException erro) {

            Messages.addGlobalError("Não foi possível excluir Registro");
            erro.printStackTrace();
        }
    }

    public void editar(ActionEvent evento) {
        try {
            funcionario = (Funcionario) evento.getComponent().getAttributes().get("funcionarioSelecionado");

            PessoaDAO pessoaDAO = new PessoaDAO();
            pessoas = pessoaDAO.listar("nome");

            Messages.addGlobalInfo("Registro atualizado com Sucesso!");
        } catch (RuntimeException erro) {

            Messages.addGlobalError("Ocorreu erro ao tentar editar Registro");
            erro.printStackTrace();
        }
    }
}
