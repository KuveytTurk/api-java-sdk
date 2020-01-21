/*
 * Copyright (c) 2020
 * KUVEYT TÜRK PARTICIPATION BANK INC.
 *
 * Author: Fikri Aydemir
 *
 * Project: Java SDK for Accessing API Endpoints
 */

package tr.com.kuveytturk.api.client.sdk.model.request;

import tr.com.kuveytturk.api.client.sdk.model.enums.ScopeType;
import tr.com.kuveytturk.api.client.sdk.model.misc.ScopeListBean;
import tr.com.kuveytturk.api.client.sdk.util.Constants;

/**
 * Container class representing an authorization code request to be made towards the Identity Server
 *
 * @author      Fikri Aydemir
 * @version     1.0
 * @since       2020-01-12
 */
public class AuthorizationCodeRequestBean implements java.io.Serializable{

    private int customerNumber;
    private String clientId;
    private String redirectURI;
    private ScopeListBean scopeList;

    /**
     * Builder class representing an instance of class AuthorizationCodeRequestBean
     *
     * @author      Fikri Aydemir
     * @version     1.0
     * @since       2020-01-12
     */
    public static class AuthorizationCodeRequestBeanBuilder {
        int customerNumber;
        String clientId;
        String redirectURI;
        ScopeListBean scopeList;

        public AuthorizationCodeRequestBeanBuilder(){

        }

        public AuthorizationCodeRequestBeanBuilder customerNumber(int value) {
             customerNumber = value;
             return this;
        }

        public AuthorizationCodeRequestBeanBuilder clientId(String value) {
            clientId = value;
            return this;
        }

        public AuthorizationCodeRequestBeanBuilder redirectURI(String value) {
            redirectURI = value;
            return this;
        }

        public AuthorizationCodeRequestBeanBuilder scopeList(ScopeListBean value) {
            scopeList = value;
            return this;
        }

        public AuthorizationCodeRequestBean build(){
            return new AuthorizationCodeRequestBean(this);
        }
        public AuthorizationCodeRequestBean buildWithDefaults(){
            return new AuthorizationCodeRequestBean();
        }
    }

    private AuthorizationCodeRequestBean(){
            AuthorizationCodeRequestBeanBuilder builder = new AuthorizationCodeRequestBeanBuilder();
            ScopeListBean defaultScopeList = new ScopeListBean();
            defaultScopeList.add(ScopeType.LOANS).add(ScopeType.TRANSFERS)
                    .add(ScopeType.TRANSFERS)
                    .add(ScopeType.PUBLIC)
                    .add(ScopeType.ACCOUNTS)
                    .add(ScopeType.OFFLINE_ACCESS);
            builder.customerNumber(Constants.DEFAULT_TEST_ACCOUNT_NUMBER)
                    .clientId(Constants.DEFAULT_CLIENT_ID)
                    .redirectURI(Constants.DEFAULT_REDIRECT_URI).
                    scopeList(defaultScopeList);

            this.customerNumber = builder.customerNumber;
            this.clientId = builder.clientId;
            this.redirectURI = builder.redirectURI;
            this.scopeList = builder.scopeList;
    }

    private AuthorizationCodeRequestBean(AuthorizationCodeRequestBeanBuilder builder){
        this.customerNumber = builder.customerNumber;
        this.clientId = builder.clientId;
        this.redirectURI = builder.redirectURI;
        this.scopeList = builder.scopeList;
    }

    public int getCustomerNumber() {
        return customerNumber;
    }


    public String getClientId() {
        return clientId;
    }


    public String getRedirectURI() {
        return redirectURI;
    }


    public ScopeListBean getScopeList() {
        return scopeList;
    }

}
