/*
 * Copyright (c) 2020
 * KUVEYT TÜRK PARTICIPATION BANK INC.
 *
 * Author: Fikri Aydemir
 *
 * Project: Java SDK for Accessing API Endpoints
 */

package tr.com.kuveytturk.api.client.sdk.services.retrofit;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

import java.util.Map;


public interface GetServiceRetrofitInterface {

    @GET("{endPoint}")
    Call<ResponseBody> get(@Path("endPoint") String endPoint,
                           @Header("Content-Type") String contentType,
                           @Header("Authorization") String authorizationBearer,
                           @Header("Signature") String signature,
                           @Header("LanguageId") int languageId,
                           @QueryMap Map<String, Object> queryParams);

    @GET("{endPoint}")
    Call<ResponseBody> get(@Path("endPoint") String endPoint,
                           @Header("Content-Type") String contentType,
                           @Header("Authorization") String authorizationBearer,
                           @Header("Signature") String signature,
                           @Header("LanguageId") int languageId);

    @GET("{endPoint}")
    Call<ResponseBody> get(@Path("endPoint") String endPoint,
                           @Header("Content-Type") String contentType,
                           @Header("Authorization") String authorizationBearer,
                           @Header("Signature") String signature,
                           @Header("LanguageId") int languageId,
                           @Header("DeviceId") String deviceId);


    @GET("{endPoint}")
    Call<ResponseBody> get(@Path("endPoint") String endPoint,
                           @Header("Content-Type") String contentType,
                           @Header("Authorization") String authorizationBearer,
                           @Header("Signature") String signature,
                           @Header("LanguageId") int languageId,
                           @Header("DeviceId") String deviceId,
                           @QueryMap Map<String, Object> queryParams);

}
