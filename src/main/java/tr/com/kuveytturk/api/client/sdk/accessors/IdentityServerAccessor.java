/*
 * Copyright (c) 2020
 * KUVEYT TÜRK PARTICIPATION BANK INC.
 *
 * Author: Fikri Aydemir
 *
 * Project: Java SDK for Accessing API Endpoints
 */

package tr.com.kuveytturk.api.client.sdk.accessors;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tr.com.kuveytturk.api.client.sdk.model.request.AccessTokenRequestBean;
import tr.com.kuveytturk.api.client.sdk.model.request.AuthorizationCodeRequestBean;
import tr.com.kuveytturk.api.client.sdk.model.response.AccessTokenResponseBean;
import tr.com.kuveytturk.api.client.sdk.services.AccessTokenRetrievalService;
import tr.com.kuveytturk.api.client.sdk.services.base.AccessTokenRetrievalServiceAPI;
import tr.com.kuveytturk.api.client.sdk.util.AccessTokenRetrievalException;
import tr.com.kuveytturk.api.client.sdk.util.AuthorizationCodeRetrievalException;
import tr.com.kuveytturk.api.client.sdk.util.Constants;

import static com.codeborne.selenide.Selenide.*;

/**
 * Concrete class implementing abstract class AbstractIdentityServerAccessor
 *
 * @author      Fikri Aydemir
 * @version     1.0
 * @since       2020-01-12
 */
public class IdentityServerAccessor extends AbstractIdentityServerAccessor {

    public IdentityServerAccessor() {
        this(Constants.BASE_AUTHORIZATION_URL,
                Constants.AUTHORIZATION_ENDPOINT,
                Constants.BASE_AUTHORIZATION_URL,
                Constants.ACCESS_TOKEN_ENDPOINT,
                Constants.REVOKE_TOKEN_ENDPOINT);
    }

    public IdentityServerAccessor(String baseAuthorizationUrl,
                                  String authorizationEndpoint,
                                  String baseAccessTokenURL,
                                  String accessTokenEndpoint,
                                  String revokeTokenEndPoint) {
        setBaseAuthorizationUrl(baseAuthorizationUrl);
        setAuthorizationEndpoint(authorizationEndpoint);
        setBaseAccessTokenURL(baseAccessTokenURL);
        setAccessTokenEndpoint(accessTokenEndpoint);
        setRevokeTokenEndPoint(revokeTokenEndPoint);
        setAuthorizationCodeRetrievalUrl(getBaseAuthorizationUrl() + getAuthorizationEndpoint());
        setAccessTokenRetrievalUrl(getBaseAuthorizationUrl() + getAccessTokenEndpoint());
    }

    @Override
    public String requestAuthorizationCode(AuthorizationCodeRequestBean requestBean) throws AuthorizationCodeRetrievalException {
        return this.requestAuthorizationCode(requestBean.getCustomerNumber(),
                requestBean.getClientId(),
                requestBean.getRedirectURI(),
                requestBean.getScopeList().toString());
    }

    @Override
    public String requestAuthorizationCode(int customerNumber,
                                           String clientId,
                                           String redirectURI,
                                           String scopeListString) throws AuthorizationCodeRetrievalException {

        String accessURL = super.getAuthorizationCodeRetrievalUrl()
                + super.buildAuthorizationQueryString(clientId, redirectURI, scopeListString);

        String urlContainingTheCode = null;

        for(int i = 0; i < 3; i++) {
            try {
                open(accessURL);
                //String winHandleBefore = WebDriverRunner.getWebDriver().getWindowHandle();
                $(By.name("customerId")).setValue(String.valueOf(customerNumber));
                $(By.name("password")).setValue(String.valueOf(Constants.DEFAULT_CUSTOMER_PASSWORD));
                $(By.tagName("button")).click();
                $(By.name("password")).setValue("123456");
                $(By.id("ProceedButton")).$(By.tagName("button")).click();
                $(By.id("NextButton")).$(By.tagName("button")).click();
                //$(By.name("rdParticipation")).exists();//isSelected();
                //$(By.name("rdMaturity")).exists();

                //WebDriverRunner.getWebDriver().getCurrentUrl();
               WebDriverWait wait = new WebDriverWait(WebDriverRunner.getWebDriver(), 1);
               wait.until(ExpectedConditions.urlContains("code="));
                //wait.withTimeout(Duration.ofSeconds(2));//(ExpectedConditions.urlContains("code="));
                //Alert alert = WebDriverRunner.getWebDriver().switchTo().alert();
                //alert.accept();
                //DesiredCapabilities caps = new DesiredCapabilities();
                //ChromeOptions options = new ChromeOptions();
                //options.addArguments("--enable-popup-blocking");
                //WebDriverRunner.webdriverContainer.getWebDriver().setCapability(ChromeOptions.CAPABILITY, options);

                if(WebDriverRunner.getWebDriver().getCurrentUrl().contains("code=")){
                    urlContainingTheCode = WebDriverRunner.getWebDriver().getCurrentUrl();
                    break;
                } else if (i<=2){
                    closeWindow();
                    continue;
                } else {
                    AuthorizationCodeRetrievalException exp =
                            new AuthorizationCodeRetrievalException("Unable to parse the authorization code from the address bar of the internet browser after 3 attempts!");
                    throw exp;
                }
            } catch (Exception e) {
                if(i <= 2) {
                    closeWindow();
                    continue;
                }
                AuthorizationCodeRetrievalException exp =
                        new AuthorizationCodeRetrievalException(
                                e.getMessage(),
                                e,
                                "Unable to parse the HTML elements in identity server provided page that is available at: " + WebDriverRunner.getWebDriver().getCurrentUrl());
                throw exp;
            }
        }

        String theCode = super.parseCodeString(urlContainingTheCode);
        closeWindow();

        return theCode;
    }

    @Override
    public void doLogOut() throws AuthorizationCodeRetrievalException {
        try {
            open(Constants.BASE_AUTHORIZATION_URL + "/#/logout");
        } catch (Exception e) {
            AuthorizationCodeRetrievalException exp =
                    new AuthorizationCodeRetrievalException(
                            e.getMessage(),
                            e,
                            "Error occurred while attempting to parse the content of the IdentityServerAccessor pages!");
            throw exp;
        }
    }

    @Override
    public AccessTokenResponseBean requestAccessTokenWithAuthorizationCode(AccessTokenRequestBean accessTokenRequestBean)
            throws AccessTokenRetrievalException {

        return requestAccessTokenWithAuthorizationCode(
                accessTokenRequestBean.getClientId(),
                accessTokenRequestBean.getClientSecret(),
                accessTokenRequestBean.getAuthorizationCode(),
                accessTokenRequestBean.getRedirectURI());
    }

    @Override
    public AccessTokenResponseBean requestAccessTokenWithAuthorizationCode(String clientId,
                                                                           String clientSecret,
                                                                           String code,
                                                                           String redirectUri) throws AccessTokenRetrievalException {

        AccessTokenRetrievalServiceAPI accessor = new AccessTokenRetrievalService(getBaseAccessTokenURL(), getAccessTokenEndpoint(), getRevokeTokenEndPoint());
        return accessor.requestAccessTokenWithAuthorizationCode(clientId, clientSecret, code, redirectUri);
    }

    @Override
    public AccessTokenResponseBean requestAccessTokenWithClientCredentials(AccessTokenRequestBean accessTokenRequestBean)
            throws AccessTokenRetrievalException {

        return requestAccessTokenWithClientCredentials(
                accessTokenRequestBean.getClientId(),
                accessTokenRequestBean.getClientSecret(),
                accessTokenRequestBean.getScopeList().toString());
    }

    @Override
    public AccessTokenResponseBean requestAccessTokenWithClientCredentials(String clientId,
                                                                           String clientSecret,
                                                                           String scope) throws AccessTokenRetrievalException {

        AccessTokenRetrievalServiceAPI accessor = new AccessTokenRetrievalService(getBaseAccessTokenURL(), getAccessTokenEndpoint(), getRevokeTokenEndPoint());
        return accessor.requestAccessTokenWithClientCredentials(clientId, clientSecret, scope);
    }

    @Override
    public AccessTokenResponseBean requestAccessTokenWithRefreshToken(String clientId,
                                                                      String clientSecret,
                                                                      String refreshToken) throws AccessTokenRetrievalException {

        AccessTokenRetrievalServiceAPI accessor = new AccessTokenRetrievalService(getBaseAccessTokenURL(), getAccessTokenEndpoint(), getRevokeTokenEndPoint());
        return accessor.requestAccessTokenWithRefreshToken(clientId, clientSecret, refreshToken);
    }

    @Override
    public AccessTokenResponseBean revokeAccessToken(String clientId,
                                                     String clientSecret,
                                                     String accessToken) throws AccessTokenRetrievalException {

        AccessTokenRetrievalServiceAPI accessor = new AccessTokenRetrievalService(getBaseAccessTokenURL(), getAccessTokenEndpoint(), getRevokeTokenEndPoint());
        return accessor.revokeAccessToken(clientId, clientSecret, accessToken);
    }

    @Override
    public AccessTokenResponseBean revokeRefreshToken(String clientId,
                                                      String clientSecret,
                                                      String refreshToken) throws AccessTokenRetrievalException {

        AccessTokenRetrievalServiceAPI accessor = new AccessTokenRetrievalService(getBaseAccessTokenURL(), getAccessTokenEndpoint(), getRevokeTokenEndPoint());
        return accessor.revokeRefreshToken(clientId, clientSecret, refreshToken);
    }

}
