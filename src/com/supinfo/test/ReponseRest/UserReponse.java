package com.supinfo.test.ReponseRest;

import com.google.gson.Gson;
import com.supinfo.test.entity.Utilisateurs;

import java.io.Serializable;

/**
 * Created by alexa on 04/06/2016.
 */
public class UserReponse implements Serializable {
    Utilisateurs user = null;
    Success success = new Success(false,"Erreur inconue");

    public UserReponse(Utilisateurs user, Success success) {
        this.user = user;
        this.success = success;
    }

    public UserReponse() {

    }

    public Utilisateurs getUser() {
        return user;
    }

    public void setUser(Utilisateurs user) {
        this.user = user;
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
