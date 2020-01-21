/*
 * Copyright (c) 2020
 * KUVEYT TÜRK PARTICIPATION BANK INC.
 *
 * Author: Fikri Aydemir
 *
 * Project: Java SDK for Accessing API Endpoints
 */

package tr.com.kuveytturk.api.client.sdk.accessors;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import tr.com.kuveytturk.api.client.sdk.model.misc.QueryParameterListBean;
import tr.com.kuveytturk.api.client.sdk.model.response.APIResponseBean;
import tr.com.kuveytturk.api.client.sdk.util.Constants;
import tr.com.kuveytturk.api.client.sdk.util.GetRequestExecutionException;
import tr.com.kuveytturk.api.client.sdk.util.PostRequestExecutionException;
import tr.com.kuveytturk.api.client.sdk.util.SignatureGenerationException;


/**
 * Abstract class representing an instance of class AbstractAPIGatewayAccessor
 *
 * @author      Fikri Aydemir
 * @version     1.0
 * @since       2020-01-12
 */
public abstract class AbstractAPIGatewayAccessor<T> {

    private String apiAccessUrl;
    private String privateKey;

    public abstract APIResponseBean<T> doGet(String endPoint,
                                             String accessToken,
                                             int languageId) throws GetRequestExecutionException, SignatureGenerationException;

    public abstract APIResponseBean<T> doGet(String endPoint,
                                 String accessToken,
                                 int languageId,
                                 String deviceId) throws GetRequestExecutionException, SignatureGenerationException;

    public abstract APIResponseBean<T> doGet(String endPoint,
                                 String accessToken,
                                 int languageId,
                                 QueryParameterListBean queryParams) throws GetRequestExecutionException, SignatureGenerationException;

    public abstract APIResponseBean<T> doGet(String endPoint,
                                 String accessToken,
                                 int languageId,
                                 String deviceId,
                                 QueryParameterListBean queryParams) throws GetRequestExecutionException, SignatureGenerationException;

    public abstract APIResponseBean<T> doPost(String endPoint,
                                  String accessToken,
                                  String jsonBodyAsString) throws PostRequestExecutionException, SignatureGenerationException;

    public abstract APIResponseBean<T> doPost(String endPoint,
                                  String accessToken,
                                  JsonObject jsonBody) throws PostRequestExecutionException, SignatureGenerationException;

    public abstract APIResponseBean<T> doPost(String endPoint,
                                  String jsonBodyAsString,
                                  String accessToken,
                                  int languageId,
                                  String deviceId) throws PostRequestExecutionException, SignatureGenerationException;

    public abstract APIResponseBean<T> doPost(String endPoint,
                                  JsonObject jsonBody,
                                  String accessToken,
                                  int languageId,
                                  String deviceId) throws PostRequestExecutionException, SignatureGenerationException;

    protected String getAuthorizationBearer(String accessToken){
        return Constants.BEARER_PREFIX + accessToken;
    }

    protected String getApiAccessUrl() {
        return apiAccessUrl;
    }

    protected void setApiAccessUrl(String apiAccessUrl) {
        this.apiAccessUrl = apiAccessUrl;
    }

    protected String getPrivateKey() {
        return privateKey;
    }

    protected void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    protected JsonObject buildJsonObjectFromRawString(String jsonBodyAsString){
        if(jsonBodyAsString != null && !jsonBodyAsString.isEmpty()) {
            JsonObject jsonObject = new JsonParser().parse(jsonBodyAsString).getAsJsonObject();
                 return jsonObject;
        } else {
            return new JsonObject();
        }
    }

    protected boolean isValidJson(JsonObject jsonObject){
        if(jsonObject.isJsonNull()) { return false;}
        return jsonObject.isJsonObject();
    }
}
