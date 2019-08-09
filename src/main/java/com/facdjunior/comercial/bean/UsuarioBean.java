package com.facdjunior.comercial.bean;

import com.facdjunior.comercial.dao.UsuarioDAO;
import com.facdjunior.comercial.domain.Usuario;
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
public class UsuarioBean implements Serializable {

    private List<Usuario> usuarios;

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    @PostConstruct
    public void listar() {
        try {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarios = usuarioDAO.listar("tipo");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar listar os usuários");
            erro.printStackTrace();
        }
    }
}
