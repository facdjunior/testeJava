package com.facdjunior.comercial.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 *
 * @author Francisco Junior
 */
//http://localhost:8080/comercial/rest/[nomeServiço"Comercial" nome dado no Path da classe]
@Path("junior")
public class ComercialService {

    @GET
    public String exibir() {
        return "Curso Java";
    }

}
