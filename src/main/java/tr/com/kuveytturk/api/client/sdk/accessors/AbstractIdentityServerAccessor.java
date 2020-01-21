/*
 * Copyright (c) 2020
 * KUVEYT TÜRK PARTICIPATION BANK INC.
 *
 * Author: Fikri Aydemir
 *
 * Project: Java SDK for Accessing API Endpoints
 */

package tr.com.kuveytturk.api.client.sdk.accessors;

import tr.com.kuveytturk.api.client.sdk.model.request.AccessTokenRequestBean;
import tr.com.kuveytturk.api.client.sdk.model.request.AuthorizationCodeRequestBean;
import tr.com.kuveytturk.api.client.sdk.model.response.AccessTokenResponseBean;
import tr.com.kuveytturk.api.client.sdk.util.AccessTokenRetrievalException;
import tr.com.kuveytturk.api.client.sdk.util.AuthorizationCodeRetrievalException;
import tr.com.kuveytturk.api.client.sdk.util.Constants;

/**
 * Abstract class representing an instance of class AbstractIdentityServerAccessor
 *
 * @author      Fikri Aydemir
 * @version     1.0
 * @since       2020-01-12
 */
public abstract class AbstractIdentityServerAccessor {
    private String authorizationCodeRetrievalUrl;
    private String accessTokenRetrievalUrl;
    private String baseAuthorizationUrl;
    private String authorizationEndpoint;
    private String baseAccessTokenURL;
    private String accessTokenEndpoint;
    private String revokeTokenEndPoint;

    public abstract String requestAuthorizationCode(AuthorizationCodeRequestBean requestBean) throws AuthorizationCodeRetrievalException;

    public abstract String requestAuthorizationCode(int customerNumber,
                                                    String clientId,
                                                    String redirectURI,
                                                    String scopeListString) throws AuthorizationCodeRetrievalException;


    public abstract void doLogOut() throws AuthorizationCodeRetrievalException;

    public abstract AccessTokenResponseBean requestAccessTokenWithAuthorizationCode(AccessTokenRequestBean accessTokenRequestBean) throws AccessTokenRetrievalException;

    public abstract AccessTokenResponseBean requestAccessTokenWithAuthorizationCode(
            String clientId,
            String clientSecret,
            String code,
            String redirectUri) throws AccessTokenRetrievalException;

    public abstract AccessTokenResponseBean requestAccessTokenWithClientCredentials(AccessTokenRequestBean accessTokenRequestBean) throws AccessTokenRetrievalException;

    public abstract AccessTokenResponseBean requestAccessTokenWithClientCredentials(
            String clientId,
            String clientSecret,
            String scope) throws AccessTokenRetrievalException;

    public abstract AccessTokenResponseBean requestAccessTokenWithRefreshToken(
            String clientId,
            String clientSecret,
            String refreshToken) throws AccessTokenRetrievalException;


    public abstract AccessTokenResponseBean revokeAccessToken(String clientId, String clientSecret, String accessToken)
            throws AccessTokenRetrievalException;

    public abstract AccessTokenResponseBean revokeRefreshToken(String clientId, String clientSecret, String refreshToken) throws AccessTokenRetrievalException;

    public String getAuthorizationCodeRetrievalUrl() {
        return authorizationCodeRetrievalUrl;
    }

    public String getAccessTokenRetrievalUrl() {
        return accessTokenRetrievalUrl;
    }

    public void setAuthorizationCodeRetrievalUrl(String authorizationCodeRetrievalUrl) {
        this.authorizationCodeRetrievalUrl = authorizationCodeRetrievalUrl;
    }

    public void setAccessTokenRetrievalUrl(String accessTokenRetrievalUrl) {
        this.accessTokenRetrievalUrl = accessTokenRetrievalUrl;
    }

    public String getBaseAuthorizationUrl() {
        return baseAuthorizationUrl;
    }

    public void setBaseAuthorizationUrl(String baseAuthorizationUrl) {
        this.baseAuthorizationUrl = baseAuthorizationUrl;
    }

    public String getAuthorizationEndpoint() {
        return authorizationEndpoint;
    }

    public void setAuthorizationEndpoint(String authorizationEndpoint) {
        this.authorizationEndpoint = authorizationEndpoint;
    }

    public String getBaseAccessTokenURL() {
        return baseAccessTokenURL;
    }

    public void setBaseAccessTokenURL(String baseAccessTokenURL) {
        this.baseAccessTokenURL = baseAccessTokenURL;
    }

    public String getAccessTokenEndpoint() {
        return accessTokenEndpoint;
    }

    public void setAccessTokenEndpoint(String accessTokenEndpoint) {
        this.accessTokenEndpoint = accessTokenEndpoint;
    }

    public String getRevokeTokenEndPoint() {
        return revokeTokenEndPoint;
    }

    public void setRevokeTokenEndPoint(String revokeTokenEndPoint) {
        this.revokeTokenEndPoint = revokeTokenEndPoint;
    }

    protected String buildAuthorizationQueryString(String clientId, String redirectURI, String scopeListString){
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("?").append(Constants.CLIENT_ID_LABEL).append("=").append(clientId).append("&")
                .append(Constants.SCOPE_LABEL).append("=").append(scopeListString).append("&")
                .append(Constants.RESPONSE_TYPE_LABEL).append("=").append(Constants.RESPONSE_TYPE_VALUE).append("&")
                .append(Constants.REDIRECT_URI_LABEL).append("=").append(redirectURI);

        return strBuilder.toString();
    }

    protected String parseCodeString(String theUrl){
        String tokensByQuestionMark[] = theUrl.split("\\?");
        if(tokensByQuestionMark != null && tokensByQuestionMark.length > 1){
            String tokensByAndSign[] = tokensByQuestionMark[1].split("&");
            if(tokensByAndSign != null && tokensByAndSign.length > 0){
                String rawCodeText = tokensByAndSign[0];
                String finalArray[] = rawCodeText.split("=");
                if(finalArray != null && finalArray.length > 1){
                    return finalArray[1];
                }
            }
        }

        return "";
    }

}
