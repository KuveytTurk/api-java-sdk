/*
 * Copyright (c) 2020
 * KUVEYT TÜRK PARTICIPATION BANK INC.
 *
 * Author: Fikri Aydemir
 *
 * Project: Java SDK for Accessing API Endpoints
 */

package tr.com.kuveytturk.api.client.sdk.util;

import tr.com.kuveytturk.api.client.sdk.model.response.AccessTokenResponseBean;

/**
 * Custom exception class that can be thrown during Access Token Retrieval
 *
 * @author      Fikri Aydemir
 * @version     1.0
 * @since       2019-12-29
 */
public final class AccessTokenRetrievalException extends Exception {

    private AccessTokenResponseBean errorResponse;

    /**
     * Constructor
     *
     * @param  message Holds the error message
     * @param  e Holds the original exception object
     */
    public AccessTokenRetrievalException(String message, Exception e){
        super(message,e);
    }

    /**
     * Constructor
     *
     * @param  message Holds the error message
     * @param  e Holds the original exception object
     * @param  errResponseBean Holds the error response object
     */
    public AccessTokenRetrievalException(String message, Exception e, AccessTokenResponseBean errResponseBean){
        super(message,e);
        errorResponse = errResponseBean;
    }

    public AccessTokenResponseBean getErrorResponse() {
        return errorResponse;
    }

}
