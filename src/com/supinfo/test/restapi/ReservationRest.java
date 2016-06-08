package com.supinfo.test.restapi;

import com.supinfo.test.ReponseRest.ReservationReponse;
import com.supinfo.test.dao.JpaReservations;

import javax.servlet.http.HttpServlet;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

/**
 * Created by alexa on 08/06/2016.
 */
@Path("/reservationrest")
public class ReservationRest extends HttpServlet{

    @Path("/searchReservationById")
    @POST
    public String searchReservationById(@HeaderParam("id_reservation") Integer id_reservation){

        JpaReservations reservations = new JpaReservations();

        ReservationReponse userReponse = reservations.getById(id_reservation);

        return userReponse.toJson();
    }

    @Path("/searchReservationByUserId")
    @POST
    public String searchReservationByUserId(@HeaderParam("id_utilisateur") Integer id_utilisateur){

        JpaReservations reservations = new JpaReservations();

        ReservationReponse userReponse = reservations.getByUserId(id_utilisateur);

        return userReponse.toJson();
    }

    @Path("/addreservation")
    @POST
    public String addreservation(@HeaderParam("id_utilisateur") Integer id_utilisateur, @HeaderParam("civilite") String civilite,
                                 @HeaderParam("nom") String nom, @HeaderParam("prenom") String prenom,@HeaderParam("mail") String mail,
                                 @HeaderParam("PossibilityReponse") String PossibilityReponse){

        JpaReservations reservations = new JpaReservations();

        ReservationReponse userReponse = reservations.addReservation(id_utilisateur,civilite,nom,prenom,PossibilityReponse, mail);

        return userReponse.toJson();
    }
}
