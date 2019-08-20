package com.facdjunior.comercial.dao;

import com.facdjunior.comercial.domain.Pessoa;
import com.facdjunior.comercial.domain.Usuario;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author Francisco
 */
public class UsuarioDAOTest {

    @Test
   @Ignore
    public void salvar() {

        PessoaDAO pessoaDAO = new PessoaDAO();
        Pessoa pessoa = pessoaDAO.buscar(67L);

        System.out.println("Usuário: " + pessoa.getNome());
        System.out.println("CPF.: " + pessoa.getCpf());

        Usuario usuario = new Usuario();
        usuario.setAtivo(true);
        usuario.setPessoa(pessoa);
        usuario.setSenhaSemCriptografia("123456");

        SimpleHash hash = new SimpleHash("md5", usuario.getSenhaSemCriptografia());
        usuario.setSenha(hash.toHex());

        usuario.setTipo('B');

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.salvar(usuario);

        System.out.println("Usuário salvo com sucesso.");
    }

    @Test
    @Ignore
    public void autenticar() {
        String cpf = "999.999.999-99";
        String senha = "12345678";

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.autenticar(cpf, senha);

        System.out.println("Usuário autentica: " + usuario);
    }

}
