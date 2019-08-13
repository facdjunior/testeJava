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
    }

}
