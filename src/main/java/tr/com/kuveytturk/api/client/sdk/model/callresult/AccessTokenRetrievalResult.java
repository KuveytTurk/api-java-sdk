/*
 * Copyright (c) 2020
 * KUVEYT TÜRK PARTICIPATION BANK INC.
 *
 * Author: Fikri Aydemir
 *
 * Project: Java SDK for Accessing API Endpoints
 */

package tr.com.kuveytturk.api.client.sdk.model.callresult;

import tr.com.kuveytturk.api.client.sdk.util.AccessTokenRetrievalException;

/**
 * The utility class containing properties about the result of an access token request
 * that has been made towards Idenetity Server.
 *
 * @author      Fikri Aydemir
 * @version     1.0
 * @since       2020-01-12
 */
public class AccessTokenRetrievalResult {

    private int httpStatusCode;
    private boolean hasSucceeded;
    private String accessToken;
    private String tokenType;
    private String scope;
    private int expiresIn;
    private String refreshToken;
    private String error;
    private String errorDescription;
    private AccessTokenRetrievalException innerException;

    /**
     * Inner builder class for building an instance of AccessTokenRetrievalResult
     *
     * @author      Fikri Aydemir
     * @version     1.0
     * @since       2020-01-12
     */
    public static class AccessTokenRetrievalResultBuilder {

        int httpStatusCode;
        boolean hasSucceeded;
        String accessToken;
        String tokenType;
        String scope;
        int expiresIn;
        String refreshToken;
        String error;
        String errorDescription;
        AccessTokenRetrievalException innerException;

        public AccessTokenRetrievalResultBuilder innerException(AccessTokenRetrievalException innerException) {
            this.innerException = innerException;
            return this;
        }

        public AccessTokenRetrievalResultBuilder hasSucceeded(boolean hasSucceeded) {
            this.hasSucceeded = hasSucceeded;
            return this;
        }

        public AccessTokenRetrievalResultBuilder httpStatusCode(int value) {
            this.httpStatusCode = value;
            return this;
        }

        public AccessTokenRetrievalResultBuilder accessToken(String accessToken) {
            this.accessToken = accessToken;
            return this;
        }

        public AccessTokenRetrievalResultBuilder tokenType(String tokenType) {
            this.tokenType = tokenType;
            return this;
        }

        public AccessTokenRetrievalResultBuilder scope(String scope) {
            this.scope = scope;
            return this;
        }

        public AccessTokenRetrievalResultBuilder expiresIn(int expiresIn) {
            this.expiresIn = expiresIn;
            return this;
        }

        public AccessTokenRetrievalResultBuilder refreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
            return this;
        }

        public AccessTokenRetrievalResultBuilder error(String error) {
            this.error = error;
            return this;
        }

        public AccessTokenRetrievalResultBuilder errorDescription(String errorDescription) {
            this.errorDescription = errorDescription;
            return this;
        }

        public AccessTokenRetrievalResult build() {return new AccessTokenRetrievalResult(this);}
    }


    private AccessTokenRetrievalResult(AccessTokenRetrievalResultBuilder builder) {
        this.innerException = builder.innerException;
        this.httpStatusCode =builder.httpStatusCode;
        this.hasSucceeded = builder.hasSucceeded;
        this.accessToken = builder.accessToken;
        this.tokenType = builder.tokenType;
        this.scope = builder.scope;
        this.expiresIn = builder.expiresIn;
        this.refreshToken = builder.refreshToken;
        this.error = builder.error;
        this.errorDescription = builder.errorDescription;
    }

    public AccessTokenRetrievalException getInnerException() {
        return innerException;
    }

    public boolean isSuccessful() {
        return hasSucceeded;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public String getScope() {
        return scope;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getError() {
        return error;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public String getErrorDetailsIfAny() {
        return  " Error = " + error +
                ", ErrorDescription =" + errorDescription;
    }
}
