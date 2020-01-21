/*
 * Copyright (c) 2020
 * KUVEYT TÜRK PARTICIPATION BANK INC.
 *
 * Author: Fikri Aydemir
 *
 * Project: Java SDK for Accessing API Endpoints
 */

package tr.com.kuveytturk.api.client.sdk.services.base;

import tr.com.kuveytturk.api.client.sdk.model.response.AccessTokenResponseBean;
import tr.com.kuveytturk.api.client.sdk.util.AccessTokenRetrievalException;

/**
 * Service Interface for retrieving access tokens from the Identity Server
 *
 * @author      Fikri Aydemir
 * @version     1.0
 * @since       2020-01-12
 */
public interface AccessTokenRetrievalServiceAPI {
    AccessTokenResponseBean requestAccessTokenWithAuthorizationCode(
            String clientId,
            String clientSecret,
            String code,
            String redirectUri) throws AccessTokenRetrievalException;

    AccessTokenResponseBean requestAccessTokenWithClientCredentials(
            String clientId,
            String clientSecret,
            String scope) throws AccessTokenRetrievalException;

    AccessTokenResponseBean requestAccessTokenWithRefreshToken(
            String clientId,
            String clientSecret,
            String refreshToken) throws AccessTokenRetrievalException;

    AccessTokenResponseBean revokeAccessToken(String clientId, String clientSecret, String accessToken) throws AccessTokenRetrievalException;

    AccessTokenResponseBean revokeRefreshToken(String clientId, String clientSecret, String refreshToken) throws AccessTokenRetrievalException;

}
