package com.facdjunior.comercial.service;

import com.facdjunior.comercial.domain.Endereco;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.Serializable;

/**
 *
 * @author Francisco
 */
public class ServicoEndereco implements Serializable{
    
    public Endereco buscarEnderecoPor(String urlJson){
        
        System.out.println("Iniciou pesquisa....");
        
        final GsonBuilder gsonBuilder = new GsonBuilder();
        final Gson gson = gsonBuilder.create();
        Gson g = new Gson();
        
        Endereco retornoEndereco = gson.fromJson(urlJson, Endereco.class);
        return retornoEndereco;
    }
    
}