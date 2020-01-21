/*
 * Copyright (c) 2020
 * KUVEYT TÜRK PARTICIPATION BANK INC.
 *
 * Author: Fikri Aydemir
 *
 * Project: Java SDK for Accessing API Endpoints
 */

package tr.com.kuveytturk.api.client.sdk.services.base;

import tr.com.kuveytturk.api.client.sdk.model.response.APIResponseBean;
import tr.com.kuveytturk.api.client.sdk.util.GetRequestExecutionException;

import java.util.Map;

/**
 * Service Interface for executing GET operations at API Gateway
 *
 * @author      Fikri Aydemir
 * @version     1.0
 * @since       2020-01-12
 */
public interface GetServiceAPI<T> {
    APIResponseBean<T> doGet(String endPoint,
                             String contentType,
                             String authorizationBearer,
                             String signature,
                             int languageId,
                             Map<String, Object> queryParams) throws GetRequestExecutionException;


    APIResponseBean<T> doGet(String endPoint,
                 String contentType,
                 String authorizationBearer,
                 String signature,
                 int languageId) throws GetRequestExecutionException;

    APIResponseBean<T> doGet(String endPoint,
                 String contentType,
                 String authorizationBearer,
                 String signature,
                 int languageId,
                 String deviceId) throws GetRequestExecutionException;

    APIResponseBean<T> doGet(String endPoint,
                 String contentType,
                 String authorizationBearer,
                 String signature,
                 int languageId,
                 String deviceId,
                 Map<String, Object> queryParams) throws GetRequestExecutionException;


}
