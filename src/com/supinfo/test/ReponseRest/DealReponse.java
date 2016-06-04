package com.supinfo.test.ReponseRest;

import com.google.gson.Gson;
import com.supinfo.test.entity.Deal;
import com.supinfo.test.entity.Gare;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexa on 04/06/2016.
 */
public class DealReponse implements Serializable {
    List<Deal> dealList = null;
    Success success = new Success(false,"Erreur inconue");

    public DealReponse(List<Deal> dealList, Success success) {
        this.dealList = dealList;
        this.success = success;
    }

    public DealReponse() {

    }

    public List<Deal> getDealList() {
        return dealList;
    }

    public void setDealList(List<Deal> gares) {
        this.dealList = gares;
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
