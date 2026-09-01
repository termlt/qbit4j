package org.termlt.qbit4j.api;

import org.junit.jupiter.api.BeforeEach;
import org.termlt.qbit4j.api.auth.AuthApi;
import org.termlt.qbit4j.http.QBitHttpClient;

public abstract class QBitIntegrationTest {

    protected static final String HOST = "http://localhost:8080";
    protected static final String USERNAME = "admin";
    protected static final String PASSWORD = "admin1";

    protected QBitHttpClient httpClient;

    @BeforeEach
    void setUpHttpClient() {
        httpClient = new QBitHttpClient();
    }

    protected void loginAsDefaultUser() {
        new AuthApi(httpClient, HOST, USERNAME, PASSWORD).login();
    }
}