package com.facdjunior.comercial.bean;

import com.facdjunior.comercial.dao.FabricanteDAO;
import com.facdjunior.comercial.dao.ProdutoDAO;
import com.facdjunior.comercial.domain.Fabricante;
import com.facdjunior.comercial.domain.Produto;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Francisco Junior 2019-08-05
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ProdutoBean implements Serializable {

    private Produto produto;
    private List<Produto> produtos;
    private List<Fabricante> fabricantes;

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

    public List<Fabricante> getFabricantes() {
        return fabricantes;
    }

    public void setFabricantes(List<Fabricante> fabricantes) {
        this.fabricantes = fabricantes;
    }

    @PostConstruct
    public void listar() {
        try {

            ProdutoDAO produtoDAO = new ProdutoDAO();
            produtos = produtoDAO.listar();

        } catch (RuntimeException erro) {

            Messages.addGlobalError("Ocorreu um erro ao tentar listar registros cadastrados");
            erro.printStackTrace();
        }
    }

    public void novo() {
        try {
            produto = new Produto();

            FabricanteDAO fabricanteDAO = new FabricanteDAO();
            fabricantes = fabricanteDAO.listar();

        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao gerar um novo Registro!");
            erro.printStackTrace();
        }
    }

    public void salvar() {
        try {
            
            ProdutoDAO produtoDAO = new ProdutoDAO();
            Produto produtoRetorno = produtoDAO.merge(produto);
            
            Path origem = Paths.get(produto.getCaminho());
            Path destino = Paths.get("D:/Curso/imagemProjeto/"+produtoRetorno.getCodigo()+ ".png");
            Files.copy(origem, destino, StandardCopyOption.REPLACE_EXISTING);

            produto = new Produto();

            FabricanteDAO fabricanteDAO = new FabricanteDAO();
            fabricantes = fabricanteDAO.listar();

            produtos = produtoDAO.listar();

            Messages.addGlobalInfo("Registro salvo com sucesso!");

        } catch (RuntimeException | IOException erro) {
            Messages.addGlobalError("Ocorreu um erro ao salvar o Registro!");
            erro.printStackTrace();
        }
    }

    public void excluir(ActionEvent evento) {
        try {
            produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");

            ProdutoDAO produtoDAO = new ProdutoDAO();
            produtoDAO.excluir(produto);

            produtos = produtoDAO.listar();

            Messages.addGlobalInfo("Registro removido com Sucesso!");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar excluir o Registro!");
            erro.printStackTrace();
        }
    }

    public void editar(ActionEvent evento) {
        try {
            produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");

            FabricanteDAO fabricanteDAO = new FabricanteDAO();
            fabricantes = fabricanteDAO.listar();

        } catch (RuntimeException erro) {

            Messages.addGlobalError("Ocorreu um erro ao tentar 'Editar' Registro!");
            erro.printStackTrace();
        }
    }

    public void upload(FileUploadEvent evento) {
        try {
            UploadedFile arquivoUpload = evento.getFile();
            Path arquivoTemp = Files.createTempFile(null, null);

            Files.copy(arquivoUpload.getInputstream(), arquivoTemp, StandardCopyOption.REPLACE_EXISTING);
            produto.setCaminho(arquivoTemp.toString());
            
            Messages.addGlobalInfo("Produto"+produto.getCaminho());
            System.out.println(produto.getCaminho());
            
            
        } catch (IOException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar realizar upload do arquivo");
            erro.printStackTrace();
        }
    }

}
