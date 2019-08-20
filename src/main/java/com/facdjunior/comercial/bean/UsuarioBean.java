package com.facdjunior.comercial.bean;

import com.facdjunior.comercial.dao.PessoaDAO;
import com.facdjunior.comercial.dao.UsuarioDAO;
import com.facdjunior.comercial.domain.Pessoa;
import com.facdjunior.comercial.domain.Usuario;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.apache.shiro.crypto.hash.SimpleHash;

import org.omnifaces.util.Messages;

/**
 *
 * @author Francisco Junior
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable {

    private Usuario usuario;
    private List<Pessoa> pessoas;
    private List<Usuario> usuarios;

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
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

    public void novo() {
        try {
            usuario = new Usuario();

            PessoaDAO pessoaDAO = new PessoaDAO();
            pessoas = pessoaDAO.listar("nome");

        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar criar um novo usuário");
            erro.printStackTrace();
        }
    }

    public void salvar() {
        try {

            UsuarioDAO usuarioDAO = new UsuarioDAO();
            
            SimpleHash hash = new SimpleHash("md5", usuario.getSenhaSemCriptografia());
            usuario.setSenha(hash.toHex());
            
            usuarioDAO.merge(usuario);

            novo();
            listar();

            PessoaDAO pessoaDAO = new PessoaDAO();
            pessoas = pessoaDAO.listar("nome");

            Messages.addGlobalInfo("Usuário salvo com sucesso");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar salvar o usuário");
            erro.printStackTrace();
        }
    }

    public void excluir(ActionEvent evento) {

        try {

            usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");

            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioDAO.excluir(usuario);

            listar();
            PessoaDAO pessoaDAO = new PessoaDAO();
            pessoas = pessoaDAO.listar("nome");

            Messages.addGlobalInfo("Registro removido com sucesso!");

        } catch (RuntimeException erro) {

            Messages.addGlobalError("Não foi possível excluir Registro!");
            erro.printStackTrace();
        }
    }

    public void editar(ActionEvent evento) {
        try {
            usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");

            PessoaDAO pessoaDAO = new PessoaDAO();
            pessoas = pessoaDAO.listar("nome");

        } catch (RuntimeException erro) {

            Messages.addGlobalError("Ocorreu erro ao tentar selecionar cadastro!");
            erro.printStackTrace();
        }
    }
}
