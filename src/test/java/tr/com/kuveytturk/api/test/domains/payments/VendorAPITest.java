/*
 * Copyright (c) 2020
 * KUVEYT TÜRK PARTICIPATION BANK INC.
 *
 * Author: Fikri Aydemir
 *
 * Project: Java SDK for Accessing API Endpoints
 */

package tr.com.kuveytturk.api.test.domains.payments;

import com.consol.citrus.annotations.CitrusTest;
import org.junit.Test;
import tr.com.kuveytturk.api.client.sdk.api.APIExecutionInterface;
import tr.com.kuveytturk.api.client.sdk.api.APIExecutor;
import tr.com.kuveytturk.api.client.sdk.model.callresult.APICallResult;
import tr.com.kuveytturk.api.client.sdk.model.callresult.AccessTokenRetrievalResult;

import tr.com.kuveytturk.api.client.sdk.model.enums.ScopeType;
import tr.com.kuveytturk.api.client.sdk.model.misc.ScopeListBean;
import tr.com.kuveytturk.api.client.sdk.model.response.ResponsePayloadBean;
import tr.com.kuveytturk.api.test.domains.base.AbstractAPITest;
import tr.com.kuveytturk.api.test.inputcontract.*;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class VendorAPITest extends AbstractAPITest {
    public VendorAPITest(){
        super();
    }

    @Test
    @CitrusTest
    public void testVendorPurchase() {
        List<Integer> testCustomersList = super.getTestCustomerList();
        assertTrue("Test Müşteri numaraları çekilemedi.", testCustomersList != null && !testCustomersList.isEmpty());
        assertTrue("Yeterli sayıda test müşterisi çekilemedi.", testCustomersList.size() > 0);
        int randomIndex = getRandomInt(0, testCustomersList.size()-1);
        int testCustomerId = new Integer(testCustomersList.get(randomIndex).intValue());

        ScopeListBean scopeList = new ScopeListBean();
        scopeList.add(ScopeType.PAYMENTS).add(ScopeType.PUBLIC);
        AccessTokenRetrievalResult accessTokenResultObject = fetchAccessTokenWithClientCredentialsFlow(scopeList);
        assertTrue(
                "v1/purchase/vendor endpintinin çağırımı amacıyla access token temin edilirken hata oluştu. \nHATA DETAYI:\n"
                        +  accessTokenResultObject.getErrorDetailsIfAny(),
                accessTokenResultObject.isSuccessful());

        VendorBankInfoInputBean vendorBankInfoInputBean = new VendorBankInfoInputBean(59, 289, 2, "TL", "", "tr123456789012345678901234" );
        VendorAddressInfoInputBean vendorAddressInfoInputBean =  new VendorAddressInfoInputBean("?aml?bel cd sok n?mera ŞĞÜÇÇşş", 4,5,0,1);
        VendorContactInfoInputBean vendorContactInfoInputBean = new VendorContactInfoInputBean("uzman şüğçö", "", "mali i?ler", "", "camli@bel.tr", "kulaberler",  "]nver", "");
        VendorGeneralInfoInputBean vendorGeneralInfoInputBean = new VendorGeneralInfoInputBean( "WS CATCHA","",0,0);
        VendorInputBean vendorInputBean = new VendorInputBean( "12346778630", true, true, true, false, true, vendorGeneralInfoInputBean, vendorContactInfoInputBean, vendorAddressInfoInputBean, vendorBankInfoInputBean);
        VendorContainerBean vendor = new VendorContainerBean(vendorInputBean);

        APIExecutionInterface<Double> apiHandle = new APIExecutor<>();

        APICallResult<Double> apiCallResult =
                apiHandle.doPost("v1/purchase/vendor",
                        accessTokenResultObject.getAccessToken(),
                        vendor.toJson());
        assertTrue("v1/purchase/vendor endpoitinin çağırımı hata verdi",apiCallResult.isAPICallSuccessful());

        if(apiCallResult.isAPICallSuccessful()){
            if (apiCallResult.getApiResponseAsRawText() != null && !apiCallResult.getApiResponseAsRawText().isEmpty()) {
                ResponsePayloadBean<Double> payload = apiCallResult.getResponsePayload();
                if (payload != null && payload.getValue() != null) {
                    Integer value =  new Integer(payload.getValue().intValue());
                    assertTrue("Value değeri dolu gelmeli", value != null);
                    System.out.println("VALUE: " + String.valueOf(value));

                }
            }
        }
    }
}
