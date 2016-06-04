package com.supinfo.test.ReponseRest;

import com.google.gson.Gson;
import com.supinfo.test.entity.Gare;
import com.supinfo.test.entity.Utilisateurs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexa on 04/06/2016.
 */
public class GaresReponse implements Serializable {
    List<Gare> gares = null;
    Success success = new Success(false,"Erreur inconue");

    public GaresReponse(ArrayList<Gare> gares, Success success) {
        this.gares = gares;
        this.success = success;
    }

    public GaresReponse() {

    }

    public List<Gare> getGares() {
        return gares;
    }

    public void setGares(List<Gare> gares) {
        this.gares = gares;
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
