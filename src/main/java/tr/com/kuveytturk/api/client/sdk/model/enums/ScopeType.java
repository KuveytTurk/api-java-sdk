/*
 * Copyright (c) 2020
 * KUVEYT TÜRK PARTICIPATION BANK INC.
 *
 * Author: Fikri Aydemir
 *
 * Project: Java SDK for Accessing API Endpoints
 */

package tr.com.kuveytturk.api.client.sdk.model.enums;

/**
 * Enumeration Type for representing the scope types
 *
 * @author      Fikri Aydemir
 * @version     1.0
 * @since       2020-01-12
 */
public enum ScopeType {
    ACCOUNTS("accounts"),
    LOANS("loans"),
    TRANSFERS("transfers"),
    PUBLIC("public"),
    CARDS("cards"),
    PAYMENTS("payments"),
    DONATIONS("donations"),
    ACCOUNT_ACTIVITIES("account_activities"),
    DIGITAL_PAYMENTS("digital_payments"),
    INCIDENT_OPERATIONS("incident_operations"),
    INTRABANK_MONEY_TRANSFERS("intra_bank_money_transfers"),
    OFFLINE_ACCESS("offline_access");

    String value;

    ScopeType(String theValue){
        this.value = theValue;
    }

    @Override
    public String toString(){
        return this.value;
    }
}
