/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facdjunior.comercial.util;

import com.facdjunior.comercial.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author Francisco Alves 2019-07-30
 */
public class HibernateUtilTest {

    @Test
    @Ignore
    public void conectar() {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        sessao.close();
        HibernateUtil.getSessionFactory().close();
    }

}
