package com.facdjunior.comercial.dao;

import com.facdjunior.comercial.domain.Funcionario;
import com.facdjunior.comercial.util.HibernateUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

/**
 *
 * @author Francisco Junior
 */
public class FuncionarioDAO extends GenericDAO<Funcionario>{
    public List<Funcionario> listarOdenado() {
        
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        
        try {
            Criteria consulta = sessao.createCriteria(Funcionario.class);
            consulta.createAlias("pessoa", "p");
            consulta.addOrder(Order.asc("p.nome"));
            List<Funcionario> resultado = consulta.list();
            return resultado;
        } catch (RuntimeException erro) {
            throw erro;
        } finally {
            sessao.close();
        }
    }
 
}
