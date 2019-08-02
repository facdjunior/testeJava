package com.facdjunior.comercial.bean;

import com.facdjunior.comercial.domain.Estado;
import com.facdjunior.comercial.dao.EstadoDAO;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.omnifaces.util.Messages;

/**
 *
 * @author UsuarioSaude
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class EstadoBean implements Serializable {

    private Estado estado;
    private List<Estado> estados;

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public List<Estado> getEstados() {
        return estados;
    }

    public void setEstados(List<Estado> estados) {
        this.estados = estados;
    }

    @PostConstruct
    public void listar() {
        try {
            EstadoDAO estadosDAO = new EstadoDAO();
            estados = estadosDAO.listar();
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar listar os estados");
            erro.printStackTrace();
        }
    }

    public void novo() {
        estado = new Estado();
    }

    public void salvar() {

        try {

            EstadoDAO estadosDAO = new EstadoDAO();
            estadosDAO.salvar(estado);

            novo();
            Messages.addGlobalInfo("Registro gravado com sucesso");

        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar salvar registro");
            erro.printStackTrace();
        }
    }
}
