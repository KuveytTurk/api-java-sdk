/*
 * Copyright (c) 2020
 * KUVEYT TÜRK PARTICIPATION BANK INC.
 *
 * Author: Fikri Aydemir
 *
 * Project: Java SDK for Accessing API Endpoints
 */

package tr.com.kuveytturk.api.client.sdk.model.response;

import com.google.common.reflect.TypeToken;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ResponsePayloadBean<T> implements java.io.Serializable {
    @SerializedName("success")
    @Expose
    private boolean success;

    @SerializedName("http")
    @Expose
    private int http;

    @SerializedName("code")
    @Expose
    private String code;

    @SerializedName("text")
    @Expose
    private String text;

    @SerializedName("developerText")
    @Expose
    private String developerText;

    @SerializedName("moreInfo")
    @Expose
    private String moreInfo;

    @SerializedName("results")
    @Expose
    private ArrayList<ResultBean> results;

    @SerializedName("value")
    @Expose
    private T value;

    private final TypeToken<T> typeToken;
    private final Type type;

    public ResponsePayloadBean() {
        results = new ArrayList<>();
        typeToken = new TypeToken<T>(getClass()) { };
        type = typeToken.getType();
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getHttp() {
        return http;
    }

    public void setHttp(int http) {
        this.http = http;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDeveloperText() {
        return developerText;
    }

    public void setDeveloperText(String developerText) {
        this.developerText = developerText;
    }

    public String getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }

    public ArrayList<ResultBean> getResults() {
        return results;
    }

    public void setResults(ArrayList<ResultBean> results) {
        this.results = results;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Type getType() {
        return type;
    }

    private String getResultsText(){
        if(results.isEmpty()) {
            return "";
        }
        StringBuilder strBuilder = new StringBuilder();
        for (ResultBean bean:  getResults()
             ) {
            strBuilder.append(bean.toString());
        }

        return strBuilder.toString();
    }

    @Override
    public String toString() {
        return "ResponsePayloadBean{" +
                "success=" + success +
                ", http=" + http +
                ", code='" + code + '\'' +
                ", text='" + text + '\'' +
                ", developerText='" + developerText + '\'' +
                ", moreInfo='" + moreInfo + '\'' +
                ", results=" + getResultsText() +
                ", value=" + value +
                ", typeToken=" + typeToken +
                ", type=" + type +
                '}';
    }
}
