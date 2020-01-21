/*
 * Copyright (c) 2020
 * KUVEYT TÜRK PARTICIPATION BANK INC.
 *
 * Author: Fikri Aydemir
 *
 * Project: Java SDK for Accessing API Endpoints
 */

package tr.com.kuveytturk.api.client.sdk.model.request;

import tr.com.kuveytturk.api.client.sdk.model.enums.GrantType;
import tr.com.kuveytturk.api.client.sdk.model.misc.ScopeListBean;

/**
 * Container class representing an access token request to be made towards IdentityServer
 *
 * @author      Fikri Aydemir
 * @version     1.0
 * @since       2020-01-12
 */
public class AccessTokenRequestBean {
    private GrantType grantType;
    private String authorizationCode;
    private String redirectURI;
    private String clientId;
    private String clientSecret;
    private ScopeListBean scopeList;
    /**
     * Builder class representing an instance of class AccessTokenRequestBean
     *
     * @author      Fikri Aydemir
     * @version     1.0
     * @since       2020-01-12
     */
    public static class AccessTokenRequestBeanBuilder {
        GrantType grantType;
        String authorizationCode;
        String redirectURI;
        String clientId;
        String clientSecret;
        ScopeListBean scopeList;

        public AccessTokenRequestBeanBuilder(){ }

        public AccessTokenRequestBeanBuilder grantType(GrantType value) {
            grantType = value;
            return this;
        }

        public AccessTokenRequestBeanBuilder authorizationCode(String value) {
            authorizationCode = value;
            return this;
        }

        public AccessTokenRequestBeanBuilder clientId(String value) {
            clientId = value;
            return this;
        }

        public AccessTokenRequestBeanBuilder clientSecret(String value) {
            clientSecret = value;
            return this;
        }

        public AccessTokenRequestBeanBuilder redirectURI(String value) {
            redirectURI = value;
            return this;
        }

        public AccessTokenRequestBeanBuilder scopeList(ScopeListBean value) {
            scopeList = value;
            return this;
        }

        public AccessTokenRequestBean build(){
            return new AccessTokenRequestBean(this);
        }
    }

    private AccessTokenRequestBean(AccessTokenRequestBeanBuilder builder){
        if(builder.grantType == GrantType.AUTHORIZATION_CODE) {
            this.grantType = builder.grantType;
            this.authorizationCode = builder.authorizationCode;
            this.clientId = builder.clientId;
            this.clientSecret = builder.clientSecret;
            this.redirectURI = builder.redirectURI;
        } else  if(builder.grantType == GrantType.CLIENT_CREDENTIALS) {
            this.grantType = builder.grantType;
            this.clientId = builder.clientId;
            this.clientSecret = builder.clientSecret;
            this.scopeList = builder.scopeList;
        }
        else  {
            this.grantType = builder.grantType;
            this.authorizationCode = builder.authorizationCode;
            this.clientId = builder.clientId;
            this.clientSecret = builder.clientSecret;
            this.redirectURI = builder.redirectURI;
            this.scopeList = builder.scopeList;
        }
    }


    public GrantType getGrantType() {
        return grantType;
    }

    public String getAuthorizationCode() {
        return authorizationCode;
    }

    public String getRedirectURI() {
        return redirectURI;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public ScopeListBean getScopeList() {
        return scopeList;
    }
}
