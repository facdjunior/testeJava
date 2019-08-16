package com.facdjunior.comercial.domain;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Francisco Junior 2019-08-16
 */
@SuppressWarnings("serial")
@Entity
public class MovimentacaoCaixa extends GenericDomain{
    
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataMovimentacao;
    
    @Column(nullable = false, precision = 9, scale = 2)
    private BigDecimal valorMovimentacao;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private Caixa caixa;

    public Date getDataMovimentacao() {
        return dataMovimentacao;
    }

    public void setDataMovimentacao(Date dataMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
    }

    public BigDecimal getValorMovimentacao() {
        return valorMovimentacao;
    }

    public void setValorMovimentacao(BigDecimal valorMovimentacao) {
        this.valorMovimentacao = valorMovimentacao;
    }

    public Caixa getCaixa() {
        return caixa;
    }

    public void setCaixa(Caixa caixa) {
        this.caixa = caixa;
    }
}
