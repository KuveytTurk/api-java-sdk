/*
 * Copyright (c) 2020
 * KUVEYT TÜRK PARTICIPATION BANK INC.
 *
 * Author: Fikri Aydemir
 *
 * Project: Java SDK for Accessing API Endpoints
 */

package tr.com.kuveytturk.api.test.outputcontract;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.LinkedTreeMap;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * POJO class for representing the output parameters that are related to AccountTransactions endpoint.
 *
 * @author      Fikri Aydemir
 * @version     1.0
 * @since       2020-01-12
 */
public class AccountTransactionOutputBean implements java.io.Serializable{

    @SerializedName("suffix")
    @Expose
    private int suffix;

    @SerializedName("date")
    @Expose
    private Date date;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("amount")
    @Expose
    private BigDecimal amount;

    @SerializedName("balance")
    @Expose
    private BigDecimal balance;

    @SerializedName("businessKey")
    @Expose
    private String businessKey;

    @SerializedName("fxCode")
    @Expose
    private String fxCode;

    public AccountTransactionOutputBean() {
    }

    public AccountTransactionOutputBean(LinkedTreeMap<String, Object> treeMap) {
        //new Integer(payload.getValue().get(i).intValue());
        suffix = new Integer(Double.valueOf((Double) treeMap.getOrDefault("suffix", 0d)).intValue());
        String strDate = (String) treeMap.getOrDefault("date", "");
        if(!strDate.isEmpty())
        {
            try {
                date = new SimpleDateFormat("yyyy-MM-dd'T'mm:hh:ss").parse(strDate);
            } catch (ParseException e) {
                date = new Date();
            }
        }
        description = (String) treeMap.getOrDefault("description", "");

        double amountD = (Double) treeMap.getOrDefault("amount", 0);
        amount = new BigDecimal(amountD);

        double balanceD = (Double) treeMap.getOrDefault("balance", 0);
        amount = new BigDecimal(balanceD);

        businessKey = (String) treeMap.getOrDefault("businessKey", "");
        fxCode = (String) treeMap.getOrDefault("fxCode", "");
    }

    public int getSuffix() {
        return suffix;
    }

    public void setSuffix(int suffix) {
        this.suffix = suffix;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    public String getFxCode() {
        return fxCode;
    }

    public void setFxCode(String fxCode) {
        this.fxCode = fxCode;
    }

    @Override
    public String toString() {
        return "AccountTransactionOutputBean{" +
                "suffix=" + suffix +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", balance=" + balance +
                ", businessKey='" + businessKey + '\'' +
                ", fxCode='" + fxCode + '\'' +
                '}';
    }
}
