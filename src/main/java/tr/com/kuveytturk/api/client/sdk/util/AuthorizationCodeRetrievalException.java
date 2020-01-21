/*
 * Copyright (c) 2020
 * KUVEYT TÜRK PARTICIPATION BANK INC.
 *
 * Author: Fikri Aydemir
 *
 * Project: Java SDK for Accessing API Endpoints
 */

package tr.com.kuveytturk.api.client.sdk.util;

/**
 * Custom exception class that can be thrown during Access Token Retrieval
 *
 * @author Fikri Aydemir
 * @version 1.0
 * @since 2019-12-29
 */
public class AuthorizationCodeRetrievalException extends Exception{
    private String errorResponse;

    /**
     * Constructor
     *
     * @param message Holds the error message
     */
    public AuthorizationCodeRetrievalException(String message) {
        super(message);
    }

    /**
     * Constructor
     *
     * @param message Holds the error message
     * @param e       Holds the original exception object
     */
    public AuthorizationCodeRetrievalException(String message, Exception e) {
        super(message, e);
    }

    /**
     * Constructor
     *
     * @param message         Holds the error message
     * @param e               Holds the original exception object
     * @param errResponse Holds the error response object
     */
    public AuthorizationCodeRetrievalException(String message, Exception e, String errResponse) {
        super(message, e);
        errorResponse = errResponse;
    }

    public String getErrorResponse() {
        return errorResponse;
    }

    public String getErrorDetailsIfAny() {
        return  " ErrorResponse = " + errorResponse +
                ", ExceptionMessage ='" + getMessage();
    }
}
