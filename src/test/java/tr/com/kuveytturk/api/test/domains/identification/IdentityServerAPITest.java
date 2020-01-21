/*
 * Copyright (c) 2020
 * KUVEYT TÜRK PARTICIPATION BANK INC.
 *
 * Author: Fikri Aydemir
 *
 * Project: Java SDK for Accessing API Endpoints
 */

package tr.com.kuveytturk.api.test.domains.identification;

import com.consol.citrus.annotations.CitrusTest;
import org.junit.Test;
import tr.com.kuveytturk.api.client.sdk.model.callresult.AccessTokenRetrievalResult;
import tr.com.kuveytturk.api.test.domains.base.AbstractAPITest;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Class implementing routines for testing authorization code and acces token retrieval related operations.
 *
 * @author      Fikri Aydemir
 * @version     1.0
 * @since       2020-01-12
 */
public class IdentityServerAPITest extends AbstractAPITest {

    @Test
    @CitrusTest
    public void testAccessTokenRetrievalInAuthorizationCodeFlow() {
        List<Integer> testCustomersList = super.getTestCustomerList();
        assertTrue("Test Müşteri numaraları çekilemedi.", testCustomersList != null && !testCustomersList.isEmpty());
        assertTrue("Yeterli sayıda test müşterisi çekilemedi.", testCustomersList.size() >= 2);
        int randomIndex = getRandomInt(0, testCustomersList.size() - 1);
        int testCustomerId = new Integer(testCustomersList.get(randomIndex).intValue());

        AccessTokenRetrievalResult accessTokenResult = super.fetchAccessTokenWithAuthorizationCodeFlow(testCustomerId);

        assertTrue(testCustomerId +
                        " nolu müşteri için authorization code kullanarak access token alınırken hata oluştu!\nHATA DETAYI:\n" +
                        accessTokenResult.getErrorDetailsIfAny(),
                accessTokenResult.isSuccessful());
    }

    @Test
    @CitrusTest
    public void testAccessTokenRetrievalInClientCredentialsFlow() {

        List<Integer> testCustomersList = super.getTestCustomerList();
        assertTrue("Test Müşteri numaraları çekilemedi.", testCustomersList != null && !testCustomersList.isEmpty());
        assertTrue("Yeterli sayıda test müşterisi çekilemedi.", testCustomersList.size() >= 2);
        int randomIndex = getRandomInt(0, testCustomersList.size() - 1);
        int testCustomerId = new Integer(testCustomersList.get(randomIndex).intValue());

        AccessTokenRetrievalResult accessTokenResult = super.fetchAccessTokenWithClientCredentialsFlow();
        assertTrue("Client Credential Flow'u ile access token alınırken hata oluştu!\nHATA DETAYI:\n" +
                        accessTokenResult.getErrorDetailsIfAny(),
                accessTokenResult.isSuccessful());
    }

    @Test
    @CitrusTest
    public void testAccessTokenRetrievalWithRefreshToken() {

        List<Integer> testCustomersList = super.getTestCustomerList();
        assertTrue("Test Müşteri numaraları çekilemedi.", testCustomersList != null && !testCustomersList.isEmpty());
        assertTrue("Yeterli sayıda test müşterisi çekilemedi.", testCustomersList.size() >= 2);
        int randomIndex = getRandomInt(0, testCustomersList.size() - 1);
        int testCustomerId = new Integer(testCustomersList.get(randomIndex).intValue());

        AccessTokenRetrievalResult accessTokenResult = super.fetchAccessTokenWithAuthorizationCodeFlow(testCustomerId);
        assertTrue("Client Credential Flow'u ile access token alınırken hata oluştu!\nHATA DETAYI:\n" +
                        accessTokenResult.getErrorDetailsIfAny(),
                accessTokenResult.isSuccessful());

        AccessTokenRetrievalResult accessTokenWithRefreshTokenResult = super.fetchAccessTokenWithRefreshToken(accessTokenResult.getRefreshToken());
        assertTrue("Refresh token kullanarak access token alınırken hata oluştu!\nHATA DETAYI:\n" +
                        accessTokenWithRefreshTokenResult.getErrorDetailsIfAny(),
                accessTokenWithRefreshTokenResult.isSuccessful());
    }

}
