package com.facdjunior.comercial.dao;

import com.facdjunior.comercial.domain.Usuario;
import com.facdjunior.comercial.util.HibernateUtil;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.hibernate.Session;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Francisco Junior
 */
public class UsuarioDAO extends GenericDAO<Usuario> {

    public Usuario autenticar(String cpf, String senha) {

        Session sessao = HibernateUtil.getSessionFactory().openSession();

        try {
            Criteria consulta = sessao.createCriteria(Usuario.class);

            consulta.createAlias("pessoa", "p");
            consulta.add(Restrictions.eq("p.cpf", cpf));

            SimpleHash hash = new SimpleHash("md5", senha);
            consulta.add(Restrictions.eq("senha", hash.toHex()));

            Usuario resultado = (Usuario) consulta.uniqueResult();
            return resultado;
        } catch (RuntimeException erro) {
            throw erro;
        } finally {
            sessao.close();
        }

    }
}
