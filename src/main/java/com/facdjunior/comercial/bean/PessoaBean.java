package com.facdjunior.comercial.bean;

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
import org.omnifaces.util.Messages;

/**
 *
 * @author Francisco Junior 2019-08-05
 */

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class PessoaBean implements Serializable{
    
    private Pessoa pessoa;
    private List<Pessoa> pessoas;
    
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
    
    @PostConstruct
    public void listar(){
        
        try {
            
            PessoaDAO pessoaDAO = new PessoaDAO();
            pessoas = pessoaDAO.listar();
            
        } catch (RuntimeException erro) {
            
            Messages.addGlobalError("Erro ao tentar listar registros");
            erro.printStackTrace();
        }
    }
    
    public void novo(){
        
        try{
        pessoa = new Pessoa();
        
        EstadoDAO estadoDAO = new EstadoDAO();
        estados = estadoDAO.listar("nome");
        
        cidades = new ArrayList<Cidade>();
        }catch(RuntimeException erro){
            Messages.addGlobalError("Ocorreu um erro ao tentar gerar um novo Registro");
        }
        
    }
    
    public void salvar(){
        
    }
    
    public void excluir(){
        
    }
    
    public void editar(){
        
    }
    
}
