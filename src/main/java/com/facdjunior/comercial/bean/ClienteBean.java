
package com.facdjunior.comercial.bean;

import com.facdjunior.comercial.dao.ClienteDAO;
import com.facdjunior.comercial.dao.PessoaDAO;
import com.facdjunior.comercial.domain.Cliente;
import com.facdjunior.comercial.domain.Pessoa;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.omnifaces.util.Messages;

/**
 *
 * @author Francisco Junior
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ClienteBean implements Serializable{
    
    private Cliente cliente;
    private List<Cliente> clientes;
    private List<Pessoa> pessoas;

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }
    
    @PostConstruct
    public void listar(){
        try {
            ClienteDAO clienteDAO = new ClienteDAO();
            clientes = clienteDAO.listar("pessoa.nome");
            
        } catch (RuntimeException erro) {
            
            Messages.addGlobalError("Erro ao exibir lista de clientes");
            erro.printStackTrace();
        }
    }
    
    public void novo(){
        
        try {
            cliente = new Cliente();
        
        PessoaDAO pessoaDAO = new PessoaDAO();
        pessoas = pessoaDAO.listar("nome");
            
        } catch (RuntimeException erro) {
            
            Messages.addGlobalError("Erro ao tentar criar novo Registro");
            erro.printStackTrace();
            
        }
        
    }
    
    public void salvar(){
        try {
            
            ClienteDAO clienteDAO = new ClienteDAO();
            clienteDAO.merge(cliente);
            
            novo();
            
            Messages.addGlobalInfo("Registro gravado com sucesso");
            
        } catch (RuntimeException erro) {
            
            Messages.addGlobalError("Erro ao tentar gravar Registro");
            erro.printStackTrace();
        }
    }
    
    
}
