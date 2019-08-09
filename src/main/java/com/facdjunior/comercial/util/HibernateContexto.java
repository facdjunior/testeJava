package com.facdjunior.comercial.util;

/**
 *
 * @author Francisco Junior
 */
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class HibernateContexto implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        HibernateUtil.getSessionFactory().close();
    }

    @Override
    public void contextInitialized(ServletContextEvent event) {
        HibernateUtil.getSessionFactory().openSession();
    }

}
