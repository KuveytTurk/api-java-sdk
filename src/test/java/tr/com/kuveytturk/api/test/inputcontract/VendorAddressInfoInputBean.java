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
 * @author Fikri Aydemir
 * @version 1.0
 * @since 2020-01-12
 */
public class VendorAddressInfoInputBean implements java.io.Serializable {

    @SerializedName("vendorAddressText")
    @Expose
    private String vendorAddressText;

    @SerializedName("vendorCountyId")
    @Expose
    private int vendorCountyId;

    @SerializedName("vendorCityId")
    @Expose
    private int vendorCityId;

    @SerializedName("vendorAddressTypeId")
    @Expose
    private int vendorAddressTypeId;

    @SerializedName("vendorCountryId")
    @Expose
    private int vendorCountryId;

    public VendorAddressInfoInputBean() {
    }

    public VendorAddressInfoInputBean(String vendorAddressText,
                                      int vendorCountyId,
                                      int vendorCityId,
                                      int vendorAddressTypeId,
                                      int vendorCountryId) {
        this.vendorAddressText = vendorAddressText;
        this.vendorCountyId = vendorCountyId;
        this.vendorCityId = vendorCityId;
        this.vendorAddressTypeId = vendorAddressTypeId;
        this.vendorCountryId = vendorCountryId;
    }

    public String getVendorAddressText() {
        return vendorAddressText;
    }

    public void setVendorAddressText(String vendorAddressText) {
        this.vendorAddressText = vendorAddressText;
    }

    public int getVendorCountyId() {
        return vendorCountyId;
    }

    public void setVendorCountyId(int vendorCountyId) {
        this.vendorCountyId = vendorCountyId;
    }

    public int getVendorCityId() {
        return vendorCityId;
    }

    public void setVendorCityId(int vendorCityId) {
        this.vendorCityId = vendorCityId;
    }

    public int getVendorAddressTypeId() {
        return vendorAddressTypeId;
    }

    public void setVendorAddressTypeId(int vendorAddressTypeId) {
        this.vendorAddressTypeId = vendorAddressTypeId;
    }

    public int getVendorCountryId() {
        return vendorCountryId;
    }

    public void setVendorCountryId(int vendorCountryId) {
        this.vendorCountryId = vendorCountryId;
    }
}
