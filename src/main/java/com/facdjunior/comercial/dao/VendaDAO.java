package com.facdjunior.comercial.dao;


import com.facdjunior.comercial.domain.ItemVenda;
import com.facdjunior.comercial.domain.Venda;
import com.facdjunior.comercial.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Francisco Junior
 */
public class VendaDAO extends GenericDAO<Venda>{
    
    public void salvar(Venda venda, List<ItemVenda> itensVendas){
        
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        
        try {
            transacao = sessao.beginTransaction();
            sessao.save(venda);
            
            for(int item = 0; item < itensVendas.size(); item++){
                
                ItemVenda itemVenda = itensVendas.get(item);
                itemVenda.setVenda(venda);
                
                sessao.save(itemVenda);
            }
            
        } catch (RuntimeException erro) {
        }
    }
}
