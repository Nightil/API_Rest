package com.supinfo.test.ReponseRest;

import com.google.gson.Gson;

import java.io.Serializable;

/**
 * Created by Théo on 08/06/2016.
 */
public class SearchResponse implements Serializable {
    VoyageReponse allee = null;
    VoyageReponse retour = null;

    public VoyageReponse getAllee() {
        return allee;
    }

    public void setAllee(VoyageReponse allee) {
        this.allee = allee;
    }

    public VoyageReponse getRetour() {
        return retour;
    }

    public void setRetour(VoyageReponse retour) {
        this.retour = retour;
    }
    public String toJson(){
        Gson gson = new Gson();
        return  gson.toJson(this);
    }
}
