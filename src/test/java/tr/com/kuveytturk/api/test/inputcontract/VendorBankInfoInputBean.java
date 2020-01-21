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
public class VendorBankInfoInputBean implements java.io.Serializable {
    @SerializedName("vendorBankBankId")
    @Expose
    private int vendorBankBankId;

    @SerializedName("vendorBankBranchId")
    @Expose
    private int vendorBankBranchId;

    @SerializedName("vendorBankAccountTypeId")
    @Expose
    private int vendorBankAccountTypeId;

    @SerializedName("vendorBankFecText")
    @Expose
    private String vendorBankFecText;

    @SerializedName("vendorBankAccountNumber")
    @Expose
    private String vendorBankAccountNumber;

    @SerializedName("vendorBankIBAN")
    @Expose
    private String vendorBankIBAN;

    public VendorBankInfoInputBean() {
    }

    public VendorBankInfoInputBean(int vendorBankBankId,
                                   int vendorBankBranchId,
                                   int vendorBankAccountTypeId,
                                   String vendorBankFecText,
                                   String vendorBankAccountNumber,
                                   String vendorBankIBAN) {
        this.vendorBankBankId = vendorBankBankId;
        this.vendorBankBranchId = vendorBankBranchId;
        this.vendorBankAccountTypeId = vendorBankAccountTypeId;
        this.vendorBankFecText = vendorBankFecText;
        this.vendorBankAccountNumber = vendorBankAccountNumber;
        this.vendorBankIBAN = vendorBankIBAN;
    }

    public int getVendorBankBankId() {
        return vendorBankBankId;
    }

    public void setVendorBankBankId(int vendorBankBankId) {
        this.vendorBankBankId = vendorBankBankId;
    }

    public int getVendorBankBranchId() {
        return vendorBankBranchId;
    }

    public void setVendorBankBranchId(int vendorBankBranchId) {
        this.vendorBankBranchId = vendorBankBranchId;
    }

    public int getVendorBankAccountTypeId() {
        return vendorBankAccountTypeId;
    }

    public void setVendorBankAccountTypeId(int vendorBankAccountTypeId) {
        this.vendorBankAccountTypeId = vendorBankAccountTypeId;
    }

    public String getVendorBankFecText() {
        return vendorBankFecText;
    }

    public void setVendorBankFecText(String vendorBankFecText) {
        this.vendorBankFecText = vendorBankFecText;
    }

    public String getVendorBankAccountNumber() {
        return vendorBankAccountNumber;
    }

    public void setVendorBankAccountNumber(String vendorBankAccountNumber) {
        this.vendorBankAccountNumber = vendorBankAccountNumber;
    }

    public String getVendorBankIBAN() {
        return vendorBankIBAN;
    }

    public void setVendorBankIBAN(String vendorBankIBAN) {
        this.vendorBankIBAN = vendorBankIBAN;
    }
}
