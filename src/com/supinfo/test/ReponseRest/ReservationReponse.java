package com.supinfo.test.ReponseRest;

import com.google.gson.Gson;
import com.supinfo.test.entity.Reservations;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexa on 08/06/2016.
 */
public class ReservationReponse implements Serializable{

    Reservations reservation = null;
    Success success = new Success(false, "Erreur inconnu");

    public ReservationReponse(Reservations reservation, Success success){
        this.reservation = reservation;
        this.success = success;
    }

    public ReservationReponse(){

    }

    public Reservations getReservation() {
        return reservation;
    }

    public void setReservation(Reservations reservation) {
        this.reservation = reservation;
    }

    public Success getSuccess() {
        return success;
    }

    public void setSuccess(Success success) {
        this.success = success;
    }

    public String toJson(){
        Gson gson = new Gson();
        return  gson.toJson(this);
    }
}
