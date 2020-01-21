/*
 * Copyright (c) 2020
 * KUVEYT TÜRK PARTICIPATION BANK INC.
 *
 * Author: Fikri Aydemir
 *
 * Project: Java SDK for Accessing API Endpoints
 */

package tr.com.kuveytturk.api.client.sdk.services.base;

import com.google.gson.JsonObject;
import tr.com.kuveytturk.api.client.sdk.model.response.APIResponseBean;
import tr.com.kuveytturk.api.client.sdk.util.PostRequestExecutionException;

/**
 * Service Interface for executing POST operations at API Gateway
 *
 * @author      Fikri Aydemir
 * @version     1.0
 * @since       2020-01-12
 */
public interface PostServiceAPI<T> {
    APIResponseBean<T> doPost(String endPoint,
                              JsonObject jsonBody,
                              String contentType,
                              String authorizationBearer,
                              String signature) throws PostRequestExecutionException;

   APIResponseBean<T> doPost( String endPoint,
                          JsonObject jsonBody,
                          String contentType,
                          String authorizationBearer,
                          String signature,
                          int languageId,
                          String deviceId) throws PostRequestExecutionException;
}
