package com.facdjunior.comercial.bean;

import com.facdjunior.comercial.dao.ProdutoDAO;
import com.facdjunior.comercial.domain.ItemVenda;
import com.facdjunior.comercial.domain.Produto;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
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
    
    

    @PostConstruct
    public void listar() {
        try {

            ProdutoDAO produtoDAO = new ProdutoDAO();
            produtos = produtoDAO.listar("descricao");
            
            itensVendas = new ArrayList<>();

        } catch (RuntimeException erro) {

            Messages.addGlobalError("Erro ao tentar carregar lista de produtos");
            erro.printStackTrace();
        }
    }
    
    public void adicionar(ActionEvent evento){
        
        Produto produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");
        
        ItemVenda itemVenda = new ItemVenda();
        
        itemVenda.setPrecoParcial(produto.getPreco());
        itemVenda.setProduto(produto);
        itemVenda.setQuantidade(new Short("1"));
        
        itensVendas.add(itemVenda);
    }

}
