package com.supinfo.test.ReponseRest;

import com.supinfo.test.entity.Gare;
import com.supinfo.test.entity.Ligne;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Théo on 08/06/2016.
 */
public class CorrespondanceReponse implements Serializable{

    Date heureD = null;
    Date heureA = null;
    Gare gareD = null;
    Gare gareA = null;
    Integer duree = 0;
    String ligne = null;

    public Gare getGareD() {
        return gareD;
    }

    public void setGareD(Gare gareD) {
        this.gareD = gareD;
    }

    public Gare getGareA() {
        return gareA;
    }

    public void setGareA(Gare gareA) {
        this.gareA = gareA;
    }

    public String getLigne() {
        return ligne;
    }

    public void setLigne(String ligne) {
        this.ligne = ligne;
    }

    public Integer getDuree() {
        return duree;
    }

    public void setDuree(Integer duree) {
        this.duree = duree;
    }

    public Date getHeureA() {
        return heureA;
    }

    public void setHeureA(Date heureA) {
        this.heureA = heureA;
    }

    public Date getHeureD() {
        return heureD;
    }

    public void setHeureD(Date heureD) {
        this.heureD = heureD;
    }
}
