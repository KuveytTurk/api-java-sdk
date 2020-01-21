/*
 * Copyright (c) 2020
 * KUVEYT TÜRK PARTICIPATION BANK INC.
 *
 * Author: Fikri Aydemir
 *
 * Project: Java SDK for Accessing API Endpoints
 */

package tr.com.kuveytturk.api.test.domains.base;

import org.junit.Before;
import tr.com.kuveytturk.api.client.sdk.api.APIExecutionInterface;
import tr.com.kuveytturk.api.client.sdk.api.APIExecutor;
import tr.com.kuveytturk.api.client.sdk.model.callresult.APICallResult;
import tr.com.kuveytturk.api.client.sdk.model.callresult.AccessTokenRetrievalResult;
import tr.com.kuveytturk.api.client.sdk.model.callresult.AuthorizationCodeRetrievalResult;
import tr.com.kuveytturk.api.client.sdk.model.enums.ScopeType;
import tr.com.kuveytturk.api.client.sdk.model.misc.ScopeListBean;
import tr.com.kuveytturk.api.client.sdk.model.response.ResponsePayloadBean;
import tr.com.kuveytturk.api.client.sdk.util.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertTrue;

/**
 * Abstract class implementing APITestInterface to used by concrete subclasses.
 *
 * @author      Fikri Aydemir
 * @version     1.0
 * @since       2020-01-12
 */
public abstract class AbstractAPITest implements APITestInterface {

    private List<Integer> testCustomerList;

    public AbstractAPITest() {
        testCustomerList = new ArrayList<>();
    }

    @Before
    public void setUp() throws Exception {
        this.testCustomerList = fetchTestCustomers();
        assertTrue("Test Müşteri numaraları çekilemedi.", this.testCustomerList != null && !this.testCustomerList.isEmpty());
        assertTrue("Yeterli sayıda test müşterisi çekilemedi.", this.testCustomerList.size() >= 2);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Integer> fetchTestCustomers() {
        return fetchTestCustomers(Constants.DEFAULT_CLIENT_ID, Constants.DEFAULT_CLIENT_SECRET);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Integer> fetchTestCustomers(String clientId, String clientSecret) {
        List<Integer> theTestCustomerList = new ArrayList<>();

        APIExecutionInterface<ArrayList<Double>> apiHandle = new APIExecutor<ArrayList<Double>>();
        APICallResult<ArrayList<Double>> result = null;
        //Make three attempts
        for(int i = 0; i<3; i++) {
            result = apiHandle.fetchTestCustomerList(clientId, clientSecret);
            if (result.isAPICallSuccessful()) {
                break;
            }
        }

        assertTrue(
                "Test müşterileri listesi getirilirken hata oluştu.\n HATA DETAYI:\n" + result.getErrorDetailsIfAny(),
                result.isAPICallSuccessful());

        if (result.isAPICallSuccessful()) {
            if (result.getApiResponseAsRawText() != null && !result.getApiResponseAsRawText().isEmpty()) {
                ResponsePayloadBean<ArrayList<Double>> payload = result.getResponsePayload();
                if (payload != null && payload.getValue() != null) {
                    if (payload.getValue().size() > 0) {
                        for (int i = 0; i < payload.getValue().size(); i++) {
                            Integer testCustomerId = new Integer(payload.getValue().get(i).intValue());
                            theTestCustomerList.add(testCustomerId);
                        }
                        return theTestCustomerList;
                    }
                }
            } else {
                return new ArrayList<>();
            }
        } else {
            return new ArrayList<>();
        }

        return new ArrayList<>();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public  AuthorizationCodeRetrievalResult fetchAuthorizationCode(int testCustomerId) {
        ScopeListBean scopeList = new ScopeListBean();
        scopeList.add(ScopeType.LOANS)
                .add(ScopeType.TRANSFERS)
                .add(ScopeType.PUBLIC)
                .add(ScopeType.ACCOUNTS)
                .add(ScopeType.PAYMENTS)
                .add(ScopeType.CARDS)
                .add(ScopeType.OFFLINE_ACCESS);

        return fetchAuthorizationCode(testCustomerId, scopeList);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public AuthorizationCodeRetrievalResult fetchAuthorizationCode(int testCustomerId, ScopeListBean scopeList) {

        APIExecutionInterface apiHandle = new APIExecutor();

        apiHandle.setScopeList(scopeList);

        AuthorizationCodeRetrievalResult codeResult =
                apiHandle.fetchAuthorizationCode(
                        testCustomerId,
                        Constants.DEFAULT_CLIENT_ID,
                        Constants.DEFAULT_REDIRECT_URI,
                        apiHandle.getScopeListAsText());
        assertTrue(testCustomerId +
                        " nolu müşteri için authorization code alınırken hata oluştu!\nHATA DETAYI:\n" +
                        codeResult.getErrorDetailsIfAny(),
                codeResult.isSuccessful());

        return codeResult;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public  AccessTokenRetrievalResult fetchAccessTokenWithAuthorizationCodeFlow(int testCustomerId) {
        ScopeListBean scopeList = new ScopeListBean();
        scopeList.add(ScopeType.LOANS)
                .add(ScopeType.TRANSFERS)
                .add(ScopeType.PUBLIC)
                .add(ScopeType.ACCOUNTS)
                .add(ScopeType.PAYMENTS)
                .add(ScopeType.CARDS)
                .add(ScopeType.OFFLINE_ACCESS);

        return fetchAccessTokenWithAuthorizationCodeFlow(testCustomerId, scopeList);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public AccessTokenRetrievalResult fetchAccessTokenWithAuthorizationCodeFlow(int testCustomerId, ScopeListBean scopeList){

        AuthorizationCodeRetrievalResult codeResult = fetchAuthorizationCode(testCustomerId, scopeList);

        APIExecutionInterface apiHandle = new APIExecutor();

        apiHandle.setScopeList(scopeList);

        if(codeResult.isSuccessful()) {
            String code = codeResult.getAuthorizationCode();
            AccessTokenRetrievalResult accessTokenResult =
                    apiHandle.fetchAccessTokenWithAuthorizationCode(
                            Constants.DEFAULT_CLIENT_ID,
                            Constants.DEFAULT_CLIENT_SECRET,
                            code,
                            Constants.DEFAULT_REDIRECT_URI);

            assertTrue(testCustomerId +
                            " nolu müşteri için authorization code kullanarak access token alınırken hata oluştu!\nHATA DETAYI:\n" +
                            accessTokenResult.getErrorDetailsIfAny(),
                    accessTokenResult.isSuccessful());

            return accessTokenResult;
        } else {
            AccessTokenRetrievalResult.AccessTokenRetrievalResultBuilder builder = new AccessTokenRetrievalResult.AccessTokenRetrievalResultBuilder();
            AccessTokenRetrievalResult accessTokenRetrievalResult =
                    builder.hasSucceeded(false)
                    .errorDescription(codeResult.getErrorDetailsIfAny())
                    .error(codeResult.getInnerException() != null ? codeResult.getInnerException().getMessage() : "Authorization code değeri alınamadı.")
                    .build();
            return accessTokenRetrievalResult;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AccessTokenRetrievalResult fetchAccessTokenWithClientCredentialsFlow() {
        ScopeListBean scopeList = new ScopeListBean();
        scopeList.add(ScopeType.PUBLIC);
        return fetchAccessTokenWithClientCredentialsFlow(scopeList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AccessTokenRetrievalResult fetchAccessTokenWithClientCredentialsFlow(ScopeListBean scopeList) {
        APIExecutionInterface apiHandle = new APIExecutor();

        apiHandle.setScopeList(scopeList);

        AccessTokenRetrievalResult accessTokenResult =
                apiHandle.fetchAccessTokenWithClientCredentials(
                        Constants.DEFAULT_CLIENT_ID,
                        Constants.DEFAULT_CLIENT_SECRET,
                        apiHandle.getScopeListAsText());
        assertTrue("Client Credential Flow'u ile access token alınırken hata oluştu!\nHATA DETAYI:\n" +
                        accessTokenResult.getErrorDetailsIfAny(),
                accessTokenResult.isSuccessful());

        return accessTokenResult;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public AccessTokenRetrievalResult fetchAccessTokenWithRefreshToken(String refreshToken){

        APIExecutionInterface apiHandle = new APIExecutor();
        AccessTokenRetrievalResult accessTokenWithRefreshTokenResult =
                apiHandle.fetchAccessTokenWithRefreshToken(
                        Constants.DEFAULT_CLIENT_ID,
                        Constants.DEFAULT_CLIENT_SECRET,
                        refreshToken);
        assertTrue("Refresh token kullanarak access token alınırken hata oluştu!\nHATA DETAYI:\n" +
                        accessTokenWithRefreshTokenResult.getErrorDetailsIfAny(),
                accessTokenWithRefreshTokenResult.isSuccessful());

        return accessTokenWithRefreshTokenResult;
    }

    public int getRandomInt(int min, int max){
        if(min == 0 && max == 0) {
            return 0;
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public List<Integer> getTestCustomerList() {
        return testCustomerList;
    }
}
