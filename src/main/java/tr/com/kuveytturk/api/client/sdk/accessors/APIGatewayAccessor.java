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
import tr.com.kuveytturk.api.client.sdk.model.misc.QueryParameterListBean;
import tr.com.kuveytturk.api.client.sdk.model.response.APIResponseBean;
import tr.com.kuveytturk.api.client.sdk.services.GetService;
import tr.com.kuveytturk.api.client.sdk.services.PostService;
import tr.com.kuveytturk.api.client.sdk.services.base.GetServiceAPI;
import tr.com.kuveytturk.api.client.sdk.services.base.PostServiceAPI;
import tr.com.kuveytturk.api.client.sdk.util.*;

/**
 * Concrete class implementing abstract class AbstractAPIGatewayAccessor
 *
 * @author      Fikri Aydemir
 * @version     1.0
 * @since       2020-01-12
 */
public class APIGatewayAccessor<T> extends AbstractAPIGatewayAccessor {

    public APIGatewayAccessor() {
        this(Constants.BASE_API_ACCESS_URL, Constants.DEFAULT_PRIVATE_KEY);
    }

    public APIGatewayAccessor(String apiAccessUrl, String privateKey) {
        super.setApiAccessUrl(apiAccessUrl);
        super.setPrivateKey(privateKey);
    }


    @Override
    public APIResponseBean<T> doGet(String endPoint,
                                    String accessToken,
                                    int languageId) throws GetRequestExecutionException, SignatureGenerationException {

        if(endPoint == null
                || accessToken == null
                || endPoint.isEmpty()
                || accessToken.isEmpty()){
            GetRequestExecutionException exp =
                    new GetRequestExecutionException("The method parameters cannot be null and/or empty!");
            throw exp;
        }

        String signature = SignatureGenerator.generateSignatureForGetRequest(accessToken, super.getPrivateKey());
        GetServiceAPI getRequestExecutor = new GetService(super.getApiAccessUrl());
        return getRequestExecutor.doGet(
                endPoint,
                Constants.CONTENT_TYPE,
                super.getAuthorizationBearer(accessToken),
                signature,
                languageId
        );
    }

    @Override
    public APIResponseBean<T> doGet(String endPoint,
                                 String accessToken,
                                 int languageId,
                                 String deviceId) throws GetRequestExecutionException, SignatureGenerationException {
        if(endPoint == null
                || accessToken == null
                || deviceId == null
                || endPoint.isEmpty()
                || accessToken.isEmpty()
                || deviceId.isEmpty()){
            GetRequestExecutionException exp =
                    new GetRequestExecutionException("The method parameters cannot be null and/or empty!");
            throw exp;
        }

        String signature = SignatureGenerator.generateSignatureForGetRequest(accessToken, super.getPrivateKey());
        GetServiceAPI getRequestExecutor = new GetService(super.getApiAccessUrl());
        return getRequestExecutor.doGet(
                endPoint,
                Constants.CONTENT_TYPE,
                super.getAuthorizationBearer(accessToken),
                signature,
                languageId,
                deviceId
        );
    }

    @Override
    public APIResponseBean<T> doGet(String endPoint,
                                 String accessToken,
                                 int languageId,
                                 QueryParameterListBean queryParams) throws GetRequestExecutionException, SignatureGenerationException {

        if(endPoint == null || accessToken == null || endPoint.isEmpty() || accessToken.isEmpty() || queryParams.isEmpty() ){
            GetRequestExecutionException exp =
                    new GetRequestExecutionException("The method parameters cannot be null and/or empty!");
            throw exp;
        }

        String signature =
                SignatureGenerator.generateSignatureForGetRequest(accessToken, super.getPrivateKey(), queryParams.toList());

        GetServiceAPI getRequestExecutor = new GetService(super.getApiAccessUrl());

        return getRequestExecutor.doGet(
                endPoint,
                Constants.CONTENT_TYPE,
                super.getAuthorizationBearer(accessToken),
                signature,
                languageId,
                queryParams.toMap()
        );
    }

    @Override
    public APIResponseBean<T> doGet(String endPoint,
                                 String accessToken,
                                 int languageId,
                                 String deviceId,
                                 QueryParameterListBean queryParams) throws GetRequestExecutionException, SignatureGenerationException {

        String signature =
                SignatureGenerator.generateSignatureForGetRequest(accessToken, super.getPrivateKey(), queryParams.toList());

        GetServiceAPI getRequestExecutor = new GetService(super.getApiAccessUrl());

        return getRequestExecutor.doGet(
                endPoint,
                Constants.CONTENT_TYPE,
                super.getAuthorizationBearer(accessToken),
                signature,
                languageId,
                deviceId,
                queryParams.toMap()
        );
    }

    @Override
    public APIResponseBean<T> doPost(String endPoint,
                                  String accessToken,
                                  String jsonBodyAsString
                                  ) throws PostRequestExecutionException, SignatureGenerationException {

        if(endPoint == null || accessToken == null || endPoint.isEmpty() || accessToken.isEmpty()){
            PostRequestExecutionException exp =
                    new PostRequestExecutionException("The method parameters, namely endPoint and accessToken cannot be null!");
            throw exp;
        }

       JsonObject jsonBody = super.buildJsonObjectFromRawString(jsonBodyAsString);

        if (jsonBody != null && !jsonBody.isJsonNull()) {
            if (jsonBody.isJsonObject()) {
                String signature =
                        SignatureGenerator.generateSignatureForPostRequest(accessToken, super.getPrivateKey(), jsonBody.toString());

                PostServiceAPI postRequestExecutor = new PostService(super.getApiAccessUrl());

                return postRequestExecutor.doPost(
                        endPoint,
                        jsonBody,
                        Constants.CONTENT_TYPE,
                        super.getAuthorizationBearer(accessToken),
                        signature
                );
            } else {
                PostRequestExecutionException exp =
                        new PostRequestExecutionException("Provided request body is not in proper JSON format!");
                throw exp;
            }
        } else {
            PostRequestExecutionException exp =
                    new PostRequestExecutionException("The method parameter jsonBodyAsString cannot be null!");
            throw exp;
        }
    }

    @Override
    public APIResponseBean<T> doPost(String endPoint,
                                  String accessToken,
                                  JsonObject jsonBody
    ) throws PostRequestExecutionException, SignatureGenerationException {

        if(endPoint == null || accessToken == null || endPoint.isEmpty() || accessToken.isEmpty()){
            PostRequestExecutionException exp =
                    new PostRequestExecutionException("The method parameters, namely endPoint and accessToken cannot be null!");
            throw exp;
        }

        if (jsonBody != null && !jsonBody.isJsonNull()) {
            if (jsonBody.isJsonObject()) {
                String signature =
                        SignatureGenerator.generateSignatureForPostRequest(accessToken, super.getPrivateKey(), jsonBody.toString());

                PostServiceAPI postRequestExecutor = new PostService(super.getApiAccessUrl());

                return postRequestExecutor.doPost(
                        endPoint,
                        jsonBody,
                        Constants.CONTENT_TYPE,
                        super.getAuthorizationBearer(accessToken),
                        signature
                );
            } else {
                PostRequestExecutionException exp =
                        new PostRequestExecutionException("Provided request body is not in proper JSON format!");
                throw exp;
            }
        } else {
            PostRequestExecutionException exp =
                    new PostRequestExecutionException("The method parameter jsonBody cannot be null!");
            throw exp;
        }
    }

    @Override
    public APIResponseBean<T> doPost(String endPoint,
                                  String jsonBodyAsString,
                                  String accessToken,
                                  int languageId,
                                  String deviceId) throws PostRequestExecutionException, SignatureGenerationException {

        if(endPoint == null || accessToken == null || endPoint.isEmpty() || accessToken.isEmpty()){
            PostRequestExecutionException exp =
                    new PostRequestExecutionException("The method parameters, namely endPoint and accessToken cannot be null!");
            throw exp;
        }

        JsonObject jsonBody = super.buildJsonObjectFromRawString(jsonBodyAsString);

        if (jsonBody != null && !jsonBody.isJsonNull()) {
            if (jsonBody.isJsonObject()) {
                String signature =
                        SignatureGenerator.generateSignatureForPostRequest(accessToken, super.getPrivateKey(), jsonBody.toString());

                PostServiceAPI postRequestExecutor = new PostService(super.getApiAccessUrl());

                return postRequestExecutor.doPost(
                        endPoint,
                        jsonBody,
                        Constants.CONTENT_TYPE,
                        super.getAuthorizationBearer(accessToken),
                        signature,
                        languageId,
                        deviceId
                );
            } else {
                PostRequestExecutionException exp =
                        new PostRequestExecutionException("Provided json body is not in proper JSON format!");
                throw exp;
            }
        } else {
            PostRequestExecutionException exp =
                    new PostRequestExecutionException("The method parameter jsonBodyAsString cannot be null!");
            throw exp;
        }
    }

    @Override
    public APIResponseBean<T> doPost(String endPoint,
                                  JsonObject jsonBody,
                                  String accessToken,
                                  int languageId,
                                  String deviceId) throws PostRequestExecutionException, SignatureGenerationException {

        if(endPoint == null || accessToken == null || endPoint.isEmpty() || accessToken.isEmpty()){
            PostRequestExecutionException exp =
                    new PostRequestExecutionException("The method parameters, namely endPoint and accessToken cannot be null!");
            throw exp;
        }

        if (jsonBody != null && !jsonBody.isJsonNull()) {
            if (jsonBody.isJsonObject()) {
                String signature =
                        SignatureGenerator.generateSignatureForPostRequest(accessToken, super.getPrivateKey(), jsonBody.toString());

                PostServiceAPI postRequestExecutor = new PostService(super.getApiAccessUrl());

                return postRequestExecutor.doPost(
                        endPoint,
                        jsonBody,
                        Constants.CONTENT_TYPE,
                        super.getAuthorizationBearer(accessToken),
                        signature,
                        languageId,
                        deviceId
                );
            } else {
                PostRequestExecutionException exp =
                        new PostRequestExecutionException("Provided json body is not in proper JSON format!");
                throw exp;
            }
        } else {
            PostRequestExecutionException exp =
                    new PostRequestExecutionException("The method parameter jsonBody cannot be null!");
            throw exp;
        }
    }
}
