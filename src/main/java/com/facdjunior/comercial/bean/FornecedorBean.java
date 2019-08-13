package com.facdjunior.comercial.bean;

import com.facdjunior.comercial.dao.FabricanteDAO;
import com.facdjunior.comercial.domain.Fabricante;

import com.google.gson.Gson;
import java.io.Serializable;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;

import org.omnifaces.util.Messages;

/**
 *
 * @author Francisco Junior 2019-08-05
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class FornecedorBean implements Serializable {

    private Fabricante fabricante;
    private List<Fabricante> fabricantes;

    public Fabricante getFabricante() {
        return fabricante;
    }

    public void setFabricante(Fabricante fabricante) {
        this.fabricante = fabricante;
    }

    public List<Fabricante> getFabricantes() {
        return fabricantes;
    }

    public void setFabricantes(List<Fabricante> fabricantes) {
        this.fabricantes = fabricantes;
    }

    @PostConstruct
    public void listar() {
        try {

            Client cliente = ClientBuilder.newClient();
            WebTarget caminho = cliente.target("http://127.0.0.1:8080/Comercial/rest/fabricante");
            String json = caminho.request().get(String.class);

            Gson gson = new Gson();
            Fabricante[] vetor = gson.fromJson(json, Fabricante[].class);

            fabricantes = Arrays.asList(vetor);
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar listar registros");
            erro.printStackTrace();
        }
    }

    public void novo() {
        fabricante = new Fabricante();
    }

    public void salvar() {

        try {

            Client cliente = ClientBuilder.newClient();
            WebTarget caminho = cliente.target("http://localhost:8080/Comercial/rest/fabricante");

            Gson gson = new Gson();
            String json = gson.toJson(fabricante);
            caminho.request().post(Entity.json(json));

            novo();

            json = caminho.request().get(String.class);
            Fabricante[] vetor = gson.fromJson(json, Fabricante[].class);
            fabricantes = Arrays.asList(vetor);

            Messages.addGlobalInfo("Registro gravado com sucesso");

        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar salvar registro");
            erro.printStackTrace();
        }
    }

    public void excluir(ActionEvent evento) {
        try {
            fabricante = (Fabricante) evento.getComponent().getAttributes().get("fornecedorSelecionado");

            Client cliente = ClientBuilder.newClient();
            WebTarget caminho = cliente.target("http://localhost:8080/Comercial/rest/fabricante/");

            caminho.path("{codigo}").resolveTemplate("codigo",fabricante.getCodigo()).request().delete();
            String json = caminho.request().get(String.class);
            
            Gson gson = new Gson();
            Fabricante[] vetor = gson.fromJson(json, Fabricante[].class);

            fabricantes = Arrays.asList(vetor);

            Messages.addGlobalInfo("Registro removido com sucesso");
        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover Registro!");
            erro.printStackTrace();
        }
    }

    public void editar(ActionEvent evento) {
        fabricante = (Fabricante) evento.getComponent().getAttributes().get("fornecedorSelecionado");
    }
}
