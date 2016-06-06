package com.supinfo.test.restapi;

import com.supinfo.test.ReponseRest.DealReponse;
import com.supinfo.test.ReponseRest.GaresReponse;
import com.supinfo.test.dao.JpaDeal;
import com.supinfo.test.dao.JpaGares;

import javax.servlet.http.HttpServlet;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.Random;

/**
 * Created by Alexa on 04/06/2015.
 */

@Path("/dealrest")
public class DealRest extends HttpServlet{

    @Path("/listdeal")
    @GET
    public String listdeal(  ) //!!!! Toujours mettre vos méthode en public sinon lourde perte de temps
    {
        JpaDeal test = new JpaDeal();


        DealReponse userReponse = test.alldeal();

        return userReponse.toJson(); //TODO replace this stub to something useful
    }



}
