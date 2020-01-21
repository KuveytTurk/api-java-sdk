/*
 * Copyright (c) 2020
 * KUVEYT TÜRK PARTICIPATION BANK INC.
 *
 * Author: Fikri Aydemir
 *
 * Project: Java SDK for Accessing API Endpoints
 */

package tr.com.kuveytturk.api.test.outputcontract;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MoneyTransferOutputBean implements java.io.Serializable {

    @SerializedName("secureOtpId")
    @Expose
    private String secureOtpId;

    @SerializedName("receiverName")
    @Expose
    private String receiverName;

    public MoneyTransferOutputBean() {
    }

    public String getSecureOtpId() {
        return secureOtpId;
    }

    public void setSecureOtpId(String secureOtpId) {
        this.secureOtpId = secureOtpId;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    @Override
    public String toString() {
        return "MoneyTransferOutputBean{" +
                "secureOtpId='" + secureOtpId + '\'' +
                ", receiverName='" + receiverName + '\'' +
                '}';
    }

    public static MoneyTransferOutputBean fromJsonToPOJO(Object obj){
        Gson gson = new Gson();
        JsonElement jsonElement = gson.toJsonTree(obj);
        MoneyTransferOutputBean pojo = gson.fromJson(jsonElement, MoneyTransferOutputBean.class);
        return pojo;
    }
}
