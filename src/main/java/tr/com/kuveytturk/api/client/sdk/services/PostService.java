/*
 * Copyright (c) 2020
 * KUVEYT TÜRK PARTICIPATION BANK INC.
 *
 * Author: Fikri Aydemir
 *
 * Project: Java SDK for Accessing API Endpoints
 */

package tr.com.kuveytturk.api.client.sdk.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tr.com.kuveytturk.api.client.sdk.model.response.APIResponseBean;
import tr.com.kuveytturk.api.client.sdk.services.base.PostServiceAPI;
import tr.com.kuveytturk.api.client.sdk.services.retrofit.PostServiceRetrofitInterface;
import tr.com.kuveytturk.api.client.sdk.util.Constants;
import tr.com.kuveytturk.api.client.sdk.util.PostRequestExecutionException;

/**
 * Concrete class for executing POST requests
 *
 * @author      Fikri Aydemir
 * @version     1.0
 * @since       2020-01-12
 */
public class PostService<T> extends AbstractService<T> implements PostServiceAPI<T> {
    public PostService() {
        this(Constants.BASE_API_ACCESS_URL);
    }

    public PostService(String theApiAccessUrl) {
        super.setApiAccessUrl(theApiAccessUrl);
    }

    @Override
    public APIResponseBean<T> doPost(String endPoint,
                                     JsonObject jsonBody,
                                     String contentType,
                                     String authorizationBearer,
                                     String signature) throws PostRequestExecutionException {
        try {
            Gson gson = new GsonBuilder().setLenient().create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(super.getApiAccessUrl())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            PostServiceRetrofitInterface apiService = retrofit.create(PostServiceRetrofitInterface.class);

            Call<JsonObject> call = apiService.post(endPoint, jsonBody, contentType, authorizationBearer, signature);
            APIResponseBean<T> theResponseBean = super.sendPostRequestToAPIGateway(call);
            return theResponseBean;
        } catch (PostRequestExecutionException e){
            throw e;
        }
        catch (Exception e){
            PostRequestExecutionException exp = new PostRequestExecutionException(e.getMessage(), e ,"POST Request execution failed!");
            throw exp;
        }

    }

    @Override
    public APIResponseBean<T> doPost(String endPoint,
                                  JsonObject jsonBody,
                                  String contentType,
                                  String authorizationBearer,
                                  String signature,
                                  int languageId,
                                  String deviceId) throws PostRequestExecutionException {
        try {
            Gson gson = new GsonBuilder().setLenient().create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(super.getApiAccessUrl())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            PostServiceRetrofitInterface apiService = retrofit.create(PostServiceRetrofitInterface.class);

            Call<JsonObject> call =
                    apiService.post(endPoint, jsonBody, contentType, authorizationBearer, signature, languageId, deviceId);
            APIResponseBean<T> theResponseBean = super.sendPostRequestToAPIGateway(call);
            return theResponseBean;
        } catch (PostRequestExecutionException e){
            throw e;
        }
        catch (Exception e){
            PostRequestExecutionException exp = new PostRequestExecutionException(e.getMessage(), e, "POST request execution failed!");
            throw exp;
        }
    }
}
