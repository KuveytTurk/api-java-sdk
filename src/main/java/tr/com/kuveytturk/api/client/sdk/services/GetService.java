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
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tr.com.kuveytturk.api.client.sdk.model.response.APIResponseBean;
import tr.com.kuveytturk.api.client.sdk.services.base.GetServiceAPI;
import tr.com.kuveytturk.api.client.sdk.services.retrofit.GetServiceRetrofitInterface;
import tr.com.kuveytturk.api.client.sdk.util.Constants;
import tr.com.kuveytturk.api.client.sdk.util.GetRequestExecutionException;

import java.util.Map;

/**
 * Concrete class for executing GET requests
 *
 * @author      Fikri Aydemir
 * @version     1.0
 * @since       2020-01-12
 */
public class GetService<T> extends AbstractService<T> implements GetServiceAPI<T> {

    public GetService() {
        this(Constants.BASE_API_ACCESS_URL);
    }

    public GetService(String theApiAccessUrl) {
        super.setApiAccessUrl(theApiAccessUrl);
    }

    @Override
    public APIResponseBean<T> doGet(String endPoint,
                                    String contentType,
                                    String authorizationBearer,
                                    String signature,
                                    int languageId) throws GetRequestExecutionException {
        try {
            Gson gson = new GsonBuilder().setLenient().create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(this.getApiAccessUrl())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            GetServiceRetrofitInterface apiService = retrofit.create(GetServiceRetrofitInterface.class);
            Call<ResponseBody> call = apiService.get(endPoint, contentType, authorizationBearer, signature, languageId);

            APIResponseBean<T> theResponseBean = super.sendGETRequestToAPIGateway(call);
            return theResponseBean;
        } catch (GetRequestExecutionException e) {
            throw e;
        } catch (Exception e) {
            GetRequestExecutionException exp = new GetRequestExecutionException(e.getMessage(), e, "GET request execution failed!");
            throw exp;
        }
    }

    @Override
    public APIResponseBean<T> doGet(String endPoint,
                                 String contentType,
                                 String authorizationBearer,
                                 String signature,
                                 int languageId,
                                 String deviceId) throws GetRequestExecutionException {
        try {
            Gson gson = new GsonBuilder().setLenient().create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(this.getApiAccessUrl())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            GetServiceRetrofitInterface apiService = retrofit.create(GetServiceRetrofitInterface.class);
            Call<ResponseBody> call = apiService.get(endPoint, contentType, authorizationBearer, signature, languageId, deviceId);

            APIResponseBean<T> theResponseBean = super.sendGETRequestToAPIGateway(call);
            return theResponseBean;
        } catch (GetRequestExecutionException e) {
            throw e;
        } catch (Exception e) {
            GetRequestExecutionException exp = new GetRequestExecutionException(e.getMessage(), e, "GET request execution failed!");
            throw exp;
        }
    }

    @Override
    public APIResponseBean<T> doGet(String endPoint,
                                 String contentType,
                                 String authorizationBearer,
                                 String signature,
                                 int languageId,
                                 Map<String, Object> queryParams) throws GetRequestExecutionException {
        try {
            Gson gson = new GsonBuilder().setLenient().create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(this.getApiAccessUrl())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            GetServiceRetrofitInterface apiService = retrofit.create(GetServiceRetrofitInterface.class);
            Call<ResponseBody> call = apiService.
                    get(endPoint,
                            contentType,
                            authorizationBearer,
                            signature,
                            languageId,
                            queryParams);

            APIResponseBean<T> theResponseBean = super.sendGETRequestToAPIGateway(call);
            return theResponseBean;
        } catch (GetRequestExecutionException e) {
            throw e;
        } catch (Exception e) {
            GetRequestExecutionException exp = new GetRequestExecutionException(e.getMessage(), e, "GET request execution failed!");
            throw exp;
        }
    }

    @Override
    public APIResponseBean<T> doGet(String endPoint,
                                 String contentType,
                                 String authorizationBearer,
                                 String signature,
                                 int languageId,
                                 String deviceId,
                                 Map<String, Object> queryParams) throws GetRequestExecutionException {
        try {
            Gson gson = new GsonBuilder().setLenient().create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(this.getApiAccessUrl())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            GetServiceRetrofitInterface apiService = retrofit.create(GetServiceRetrofitInterface.class);
            Call<ResponseBody> call =
                    apiService.get(endPoint, contentType, authorizationBearer, signature, languageId, deviceId, queryParams);

            APIResponseBean<T> theResponseBean = super.sendGETRequestToAPIGateway(call);
            return theResponseBean;
        } catch (GetRequestExecutionException e){
            throw e;
        }
        catch (Exception e){
            GetRequestExecutionException exp = new GetRequestExecutionException(e.getMessage(), e, "GET request execution failed!");
            throw exp;
        }
    }

}

