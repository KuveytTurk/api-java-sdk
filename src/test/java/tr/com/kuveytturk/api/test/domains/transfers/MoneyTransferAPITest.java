/*
 * Copyright (c) 2020
 * KUVEYT TÜRK PARTICIPATION BANK INC.
 *
 * Author: Fikri Aydemir
 *
 * Project: Java SDK for Accessing API Endpoints
 */

package tr.com.kuveytturk.api.test.domains.transfers;

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
import tr.com.kuveytturk.api.test.inputcontract.MoneyTransferExecuteInputBean;
import tr.com.kuveytturk.api.test.inputcontract.MoneyTransferInputBean;
import tr.com.kuveytturk.api.test.outputcontract.AccountTransactionOutputBean;
import tr.com.kuveytturk.api.test.outputcontract.MoneyTransferOutputBean;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Class implementing routines for testing money transfer related operations.
 *
 * @author      Fikri Aydemir
 * @version     1.0
 * @since       2020-01-12
 */
public class MoneyTransferAPITest extends AbstractAPITest {

    private static BigDecimal TRANSFER_AMOUNT = new BigDecimal(10);

    @Test
    @CitrusTest
    public void testMoneyTransferToAccount() {

        List<Integer> testCustomersList = super.getTestCustomerList();
        assertTrue("Test Müşteri numaraları çekilemedi.", testCustomersList != null && !testCustomersList.isEmpty());
        assertTrue("Yeterli sayıda test müşterisi çekilemedi.", testCustomersList.size() >= 2);
        int senderCustomerId = new Integer(testCustomersList.get(0).intValue());
        int receiverCustomerId = new Integer(testCustomersList.get(1).intValue());
        AccessTokenRetrievalResult accessTokenretrievalResult = fetchAccessTokenWithAuthorizationCodeFlow(senderCustomerId);

        assertTrue(senderCustomerId +
                        " müşteri nosu için accesstoken alınamadı!\n HATA DETAYI:\n " +
                        accessTokenretrievalResult.getErrorDetailsIfAny(),
                accessTokenretrievalResult.isSuccessful());

        MoneyTransferOutputBean moneyTransferOutputBean = null;

        MoneyTransferInputBean inputBean = new MoneyTransferInputBean();
        inputBean.setSenderAccountSuffix("1");
        inputBean.setReceiverAccountNumber(String.valueOf(receiverCustomerId));
        inputBean.setReceiverAccountSuffix("1");
        inputBean.setReceiverName("Miktat Behçet");
        inputBean.setReceiverBankId(10);
        inputBean.setReceiverBranchId(909);
        inputBean.setPaymentTypeId(1);
        inputBean.setComment("TRANSFER BY API TEST FRAMEWORK");
        inputBean.setAmount(TRANSFER_AMOUNT);
        String jsonBody = inputBean.toJson();

        APIExecutionInterface<MoneyTransferOutputBean> apiHandle = new APIExecutor<>();

        APICallResult<MoneyTransferOutputBean> apiCallResult =
                apiHandle.doPost("v1/transfers/ToAccount",
                        accessTokenretrievalResult.getAccessToken(),
                        jsonBody);

        assertTrue("v1/transfers/ToAccount endpoitinin çağırımı hata verdi.\nHATA DETAYI:\n "
                        + apiCallResult.getErrorDetailsIfAny(),
                apiCallResult.isAPICallSuccessful());

        if(apiCallResult.isAPICallSuccessful()){
            if (apiCallResult.getApiResponseAsRawText() != null && !apiCallResult.getApiResponseAsRawText().isEmpty()) {
                ResponsePayloadBean<MoneyTransferOutputBean> payload = apiCallResult.getResponsePayload();

                assertTrue("v1/transfers/ToAccount endpoitinin çağırımı sırasında ResponsePayload boş döndü!", payload != null && payload.getValue() != null);

                if (payload != null && payload.getValue() != null) {

                    moneyTransferOutputBean =  MoneyTransferOutputBean.fromJsonToPOJO(payload.getValue());
                    assertTrue("v1/transfers/ToAccount endpoitinin çağırımı sırasında SecureOTPId alanı boş döndü!", moneyTransferOutputBean.getSecureOtpId() != null && !moneyTransferOutputBean.getSecureOtpId().isEmpty());
                    assertTrue("v1/transfers/ToAccount endpoitinin çağırımı sırasında ReceiverName alanı boş döndü!", moneyTransferOutputBean.getReceiverName() != null && !moneyTransferOutputBean.getSecureOtpId().isEmpty());

                    boolean executeResult = testMoneyTransferExecute(moneyTransferOutputBean, accessTokenretrievalResult.getAccessToken(), receiverCustomerId);
                    assertTrue("v1/transfers/ToAccount endpoitinin çağırımı başarısızlıkla sonuçlandı", executeResult);
                }
            }
        }
    }

    private boolean testMoneyTransferExecute(MoneyTransferOutputBean moneyTransferOutputBean, String accessToken, int receiverCustomerId){

        MoneyTransferExecuteInputBean inputBean = new MoneyTransferExecuteInputBean();
        inputBean.setSecureOtpId(moneyTransferOutputBean.getSecureOtpId());
        inputBean.setSmsOtp("4976782");
        String jsonBody = inputBean.toJson();

        APIExecutionInterface<MoneyTransferOutputBean> apiHandle = new APIExecutor<>();

        APICallResult apiCallResult =
                apiHandle.doPost("v1/transfers/execute",
                        accessToken,
                        jsonBody);

        assertTrue(
                receiverCustomerId +
                        " nolu müşteri için v1/transfers/execute endpoitinin çağırımı hata verdi.\n HATA DETAYLARI: "
                        + apiCallResult.getErrorDetailsIfAny(),
                apiCallResult.isAPICallSuccessful());

        if(apiCallResult.isAPICallSuccessful()){
            if (apiCallResult.getApiResponseAsRawText() != null && !apiCallResult.getApiResponseAsRawText().isEmpty()) {
                testAccountTransactions(receiverCustomerId);
            }
        }

        return true;
    }

    private void testAccountTransactions(int receiverCustomerId) {
        AccessTokenRetrievalResult accessTokenResultObject = fetchAccessTokenWithAuthorizationCodeFlow(receiverCustomerId);
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
