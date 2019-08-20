package com.facdjunior.comercial.dao;

import com.facdjunior.comercial.domain.Caixa;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author Francisco Junior
 */
public class CaixaDAOTest {

    @Test
    @Ignore
    public void salvar() throws ParseException {

        Caixa caixa = new Caixa();
        caixa.setDataAbertura(new SimpleDateFormat("2019-08-16").parse("16/08/2019"));
        caixa.setValorAbertura(new BigDecimal("0.00"));

        CaixaDAO caixaDAO = new CaixaDAO();
        caixaDAO.merge(caixa);

    }

    @Test
    @Ignore
    public void buscar() throws ParseException {

        CaixaDAO caixaDAO = new CaixaDAO();
        Caixa caixa = new Caixa();
        
        Date data = caixa.getDataAbertura();
        // SimpleDateFormat dataFormata = new SimpleDateFormat("dd/MM/yyyy");
        // Date dr = dataFormata.parse("16/08/2019");
        //caixa = caixaDAO.buscar(new Date("dd/MM/yyyy").toString("16/08/2019"));
        System.out.println(caixa);
        Assert.assertNull(caixa);

    }

}
