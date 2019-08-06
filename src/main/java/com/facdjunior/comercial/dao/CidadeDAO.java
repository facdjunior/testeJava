package com.facdjunior.comercial.dao;

import com.facdjunior.comercial.domain.Cidade;
import com.facdjunior.comercial.util.HibernateUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Francisco Junior 2019-08-06
 */
public class CidadeDAO extends GenericDAO<Cidade>{
 
    public List<Cidade> buscarPorEstado(Long estadoCodigo){
        
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        
        try {
            Criteria consulta = sessao.createCriteria(Cidade.class);
            consulta.add(Restrictions.eq("estado.codigo", estadoCodigo));
            
            consulta.addOrder(Order.asc("nome"));
            List<Cidade> resultado = consulta.list();
            return resultado;
        } catch (RuntimeException erro) {
            throw erro;
        }finally{
            sessao.close();
        }
    }
}
