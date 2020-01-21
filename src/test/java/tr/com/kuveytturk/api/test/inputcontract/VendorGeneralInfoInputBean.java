/*
 * Copyright (c) 2020
 * KUVEYT TÜRK PARTICIPATION BANK INC.
 *
 * Author: Fikri Aydemir
 *
 * Project: Java SDK for Accessing API Endpoints
 */

package tr.com.kuveytturk.api.test.inputcontract;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VendorGeneralInfoInputBean implements java.io.Serializable {

    @SerializedName("vendorName")
    @Expose
    private String vendorName;

    @SerializedName("vendorTaxOffice")
    @Expose
    private String vendorTaxOffice;

    @SerializedName("vendorScore")
    @Expose
    private int vendorScore;

    @SerializedName("vendorEInvoice")
    @Expose
    private int vendorEInvoice;

    public VendorGeneralInfoInputBean() {
    }

    public VendorGeneralInfoInputBean(String vendorName, String vendorTaxOffice, int vendorScore, int vendorEInvoice) {
        this.vendorName = vendorName;
        this.vendorTaxOffice = vendorTaxOffice;
        this.vendorScore = vendorScore;
        this.vendorEInvoice = vendorEInvoice;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorTaxOffice() {
        return vendorTaxOffice;
    }

    public void setVendorTaxOffice(String vendorTaxOffice) {
        this.vendorTaxOffice = vendorTaxOffice;
    }

    public int getVendorScore() {
        return vendorScore;
    }

    public void setVendorScore(int vendorScore) {
        this.vendorScore = vendorScore;
    }

    public int getVendorEInvoice() {
        return vendorEInvoice;
    }

    public void setVendorEInvoice(int vendorEInvoice) {
        this.vendorEInvoice = vendorEInvoice;
    }
}
