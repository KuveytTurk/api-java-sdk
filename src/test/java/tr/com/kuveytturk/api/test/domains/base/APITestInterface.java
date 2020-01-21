/*
 * Copyright (c) 2020
 * KUVEYT TÜRK PARTICIPATION BANK INC.
 *
 * Author: Fikri Aydemir
 *
 * Project: Java SDK for Accessing API Endpoints
 */

package tr.com.kuveytturk.api.test.domains.base;

import tr.com.kuveytturk.api.client.sdk.model.callresult.AccessTokenRetrievalResult;
import tr.com.kuveytturk.api.client.sdk.model.callresult.AuthorizationCodeRetrievalResult;
import tr.com.kuveytturk.api.client.sdk.model.misc.ScopeListBean;

import java.util.List;

/**
 * Interface representing a high level template that defines the routines
 * for getting authorization code and access token which are, in turn, can
 * be utilized during test development.
 *
 * @author      Fikri Aydemir
 * @version     1.0
 * @since       2020-01-12
 */
public interface APITestInterface {

    /**
     * Utility method for retrieving test customers by using default clientId and secret
     *
     * @return A list of test customers
     */
    List<Integer> fetchTestCustomers();

    /**
     * Utility method for retrieving test customers
     *
     * @param clientId The ClientId that can be obtained from API Market
     * @param clientId The ClientSecret that can be obtained from API Market
     * @return An instance of class AuthorizationCodeRetrievalResult
     */
    List<Integer> fetchTestCustomers(String clientId, String clientSecret);

    /**
     * Utility method for retrieving authorization code from the identity server.
     *
     * @param testCustomerId  AccountNumber (i.e. Müşteri No)
     * @return An instance of class AuthorizationCodeRetrievalResult
     */
    AuthorizationCodeRetrievalResult fetchAuthorizationCode(int testCustomerId);

    /**
     * Utility method for retrieving authorization code from the identity server.
     *
     * @param testCustomerId  AccountNumber (i.e. Müşteri No)
     * @param scopeList List of Scope Beans
     * @return An instance of class AuthorizationCodeRetrievalResult
     */
    AuthorizationCodeRetrievalResult fetchAuthorizationCode(int testCustomerId, ScopeListBean scopeList);

    /**
     * Utility method for retrieving access token from the identity server based on
     * the authorization code flow.
     *
     * @param testCustomerId  AccountNumber (i.e. Müşteri No)
     * @return An instance of class AccessTokenRetrievalResult
     */
    AccessTokenRetrievalResult fetchAccessTokenWithAuthorizationCodeFlow(int testCustomerId);

    /**
     * Utility method for retrieving access token from the identity server based on
     * the authorization code flow.
     *
     * @param testCustomerId  AccountNumber (i.e. Müşteri No)
     * @param scopeList List of Scope Beans
     * @return An instance of class AccessTokenRetrievalResult
     */
    AccessTokenRetrievalResult fetchAccessTokenWithAuthorizationCodeFlow(int testCustomerId, ScopeListBean scopeList);

    /**
     * Utility method for retrieving access token from the identity server based on
     * the client credentials flow by using default scope list.
     *
     * @return An instance of class AccessTokenRetrievalResult
     */
    AccessTokenRetrievalResult fetchAccessTokenWithClientCredentialsFlow();

    /**
     * Utility method for retrieving access token from the identity server based on
     * the client credentials flow.
     *
     * @param scopeList List of Scope Beans
     * @return An instance of class AccessTokenRetrievalResult
     */
    AccessTokenRetrievalResult fetchAccessTokenWithClientCredentialsFlow(ScopeListBean scopeList);

    /**
     * Utility method for retrieving access token from the identity server by using a refresh token
     *
     * @param refreshToken the Refresh token that has been retrieved earlier by making a access token request
     *        through any of the reşated utility methods in this class.
     * @return An instance of class AccessTokenRetrievalResult
     */
    AccessTokenRetrievalResult fetchAccessTokenWithRefreshToken(String refreshToken);
}
