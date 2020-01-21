/*
 * Copyright (c) 2020
 * KUVEYT TÜRK PARTICIPATION BANK INC.
 *
 * Author: Fikri Aydemir
 *
 * Project: Java SDK for Accessing API Endpoints
 */

package tr.com.kuveytturk.api.client.sdk.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ResultBean implements java.io.Serializable{

    @SerializedName("exception")
    @Expose
    private String exception;

    @SerializedName("errorMessage")
    @Expose
    private String errorMessage;

    @SerializedName("errorCode")
    @Expose
    private String errorCode;

    public ResultBean() {
        setErrorCode("");
        setErrorMessage("");
        setException("");
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String toString() {
        return "ResultBean{" +
                "exception='" + exception + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                ", errorCode='" + errorCode + '\'' +
                '}';
    }
}
