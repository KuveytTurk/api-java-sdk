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

/**
 * POJO class
 *
 * @author      Fikri Aydemir
 * @version     1.0
 * @since       2020-01-12
 */
public class VendorContactInfoInputBean implements java.io.Serializable{

    @SerializedName("vendorContactTitle")
    @Expose
    private String vendorContactTitle;

    @SerializedName("vendorContactGSMNumber")
    @Expose
    private String vendorContactGSMNumber;

    @SerializedName("vendorContactDepartment")
    @Expose
    private String vendorContactDepartment;

    @SerializedName("vendorContactFaxNumber")
    @Expose
    private String vendorContactFaxNumber;

    @SerializedName("vendorContactEMail")
    @Expose
    private String vendorContactEMail;

    @SerializedName("vendorContactName")
    @Expose
    private String vendorContactName;

    @SerializedName("vendorContactSurname")
    @Expose
    private String vendorContactSurname;

    @SerializedName("vendorContactTelNumber")
    @Expose
    private String vendorContactTelNumber;

    public VendorContactInfoInputBean() {
    }

    public VendorContactInfoInputBean(String vendorContactTitle,
                                      String vendorContactGSMNumber,
                                      String vendorContactDepartment,
                                      String vendorContactFaxNumber,
                                      String vendorContactEMail,
                                      String vendorContactName,
                                      String vendorContactSurname,
                                      String vendorContactTelNumber) {
        this.vendorContactTitle = vendorContactTitle;
        this.vendorContactGSMNumber = vendorContactGSMNumber;
        this.vendorContactDepartment = vendorContactDepartment;
        this.vendorContactFaxNumber = vendorContactFaxNumber;
        this.vendorContactEMail = vendorContactEMail;
        this.vendorContactName = vendorContactName;
        this.vendorContactSurname = vendorContactSurname;
        this.vendorContactTelNumber = vendorContactTelNumber;
    }

    public String getVendorContactTitle() {
        return vendorContactTitle;
    }

    public void setVendorContactTitle(String vendorContactTitle) {
        this.vendorContactTitle = vendorContactTitle;
    }

    public String getVendorContactGSMNumber() {
        return vendorContactGSMNumber;
    }

    public void setVendorContactGSMNumber(String vendorContactGSMNumber) {
        this.vendorContactGSMNumber = vendorContactGSMNumber;
    }

    public String getVendorContactDepartment() {
        return vendorContactDepartment;
    }

    public void setVendorContactDepartment(String vendorContactDepartment) {
        this.vendorContactDepartment = vendorContactDepartment;
    }

    public String getVendorContactFaxNumber() {
        return vendorContactFaxNumber;
    }

    public void setVendorContactFaxNumber(String vendorContactFaxNumber) {
        this.vendorContactFaxNumber = vendorContactFaxNumber;
    }

    public String getVendorContactEMail() {
        return vendorContactEMail;
    }

    public void setVendorContactEMail(String vendorContactEMail) {
        this.vendorContactEMail = vendorContactEMail;
    }

    public String getVendorContactName() {
        return vendorContactName;
    }

    public void setVendorContactName(String vendorContactName) {
        this.vendorContactName = vendorContactName;
    }

    public String getVendorContactSurname() {
        return vendorContactSurname;
    }

    public void setVendorContactSurname(String vendorContactSurname) {
        this.vendorContactSurname = vendorContactSurname;
    }

    public String getVendorContactTelNumber() {
        return vendorContactTelNumber;
    }

    public void setVendorContactTelNumber(String vendorContactTelNumber) {
        this.vendorContactTelNumber = vendorContactTelNumber;
    }

}
