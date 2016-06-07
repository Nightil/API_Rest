package com.supinfo.test.restapi;

import com.supinfo.test.dao.JpaDeal;
import com.supinfo.test.dao.JpaGares;
import com.supinfo.test.dao.JpaLigne;
import com.supinfo.test.dao.JpaRoute;

import javax.servlet.http.HttpServlet;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.awt.*;

/**
 * Created by Alexa on 04/06/2015.
 */

@Path("/sysrest")
public class SysRest extends HttpServlet{





    @Path("/setup")
    @GET
    public String listdeal(  ) //!!!! Toujours mettre vos méthode en public sinon lourde perte de temps
    {


        JpaDeal test = new JpaDeal();
        test.addDeal( "PROMO Deal 1 ","https://www.raileurope.ca/cms-images/810/496/eurostar-train-lg-index,1.jpg");
        test.addDeal( "PROMO Deal 2","http://www.indianrailwaynews.com/wp-content/uploads/2016/03/Train-1.jpg");
        test.addDeal( "PROMO Deal 3","http://static.dnaindia.com/sites/default/files/styles/half/public/2016/03/20/439541-railways.jpg");
        test.addDeal( "PROMO Deal 4","https://www.crosscountrytrains.co.uk/media/23127/hst2012.jpg");
        test.addDeal( "PROMO Deal 5","http://referentiel.nouvelobs.com/file/6480450-saone-et-loire-une-rixe-dans-un-train-fait-1-blesse-grave.jpg");


        JpaGares testt = new JpaGares();
        //Nom gare : Nom ville : CP : GPS
        testt.addGare( "Gare d'orléans" ,"Orléans" , 45000,new Point(5,45));
        testt.addGare( "Gare de lyon" ,"Paris" , 75000,new Point(54,45));
        testt.addGare( "Gare st jean" ,"Bordeaux" , 45000,new Point(54,45));
        testt.addGare( "Gare de Montpellier-Saint-Roch" ,"Montpellier" , 45000,new Point(54,45));
        testt.addGare( "Gare de Marseille" ,"Marseille" , 45000,new Point(54,45));

        JpaRoute route = new JpaRoute();
        JpaLigne jpaLigne =  new JpaLigne();
        jpaLigne.addLigne("A");
        jpaLigne.addLigne("B");
        // Gare de depart du tronçon : Gare arrivé  : Distance
        route.addRoute(testt.get("Gare d'orléans"),testt.get("Gare de lyon"),100,jpaLigne.get("A"));
        route.addRoute(testt.get("Gare de lyon"),testt.get("Gare d'orléans"),100, jpaLigne.get("A"));


       route.addRoute(testt.get("Gare de lyon"),testt.get("Gare st jean"),250, jpaLigne.get("A"));

        route.addRoute(testt.get("Gare d'orléans"),testt.get("Gare de Montpellier-Saint-Roch"),150, jpaLigne.get("A"));

        route.addRoute(testt.get("Gare de Montpellier-Saint-Roch"),testt.get("Gare d'orléans"),150, jpaLigne.get("A"));

        route.addRoute(testt.get("Gare de Montpellier-Saint-Roch"),testt.get("Gare de Marseille"),150, jpaLigne.get("B"));
        route.addRoute(testt.get("Gare de Marseille"),testt.get("Gare de Montpellier-Saint-Roch"),150, jpaLigne.get("B"));



        return "ok";
    }



}
