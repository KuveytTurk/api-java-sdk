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

public class VendorContainerBean implements java.io.Serializable {

    @SerializedName("Vendor")
    @Expose
    private VendorInputBean vendor;

    public VendorContainerBean() {
    }

    public VendorContainerBean(VendorInputBean vendor) {
        this.vendor = vendor;
    }

    public VendorInputBean getVendor() {
        return vendor;
    }

    public void setVendor(VendorInputBean vendor) {
        this.vendor = vendor;
    }

    public String toJson(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        String jsonBodyAsString = gsonBuilder.create().toJson(this);
        return jsonBodyAsString;
    }
}
