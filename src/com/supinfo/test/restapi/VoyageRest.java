package com.supinfo.test.restapi;

import com.supinfo.test.ReponseRest.SearchResponse;
import com.supinfo.test.ReponseRest.Success;
import com.supinfo.test.ReponseRest.UserReponse;
import com.supinfo.test.ReponseRest.VoyageReponse;
import com.supinfo.test.dao.JpaGares;
import com.supinfo.test.dao.JpaUtilisateurs;
import com.supinfo.test.dao.JpaVoyages;

import javax.servlet.http.HttpServlet;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Alexa on 04/06/2015.
 */

@Path("/voyagerest")
public class VoyageRest extends HttpServlet{

    @Path("/searchVoyage")
    @POST
    public String searchVoyage(@HeaderParam("gareD") String gareD , @HeaderParam("gareA") String gareA ,
                               @HeaderParam("heureD") Integer heureD  , @HeaderParam("heureR") Integer heureR  ,
                               @HeaderParam("dateD") String dateD , @HeaderParam("dateR") String dateR )
    {

        try {
            gareD = URLDecoder.decode(gareD, "UTF-8");
            gareA = URLDecoder.decode(gareA, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // e.printStackTrace();
        }

        JpaVoyages test = new JpaVoyages();
        SearchResponse searchResponse = new SearchResponse();
        Calendar calendar = Calendar.getInstance();
        try {
            String[] parts = dateD.split("-");
            calendar.set(Integer.valueOf(parts[2]),Integer.valueOf(parts[1]),Integer.valueOf(parts[0]),heureD,0);

        }catch (Exception e){
            return new VoyageReponse( new Success(false,"Erreur du formatage de la date")).toJson();
        }

        if(heureD >= 24 || heureD < 0 ){
            return new VoyageReponse( new Success(false,"Erreur dans l'heure de depart")).toJson();
        }
        JpaGares jpaGares = new JpaGares();
        Integer gareDD = 0;
        Integer gareAA = 0;
        try{
            gareDD =  Integer.parseInt(gareD);
        }catch (Exception e){
            try {
                gareDD = Integer.parseInt(String.valueOf(jpaGares.get(gareD).getId()));
            }catch (Exception ef){
                return new VoyageReponse( new Success(false,"Erreur du formatage de la gare Depart")).toJson();
            }

        }

        try{
            gareAA =  Integer.parseInt(gareA);
        }catch (Exception eg){
            try {
                gareAA = Integer.parseInt(String.valueOf(jpaGares.get(gareA).getId()));
            }catch (Exception efg){
                return new VoyageReponse( new Success(false,"Erreur du formatage de la gare Arrivé")).toJson();
            }

        }


        VoyageReponse voyageReponse = test.search(gareDD, gareAA,calendar.getTime(),null);
        searchResponse.setAllee(voyageReponse);
        if (dateR != null){
            Calendar calendarr = Calendar.getInstance();
            try {
                String[] parts = dateR.split("-");
                calendarr.set(Integer.valueOf(parts[2]),Integer.valueOf(parts[1]),Integer.valueOf(parts[1]),heureR,0);

            }catch (Exception e){
                return new VoyageReponse( new Success(false,"Erreur du formatage de la date")).toJson();
            }

            if(heureR >= 24 || heureR < 0 ){
                return new VoyageReponse( new Success(false,"Erreur dans l'heure de depart")).toJson();
            }
            VoyageReponse voyageReponse2 = test.search(gareAA,gareDD ,calendarr.getTime(),null);
            searchResponse.setRetour(voyageReponse2);
        }

        return searchResponse.toJson();
    }


}


