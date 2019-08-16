package com.facdjunior.comercial.dao;

import com.facdjunior.comercial.domain.Caixa;
import com.facdjunior.comercial.util.HibernateUtil;
import java.util.Date;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Francisco Junior 2019-08-16
 */
public class CaixaDAO extends  GenericDAO<Caixa>{
    
    public Caixa buscarCaixa(Date dataAbertura){
        
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        
        try {
            Criteria consulta = sessao.createCriteria(Caixa.class);
            consulta.add(Restrictions.eq("dataAbertura", dataAbertura));
            Caixa resultado = (Caixa) consulta.uniqueResult();
            return resultado;
        } catch (RuntimeException erro) {
            throw erro;
        }finally{
            sessao.close();
        }
    }

    Caixa buscar(Date dr) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
