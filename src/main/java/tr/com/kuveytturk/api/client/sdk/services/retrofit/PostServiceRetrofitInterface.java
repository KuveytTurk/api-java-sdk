/*
 * Copyright (c) 2020
 * KUVEYT TÜRK PARTICIPATION BANK INC.
 *
 * Author: Fikri Aydemir
 *
 * Project: Java SDK for Accessing API Endpoints
 */

package tr.com.kuveytturk.api.client.sdk.services.retrofit;


import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PostServiceRetrofitInterface{

    @POST("{endPoint}")
    Call<JsonObject> post(@Path("endPoint") String endPoint,
                          @Body JsonObject jsonBody,
                          @Header("Content-Type") String contentType,
                          @Header("Authorization") String authorizationBearer,
                          @Header("Signature") String signature);

    @POST("{endPoint}")
    Call<JsonObject> post(@Path("endPoint") String endPoint,
                          @Body JsonObject jsonBody,
                          @Header("Content-Type") String contentType,
                          @Header("Authorization") String authorizationBearer,
                          @Header("Signature") String signature,
                          @Header("LanguageId") int languageId,
                          @Header("DeviceId") String deviceId);
}

