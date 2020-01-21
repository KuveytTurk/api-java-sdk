/*
 * Copyright (c) 2020
 * KUVEYT TÜRK PARTICIPATION BANK INC.
 *
 * Author: Fikri Aydemir
 *
 * Project: Java SDK for Accessing API Endpoints
 */

package tr.com.kuveytturk.api.client.sdk.api;

import com.google.gson.JsonObject;
import tr.com.kuveytturk.api.client.sdk.accessors.APIGatewayAccessor;
import tr.com.kuveytturk.api.client.sdk.accessors.AbstractAPIGatewayAccessor;
import tr.com.kuveytturk.api.client.sdk.accessors.AbstractIdentityServerAccessor;
import tr.com.kuveytturk.api.client.sdk.accessors.IdentityServerAccessor;
import tr.com.kuveytturk.api.client.sdk.model.callresult.APICallResult;
import tr.com.kuveytturk.api.client.sdk.model.callresult.AccessTokenRetrievalResult;
import tr.com.kuveytturk.api.client.sdk.model.callresult.AuthorizationCodeRetrievalResult;
import tr.com.kuveytturk.api.client.sdk.model.enums.ScopeType;
import tr.com.kuveytturk.api.client.sdk.model.misc.QueryParameterListBean;
import tr.com.kuveytturk.api.client.sdk.model.misc.ScopeListBean;
import tr.com.kuveytturk.api.client.sdk.model.response.APIResponseBean;
import tr.com.kuveytturk.api.client.sdk.model.response.AccessTokenResponseBean;
import tr.com.kuveytturk.api.client.sdk.util.*;

/**
 * Concrete class for executing operations on IdentityServer and APIGateway
 *
 * @author      Fikri Aydemir
 * @version     1.0
 * @since       2020-01-11
 */

public class APIExecutor<T> implements APIExecutionInterface<T> {
    protected String baseAuthorizationUrl;
    protected String authorizationEndpoint;
    protected String baseAccessTokenURL;
    protected String accessTokenEndpoint;
    protected String revokeTokenEndPoint;
    protected String apiAccessUrl;
    protected String privateKey;
    protected ScopeListBean scopeList;

    /**
     * Default Constructor
     *
     */
    public APIExecutor() {
        this(Constants.BASE_AUTHORIZATION_URL,
                Constants.AUTHORIZATION_ENDPOINT,
                Constants.BASE_ACCESS_TOKEN_URL,
                Constants.ACCESS_TOKEN_ENDPOINT,
                Constants.REVOKE_TOKEN_ENDPOINT,
                Constants.BASE_API_ACCESS_URL,
                Constants.DEFAULT_PRIVATE_KEY);
    }

    /**
     * Constructor
     *
     * @param  baseAuthorizationUrl Holds the error message
     * @param  authorizationEndpoint Holds the original exception object
     * @param  baseAccessTokenURL Holds the error message
     * @param  accessTokenEndpoint Holds the original exception object
     * @param  revokeTokenEndPoint Holds the error message
     * @param  apiAccessUrl Holds the original exception object
     * @param  privateKey Holds the error message
     */
    public APIExecutor(String baseAuthorizationUrl,
                       String authorizationEndpoint,
                       String baseAccessTokenURL,
                       String accessTokenEndpoint,
                       String revokeTokenEndPoint,
                       String apiAccessUrl,
                       String privateKey) {


        this.baseAuthorizationUrl = baseAuthorizationUrl;
        this.authorizationEndpoint = authorizationEndpoint;
        this.baseAccessTokenURL = baseAccessTokenURL;
        this.accessTokenEndpoint = accessTokenEndpoint;
        this.revokeTokenEndPoint = revokeTokenEndPoint;
        this.apiAccessUrl = apiAccessUrl;
        this.privateKey = privateKey;
        scopeList = new ScopeListBean();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public AuthorizationCodeRetrievalResult doLogOut() {
        AbstractIdentityServerAccessor idServerHandle = buildIdentityServerAccessorInstance();

        String code = "";

        try {
            idServerHandle.doLogOut();
        } catch (AuthorizationCodeRetrievalException e) {
            AuthorizationCodeRetrievalResult result = new AuthorizationCodeRetrievalResult(e);
            return result;
        }

        AuthorizationCodeRetrievalResult result = new AuthorizationCodeRetrievalResult(code);
        return result;
    }


    /**
     * {@inheritDoc}
     */
    public AuthorizationCodeRetrievalResult fetchAuthorizationCode(int customerNumber,
                                                                   String clientId,
                                                                   String redirectURI,
                                                                   String scopeListString) {

        AbstractIdentityServerAccessor idServerHandle = buildIdentityServerAccessorInstance();

        String code = "";

        try {
            code =
                    idServerHandle.requestAuthorizationCode(customerNumber,
                            clientId,
                            redirectURI,
                            scopeListString);
        } catch (AuthorizationCodeRetrievalException e) {
            AuthorizationCodeRetrievalResult result = new AuthorizationCodeRetrievalResult(e);
            return result;
        }

        AuthorizationCodeRetrievalResult result = new AuthorizationCodeRetrievalResult(code);
        return result;
    }


    /**
     * {@inheritDoc}
     */
    public AccessTokenRetrievalResult fetchAccessTokenWithAuthorizationCode(String clientId,
                                                                            String clientSecret,
                                                                            String authorizationCode,
                                                                            String redirectUri) {
        AbstractIdentityServerAccessor idServerHandle = buildIdentityServerAccessorInstance();
        AccessTokenResponseBean response;

        try {
            response =
                    idServerHandle.requestAccessTokenWithAuthorizationCode(clientId,
                            clientSecret,
                            authorizationCode,
                            redirectUri);

        } catch (AccessTokenRetrievalException e) {
            AccessTokenRetrievalResult result = this.buildAccessTokenRetrievalResult(e);
            return result;
        }

        AccessTokenRetrievalResult result = this.buildAccessTokenRetrievalResult(response);
        return result;

    }


    /**
     * {@inheritDoc}
     */
    public AccessTokenRetrievalResult fetchAccessTokenWithClientCredentials(String clientId,
                                                                            String clientSecret,
                                                                            String scopeListAsString) {
        AbstractIdentityServerAccessor idServerHandle = buildIdentityServerAccessorInstance();
        AccessTokenResponseBean response;

        try {
            response =
                    idServerHandle.requestAccessTokenWithClientCredentials(clientId,
                            clientSecret,
                            scopeListAsString);

        } catch (AccessTokenRetrievalException e) {
            AccessTokenRetrievalResult result = this.buildAccessTokenRetrievalResult(e);
            return result;
        }

        AccessTokenRetrievalResult result = this.buildAccessTokenRetrievalResult(response);
        return result;

    }


    /**
     * {@inheritDoc}
     */
    @Override
    public AccessTokenRetrievalResult fetchAccessTokenWithRefreshToken(String clientId, String clientSecret, String refreshToken) {
        AbstractIdentityServerAccessor idServerHandle = buildIdentityServerAccessorInstance();
        AccessTokenResponseBean response;

        try {
            response =
                    idServerHandle.requestAccessTokenWithRefreshToken(clientId, clientSecret, refreshToken);

        } catch (AccessTokenRetrievalException e) {
            AccessTokenRetrievalResult result = this.buildAccessTokenRetrievalResult(e);
            return result;
        }

        AccessTokenRetrievalResult result = this.buildAccessTokenRetrievalResult(response);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AccessTokenRetrievalResult revokeAccessToken(String clientId, String clientSecret, String accessToken) {
        AbstractIdentityServerAccessor idServerHandle = buildIdentityServerAccessorInstance();
        AccessTokenResponseBean response;

        try {
            response =
                    idServerHandle.revokeAccessToken(clientId, clientSecret, accessToken);

        } catch (AccessTokenRetrievalException e) {
            AccessTokenRetrievalResult result = this.buildAccessTokenRetrievalResult(e);
            return result;
        }

        AccessTokenRetrievalResult result = this.buildAccessTokenRetrievalResult(response);
        return result;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public AccessTokenRetrievalResult revokeRefreshToken(String clientId, String clientSecret, String refreshToken) {
        AbstractIdentityServerAccessor idServerHandle = buildIdentityServerAccessorInstance();
        AccessTokenResponseBean response;

        try {
            response =
                    idServerHandle.revokeRefreshToken(clientId, clientSecret, refreshToken);

        } catch (AccessTokenRetrievalException e) {
            AccessTokenRetrievalResult result = this.buildAccessTokenRetrievalResult(e);
            return result;
        }

        AccessTokenRetrievalResult result = this.buildAccessTokenRetrievalResult(response);
        return result;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public APICallResult<T> fetchTestCustomerList(String clientId, String clientSecret){
        AccessTokenRetrievalResult accessTokenResult =
                fetchAccessTokenWithClientCredentials(clientId, clientSecret,"public");
        if(accessTokenResult.isSuccessful()){
            String accessToken = accessTokenResult.getAccessToken();
            APICallResult<T> apiCallResult = doGet("v1/data/testcustomers", accessToken);
            return apiCallResult;
        } else {
            APICallResult.APICallResultBuilder builder = new APICallResult.APICallResultBuilder();
            APICallResult errResult = builder
                    .hasAPICallSucceeded(accessTokenResult.isSuccessful())
                    .error(accessTokenResult.getError())
                    .errorDescription(accessTokenResult.getErrorDetailsIfAny())
                    .build();
            return errResult;
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public  APICallResult<T> doGet(String endPoint,
                                String accessToken) {

        return doGet(endPoint, accessToken,1);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public  APICallResult<T> doGet(String endPoint,
                                String accessToken,
                                int languageId) {

        AbstractAPIGatewayAccessor<T> accessor = buildAPIGatewayAccessorInstance();

        try {
            APIResponseBean<T> response = accessor.doGet(endPoint, accessToken, languageId);
            APICallResult<T> result = this.buildSuccessfullyEndedAPICallResult(response);
            return result;
        } catch (GetRequestExecutionException e) {
            return this.processGetRequestExecutionException(e);
        } catch (SignatureGenerationException e) {
            return this.processSignatureGenerationException(e);
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public APICallResult<T> doGet(String endPoint,
                                 String accessToken,
                                 int languageId,
                                 String deviceId) {

        AbstractAPIGatewayAccessor<T> accessor = buildAPIGatewayAccessorInstance();

        try {
            APIResponseBean<T> response = accessor.doGet(endPoint, accessToken, languageId, deviceId);
            APICallResult<T> result = this.buildSuccessfullyEndedAPICallResult(response);
            return result;
        } catch (GetRequestExecutionException e) {
            return this.processGetRequestExecutionException(e);
        } catch (SignatureGenerationException e) {
            return this.processSignatureGenerationException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public  APICallResult<T> doGet(String endPoint,
                                String accessToken,
                                QueryParameterListBean queryParams){

        return doGet(endPoint, accessToken,1, queryParams);

    }


    /**
     * {@inheritDoc}
     */
    @Override
    public  APICallResult<T> doGet(String endPoint,
                                String accessToken,
                                int languageId,
                                QueryParameterListBean queryParams){

        AbstractAPIGatewayAccessor accessor = buildAPIGatewayAccessorInstance();

        try {
            APIResponseBean<T> response = accessor.doGet(endPoint, accessToken, languageId, queryParams);
            APICallResult<T> result = this.buildSuccessfullyEndedAPICallResult(response);
            return result;
        } catch (GetRequestExecutionException e) {
            return this.processGetRequestExecutionException(e);
        } catch (SignatureGenerationException e) {
            return this.processSignatureGenerationException(e);
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public  APICallResult<T> doGet(String endPoint,
                                String accessToken,
                                int languageId,
                                String deviceId,
                                QueryParameterListBean queryParams){

        AbstractAPIGatewayAccessor<T> accessor = buildAPIGatewayAccessorInstance();

        try {
            APIResponseBean<T> response = accessor.doGet(endPoint, accessToken, languageId, deviceId, queryParams);
            APICallResult<T> result = this.buildSuccessfullyEndedAPICallResult(response);
            return result;
        } catch (GetRequestExecutionException e) {
            return this.processGetRequestExecutionException(e);
        } catch (SignatureGenerationException e) {
            return this.processSignatureGenerationException(e);
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public  APICallResult<T> doPost(String endPoint,
                                 String accessToken,
                                 String jsonBodyAsString){

        AbstractAPIGatewayAccessor<T> accessor = buildAPIGatewayAccessorInstance();

        try {
            APIResponseBean<T> response = accessor.doPost(endPoint, accessToken, jsonBodyAsString);
            APICallResult<T> result = this.buildSuccessfullyEndedAPICallResult(response);
            return result;
        } catch (PostRequestExecutionException e) {
            return this.processPostRequestExecutionException(e);
        } catch (SignatureGenerationException e) {
            return this.processSignatureGenerationException(e);
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public  APICallResult<T> doPost(String endPoint,
                                           String accessToken,
                                           JsonObject jsonBody){

        AbstractAPIGatewayAccessor<T> accessor = buildAPIGatewayAccessorInstance();

        try {
            APIResponseBean<T> response = accessor.doPost(endPoint, accessToken, jsonBody);
            APICallResult<T> result = this.buildSuccessfullyEndedAPICallResult(response);
            return result;
        } catch (PostRequestExecutionException e) {
            return this.processPostRequestExecutionException(e);
        } catch (SignatureGenerationException e) {
            return this.processSignatureGenerationException(e);
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public  APICallResult<T> doPost(String endPoint,
                                 String accessToken,
                                 String jsonBodyAsString,
                                 int languageId,
                                 String deviceId){

        AbstractAPIGatewayAccessor<T> accessor = buildAPIGatewayAccessorInstance();

        try {
            APIResponseBean<T> response = accessor.doPost(endPoint, jsonBodyAsString, accessToken, languageId, deviceId);
            APICallResult<T> result = this.buildSuccessfullyEndedAPICallResult(response);
            return result;
        } catch (PostRequestExecutionException e) {
            return this.processPostRequestExecutionException(e);
        } catch (SignatureGenerationException e) {
            return this.processSignatureGenerationException(e);
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public  APICallResult<T> doPost(String endPoint,
                                 String accessToken,
                                 JsonObject jsonBody,
                                 int languageId,
                                 String deviceId) {

        AbstractAPIGatewayAccessor<T> accessor = buildAPIGatewayAccessorInstance();

        try {
            APIResponseBean<T> response = accessor.doPost(endPoint, jsonBody, accessToken, languageId, deviceId);
            APICallResult<T> result = this.buildSuccessfullyEndedAPICallResult(response);
            return result;
        } catch (PostRequestExecutionException e) {
            return this.processPostRequestExecutionException(e);
        } catch (SignatureGenerationException e) {
            return this.processSignatureGenerationException(e);
        }

    }


    /**
     * {@inheritDoc}
     */
    @Override
    public APIExecutor<T> addScope(ScopeType theScopeType) {
        scopeList.add(theScopeType);
        return this;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public APIExecutor<T> removeScope(ScopeType theScopeType) {
        scopeList.remove(theScopeType);
        return this;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isScopeListEmpty() {
        return scopeList.isEmpty();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public APIExecutor<T> clearScopeList() {
        scopeList.clear();
        return this;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public String getScopeListAsText() {
        if (isScopeListEmpty()) {
            return "";
        }
        return scopeList.toString();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void setScopeList(ScopeListBean scopeList){
        this.scopeList = scopeList;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public ScopeListBean getScopeList() {
        return scopeList;
    }

    public String getBaseAuthorizationUrl() { return baseAuthorizationUrl; }

    public String getAuthorizationEndpoint() { return authorizationEndpoint; }

    public String getBaseAccessTokenURL() { return baseAccessTokenURL; }

    public String getAccessTokenEndpoint() { return accessTokenEndpoint; }

    public String getRevokeTokenEndPoint() { return revokeTokenEndPoint; }

    public String getApiAccessUrl() {
        return apiAccessUrl;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    private AbstractIdentityServerAccessor buildIdentityServerAccessorInstance() {
        return new IdentityServerAccessor(getBaseAuthorizationUrl(),
                getAuthorizationEndpoint(),
                getBaseAccessTokenURL(),
                getAccessTokenEndpoint(),
                getRevokeTokenEndPoint());
    }

    private AbstractAPIGatewayAccessor<T> buildAPIGatewayAccessorInstance() {
        return new APIGatewayAccessor<T>(getApiAccessUrl(), getPrivateKey());
    }

    private AccessTokenRetrievalResult buildAccessTokenRetrievalResult(AccessTokenResponseBean response) {
        AccessTokenRetrievalResult result;
        if (response.getHttpStatusCode() != 200 ||
                (response.getError() != null && !response.getError().isEmpty()) ||
                (response.getErrorDescription() != null && !response.getErrorDescription().isEmpty())) {

            AccessTokenRetrievalResult.AccessTokenRetrievalResultBuilder builder
                    = new AccessTokenRetrievalResult.AccessTokenRetrievalResultBuilder();

            result = builder.error(response.getError())
                    .errorDescription(response.getErrorDetailsIfAny())
                    .hasSucceeded(false)
                    .httpStatusCode(response.getHttpStatusCode())
                    .build();
            return result;

        } else {
            AccessTokenRetrievalResult.AccessTokenRetrievalResultBuilder builder
                    = new AccessTokenRetrievalResult.AccessTokenRetrievalResultBuilder();
            result = builder.accessToken(response.getAccessToken())
                    .httpStatusCode(response.getHttpStatusCode())
                    .expiresIn(response.getExpiresIn())
                    .refreshToken(response.getRefreshToken())
                    .scope(response.getScope())
                    .tokenType(response.getTokenType())
                    .hasSucceeded(true)
                    .build();
            return result;
        }
    }

    private AccessTokenRetrievalResult buildAccessTokenRetrievalResult(AccessTokenRetrievalException exp) {
        AccessTokenRetrievalResult.AccessTokenRetrievalResultBuilder builder
                = new AccessTokenRetrievalResult.AccessTokenRetrievalResultBuilder();
        AccessTokenRetrievalResult result = builder.error(exp.getErrorResponse().getError())
                .errorDescription(exp.getErrorResponse().getErrorDescription())
                .hasSucceeded(false)
                .build();
        return result;

    }

    private APICallResult<T> buildSuccessfullyEndedAPICallResult(APIResponseBean<T> bean) {
        APICallResult.APICallResultBuilder builder = new APICallResult.APICallResultBuilder();
        APICallResult result = builder.httpStatusCode(bean.getHttpStatusCode())
                .apiResponseAsRawText(bean.getRawResponseContent())
                .apiResponseAsJSONObject(bean.getResponseInJSON())
                .responsePayload(bean.getResponsePayloadAsJavaObject())
                .hasAPICallSucceeded(bean.isSuccessful())
                .build();
        return result;
    }

    private APICallResult<T> processGetRequestExecutionException(GetRequestExecutionException e){
        APICallResult.APICallResultBuilder<T> builder = new APICallResult.APICallResultBuilder<T>();
        APICallResult result = builder.hasAPICallSucceeded(false)
                .getAPICallException(e)
                .postAPICallException(null)
                .signatureException(null)
                .error(e.getMessage())
                .errorDescription(e.getErrorDetailsIfAny())
                .build();
        return result;
    }

    private APICallResult<T> processPostRequestExecutionException(PostRequestExecutionException e){
        APICallResult.APICallResultBuilder<T> builder = new APICallResult.APICallResultBuilder<T>();
        APICallResult result = builder.hasAPICallSucceeded(false)
                .getAPICallException(null)
                .postAPICallException(e)
                .signatureException(null)
                .error(e.getMessage())
                .errorDescription(e.getErrorDetailsIfAny())
                .build();
        return result;
    }

    private APICallResult<T> processSignatureGenerationException(SignatureGenerationException e){
        APICallResult.APICallResultBuilder builder = new APICallResult.APICallResultBuilder();
        APICallResult result = builder.hasAPICallSucceeded(false)
                .getAPICallException(null)
                .postAPICallException(null)
                .signatureException(e)
                .error(e.getMessage())
                .build();
        return result;
    }
}
