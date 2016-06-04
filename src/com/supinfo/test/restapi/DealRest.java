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

    @Path("/listinit")
    @GET
    public void dealinit() //!!!! Toujours mettre vos méthode en public sinon lourde perte de temps
    {
        JpaDeal test = new JpaDeal();
        test.addDeal( "PROMO Deal 1 ","https://www.raileurope.ca/cms-images/810/496/eurostar-train-lg-index,1.jpg");
        test.addDeal( "PROMO Deal 2","http://www.indianrailwaynews.com/wp-content/uploads/2016/03/Train-1.jpg");
        test.addDeal( "PROMO Deal 3","http://static.dnaindia.com/sites/default/files/styles/half/public/2016/03/20/439541-railways.jpg");
        test.addDeal( "PROMO Deal 4","https://www.crosscountrytrains.co.uk/media/23127/hst2012.jpg");
        test.addDeal( "PROMO Deal 5","http://referentiel.nouvelobs.com/file/6480450-saone-et-loire-une-rixe-dans-un-train-fait-1-blesse-grave.jpg");


    }

}
