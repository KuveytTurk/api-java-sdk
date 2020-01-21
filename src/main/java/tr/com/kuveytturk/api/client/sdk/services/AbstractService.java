/*
 * Copyright (c) 2020
 * KUVEYT TÜRK PARTICIPATION BANK INC.
 *
 * Author: Fikri Aydemir
 *
 * Project: Java SDK for Accessing API Endpoints
 */

package tr.com.kuveytturk.api.client.sdk.services;

import com.google.gson.JsonObject;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import tr.com.kuveytturk.api.client.sdk.model.response.APIResponseBean;
import tr.com.kuveytturk.api.client.sdk.util.GetRequestExecutionException;
import tr.com.kuveytturk.api.client.sdk.util.PostRequestExecutionException;

import java.io.IOException;

/**
 * Abstract class for executing backend API endpoint services
 *
 * @author      Fikri Aydemir
 * @version     1.0
 * @since       2020-01-12
 */
public abstract class AbstractService<T> {
    protected String apiAccessUrl;

    protected APIResponseBean<T> sendGETRequestToAPIGateway(Call<ResponseBody> call) throws GetRequestExecutionException {
        Response<ResponseBody> backEndResponse = null;
        APIResponseBean<T> responseBean = new APIResponseBean<>();
        if (call != null) {
            try {
                backEndResponse = call.execute();
            } catch (IOException | IllegalArgumentException e) {
                GetRequestExecutionException exp =
                        new GetRequestExecutionException(e.getMessage(), e, "Error occurred while sending the request to API Gateway!");
                throw exp;
            }

            if (backEndResponse.isSuccessful()) {
                String responseBodyAsString = "";
                try {
                    responseBodyAsString = backEndResponse.body().string();
                    responseBean = new APIResponseBean<T>(backEndResponse.code(), responseBodyAsString, true);
                    responseBean.buildResponsePayload();
                    return responseBean;
                } catch (IOException e) {
                    GetRequestExecutionException exp =
                            new GetRequestExecutionException(
                                    e.getMessage(),
                                    e,
                                    "Error occurred while fetching the API response from API Gateway!");
                    throw exp;
                }
            } else {
                String errResponseBody = "";
                try {
                    errResponseBody = backEndResponse.errorBody().string();
                    responseBean = new APIResponseBean<>(backEndResponse.code(), errResponseBody, false);
                    responseBean.buildResponsePayload();
                    return responseBean;
                } catch (IOException e) {
                    GetRequestExecutionException exp =
                            new GetRequestExecutionException(e.getMessage(), e, errResponseBody);
                    throw exp;
                }

            }
        }

        return responseBean;
    }

    protected APIResponseBean<T> sendPostRequestToAPIGateway(Call<JsonObject> call) throws PostRequestExecutionException {
        Response<JsonObject> backEndResponse = null;
        APIResponseBean<T> responseBean = new APIResponseBean<T>();
        if (call != null) {
            try {
                backEndResponse = call.execute();
            } catch (IOException | IllegalArgumentException e) {
                PostRequestExecutionException exp =
                        new PostRequestExecutionException(e.getMessage(), e, "Error occurred while sending the request to API Gateway!");
                throw exp;
            }

            String responseBodyAsString = "";
            if (backEndResponse != null) {
                if (backEndResponse.isSuccessful()) {
                    //Return the data to MainActivity
                    try {
                        responseBean = new APIResponseBean<>(backEndResponse.code(), backEndResponse.body(), true);
                        responseBean.buildResponsePayload();
                        return responseBean;
                    } catch (Exception e) {
                        PostRequestExecutionException exp =
                                new PostRequestExecutionException(
                                        e.getMessage(),
                                        e,
                                        "Error occurred while fetching the API response from API Gateway!");
                        throw exp;
                    }
                } else {
                    try {
                        responseBodyAsString = backEndResponse.errorBody().string();
                        responseBean = new APIResponseBean<>(backEndResponse.code(), responseBodyAsString, false);
                        responseBean.buildResponsePayload();
                        return responseBean;
                    } catch (IOException e) {
                        PostRequestExecutionException exp =
                                new PostRequestExecutionException(e.getMessage(), e, responseBodyAsString);
                        throw exp;
                    }
                }
            }
        }

        return responseBean;
    }

    protected String getApiAccessUrl() {
        return apiAccessUrl + "/";
    }

    protected void setApiAccessUrl(String apiAccessUrl) {
        this.apiAccessUrl = apiAccessUrl;
    }
}
