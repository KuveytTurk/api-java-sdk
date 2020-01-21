
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
import tr.com.kuveytturk.api.client.sdk.model.callresult.APICallResult;
import tr.com.kuveytturk.api.client.sdk.model.callresult.AccessTokenRetrievalResult;
import tr.com.kuveytturk.api.client.sdk.model.callresult.AuthorizationCodeRetrievalResult;
import tr.com.kuveytturk.api.client.sdk.model.enums.ScopeType;
import tr.com.kuveytturk.api.client.sdk.model.misc.QueryParameterListBean;
import tr.com.kuveytturk.api.client.sdk.model.misc.ScopeListBean;

/**
 * Interface constituting a template for accessing IdentityServer and APIGateway
 *
 * @author      Fikri Aydemir
 * @version     1.0
 * @since       2020-01-11
 */

public interface APIExecutionInterface<T> {

    /**
     * Executes the logout logic from the IdentityServer
     *
     */
    public abstract AuthorizationCodeRetrievalResult doLogOut();


    /**
     * Accessor method for retrieving authorization code from the identity server.
     *
     * @param customerNumber  AccountNumber (i.e. Müşteri No)
     * @param clientId The ClientId that can be obtained from API Market
     * @param redirectURI  The redirectURI that is available at API Market application window
     * @param scopeListString List of Scopes
     * @return An instance of class AuthorizationCodeRetrievalResult
     */
    public abstract AuthorizationCodeRetrievalResult fetchAuthorizationCode(int customerNumber,
                                                                            String clientId,
                                                                            String redirectURI,
                                                                            String scopeListString);

    /**
     * Accessor method for retrieving access token from the identity server based on the authorization code flow.
     *
     * @param clientId The ClientId that can be obtained from API Market
     * @param clientSecret The ClientSecret that can be obtained from API Market
     * @param authorizationCode  The authorizationCode that is to be retrieved from the identity server by
     *                           using the method fetchAuthorizationCode.
     * @param redirectUri The redirectURI that is available at API Market application window
     * @return An instance of class AccessTokenRetrievalResult
     */
    public abstract AccessTokenRetrievalResult fetchAccessTokenWithAuthorizationCode(String clientId,
                                                                                     String clientSecret,
                                                                                     String authorizationCode,
                                                                                     String redirectUri);

    /**
     * Accessor method for retrieving access token from the identity server based on the client credentials flow.
     *
     * @param clientId The ClientId that can be obtained from API Market
     * @param clientSecret The ClientSecret that can be obtained from API Market
     * @param scopeListAsString List of Scopes as ane line text where each scope is seperated by a space character
     * @return An instance of class AccessTokenRetrievalResult
     */
    public abstract AccessTokenRetrievalResult fetchAccessTokenWithClientCredentials(String clientId,
                                                                            String clientSecret,
                                                                            String scopeListAsString);

    /**
     * Accessor method for retrieving access token from the identity server by suing a refresh token.
     *
     * @param clientId The ClientId that can be obtained from API Market
     * @param clientSecret The ClientSecret that can be obtained from API Market
     * @param refreshToken The refresh token that is available as a field in an instance of class AccessTokenRetrieval
     * @return An instance of class AccessTokenRetrievalResult
     */
    public abstract AccessTokenRetrievalResult fetchAccessTokenWithRefreshToken(String clientId,
                                                                                String clientSecret,
                                                                                String refreshToken);

    /**
     * Accessor method for revoking an access token that is previously retrieved from the identity server by using one
     * of the other the accessors methods for retrieving access token in this class.
     *
     * @param clientId The ClientId that can be obtained from API Market
     * @param clientSecret The ClientSecret that can be obtained from API Market
     * @param accessToken The access token that is retrieved by using one of the accessors methods in this class
     * @return An instance of class AccessTokenRetrievalResult
     */
    public abstract AccessTokenRetrievalResult revokeAccessToken(String clientId, String clientSecret, String accessToken);

    /**
     * Accessor method for revoking an refresh token that is previously retrieved from the identity server by using one
     * of the other the accessors methods for retrieving access token in this class.
     *
     * @param clientId The ClientId that can be obtained from API Market
     * @param clientSecret The ClientSecret that can be obtained from API Market
     * @param refreshToken The refresh token
     * @return An instance of class AccessTokenRetrievalResult
     */
    public abstract AccessTokenRetrievalResult revokeRefreshToken(String clientId, String clientSecret, String refreshToken);

    /**
     * Accessor method for retrieving API Banking test customer numbers
     *
     * @param clientId The ClientId that can be obtained from API Market
     * @param clientSecret The ClientSecret that can be obtained from API Market
     * @return An instance of class APICallResult including the list of test customers in its payload
     */
    public abstract APICallResult<T> fetchTestCustomerList(String clientId, String clientSecret);

    /**
     * Method for executing a GET Request at API Gateway
     *
     * @param endPoint API Endpoint (i.e. method) that peforms a specific read task at API Gateway (e.g. fetching account trantions)
     * @param accessToken The access token that is retrieved by using one of the accessors methods in this class
     * @return An instance of class APICallResult including the result in its payload
     */
    public abstract APICallResult<T> doGet(String endPoint, String accessToken);

    /**
     * Method for executing a GET Request at API Gateway
     *
     * @param endPoint API Endpoint (i.e. method) that peforms a specific read task at API Gateway (e.g. fetching account trantions)
     * @param accessToken The access token that is retrieved by using one of the accessors methods in this class
     * @param languageId The languageId parameter which takes 1 for Turkish and 2 for English
     * @return An instance of class APICallResult including the result in its payload
     */
    public abstract APICallResult<T> doGet(String endPoint, String accessToken, int languageId);

    /**
     * Method for executing a GET Request at API Gateway
     *
     * @param endPoint API Endpoint (i.e. method) that performs a specific read task at API Gateway (e.g. fetching account trantions)
     * @param accessToken The access token that is retrieved by using one of the accessors methods in this class
     * @param languageId The languageId parameter which takes 1 for Turkish and 2 for English
     * @param deviceId The deviceId parameter which can contain a dummy text
     * @return An instance of class APICallResult including the result in its payload
     */
    public abstract APICallResult<T> doGet(String endPoint, String accessToken, int languageId, String deviceId);

    /**
     * Method for executing a GET Request at API Gateway
     *
     * @param endPoint API Endpoint (i.e. method) that performs a specific read task at API Gateway (e.g. fetching account trantions)
     * @param accessToken The access token that is retrieved by using one of the accessors methods in this class
     * @param queryParams the query parameters
     * @return An instance of class APICallResult including the result in its payload
     */
    public abstract APICallResult<T> doGet(String endPoint, String accessToken, QueryParameterListBean queryParams);

    /**
     * Method for executing a GET Request at API Gateway
     *
     * @param endPoint API Endpoint (i.e. method) that performs a specific read task at API Gateway (e.g. fetching account trantions)
     * @param accessToken The access token that is retrieved by using one of the accessors methods in this class
     * @param languageId The languageId parameter which takes 1 for Turkish and 2 for English
     * @param queryParams the query parameters
     * @return An instance of class APICallResult including the result in its payload
     */
    public abstract APICallResult<T> doGet(String endPoint, String accessToken, int languageId, QueryParameterListBean queryParams);

    /**
     * Method for executing a GET Request at API Gateway
     *
     * @param endPoint API Endpoint (i.e. method) that performs a specific read task at API Gateway (e.g. fetching account trantions)
     * @param accessToken The access token that is retrieved by using one of the accessors methods in this class
     * @param languageId The languageId parameter which takes 1 for Turkish and 2 for English
     * @param deviceId The deviceId parameter which can contain a dummy text
     * @param queryParams the query parameters
     * @return An instance of class APICallResult including the result in its payload
     */
    public abstract APICallResult<T> doGet(String endPoint, String accessToken, int languageId, String deviceId, QueryParameterListBean queryParams);

    /**
     * Method for executing a POST Request at API Gateway
     *
     * @param endPoint API Endpoint (i.e. method) that performs a specific CRUD task at API Gateway (e.g. make money transfer to a customer account)
     * @param accessToken The access token that is retrieved by using one of the accessors methods in this class
     * @param jsonBodyAsString The body text in JSON format
     * @return An instance of class APICallResult including the result in its payload
     */
    public abstract APICallResult<T> doPost(String endPoint, String accessToken, String jsonBodyAsString);

    /**
     * Method for executing a POST Request at API Gateway
     *
     * @param endPoint API Endpoint (i.e. method) that performs a specific CRUD task at API Gateway (e.g. make money transfer to a customer account)
     * @param accessToken The access token that is retrieved by using one of the accessors methods in this class
     * @param jsonBody The body object of type JsonObject
     * @return An instance of class APICallResult including the result in its payload
     */
    public  abstract APICallResult<T> doPost(String endPoint, String accessToken, JsonObject jsonBody);

    /**
     * Method for executing a POST Request at API Gateway
     *
     * @param endPoint API Endpoint (i.e. method) that performs a specific CRUD task at API Gateway (e.g. make money transfer to a customer account)
     * @param accessToken The access token that is retrieved by using one of the accessors methods in this class
     * @param jsonBodyAsString The body text in JSON format
     * @param deviceId The deviceId parameter which can contain a dummy text
     * @return An instance of class APICallResult including the result in its payload
     */
    public  abstract APICallResult<T> doPost(String endPoint, String accessToken, String jsonBodyAsString, int languageId, String deviceId);

    /**
     * Method for executing a POST Request at API Gateway
     *
     * @param endPoint API Endpoint (i.e. method) that performs a specific CRUD task at API Gateway (e.g. make money transfer to a customer account)
     * @param accessToken The access token that is retrieved by using one of the accessors methods in this class
     * @param jsonBody The body object of type JsonObject
     * @param deviceId The deviceId parameter which can contain a dummy text
     * @return An instance of class APICallResult including the result in its payload
     */
    public  abstract APICallResult<T> doPost(String endPoint, String accessToken, JsonObject jsonBody, int languageId, String deviceId);

    /**
     * Method for adding scope
     *
     * @param theScopeType API Endpoint (i.e. method) that performs a specific CRUD task at API Gateway (e.g. make money transfer to a customer account)
     * @return An instance of class APIExecutor to perform builder design pattern
     */
    public abstract APIExecutor<T> addScope(ScopeType theScopeType);

    /**
     * Method for removing scope
     *
     * @param theScopeType API Endpoint (i.e. method) that performs a specific CRUD task at API Gateway (e.g. make money transfer to a customer account)
     * @return An instance of class APIExecutor to perform builder design pattern
     */
    public abstract APIExecutor<T> removeScope(ScopeType theScopeType);

    /**
     * Method for setting scope list
     *
     * @param scopeList the ScopeLİst object
     */
    public abstract void setScopeList(ScopeListBean scopeList);

    /**
     * Method for getting scope list
     *
     * @return An instance of class ScopeListBean
     */
    public abstract ScopeListBean getScopeList();

    /**
     * Method for testing if scopeList is empty
     *
     * @return TRUE if scope list is empty. FALSE, otherwise.
     */
    public abstract boolean isScopeListEmpty();

    /**
     * Method for removing all elements in the scope list
     *
     * @return An instance of class APIExecutor to perform builder design pattern
     */
    public abstract APIExecutor<T> clearScopeList();

    /**
     * Method for removing all elements in the scope list
     *
     * @return A String object including the scopes as one line text where each scope is separated by a space character
     */
    public abstract String getScopeListAsText();
}
