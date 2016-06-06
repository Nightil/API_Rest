package com.supinfo.test.restapi;

import com.supinfo.test.ReponseRest.GaresReponse;
import com.supinfo.test.ReponseRest.UserReponse;
import com.supinfo.test.dao.JpaDeal;
import com.supinfo.test.dao.JpaGares;
import com.supinfo.test.dao.JpaRoute;
import com.supinfo.test.dao.JpaUtilisateurs;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
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
    public String searchGare(@QueryParam("search") String search  )
    {
        JpaGares test = new JpaGares();
       /* test.addGare( new Random().toString());
        test.addGare( new Random().toString());
        test.addGare( new Random().toString());
        test.addGare( new Random().toString());*/
        GaresReponse userReponse = test.search(search);

        return userReponse.toJson(); //TODO replace this stub to something useful
    }




}
