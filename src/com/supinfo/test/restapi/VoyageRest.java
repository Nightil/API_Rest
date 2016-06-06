package com.supinfo.test.restapi;

import com.supinfo.test.ReponseRest.UserReponse;
import com.supinfo.test.ReponseRest.VoyageReponse;
import com.supinfo.test.dao.JpaUtilisateurs;
import com.supinfo.test.dao.JpaVoyages;

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

    @Path("/searchVoyage")
    @POST
    public String searchVoyage(@HeaderParam("gareD") String gareD , @HeaderParam("gareA") String gareA , @HeaderParam("heureD") Integer heureD  , @HeaderParam("heureR") Integer heureR  , @HeaderParam("dateD") Integer dateD , @HeaderParam("dateR") Integer dateR )
    {


        JpaVoyages test = new JpaVoyages();
        VoyageReponse voyageReponse = test.search(gareD, gareA,heureD,heureR, dateD, dateR);

        return voyageReponse.toJson();
    }


}


