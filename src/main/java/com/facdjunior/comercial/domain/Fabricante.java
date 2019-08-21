package com.facdjunior.comercial.domain;

/**
 *
 * @author Francisco Alves 2019-08-05
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@SuppressWarnings("serial")
@Entity
public class Fabricante extends GenericDomain {

    @Column(length = 50, nullable = false)
    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
