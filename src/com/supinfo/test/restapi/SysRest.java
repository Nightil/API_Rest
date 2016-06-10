package com.supinfo.test.restapi;

import com.supinfo.test.dao.*;

import javax.servlet.http.HttpServlet;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.awt.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

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
        JpaLigne jpaLigne =  new JpaLigne();
        jpaLigne.addLigne("A");
        jpaLigne.addLigne("B");
        jpaLigne.addLigne("C");

        jpaLigne.addLigne("D");
        jpaLigne.addLigne("E");
        jpaLigne.addLigne("F");


        JpaTrain jpaTrain = new JpaTrain();
        Random r = new Random();
        int Low = 0;
        int High = 60;
        int High1 = 10;
        DateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );

        for (int m = 1; m <= 12; m++) {
            for (int d = 1; d <= 31; d++) {
                for (int i = 0; i <= 23; i++) {

                    try {
                        int u = (r.nextInt(High-Low) + Low);
                        Date Ddate = dateFormat.parse( "2016-"+m+"-"+d+" "+i+":"+u+":00" );
                        int g = (r.nextInt(High1-Low) + Low);
                        jpaTrain.addTrain(Ddate, jpaLigne.get("A"), 100, g);

                    } catch (ParseException e) {

                    }
                    try {
                        int u = (r.nextInt(High-Low) + Low);
                        Date Ddate = dateFormat.parse( "2016-"+m+"-"+d+" "+i+":"+u+":00" );
                        int g = (r.nextInt(High1-Low) + Low);
                        jpaTrain.addTrain(Ddate, jpaLigne.get("B"), 100, g);

                    } catch (ParseException e) {

                    }
                    try {
                        int u = (r.nextInt(High-Low) + Low);
                        Date Ddate = dateFormat.parse( "2016-"+m+"-"+d+" "+i+":"+u+":00" );
                        int g = (r.nextInt(High1-Low) + Low);

                        jpaTrain.addTrain(Ddate, jpaLigne.get("C"), 100, g);

                    } catch (ParseException e) {

                    }
                    try {
                        int u = (r.nextInt(High-Low) + Low);
                        Date Ddate = dateFormat.parse( "2016-"+m+"-"+d+" "+i+":"+u+":00" );
                        int g = (r.nextInt(High1-Low) + Low);

                        jpaTrain.addTrain(Ddate, jpaLigne.get("D"), 100, g);

                    } catch (ParseException e) {

                    }
                    try {
                        int u = (r.nextInt(High-Low) + Low);
                        Date Ddate = dateFormat.parse( "2016-"+m+"-"+d+" "+i+":"+u+":00" );
                        int g = (r.nextInt(High1-Low) + Low);

                        jpaTrain.addTrain(Ddate, jpaLigne.get("E"), 100, g);

                    } catch (ParseException e) {

                    }
                    try {
                        int u = (r.nextInt(High-Low) + Low);
                        Date Ddate = dateFormat.parse( "2016-"+m+"-"+d+" "+i+":"+u+":00" );
                        int g = (r.nextInt(High1-Low) + Low);

                        jpaTrain.addTrain(Ddate, jpaLigne.get("F"), 100, g);

                    } catch (ParseException e) {

                    }


                }
            }
        }

        JpaGares testt = new JpaGares();
        //Nom gare : Nom ville : CP : GPS
        //Nom gare : Nom ville : CP : GPS

        testt.addGare( "Gare d'Orléans" ,"Orléans" , 45000,new Point(48,2));

        testt.addGare( "Gare de Lyon" ,"Paris" , 75000,new Point(48,2));

        testt.addGare( "Gare Tours Centre " ,"Tours" , 37000,new Point(47,1));

        testt.addGare( "Gare Saint-Jean" ,"Bordeaux" , 33000,new Point(45,-1 ));

        testt.addGare( "Gare Matabiau" ,"Toulouse" , 31000,new Point(44,2));

        testt.addGare( "Gare Dijon-Ville" ,"Dijon" , 21000,new Point(47,5));

        testt.addGare( "Gare Pare-Dieu" ,"Lyon" , 69000,new Point(46,5));

        testt.addGare( "Gare Saint-Roch" ,"Montpellier" , 34000,new Point(44,4));

        testt.addGare( "Gare Saint-Charles" ,"Marseille" , 13000,new Point(43,5));

        testt.addGare( "Gare de Lille Flandres" ,"Lille" , 59000,new Point(6,3));

        testt.addGare( "Gare de Brest" ,"Brest" , 29200,new Point(48,-4));


        JpaRoute route = new JpaRoute();
        // Gare de depart du tronçon : Gare arrivé  : Distance
        // Orleans - Paris-Austerlitz
        route.addRoute(testt.get("Gare Matabiau"),testt.get("Gare Saint-Jean"),258,jpaLigne.get("A"));
        route.addRoute(testt.get("Gare Saint-Jean"), testt.get("Gare Tours Centre"),349,jpaLigne.get("A"));
        route.addRoute(testt.get("Gare Tours Centre"),testt.get("Gare d'Orléans"),112,jpaLigne.get("A"));
        route.addRoute(testt.get("Gare d'Orléans"),testt.get("Gare de Lyon"),118,jpaLigne.get("A"));
        route.addRoute(testt.get("Gare de Lyon"),testt.get("Gare de Lille Flandres"),226,jpaLigne.get("A"));


        route.addRoute(testt.get("Gare de Lille Flandres"),testt.get("Gare de Lyon"),226,jpaLigne.get("D"));
        route.addRoute(testt.get("Gare de Lyon"),testt.get("Gare d'Orléans"),118,jpaLigne.get("D"));
        route.addRoute(testt.get("Gare d'Orléans"),testt.get("Gare Tours Centre"),112,jpaLigne.get("D"));
        route.addRoute(testt.get("Gare Tours Centre"),testt.get("Gare Saint-Jean"),349,jpaLigne.get("D"));
        route.addRoute(testt.get("Gare Saint-Jean"),testt.get("Gare Matabiau"),258,jpaLigne.get("D"));


        route.addRoute(testt.get("Gare Pare-Dieu"),testt.get("Gare Dijon-Ville"),192,jpaLigne.get("B"));
        route.addRoute(testt.get("Gare Dijon-Ville"),testt.get("Gare de Lyon"),164,jpaLigne.get("B"));
        route.addRoute(testt.get("Gare de Lyon"),testt.get("Gare de Brest"),623,jpaLigne.get("B"));

        route.addRoute(testt.get("Gare de Brest"),testt.get("Gare de Lyon"),632,jpaLigne.get("E"));
        route.addRoute(testt.get("Gare de Lyon"),testt.get("Gare Dijon-Ville"),164,jpaLigne.get("E"));
        route.addRoute(testt.get("Gare Dijon-Ville"),testt.get("Gare Pare-Dieu"),192,jpaLigne.get("E"));

        // Lyon - Marseille
        route.addRoute(testt.get("Gare Saint-Roch"),testt.get("Gare pare-Dieu"),314,jpaLigne.get("C"));
        route.addRoute(testt.get("Gare Pare-Dieu"),testt.get("Gare Saint-Charles"),325,jpaLigne.get("C"));

        route.addRoute(testt.get("Gare Saint-Charles"),testt.get("Gare Pare-Dieu"),325,jpaLigne.get("F"));
        route.addRoute(testt.get("Gare Pare-Dieu"),testt.get("Gare Saint-Roch"),314,jpaLigne.get("F"));
        // Lyon - Montpellier




        return "ok";
    }

    @OPTIONS
    @Path("{path : .*}")
    public Response options() {
        return Response.ok("")
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .header("Access-Control-Max-Age", "1209600")
                .build();
    }


}
