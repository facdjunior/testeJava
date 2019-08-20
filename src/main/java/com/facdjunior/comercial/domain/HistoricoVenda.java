
package com.facdjunior.comercial.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Francisco Junior
 */

@Entity
public class HistoricoVenda extends GenericDomain{
    
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date datamovimentacao;
    
    @Column(nullable = true, length = 200)
    private String observacao;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private Produto produto;

    public Date getDatamovimentacao() {
        return datamovimentacao;
    }

    public void setDatamovimentacao(Date datamovimentacao) {
        this.datamovimentacao = datamovimentacao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}
