package com.mylektop.simpleappsmvp.networking;

import com.google.gson.annotations.SerializedName;

/**
 * Created by MyLektop on 21/08/2018.
 */
public class Response {
    @SerializedName("status")
    public String status;

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @SuppressWarnings({"unused", "used by Retrofit"})
    public Response() {
    }

    public Response(String status) {
        this.status = status;
    }
}
