package com.supinfo.test.restapi;

import com.supinfo.test.ReponseRest.DealReponse;
import com.supinfo.test.dao.JpaDeal;

import javax.servlet.http.HttpServlet;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Created by Alexa on 04/06/2015.
 */

@Path("/dealrest")
public class DealRest extends HttpServlet{

    @Path("/listdeal")
    @GET
    public Response listdeal(  ) //!!!! Toujours mettre vos méthode en public sinon lourde perte de temps
    {
        JpaDeal test = new JpaDeal();


        DealReponse userReponse = test.alldeal();
        return Response.status(200).entity(userReponse.toJson()).header("Access-Control-Allow-Origin","*").build();

     }



}
