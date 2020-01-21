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
 * POJO class
 *
 * @author Fikri Aydemir
 * @version 1.0
 * @since 2020-01-12
 */
public class VendorInputBean implements java.io.Serializable {

    @SerializedName("vendorTaxNumber")
    @Expose
    private String vendorTaxNumber;

    @SerializedName("hasVendorGeneralInfo")
    @Expose
    private boolean hasVendorGeneralInfo;

    @SerializedName("hasVendorContactInfo")
    @Expose
    private boolean hasVendorContactInfo;

    @SerializedName("hasVendorAddressInfo")
    @Expose
    private boolean hasVendorAddressInfo;

    @SerializedName("hasVendorPartnershipInfo")
    @Expose
    private boolean hasVendorPartnershipInfo;

    @SerializedName("hasVendorBankInfo")
    @Expose
    private boolean hasVendorBankInfo;

    @SerializedName("VendorGeneralInfo")
    @Expose
    private VendorGeneralInfoInputBean vendorGeneralInfo;

    @SerializedName("VendorContactInfo")
    @Expose
    private VendorContactInfoInputBean vendorContactInfo;

    @SerializedName("VendorAddressInfo")
    @Expose
    private VendorAddressInfoInputBean vendorAddressInfo;

    @SerializedName("VendorBankInfo")
    @Expose
    private VendorBankInfoInputBean vendorBankInfo;

    public VendorInputBean() {
    }

    public VendorInputBean(String vendorTaxNumber,
                           boolean hasVendorGeneralInfo,
                           boolean hasVendorContactInfo,
                           boolean hasVendorAddressInfo,
                           boolean hasVendorPartnershipInfo,
                           boolean hasVendorBankInfo,
                           VendorGeneralInfoInputBean vendorGeneralInfo,
                           VendorContactInfoInputBean vendorContactInfo,
                           VendorAddressInfoInputBean vendorAddressInfo,
                           VendorBankInfoInputBean vendorBankInfo) {
        this.vendorTaxNumber = vendorTaxNumber;
        this.hasVendorGeneralInfo = hasVendorGeneralInfo;
        this.hasVendorContactInfo = hasVendorContactInfo;
        this.hasVendorAddressInfo = hasVendorAddressInfo;
        this.hasVendorPartnershipInfo = hasVendorPartnershipInfo;
        this.hasVendorBankInfo = hasVendorBankInfo;
        this.vendorGeneralInfo = vendorGeneralInfo;
        this.vendorContactInfo = vendorContactInfo;
        this.vendorAddressInfo = vendorAddressInfo;
        this.vendorBankInfo = vendorBankInfo;
    }

    public String getVendorTaxNumber() {
        return vendorTaxNumber;
    }

    public void setVendorTaxNumber(String vendorTaxNumber) {
        this.vendorTaxNumber = vendorTaxNumber;
    }

    public boolean isHasVendorGeneralInfo() {
        return hasVendorGeneralInfo;
    }

    public void setHasVendorGeneralInfo(boolean hasVendorGeneralInfo) {
        this.hasVendorGeneralInfo = hasVendorGeneralInfo;
    }

    public boolean isHasVendorContactInfo() {
        return hasVendorContactInfo;
    }

    public void setHasVendorContactInfo(boolean hasVendorContactInfo) {
        this.hasVendorContactInfo = hasVendorContactInfo;
    }

    public boolean isHasVendorAddressInfo() {
        return hasVendorAddressInfo;
    }

    public void setHasVendorAddressInfo(boolean hasVendorAddressInfo) {
        this.hasVendorAddressInfo = hasVendorAddressInfo;
    }

    public boolean isHasVendorPartnershipInfo() {
        return hasVendorPartnershipInfo;
    }

    public void setHasVendorPartnershipInfo(boolean hasVendorPartnershipInfo) {
        this.hasVendorPartnershipInfo = hasVendorPartnershipInfo;
    }

    public boolean isHasVendorBankInfo() {
        return hasVendorBankInfo;
    }

    public void setHasVendorBankInfo(boolean hasVendorBankInfo) {
        this.hasVendorBankInfo = hasVendorBankInfo;
    }

    public VendorGeneralInfoInputBean getVendorGeneralInfo() {
        return vendorGeneralInfo;
    }

    public void setVendorGeneralInfo(VendorGeneralInfoInputBean vendorGeneralInfo) {
        this.vendorGeneralInfo = vendorGeneralInfo;
    }

    public VendorContactInfoInputBean getVendorContactInfo() {
        return vendorContactInfo;
    }

    public void setVendorContactInfo(VendorContactInfoInputBean vendorContactInfo) {
        this.vendorContactInfo = vendorContactInfo;
    }

    public VendorAddressInfoInputBean getVendorAddressInfo() {
        return vendorAddressInfo;
    }

    public void setVendorAddressInfo(VendorAddressInfoInputBean vendorAddressInfo) {
        this.vendorAddressInfo = vendorAddressInfo;
    }

    public VendorBankInfoInputBean getVendorBankInfo() {
        return vendorBankInfo;
    }

    public void setVendorBankInfo(VendorBankInfoInputBean vendorBankInfo) {
        this.vendorBankInfo = vendorBankInfo;
    }
}
