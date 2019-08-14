package com.facdjunior.comercial.bean;

import com.facdjunior.comercial.dao.ClienteDAO;
import com.facdjunior.comercial.dao.FuncionarioDAO;
import com.facdjunior.comercial.dao.ProdutoDAO;
import com.facdjunior.comercial.dao.VendaDAO;
import com.facdjunior.comercial.domain.Cliente;
import com.facdjunior.comercial.domain.Funcionario;
import com.facdjunior.comercial.domain.ItemVenda;
import com.facdjunior.comercial.domain.Produto;
import com.facdjunior.comercial.domain.Venda;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.omnifaces.util.Messages;

/**
 *
 * @author Francisco Junior
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class VendaBean implements Serializable {

    private List<Produto> produtos;
    private List<ItemVenda> itensVendas;
    private List<Cliente> clientes;
    private List<Funcionario> funcionarios;
    private Venda venda;

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public List<ItemVenda> getItemVendas() {
        return itensVendas;
    }

    public void setItemVendas(List<ItemVenda> itemVendas) {
        this.itensVendas = itemVendas;
    }

    public List<ItemVenda> getItensVendas() {
        return itensVendas;
    }

    public void setItensVendas(List<ItemVenda> itensVendas) {
        this.itensVendas = itensVendas;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }
    
    
    

    @PostConstruct
    public void novo() {
        try {
            
            venda = new Venda();
            venda.setPrecoTotal(new BigDecimal("0.00"));

            ProdutoDAO produtoDAO = new ProdutoDAO();
            produtos = produtoDAO.listar("descricao");

            itensVendas = new ArrayList<>();

        } catch (RuntimeException erro) {

            Messages.addGlobalError("Erro ao tentar carregar lista de produtos");
            erro.printStackTrace();
        }
    }

    public void adicionar(ActionEvent evento) {

        Produto produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");

        int item = -1;

        for (int i = 0; i < itensVendas.size(); i++) {
            if (itensVendas.get(i).getProduto().equals(produto)) {
                item = i;
            }
        }

        if (item < 0) {

            ItemVenda itemVenda = new ItemVenda();

            itemVenda.setPrecoParcial(produto.getPreco());
            itemVenda.setProduto(produto);
            itemVenda.setQuantidade(new Short("1"));

            itensVendas.add(itemVenda);

        } else {
            ItemVenda itemVenda = itensVendas.get(item);
            itemVenda.setQuantidade(new Short(itemVenda.getQuantidade() + 1 + ""));

            itemVenda.setPrecoParcial(produto.getPreco().multiply(new BigDecimal(itemVenda.getQuantidade())));
        }
        
        calcularVenda();
    }

    public void remover(ActionEvent evento) {

        ItemVenda itemVenda = (ItemVenda) evento.getComponent().getAttributes().get("itemSelecionado");

        int item = -1;
        for (int i = 0; i < itensVendas.size(); i++) {
            if (itensVendas.get(i).getProduto().equals(itemVenda.getProduto())) {
                item = i;
            }
        }
        if(item > -1){
            itensVendas.remove(item);
        }
        calcularVenda();
    }
    
    public void calcularVenda(){
        venda.setPrecoTotal(new BigDecimal("0.00"));
        
        for(int i = 0; i < itensVendas.size(); i++){
            ItemVenda itemVenda = itensVendas.get(i);
            venda.setPrecoTotal(venda.getPrecoTotal().add(itemVenda.getPrecoParcial()));
        }
    }
    
    public void finalizar(){
        try {
            venda.setHorario(new Date());
            venda.setCliente(null);
            venda.setFuncionario(null);
            
            FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
            funcionarios = funcionarioDAO.listarOdenado();
            
            ClienteDAO clienteDAO = new ClienteDAO();
            clientes = clienteDAO.listarOdenado();
            
            
        } catch (RuntimeException erro) {
            
            Messages.addGlobalError("Ocorreu um erro ao tentar finalizar venda");
            erro.printStackTrace();
        }
    }
    
    public void salvar(){
        
        try {
            
            if(venda.getPrecoTotal().signum() == 0){
                Messages.addGlobalError("Venda não poder ser com valor igual R$: 0,00!");
                return;
            }
            
            VendaDAO vendaDAO = new VendaDAO();
            vendaDAO.salvar(venda, itensVendas);
            
            novo();
            
            Messages.addGlobalInfo("Regristro gravado com Sucesso!");
                    
            
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar finalizar a venda!");
            erro.printStackTrace();
        }
    }

}