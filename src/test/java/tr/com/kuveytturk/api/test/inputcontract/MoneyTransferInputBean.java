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

import java.math.BigDecimal;

/**
 * POJO class for representing the input parameters that are related to MoneyTransfer endpoint.
 *
 * @author      Fikri Aydemir
 * @version     1.0
 * @since       2020-01-12
 */
public class MoneyTransferInputBean implements java.io.Serializable {

    @SerializedName("SenderAccountSuffix")
    @Expose
    private String senderAccountSuffix;

    @SerializedName("ReceiverAccountNumber")
    @Expose
    private String receiverAccountNumber;

    @SerializedName("ReceiverAccountSuffix")
    @Expose
    private String receiverAccountSuffix;

    @SerializedName("ReceiverBankId")
    @Expose
    private int receiverBankId;

    @SerializedName("ReceiverBranchId")
    @Expose
    private int receiverBranchId;

    @SerializedName("ReceiverName")
    @Expose
    private String receiverName;

    @SerializedName("Amount")
    @Expose
    private BigDecimal amount;

    @SerializedName("paymentTypeId")
    @Expose
    private int paymentTypeId;

    @SerializedName("comment")
    @Expose
    private String comment;

    public MoneyTransferInputBean() {
    }

    public String getSenderAccountSuffix() {
        return senderAccountSuffix;
    }

    public void setSenderAccountSuffix(String senderAccountSuffix) {
        this.senderAccountSuffix = senderAccountSuffix;
    }

    public String getReceiverAccountNumber() {
        return receiverAccountNumber;
    }

    public void setReceiverAccountNumber(String receiverAccountNumber) {
        this.receiverAccountNumber = receiverAccountNumber;
    }

    public String getReceiverAccountSuffix() {
        return receiverAccountSuffix;
    }

    public void setReceiverAccountSuffix(String receiverAccountSuffix) {
        this.receiverAccountSuffix = receiverAccountSuffix;
    }

    public int getReceiverBankId() {
        return receiverBankId;
    }

    public void setReceiverBankId(int receiverBankId) {
        this.receiverBankId = receiverBankId;
    }

    public int getReceiverBranchId() {
        return receiverBranchId;
    }

    public void setReceiverBranchId(int receiverBranchId) {
        this.receiverBranchId = receiverBranchId;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public int getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(int paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String toJson(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        String theObject = gsonBuilder.create().toJson(this);
        return theObject;
    }
}
