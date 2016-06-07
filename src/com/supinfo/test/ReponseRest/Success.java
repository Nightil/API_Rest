package com.supinfo.test.ReponseRest;

import java.io.Serializable;

/**
 * Created by alexa on 04/06/2016.
 */
public class Success implements Serializable {
    Boolean success;
    String message;

    public Success(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
