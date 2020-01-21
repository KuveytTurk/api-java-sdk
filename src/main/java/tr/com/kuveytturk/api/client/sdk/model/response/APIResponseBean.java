/*
 * Copyright (c) 2020
 * KUVEYT TÜRK PARTICIPATION BANK INC.
 *
 * Author: Fikri Aydemir
 *
 * Project: Java SDK for Accessing API Endpoints
 */

package tr.com.kuveytturk.api.client.sdk.model.response;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class APIResponseBean<T> implements java.io.Serializable{
    private int httpStatusCode;
    ResponsePayloadBean<T> responsePayload;
    private String rawResponseContent;
    private JsonObject responseInJSON;
    private boolean hasSucceeded;

    public APIResponseBean(int httpStatusCode, String rawResponseContent, boolean hasSucceeded) {
        this.httpStatusCode = httpStatusCode;
        this.rawResponseContent = rawResponseContent;
        this.hasSucceeded = hasSucceeded;
        buildJsonResponseFromRawStringResponse();
    }

    public APIResponseBean(int httpStatusCode, JsonObject jsonResponseBody, boolean hasSucceeded) {
        this.httpStatusCode = httpStatusCode;
        this.responseInJSON = jsonResponseBody;
        this.rawResponseContent = jsonResponseBody.toString();
        this.hasSucceeded = hasSucceeded;
    }

    public APIResponseBean() {
        this.httpStatusCode = 0;
        this.rawResponseContent = "";
        responseInJSON = new JsonObject();
        hasSucceeded = false;
    }

    public boolean isResponseInJSONValid(){
        if(responseInJSON != null && responseInJSON.isJsonNull()) {
            return responseInJSON.isJsonObject();
        } else {
         return false;
        }
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public String getRawResponseContent() {
        return rawResponseContent;
    }

    public JsonObject getResponseInJSON() {
        return responseInJSON;
    }

    public boolean isSuccessful(){
        return hasSucceeded;
    }

    private boolean buildJsonResponseFromRawStringResponse(){
        if(rawResponseContent != null && !rawResponseContent.isEmpty()) {
            JsonObject jsonObject = new JsonParser().parse(rawResponseContent).getAsJsonObject();
            if (jsonObject.isJsonObject()) {
                responseInJSON = jsonObject;
                return true;
            } else {
                responseInJSON = jsonObject;
                return false;
            }
        } else {
            responseInJSON = new JsonObject();
            return false;
        }
    }

    public void buildResponsePayload(){
        if(rawResponseContent != null && !rawResponseContent.isEmpty()) {
            GsonBuilder gsonBuilder = new GsonBuilder();
            responsePayload = new ResponsePayloadBean<T>();
            ResponsePayloadBean<T> theObject = gsonBuilder.create().fromJson(rawResponseContent, responsePayload.getClass());
            responsePayload = theObject;
        } else {
            responsePayload = new ResponsePayloadBean<>();
        }
    }


    public ResponsePayloadBean<T> getResponsePayloadAsJavaObject(){
        return responsePayload;
    }
}
