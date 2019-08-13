package com.facdjunior.comercial.service;

import com.facdjunior.comercial.dao.FabricanteDAO;
import com.facdjunior.comercial.domain.Fabricante;
import com.google.gson.Gson;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 *
 * @author Francisco Junior
 */
//http://localhost:8080/Comercial/rest/fabricante
@Path("fabricante")
public class FabricanteService {

    //http://localhost:8080/Comercial/rest/fabricante
    @GET
    public String listar() {

        FabricanteDAO fabricanteDAO = new FabricanteDAO();
        List<Fabricante> fabricantes = fabricanteDAO.listar("descricao");

        Gson gson = new Gson();
        String json = gson.toJson(fabricantes);

        return json;
    }
//http://localhost:8080/Comercial/rest/fabricante/codigo

    @GET
    @Path("{codigo}")
    public String buscar(@PathParam("codigo") Long codigo) {

        FabricanteDAO fabricanteDAO = new FabricanteDAO();
        Fabricante fabricante = fabricanteDAO.buscar(codigo);

        Gson gson = new Gson();
        String json = gson.toJson(fabricante);

        return json;

    }
    //http://localhost:8080/Comercial/rest/fabricante
    @POST
    public String salvar(String json) {

        Gson gson = new Gson();
        Fabricante fabricante = gson.fromJson(json, Fabricante.class);

        FabricanteDAO fabricanteDAO = new FabricanteDAO();
        fabricanteDAO.merge(fabricante);
        
        String jsonSaida = gson.toJson(fabricante);
        return jsonSaida;
    }
    @PUT
    public String editar(String json) {

        Gson gson = new Gson();
        Fabricante fabricante = gson.fromJson(json, Fabricante.class);

        FabricanteDAO fabricanteDAO = new FabricanteDAO();
        fabricanteDAO.editar(fabricante);
        
        String jsonSaida = gson.toJson(fabricante);
        return jsonSaida;
    }
    // http://localhost:8080/Comercial/rest/fabricante/{codigo}
    @DELETE
    @Path("{codigo}")
    public String excluir(@PathParam("codigo")Long codigo){
        
        FabricanteDAO fabricanteDAO = new FabricanteDAO();
        
        Fabricante fabricante = fabricanteDAO.buscar(codigo);
        fabricanteDAO.excluir(fabricante);
        
        Gson gson = new Gson();
        String saida = gson.toJson(fabricante);
        return saida;
    }
}
