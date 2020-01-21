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
 * Enumeration Type for representing the grant types
 *
 * @author      Fikri Aydemir
 * @version     1.0
 * @since       2020-01-12
 */
public enum GrantType {
    AUTHORIZATION_CODE("authorization_code"),
    CLIENT_CREDENTIALS("client_credentials"),
    REFRESH_TOKEN("refresh_token");

    String value;

    GrantType(String theValue) {
        this.value = theValue;
    }

    @Override
    public String toString(){
        return this.value;
    }
}