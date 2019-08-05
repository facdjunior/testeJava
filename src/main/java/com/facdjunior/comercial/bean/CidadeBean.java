package com.facdjunior.comercial.bean;

import com.facdjunior.comercial.dao.CidadeDAO;
import com.facdjunior.comercial.dao.EstadoDAO;
import com.facdjunior.comercial.domain.Cidade;
import com.facdjunior.comercial.domain.Estado;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.omnifaces.util.Messages;

/**
 *
 * @author Francisco Alves 2019-08-05
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CidadeBean implements Serializable {

    private Cidade cidade;
    private List<Cidade> cidades;
    private List<Estado> estados;

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public List<Cidade> getCidades() {
        return cidades;
    }

    public void setCidades(List<Cidade> cidades) {
        this.cidades = cidades;
    }

    public List<Estado> getEstados() {
        return estados;
    }

    public void setEstados(List<Estado> estados) {
        this.estados = estados;
    }

    @PostConstruct
    public void listar() {
        try {

            CidadeDAO cidadeDAO = new CidadeDAO();
            cidades = cidadeDAO.listar();

        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar exibir lista de registro");
            erro.printStackTrace();
        }
    }

    public void novo() {

        cidade = new Cidade();
        EstadoDAO estadoDAO = new EstadoDAO();
        estados = estadoDAO.listar("nome");

    }

    public void salvar() {

        try {

            CidadeDAO cidadeDAO = new CidadeDAO();
            cidadeDAO.merge(cidade);

            novo();
            EstadoDAO estadoDAO = new EstadoDAO();
            estados = estadoDAO.listar();
            
            listar();

            Messages.addGlobalInfo("Registro salvo com Sucesso!");

        } catch (RuntimeException erro) {

            Messages.addGlobalError("Não foi possível gravar cadastro de Cidade!");
            erro.printStackTrace();

        }
    }

    public void excluir(ActionEvent evento) {

        try {

            cidade = (Cidade) evento.getComponent().getAttributes().get("cidadeSelecionada");

            CidadeDAO cidadeDAO = new CidadeDAO();
            cidadeDAO.excluir(cidade);

            listar();

            Messages.addGlobalInfo("Registro removido com sucesso!");

        } catch (RuntimeException erro) {

            Messages.addGlobalError("Não foi possível excluir cadastro de Cidade!");
            erro.printStackTrace();
        }
    }

    public void editar(ActionEvent evento) {
        try {

            cidade = (Cidade) evento.getComponent().getAttributes().get("cidadeSelecionada");

            EstadoDAO estadoDAO = new EstadoDAO();
            estados = estadoDAO.listar();

        } catch (RuntimeException erro) {

            Messages.addGlobalError("Ocorreu erro ao tentar selecionar cadastro!");
            erro.printStackTrace();
        }
    }
}
