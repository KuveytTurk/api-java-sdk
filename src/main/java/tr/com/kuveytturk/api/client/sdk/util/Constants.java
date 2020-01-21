/*
 * Copyright (c) 2020
 * KUVEYT TÜRK PARTICIPATION BANK INC.
 *
 * Author: Fikri Aydemir
 *
 * Project: Java SDK for Accessing API Endpoints
 */

package tr.com.kuveytturk.api.client.sdk.util;

public class Constants {

    public static String BASE_AUTHORIZATION_URL = "https://idprep.kuveytturk.com.tr/";
    public static String AUTHORIZATION_ENDPOINT = "api/connect/authorize";
    public static String BASE_ACCESS_TOKEN_URL = "https://idprep.kuveytturk.com.tr/";
    public static String ACCESS_TOKEN_ENDPOINT = "api/connect/token";
    public static String REVOKE_TOKEN_ENDPOINT = "api/connect/revocation";
    public static String BASE_API_ACCESS_URL = "https://apitest.kuveytturk.com.tr/prep/";

    public static final String DEFAULT_REDIRECT_URI = "http://kuveytturk.com.tr";
    public static final int DEFAULT_TEST_ACCOUNT_NUMBER = 135017;
    public static final int DEFAULT_CUSTOMER_PASSWORD = 12121212;
    public final static String CONTENT_TYPE = "application/json";
    public static final String REDIRECT_URI_LABEL = "redirect_uri";
    public static final String RESPONSE_TYPE_LABEL = "response_type";
    public static final String RESPONSE_TYPE_VALUE = "code";
    public static final String CLIENT_ID_LABEL = "client_id";
    public static final String CLIENT_SECRET_LABEL = "client_secret";
    public static final String SCOPE_LABEL = "scope";
    public static final String DEFAULT_CLIENT_ID = "60b8ec00fc5647d59da7a368ef252616";
    public static final String DEFAULT_CLIENT_SECRET = "DcVC7HRevD0X3f9oCgcUpPieGO51OVqYqROH1L+gtSX8+sDwKcuDvw==";
    public static final String DEFAULT_SCOPE_LIST_STRING = "loans transfers public payments accounts offline_access";
    public static final String GRANT_TYPE_LABEL = "grant_type";
    public static String AUTHORIZATION_CODE_LABEL = "authorization_code";
    public static String CLIENT_CREDENTIALS_LABEL = "client_credentials";
    public static final String BEARER_PREFIX = "Bearer ";

    public final static String DEFAULT_PUBLIC_KEY = "-----BEGIN PUBLIC KEY-----\n" +
            "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDUTFqNTDqWj7O7yUIomESwIkM9\n" +
            "Q8UtICQOsK+rBqJeel2/veBbVOK3n+n8A/OhTIaLCjfZeIi41zDKpNStLgQFaUUQ\n" +
            "noakDx+KcBDqI3U5Ts4ofDxtYikYT44Hg+RwzfWddoY6bv23ppT08p7cszBtWZkB\n" +
            "okDHaGmEHp1R2w4ZUwIDAQAB\n" +
            "-----END PUBLIC KEY-----\n";

    public final static String DEFAULT_PRIVATE_KEY = "-----BEGIN PRIVATE KEY-----\n" +
            "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBANRMWo1MOpaPs7vJ\n" +
            "QiiYRLAiQz1DxS0gJA6wr6sGol56Xb+94FtU4ref6fwD86FMhosKN9l4iLjXMMqk\n" +
            "1K0uBAVpRRCehqQPH4pwEOojdTlOzih8PG1iKRhPjgeD5HDN9Z12hjpu/bemlPTy\n" +
            "ntyzMG1ZmQGiQMdoaYQenVHbDhlTAgMBAAECgYBFgqJ7dSQRvAdrSuBAjmqfCPjf\n" +
            "DFt5BPcJYEyEQO3U5VfgufjFrqt02AUyoNCaVYYP7E6RA+gwLTUqhIGmGlTyH376\n" +
            "Ruiz65CqPo8ZfWxOnQiywMJwFk2u4CpmdI3IJODLfF38Ps8Vwaqhr95koNT7e6mS\n" +
            "rpPKGxCvj/L9qsO1AQJBAPwL40MKlyp8B63iajMXZITrb2Yc8ZESPLkdu2X21kdT\n" +
            "mQKokdauaew9JGHkElu1J9WyUXZULEekLr4/FvRnQUECQQDXoNqiVrj46chMJrh7\n" +
            "c8I1h2MlqnYa8MMh43DmBR8uynsGlz0eFIEjcbYM4A3+5FNrEtN7wY2pE6kGJQp3\n" +
            "imGTAkEArFk8qAU/5Q82+RJP6Gvgknuji0HTdY3w8+x+znSBhfiGMqkuQIy3ZZFR\n" +
            "pZadbxRrDteGmNFqDfsY84KUob9RgQJAOrW9Ub4zFvLwamuQh2x5UIHQaQ0Eo0ky\n" +
            "mCOJNdfnKaJP5PeA2JPUpYXsf4zxwpkAbYLuuh91JrgHqXikZO/0qQJBAIqQiiHv\n" +
            "oGIUEE5y3RZ1dOsvGPbaavXG/OxtyAvjl5tfBSt1zmHMQi46ZuFrHSr54uc4BDfD\n" +
            "gDa5DmMqw0sTrhY=\n" +
            "-----END PRIVATE KEY-----\n";
;
    public static void setAccessProperties(String baseAuthorizationUrl,
                                           String authorizationEndPoint,
                                           String baseAccessTokenUrl,
                                           String accessTokenEndpoint,
                                           String apiAccessUrl) {
        BASE_AUTHORIZATION_URL = baseAuthorizationUrl;
        AUTHORIZATION_ENDPOINT = authorizationEndPoint;
        BASE_ACCESS_TOKEN_URL = baseAccessTokenUrl;
        ACCESS_TOKEN_ENDPOINT = accessTokenEndpoint;
        BASE_API_ACCESS_URL = apiAccessUrl;
    }
}
