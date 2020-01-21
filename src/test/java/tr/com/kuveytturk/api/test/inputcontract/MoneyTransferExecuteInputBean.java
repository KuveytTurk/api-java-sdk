/*
 * Copyright (c) 2020
 * KUVEYT TÜRK PARTICIPATION BANK INC.
 *
 * Author: Fikri Aydemir
 *
 * Project: Java SDK for Accessing API Endpoints
 */

package tr.com.kuveytturk.api.test.inputcontract;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * POJO class for representing the input parameters that are related to MoneyTransferExecute endpoint.
 *
 * @author      Fikri Aydemir
 * @version     1.0
 * @since       2020-01-12
 */
public class MoneyTransferExecuteInputBean implements java.io.Serializable {

    @SerializedName("smsOtp")
    @Expose
    private String smsOtp;

    @SerializedName("secureOtpId")
    @Expose
    private String secureOtpId;

    public MoneyTransferExecuteInputBean() {
    }

    public String getSmsOtp() {
        return smsOtp;
    }

    public void setSmsOtp(String smsOtp) {
        this.smsOtp = smsOtp;
    }

    public String getSecureOtpId() {
        return secureOtpId;
    }

    public void setSecureOtpId(String secureOtpId) {
        this.secureOtpId = secureOtpId;
    }

    public String toJson(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        String theObject = gsonBuilder.create().toJson(this);
        return theObject;
    }

}
