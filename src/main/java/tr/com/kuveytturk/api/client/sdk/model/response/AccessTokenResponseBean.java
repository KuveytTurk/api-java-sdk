/*
 * Copyright (c) 2020
 * KUVEYT TÜRK PARTICIPATION BANK INC.
 *
 * Author: Fikri Aydemir
 *
 * Project: Java SDK for Accessing API Endpoints
 */

package tr.com.kuveytturk.api.client.sdk.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Contains fields that are present in the response message that is sent back from the Kuveyt Türk
 * identity server for the corresponding access token related request that has been made earlier.
 *
 * @author      Fikri Aydemir
 * @version     1.0
 * @since       2019-12-29
 */
public final class AccessTokenResponseBean implements Serializable{

    @SerializedName("access_token")
    @Expose
    private String accessToken;

    @SerializedName("token_type")
    @Expose
    private String tokenType;

    @SerializedName("scope")
    @Expose
    private String scope;

    @SerializedName("expires_in")
    @Expose
    private int expiresIn;

    @SerializedName("refresh_token")
    @Expose
    private String refreshToken;

    @SerializedName("error")
    @Expose
    private String error;

    @SerializedName("error_description")
    @Expose
    private String errorDescription;

    @SerializedName("code")
    @Expose
    private int httpStatusCode;

    @SerializedName("message")
    @Expose
    private String httpMessage;

    public AccessTokenResponseBean() {}

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public int getHttpStatusCode() { return httpStatusCode; }

    public void setHttpStatusCode(int httpStatusCode) { this.httpStatusCode = httpStatusCode; }

    public String getHttpMessage() { return httpMessage; }

    public void setHttpMessage(String httpMessage) { this.httpMessage = httpMessage; }

    public String getErrorDetailsIfAny() {
        return "AccessTokenResponseBean{" +
                "tokenType='" + tokenType + '\'' +
                ", error='" + error + '\'' +
                ", errorDescription='" + errorDescription + '\'' +
                ", httpStatusCode=" + httpStatusCode +
                ", httpMessage='" + httpMessage + '\'' +
                '}';
    }
}
