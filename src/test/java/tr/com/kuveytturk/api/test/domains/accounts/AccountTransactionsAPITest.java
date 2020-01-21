/*
 * Copyright (c) 2020
 * KUVEYT TÜRK PARTICIPATION BANK INC.
 *
 * Author: Fikri Aydemir
 *
 * Project: Java SDK for Accessing API Endpoints
 */

package tr.com.kuveytturk.api.test.domains.accounts;

import com.consol.citrus.annotations.CitrusTest;
import com.google.gson.internal.LinkedTreeMap;
import org.junit.Test;
import tr.com.kuveytturk.api.client.sdk.api.APIExecutionInterface;
import tr.com.kuveytturk.api.client.sdk.api.APIExecutor;
import tr.com.kuveytturk.api.client.sdk.model.callresult.APICallResult;
import tr.com.kuveytturk.api.client.sdk.model.callresult.AccessTokenRetrievalResult;
import tr.com.kuveytturk.api.client.sdk.model.misc.QueryParameterBean;
import tr.com.kuveytturk.api.client.sdk.model.misc.QueryParameterListBean;
import tr.com.kuveytturk.api.client.sdk.model.response.ResponsePayloadBean;
import tr.com.kuveytturk.api.test.domains.base.AbstractAPITest;
import tr.com.kuveytturk.api.test.outputcontract.AccountTransactionOutputBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Class implementing routines for testing Accounts related tests.
 *
 * @author      Fikri Aydemir
 * @version     1.0
 * @since       2020-01-12
 */
public class AccountTransactionsAPITest extends AbstractAPITest {

    public AccountTransactionsAPITest(){
        super();
    }

    @Test
    @CitrusTest
    public void testAccountTransactions() {
        List<Integer> testCustomersList = super.getTestCustomerList();
        assertTrue("Test Müşteri numaraları çekilemedi.", testCustomersList != null && !testCustomersList.isEmpty());
        assertTrue("Yeterli sayıda test müşterisi çekilemedi.", testCustomersList.size() > 0);
        int randomIndex = getRandomInt(0, testCustomersList.size()-1);
        int testCustomerId = new Integer(testCustomersList.get(randomIndex).intValue());

        AccessTokenRetrievalResult accessTokenResultObject = fetchAccessTokenWithAuthorizationCodeFlow(testCustomerId);
        assertTrue(
                "Hesap hareketlerini getirmek amacıyla şifre ile access token temin edilirken hata oluştu. \nHATA DETAYI:\n"
                        +  accessTokenResultObject.getErrorDetailsIfAny(),
                accessTokenResultObject.isSuccessful());

        QueryParameterListBean queryParamList = new QueryParameterListBean();

        QueryParameterBean paramBeanForBeginDate = new QueryParameterBean("beginDate", "2010-01-01");
        queryParamList.add(paramBeanForBeginDate);

        String datePattern = "yyyy-MM-dd";
        SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        String endDate = dateFormatter.format(calendar.getTime());
        QueryParameterBean paramBeanForEndDate = new QueryParameterBean("endDate", endDate);
        queryParamList.add(paramBeanForEndDate);

        QueryParameterBean paramBeanForMinAmount = new QueryParameterBean("minAmount", "0");
        queryParamList.add(paramBeanForMinAmount);

        QueryParameterBean paramBeanForMaxAmount = new QueryParameterBean("maxAmount", "10000000");
        queryParamList.add(paramBeanForMaxAmount);

        APIExecutionInterface<ArrayList<LinkedTreeMap>> apiHandle = new APIExecutor<>();

        APICallResult<ArrayList<LinkedTreeMap>> apiCallResult =
                apiHandle.doGet("v1/accounts/1/transactions",
                                          accessTokenResultObject.getAccessToken(),
                                          queryParamList);
        assertTrue("v1/accounts/1/transactions endpoitinin çağrımı hata verdi",apiCallResult.isAPICallSuccessful());

        if(apiCallResult.isAPICallSuccessful()){
            if (apiCallResult.getApiResponseAsRawText() != null && !apiCallResult.getApiResponseAsRawText().isEmpty()) {
                ResponsePayloadBean<ArrayList<LinkedTreeMap>> payload = apiCallResult.getResponsePayload();
                if (payload != null && payload.getValue() != null) {
                    List<LinkedTreeMap> accountTransactionsList =  payload.getValue();
                    assertTrue("Account Transactions dolu gelmeli!", accountTransactionsList.isEmpty() == false);
                    System.out.println("TRANSACTIONS LIST: ");
                    for (int i= 0; i < accountTransactionsList.size(); i++ ) {
                        AccountTransactionOutputBean bean = new AccountTransactionOutputBean(accountTransactionsList.get(i));
                        System.out.println(bean.toString());
                    }
                }
            }
        }
    }
}
