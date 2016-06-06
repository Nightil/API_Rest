package com.supinfo.test.ReponseRest;

import com.google.gson.Gson;
import com.supinfo.test.entity.Route;
import com.supinfo.test.entity.Voyages;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexa on 04/06/2016.
 */
public class VoyageReponse implements Serializable {
    List<Voyages> voyages = null;

    List<Route> routeList = null;

    Success success = new Success(false,"Erreur inconue");

    public VoyageReponse(ArrayList<Voyages> voyages, Success success) {
        this.voyages = voyages;
        this.success = success;
    }

    public VoyageReponse() {

    }

    public List<Route> getRouteList() {
        return routeList;
    }

    public void setRouteList(List<Route> routeList) {
        this.routeList = routeList;
    }

    public List<Voyages> getVoyages() {
        return voyages;
    }

    public void setVoyages(List<Voyages> voyages) {
        this.voyages = voyages;
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
