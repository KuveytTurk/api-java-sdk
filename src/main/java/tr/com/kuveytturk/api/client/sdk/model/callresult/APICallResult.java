/*
 * Copyright (c) 2020
 * KUVEYT TÜRK PARTICIPATION BANK INC.
 *
 * Author: Fikri Aydemir
 *
 * Project: Java SDK for Accessing API Endpoints
 */

package tr.com.kuveytturk.api.client.sdk.model.callresult;

import com.google.gson.JsonObject;
import tr.com.kuveytturk.api.client.sdk.model.response.ResponsePayloadBean;
import tr.com.kuveytturk.api.client.sdk.util.GetRequestExecutionException;
import tr.com.kuveytturk.api.client.sdk.util.PostRequestExecutionException;
import tr.com.kuveytturk.api.client.sdk.util.SignatureGenerationException;

/**
 * The utility class containing properties about the result of
 * invocation that has been made for an API Endpoint
 *
 * @author      Fikri Aydemir
 * @version     1.0
 * @since       2020-01-12
 */
public final class APICallResult<T> {

    private int httpStatusCode;
    private String apiResponseAsRawText;
    private JsonObject apiResponseAsJSONObject;
    private ResponsePayloadBean<T> responsePayload;
    private boolean hasAPICallSucceeded;
    private String error;
    private String errorDescription;
    private GetRequestExecutionException getAPICallException ;
    private PostRequestExecutionException postAPICallException;
    private SignatureGenerationException signatureException;

    /**
     * Inner class for building an instance of class APICallResultBuilder
     *
     * @author      Fikri Aydemir
     * @version     1.0
     * @since       2020-01-12
     */
    public static class APICallResultBuilder<T> {
        int httpStatusCode;
        String apiResponseAsRawText;
        JsonObject apiResponseAsJSONObject;
        boolean hasAPICallSucceeded;
        String error;
        String errorDescription;
        GetRequestExecutionException getAPICallException ;
        PostRequestExecutionException postAPICallException;
        SignatureGenerationException signatureException;
        ResponsePayloadBean<T> responsePayload;

        public APICallResultBuilder<T> httpStatusCode(int value) {
            this.httpStatusCode = value;
            return this;
        }

        public APICallResultBuilder<T> apiResponseAsRawText(String value) {
            this.apiResponseAsRawText = value;
            return this;
        }

        public APICallResultBuilder<T> apiResponseAsJSONObject(JsonObject value) {
            this.apiResponseAsJSONObject = value;
            return this;
        }

        public APICallResultBuilder<T> hasAPICallSucceeded(boolean value) {
            this.hasAPICallSucceeded = value;
            return this;
        }

        public APICallResultBuilder<T> error(String value) {
            this.error = value;
            return this;
        }
        public APICallResultBuilder<T> errorDescription(String value) {
            this.errorDescription = value;
            return this;
        }

        public APICallResultBuilder<T> responsePayload(ResponsePayloadBean<T> value) {
            this.responsePayload = value;
            return this;
        }

        public APICallResultBuilder<T> getAPICallException(GetRequestExecutionException value) {
            this.getAPICallException = value;
            return this;
        }

        public APICallResultBuilder<T> postAPICallException(PostRequestExecutionException value) {
             this.postAPICallException = value;
            return this;
        }

        public APICallResultBuilder<T> signatureException(SignatureGenerationException value) {
            this.signatureException = value;
            return this;
        }

        public APICallResult<T> build(){
            return new APICallResult(this);
        }
    }

    private APICallResult(APICallResultBuilder<T> builder) {
        this.httpStatusCode = builder.httpStatusCode;
        this.apiResponseAsRawText = builder.apiResponseAsRawText;
        this.apiResponseAsJSONObject = builder.apiResponseAsJSONObject;
        this.hasAPICallSucceeded = builder.hasAPICallSucceeded;
        this.getAPICallException = builder.getAPICallException;
        this.postAPICallException = builder.postAPICallException;
        this.signatureException = builder.signatureException;
        this.errorDescription = builder.errorDescription;
        this.error = builder.error;
        this.responsePayload = builder.responsePayload;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public String getApiResponseAsRawText() {
        return apiResponseAsRawText;
    }

    public JsonObject getApiResponseAsJSONObject() {
        return apiResponseAsJSONObject;
    }

    public boolean isAPICallSuccessful() {
        return hasAPICallSucceeded;
    }

    public GetRequestExecutionException getGetAPICallException() {
        return getAPICallException;
    }

    public PostRequestExecutionException getPostAPICallException() {
        return postAPICallException;
    }

    public SignatureGenerationException getSignatureException() {
        return signatureException;
    }

    public ResponsePayloadBean<T> getResponsePayload(){
        return responsePayload;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public String getErrorDetailsIfAny() {
        if(error != null && !error.isEmpty()) {
            return "Error = " + error +
                    ", ExceptionDescription = " + errorDescription;
        } else {
            return " ExceptionDescription = " + errorDescription;
        }
    }
}
