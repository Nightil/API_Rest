package com.supinfo.test.restapi;

import com.supinfo.test.ReponseRest.UserReponse;
import com.supinfo.test.dao.JpaUtilisateurs;

import javax.servlet.http.HttpServlet;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

/**
 * Created by Alexa on 04/06/2015.
 */

@Path("/voyagerest")
public class VoyageRest extends HttpServlet{
    /*
    @Path("/searchVoyage")
    @POST
    public String searchVoyage(@HeaderParam("gareD") String gareD , @HeaderParam("gareA") String gareA )
    {


        JpaUtilisateurs test = new JpaUtilisateurs();
       // UserReponse userReponse = test.addUser("recule", "alexandre",email, "azerty", "homme", "234 rue de la libération");

        return userReponse.toJson(); //TODO replace this stub to something useful
    }*/
}
