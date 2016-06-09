package com.supinfo.test.ReponseRest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Théo on 08/06/2016.
 */
public class PossibilityReponse implements Serializable {

    ArrayList<CorrespondanceReponse> correspondanceReponses = new ArrayList<CorrespondanceReponse>();
    Integer pos = null;

    Integer distancetotale = null;
    Date Depart = null;
    Date ArrDate = null;
    double prix;

    public Integer getDistancetotale() {
        return distancetotale;
    }

    public void setDistancetotale(Integer distancetotale) {
        this.distancetotale = distancetotale;
    }

    public Date getDepart() {
        return Depart;
    }

    public void setDepart(Date depart) {
        Depart = depart;
    }

    public Date getArrDate() {
        return ArrDate;
    }

    public void setArrDate(Date arrDate) {
        ArrDate = arrDate;
    }

    public ArrayList<CorrespondanceReponse> getCorrespondanceReponses() {
        return correspondanceReponses;
    }

    public void setCorrespondanceReponses(ArrayList<CorrespondanceReponse> correspondanceReponses) {
        this.correspondanceReponses = correspondanceReponses;
    }

    public Integer getPos() {
        return pos;
    }

    public void setPos(Integer pos) {
        this.pos = pos;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
}
