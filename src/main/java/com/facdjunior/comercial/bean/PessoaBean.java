package com.facdjunior.comercial.bean;

import com.facdjunior.comercial.dao.CidadeDAO;
import com.facdjunior.comercial.dao.EstadoDAO;
import com.facdjunior.comercial.dao.PessoaDAO;
import com.facdjunior.comercial.domain.Cidade;
import com.facdjunior.comercial.domain.Estado;
import com.facdjunior.comercial.domain.Pessoa;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.omnifaces.util.Messages;

/**
 *
 * @author Francisco Junior 2019-08-05
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class PessoaBean implements Serializable {

    private Pessoa pessoa;
    private List<Pessoa> pessoas;

    private Estado estado;
    private List<Estado> estados;
    private List<Cidade> cidades;

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

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @PostConstruct
    public void listar() {

        try {

            PessoaDAO pessoaDAO = new PessoaDAO();
            pessoas = pessoaDAO.listar();

        } catch (RuntimeException erro) {

            Messages.addGlobalError("Erro ao tentar listar registros");
            erro.printStackTrace();
        }
    }

    public void novo() {

        try {
            pessoa = new Pessoa();
            estado = new Estado();
            EstadoDAO estadoDAO = new EstadoDAO();
            estados = estadoDAO.listar("nome");

            cidades = new ArrayList<Cidade>();
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar gerar um novo Registro");
        }

    }

    public void salvar() {
        try {

            PessoaDAO pessoaDAO = new PessoaDAO();
            pessoaDAO.merge(pessoa);

            pessoas = pessoaDAO.listar("nome");

            novo();
            Messages.addGlobalInfo("Registro salvo com sucesso");
            
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar salvar Registro");
            erro.printStackTrace();
        }
    }

    public void excluir(ActionEvent evento) {
        try {
            pessoa = (Pessoa) evento.getComponent().getAttributes().get("pessoaSelecionada");

            PessoaDAO pessoaDAO = new PessoaDAO();
            pessoaDAO.excluir(pessoa);

            listar();

            Messages.addGlobalInfo("Registro removido com sucesso");
        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover Registro");
            erro.printStackTrace();
        }
    }

    public void editar(ActionEvent evento) {

        try {
            pessoa = (Pessoa) evento.getComponent().getAttributes().get("pessoaSelecionada");

            estado = pessoa.getCidade().getEstado();

            EstadoDAO estadoDAO = new EstadoDAO();
            estados = estadoDAO.listar("nome");

            popular();

        } catch (RuntimeException erro) {

            Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar Registro");
            erro.printStackTrace();
        }
    }

    public void popular() {
        try {

            if (estado != null) {
                CidadeDAO cidadeDAO = new CidadeDAO();
                cidades = cidadeDAO.buscarPorEstado(estado.getCodigo());
            } else {
                cidades = new ArrayList<Cidade>();
            }
        } catch (RuntimeException erro) {

            Messages.addGlobalError("Ocorreu um ero ao tentar filtrar as cidades");
            erro.printStackTrace();
        }
    }
}
