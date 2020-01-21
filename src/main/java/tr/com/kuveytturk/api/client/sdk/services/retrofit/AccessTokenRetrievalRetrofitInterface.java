/*
 * Copyright (c) 2020
 * KUVEYT TÜRK PARTICIPATION BANK INC.
 *
 * Author: Fikri Aydemir
 *
 * Project: Java SDK for Accessing API Endpoints
 */

package tr.com.kuveytturk.api.client.sdk.services.retrofit;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;
import tr.com.kuveytturk.api.client.sdk.model.response.AccessTokenResponseBean;


public interface AccessTokenRetrievalRetrofitInterface {

    @POST("{endPoint}")
    @FormUrlEncoded
    Call<AccessTokenResponseBean> requestAccessTokenWithAuthorizationCode(
            @Path("endPoint") String endPoint,
            @Field("client_id") String clientId,
            @Field("client_secret") String clientSecret,
            @Field("grant_type") String grantType,
            @Field("code") String code,
            @Field("redirect_uri") String redirectUri
    );

    @POST("{endPoint}")
    @FormUrlEncoded
    Call<AccessTokenResponseBean> requestAccessTokenWithClientCredentials(
                    @Path("endPoint") String endPoint,
                    @Field("client_id") String clientId,
                    @Field("client_secret") String clientSecret,
                    @Field("grant_type") String grantType,
                    @Field("scope") String scope
            );

    @POST("{endPoint}")
    @FormUrlEncoded
    Call<AccessTokenResponseBean> requestAccessTokenWithRefreshToken(
            @Path("endPoint") String endPoint,
            @Field("client_id") String clientId,
            @Field("client_secret") String clientSecret,
            @Field("grant_type") String grantType,
            @Field("refresh_token") String refreshToken
    );

    @POST("{endPoint}")
    @FormUrlEncoded
    Call<AccessTokenResponseBean> revokeAccessToken(
            @Path("endPoint") String endPoint,
            @Field("client_id") String clientId,
            @Field("client_secret") String clientSecret,
            @Field("token") String accessToken,
            @Field("token_type_hint") String tokenTypeHint
    );

    @POST("{endPoint}")
    @FormUrlEncoded
    Call<AccessTokenResponseBean> revokeRefreshToken(
            @Path("endPoint") String endPoint,
            @Field("client_id") String clientId,
            @Field("client_secret") String clientSecret,
            @Field("token") String accessToken,
            @Field("token_type_hint") String tokenTypeHint
    );
}

