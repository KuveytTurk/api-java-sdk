/*
 * Copyright (c) 2020
 * KUVEYT TÜRK PARTICIPATION BANK INC.
 *
 * Author: Fikri Aydemir
 *
 * Project: Java SDK for Accessing API Endpoints
 */

package tr.com.kuveytturk.api.client.sdk.services;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tr.com.kuveytturk.api.client.sdk.model.enums.GrantType;
import tr.com.kuveytturk.api.client.sdk.model.response.AccessTokenResponseBean;
import tr.com.kuveytturk.api.client.sdk.services.base.AccessTokenRetrievalServiceAPI;
import tr.com.kuveytturk.api.client.sdk.services.retrofit.AccessTokenRetrievalRetrofitInterface;
import tr.com.kuveytturk.api.client.sdk.util.AccessTokenRetrievalException;
import tr.com.kuveytturk.api.client.sdk.util.Constants;

import java.io.IOException;

/**
 * Concrete class for executing access token retrieval requests
 *
 * @author      Fikri Aydemir
 * @version     1.0
 * @since       2020-01-12
 */
public class AccessTokenRetrievalService implements AccessTokenRetrievalServiceAPI {
    private String baseAccessTokenURL, accessTokenEndpoint, revokeTokenEndPoint;

    public AccessTokenRetrievalService(){
        this(Constants.BASE_ACCESS_TOKEN_URL,  Constants.ACCESS_TOKEN_ENDPOINT, Constants.REVOKE_TOKEN_ENDPOINT);
    }

    public AccessTokenRetrievalService(String baseAccessTokenURL, String accessTokenEndpoint, String revokeTokenEndPoint){
        this.baseAccessTokenURL = baseAccessTokenURL;
        this.accessTokenEndpoint = accessTokenEndpoint;
        this.revokeTokenEndPoint = revokeTokenEndPoint;
    }

    @Override
    public AccessTokenResponseBean requestAccessTokenWithAuthorizationCode(
            String clientId,
            String clientSecret,
            String code,
            String redirectUri) throws AccessTokenRetrievalException {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getBaseAccessTokenURL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Call<AccessTokenResponseBean> call;
        AccessTokenRetrievalRetrofitInterface apiService;

        //Make the web service request
        apiService = retrofit.create(AccessTokenRetrievalRetrofitInterface.class);
        call = apiService.requestAccessTokenWithAuthorizationCode(
                getAccessTokenEndpoint(),
                clientId,
                clientSecret,
                GrantType.AUTHORIZATION_CODE.toString(),
                code,
                redirectUri
        );

        Response<AccessTokenResponseBean> backEndResponse = null;

        try {
            backEndResponse = call.execute();

            if (backEndResponse != null && backEndResponse.isSuccessful()) {
                AccessTokenResponseBean responseBody = backEndResponse.body();
                responseBody.setHttpStatusCode(backEndResponse.code());
                responseBody.setHttpMessage(backEndResponse.message());
                return responseBody;
            } else {
                AccessTokenResponseBean errResponse = null;
                errResponse = new AccessTokenResponseBean();
                errResponse.setError(backEndResponse.message());
                errResponse.setErrorDescription(backEndResponse.errorBody().string());
                errResponse.setHttpStatusCode(backEndResponse.code());
                errResponse.setHttpMessage(backEndResponse.message());
                return errResponse;
            }
        } catch (IOException e) {
            AccessTokenResponseBean errResponseBody = new AccessTokenResponseBean();
            errResponseBody.setError(e.getLocalizedMessage());
            errResponseBody.setErrorDescription("IO Exception occurred while making access token request!");
            AccessTokenRetrievalException exp = new AccessTokenRetrievalException(e.getMessage(), e, errResponseBody);
            throw exp;
        }

    }

    @Override
    public AccessTokenResponseBean requestAccessTokenWithClientCredentials(
            String clientId,
            String clientSecret,
            String scope) throws AccessTokenRetrievalException {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getBaseAccessTokenURL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Call<AccessTokenResponseBean> call;
        AccessTokenRetrievalRetrofitInterface apiService;

        //Make the web service request
        apiService = retrofit.create(AccessTokenRetrievalRetrofitInterface.class);
        call = apiService.requestAccessTokenWithClientCredentials(
                getAccessTokenEndpoint(),
                clientId,
                clientSecret,
                GrantType.CLIENT_CREDENTIALS.toString(),
                scope
        );

        Response<AccessTokenResponseBean> backEndResponse = null;

        try {
            backEndResponse = call.execute();

            if (backEndResponse != null && backEndResponse.isSuccessful()) {
                AccessTokenResponseBean responseBody = backEndResponse.body();
                responseBody.setHttpStatusCode(backEndResponse.code());
                responseBody.setHttpMessage(backEndResponse.message());
                return responseBody;
            } else {
                AccessTokenResponseBean errResponse = null;
                errResponse = new AccessTokenResponseBean();
                errResponse.setError(backEndResponse.message());
                errResponse.setErrorDescription(backEndResponse.errorBody().string());
                errResponse.setHttpStatusCode(backEndResponse.code());
                errResponse.setHttpMessage(backEndResponse.message());
                return errResponse;
            }
        } catch (IOException e) {
            AccessTokenResponseBean errResponseBody = new AccessTokenResponseBean();
            errResponseBody.setError(e.getLocalizedMessage());
            errResponseBody.setErrorDescription("IO Exception occurred while making access token request!");
            AccessTokenRetrievalException exp = new AccessTokenRetrievalException(e.getMessage(), e, errResponseBody);
            throw exp;
        }


    }

    @Override
    public AccessTokenResponseBean requestAccessTokenWithRefreshToken(
            String clientId,
            String clientSecret,
            String refreshToken) throws AccessTokenRetrievalException {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getBaseAccessTokenURL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Call<AccessTokenResponseBean> call;
        AccessTokenRetrievalRetrofitInterface apiService;

        apiService = retrofit.create(AccessTokenRetrievalRetrofitInterface.class);
        call = apiService.requestAccessTokenWithRefreshToken(
                getAccessTokenEndpoint(),
                clientId,
                clientSecret,
                GrantType.REFRESH_TOKEN.toString(),
                refreshToken);

        Response<AccessTokenResponseBean> backEndResponse = null;

        try {
            backEndResponse = call.execute();

            if (backEndResponse != null && backEndResponse.isSuccessful()) {
                AccessTokenResponseBean responseBody = backEndResponse.body();
                responseBody.setHttpStatusCode(backEndResponse.code());
                responseBody.setHttpMessage(backEndResponse.message());
                return responseBody;
            } else {
                AccessTokenResponseBean errResponse = null;
                errResponse = new AccessTokenResponseBean();
                errResponse.setError(backEndResponse.message());
                errResponse.setErrorDescription(backEndResponse.errorBody().string());
                errResponse.setHttpStatusCode(backEndResponse.code());
                errResponse.setHttpMessage(backEndResponse.message());
                return errResponse;
            }
        } catch (IOException e) {
            AccessTokenResponseBean errResponseBody = new AccessTokenResponseBean();
            errResponseBody.setError(e.getLocalizedMessage());
            errResponseBody.setErrorDescription("IO Exception occurred while making access token request!");
            AccessTokenRetrievalException exp = new AccessTokenRetrievalException(e.getMessage(), e, errResponseBody);
            throw exp;
        }

    }

    @Override
    public AccessTokenResponseBean revokeAccessToken(String clientId, String clientSecret, String accessToken) throws AccessTokenRetrievalException {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getBaseAccessTokenURL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Call<AccessTokenResponseBean> call;
        AccessTokenRetrievalRetrofitInterface apiService;


        //Make the web service request
        apiService = retrofit.create(AccessTokenRetrievalRetrofitInterface.class);
        call = apiService.revokeAccessToken(
                getRevokeTokenEndPoint(),
                clientId,
                clientSecret,
                accessToken,
                GrantType.REFRESH_TOKEN.toString());

        Response<AccessTokenResponseBean> backEndResponse = null;

        try {
            backEndResponse = call.execute();

            if (backEndResponse != null && backEndResponse.isSuccessful()) {
                AccessTokenResponseBean responseBody = backEndResponse.body();
                responseBody.setHttpStatusCode(backEndResponse.code());
                responseBody.setHttpMessage(backEndResponse.message());
                return responseBody;
            } else {
                AccessTokenResponseBean errResponse = null;
                errResponse = new AccessTokenResponseBean();
                errResponse.setError(backEndResponse.message());
                errResponse.setErrorDescription(backEndResponse.errorBody().string());
                errResponse.setHttpStatusCode(backEndResponse.code());
                errResponse.setHttpMessage(backEndResponse.message());
                return errResponse;
            }
        } catch (IOException e) {
            AccessTokenResponseBean errResponseBody = new AccessTokenResponseBean();
            errResponseBody.setError(e.getLocalizedMessage());
            errResponseBody.setErrorDescription("IO Exception occurred while revoking access token!");
            AccessTokenRetrievalException exp = new AccessTokenRetrievalException(e.getMessage(), e, errResponseBody);
            throw exp;
        }


    }

    @Override
    public AccessTokenResponseBean revokeRefreshToken(String clientId, String clientSecret, String refreshToken) throws AccessTokenRetrievalException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getBaseAccessTokenURL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Call<AccessTokenResponseBean> call;
        AccessTokenRetrievalRetrofitInterface apiService;


        //Make the web service request
        apiService = retrofit.create(AccessTokenRetrievalRetrofitInterface.class);
        call = apiService.revokeRefreshToken(
                getRevokeTokenEndPoint(),
                clientId,
                clientSecret,
                refreshToken,
                GrantType.REFRESH_TOKEN.toString());

        Response<AccessTokenResponseBean> backEndResponse = null;

        try {
            backEndResponse = call.execute();

            if (backEndResponse != null && backEndResponse.isSuccessful()) {
                AccessTokenResponseBean responseBody = backEndResponse.body();
                responseBody.setHttpStatusCode(backEndResponse.code());
                responseBody.setHttpMessage(backEndResponse.message());
                return responseBody;
            } else {
                AccessTokenResponseBean errResponse = null;
                errResponse = new AccessTokenResponseBean();
                errResponse.setError(backEndResponse.message());
                errResponse.setErrorDescription(backEndResponse.errorBody().string());
                errResponse.setHttpStatusCode(backEndResponse.code());
                errResponse.setHttpMessage(backEndResponse.message());
                return errResponse;
            }
        } catch (IOException e) {
            AccessTokenResponseBean errResponseBody = new AccessTokenResponseBean();
            errResponseBody.setError(e.getLocalizedMessage());
            errResponseBody.setErrorDescription("Error occurred while revoking the refresh token!");
            AccessTokenRetrievalException exp = new AccessTokenRetrievalException(e.getMessage(), e, errResponseBody);
            throw exp;
        }


    }

    public String getBaseAccessTokenURL() {
        return baseAccessTokenURL;
    }

    public String getAccessTokenEndpoint(){
        return accessTokenEndpoint;
    }

    public String getRevokeTokenEndPoint() {
        return revokeTokenEndPoint;
    }
}

