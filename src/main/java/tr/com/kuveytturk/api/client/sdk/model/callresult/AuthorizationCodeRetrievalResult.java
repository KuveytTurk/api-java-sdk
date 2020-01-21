/*
 * Copyright (c) 2020
 * KUVEYT TÜRK PARTICIPATION BANK INC.
 *
 * Author: Fikri Aydemir
 *
 * Project: Java SDK for Accessing API Endpoints
 */

package tr.com.kuveytturk.api.client.sdk.model.callresult;

import tr.com.kuveytturk.api.client.sdk.util.AuthorizationCodeRetrievalException;

/**
 * The utility class containing properties about the result of an authorization
 * code request that has been made towards IdentityServer.
 *
 * @author      Fikri Aydemir
 * @version     1.0
 * @since       2020-01-12
 */
public final class AuthorizationCodeRetrievalResult {
    private String authorizationCode;
    private AuthorizationCodeRetrievalException innerException;
    private boolean hasSucceeded;

    public AuthorizationCodeRetrievalResult() {
        this.authorizationCode = "";
        hasSucceeded = true;
    }

    public AuthorizationCodeRetrievalResult(String authorizationCode) {
        this.authorizationCode = authorizationCode;
        hasSucceeded = true;
    }

    public AuthorizationCodeRetrievalResult(AuthorizationCodeRetrievalException innerException) {
        this.innerException = innerException;
        hasSucceeded = false;
    }

    public String getAuthorizationCode() {
        return authorizationCode;
    }

    public Exception getInnerException() {
        return innerException;
    }

    public boolean isSuccessful() {
        return hasSucceeded;
    }

    public String getErrorDetailsIfAny(){
        if(innerException != null){
            return innerException.getErrorDetailsIfAny();
        } else {
            return "";
        }
    }
}
