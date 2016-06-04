package com.supinfo.test.restapi;

import com.supinfo.test.ReponseRest.GaresReponse;
import com.supinfo.test.ReponseRest.UserReponse;
import com.supinfo.test.dao.JpaGares;
import com.supinfo.test.dao.JpaUtilisateurs;

import javax.servlet.http.HttpServlet;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.awt.*;
import java.util.Random;

/**
 * Created by Alexa on 04/06/2015.
 */

@Path("/garerest")
public class GareRest extends HttpServlet{

    @Path("/searchGare")
    @POST
    public String searchGare(@QueryParam("search") String search  ) //!!!! Toujours mettre vos méthode en public sinon lourde perte de temps
    {
        JpaGares test = new JpaGares();
       /* test.addGare( new Random().toString());
        test.addGare( new Random().toString());
        test.addGare( new Random().toString());
        test.addGare( new Random().toString());*/
        GaresReponse userReponse = test.search(search);

        return userReponse.toJson(); //TODO replace this stub to something useful
    }

    @Path("/initGare")
    @GET
    public void initGare( ) //!!!! Toujours mettre vos méthode en public sinon lourde perte de temps
    {
        JpaGares test = new JpaGares();
        test.addGare( "Gare d'orléans" ,"Orléans" , 45000,new Point(54,45));
        test.addGare( "Gare de lyon" ,"Paris" , 75000,new Point(54,45));
        test.addGare( "Gare st jean" ,"Bordeaux" , 45000,new Point(54,45));
        test.addGare( "Gare de Montpellier-Saint-Roch" ,"Montpellier" , 45000,new Point(54,45));

    }


}
