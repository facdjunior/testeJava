package com.facdjunior.comercial.util;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

/**
 *
 * @author Francisco Junior
 */

//http://localhost:8080/Comercial/[NomeRest]
@ApplicationPath("/rest")
public class ComercialResourceConfig extends ResourceConfig{
    public ComercialResourceConfig(){
        
        packages("com.facdjunior.comercial.service");
    }
    
}
