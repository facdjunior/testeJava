package com.facdjunior.comercial.dao;

import com.facdjunior.comercial.domain.Estado;
import com.facdjunior.comercial.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author UsuarioSaude
 */
public class EstadoDAOTest {

    @Test
    @Ignore
    public void salvar() {

        
        Estado estado = new Estado();
        estado.setNome("Espírito Santo");
        estado.setSigla("ES");
        EstadoDAO estadoDAO = new EstadoDAO();
        estadoDAO.merge(estado);
         /*
        try {

            Session sessao = HibernateUtil.getSessionFactory().openSession();

            Transaction transacao = sessao.beginTransaction();

            Estado estado = new Estado();
          
            estado.setNome("Ceará"); 
            estado.setSigla("CE");
            //estado.setCodigo(14L);
          
            sessao.save(estado);

            transacao.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    @Test
    @Ignore
    public void listar() {
        EstadoDAO estadoDAO = new EstadoDAO();
        List<Estado> resultado = estadoDAO.listar();

        System.out.println("Total de Registros Encontrados: " + resultado.size());

        for (Estado estado : resultado) {
            System.out.println(estado.getCodigo() + " - " + estado.getSigla() + " - " + estado.getNome());
        }
    }

    @Test
    @Ignore
    public void buscar() {
        //Long codigo = 3L;

        EstadoDAO estadoDAO = new EstadoDAO();
        Estado estado = estadoDAO.buscar(11L);

        if (estado == null) {
            System.out.println("Nenhum registro encontrado");
        } else {
            System.out.println("Registro encontrado:");
            System.out.println(estado.getCodigo() + " - " + estado.getSigla() + " - " + estado.getNome());
        }
    }

    @Test
    @Ignore
    public void excluir() {
        Long codigo = 11L;
        EstadoDAO estadoDAO = new EstadoDAO();
        Estado estado = estadoDAO.buscar(codigo);

        if (estado == null) {
            System.out.println("Nenhum registro encontrado");
        } else {
            estadoDAO.excluir(estado);
            System.out.println("Registro removido:");
            System.out.println(estado.getCodigo() + " - " + estado.getSigla() + " - " + estado.getNome());
        }
    }

    @Test
    @Ignore
    public void editar() {
        Long codigo = 13L;
        EstadoDAO estadoDAO = new EstadoDAO();
        Estado estado = estadoDAO.buscar(codigo);

        if (estado == null) {
            System.out.println("Nenhum registro encontrado");
        } else {
            System.out.println("Registro editado - Antes:");
            System.out.println(estado.getCodigo() + " - " + estado.getSigla() + " - " + estado.getNome());

            estado.setNome("Piauí");
            estado.setSigla("PI");
            estadoDAO.merge(estado);

            System.out.println("Registro editado - Depois:");
            System.out.println(estado.getCodigo() + " - " + estado.getSigla() + " - " + estado.getNome());
        }
    }

}
