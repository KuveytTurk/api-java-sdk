/*
 * Copyright (c) 2020
 * KUVEYT TÜRK PARTICIPATION BANK INC.
 *
 * Author: Fikri Aydemir
 *
 * Project: Java SDK for Accessing API Endpoints
 */

package tr.com.kuveytturk.api.client.sdk.util;

public class PostRequestExecutionException extends Exception{
    private String errorResponse;

    /**
     * Constructor
     *
     * @param message Holds the error message
     */
    public PostRequestExecutionException(String message) {
        super(message);
    }

    /**
     * Constructor
     *
     * @param message Holds the error message
     * @param e       Holds the original exception object
     */
    public PostRequestExecutionException(String message, Exception e) {
        super(message, e);
    }

    /**
     * Constructor
     *
     * @param message         Holds the error message
     * @param e               Holds the original exception object
     * @param errResponse Holds the error response object
     */
    public PostRequestExecutionException(String message, Exception e, String errResponse) {
        super(message, e);
        errorResponse = errResponse;
    }

    public String getErrorResponse() {
        return errorResponse;
    }

    public String getErrorDetailsIfAny() {
        if(errorResponse != null && !errorResponse.isEmpty()) {
            return "ErrorResponse = " + errorResponse +
                    ", ExceptionMessage = " + super.getMessage();
        } else {
            return "ExceptionMessage = " + super.getMessage();
        }
    }
}
