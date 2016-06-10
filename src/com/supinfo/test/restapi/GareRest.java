package com.supinfo.test.restapi;

import com.supinfo.test.ReponseRest.GaresReponse;
import com.supinfo.test.dao.JpaGares;

import javax.servlet.http.HttpServlet;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by Alexa on 04/06/2015.
 */

@Path("/garerest")
public class GareRest extends HttpServlet{

    @Path("/searchGare"  )
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchGare(@HeaderParam("search") String search  )
    {
        try {
            search = URLDecoder.decode(search, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // e.printStackTrace();
        }
        JpaGares test = new JpaGares();
       /* test.addGare( new Random().toString());
        test.addGare( new Random().toString());
        test.addGare( new Random().toString());
        test.addGare( new Random().toString());*/
        GaresReponse userReponse = test.search(search);

        return Response.ok(userReponse.toJson()).status(200) .header("Access-Control-Allow-Origin","*")
                .header("Access-Control-Max-Age","1728000")          .header("Content-Type","application/json")

        .header("Access-Control-Allow-Methods","POST, GET, OPTIONS, PUT, DELETE, HEAD")
                .header("Access-Control-Allow-Headers","X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept")
                .build();
    }



}
