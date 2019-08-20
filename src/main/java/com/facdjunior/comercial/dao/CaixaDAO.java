package com.facdjunior.comercial.dao;

import com.facdjunior.comercial.domain.Caixa;
import com.facdjunior.comercial.util.HibernateUtil;
import java.util.Date;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Francisco Junior 2019-08-16
 */
public class CaixaDAO extends GenericDAO<Caixa> {

    public Caixa buscarCaixa(Date dataAberto) {

        Session sessao = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder builder = HibernateUtil.getSessionFactory().getCurrentSession().getCriteriaBuilder();
        try {
            CriteriaQuery<Caixa> consulta = builder.createQuery(Caixa.class);

            consulta.equals(Restrictions.eq("dataAbertura", dataAberto));
            Caixa resultado = (Caixa) consulta.distinct(true);
            return resultado;
        } catch (RuntimeException erro) {
            throw erro;
        } finally {
            sessao.close();
        }
    }

}
