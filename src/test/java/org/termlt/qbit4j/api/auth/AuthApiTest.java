package org.termlt.qbit4j.api.auth;

import org.junit.jupiter.api.Test;
import org.termlt.qbit4j.api.QBitIntegrationTest;
import org.termlt.qbit4j.exception.AuthException;
import org.termlt.qbit4j.exception.QBitException;
import org.termlt.qbit4j.http.QBitHttpClient;

import static org.junit.jupiter.api.Assertions.*;

public class AuthApiTest extends QBitIntegrationTest {

    @Test
    void loginSucceedsWithValidCredentials() {
        AuthApi auth = new AuthApi(httpClient, HOST, USERNAME, PASSWORD);

        assertDoesNotThrow(auth::login);
    }

    @Test
    void logoutSucceedsAfterLogin() {
        AuthApi auth = new AuthApi(httpClient, HOST, USERNAME, PASSWORD);
        auth.login();

        assertDoesNotThrow(auth::logout);
    }

    @Test
    void loginFailsWithWrongPassword() {
        AuthApi auth = new AuthApi(httpClient, HOST, USERNAME, "wrongPass");

        AuthException exception = assertThrows(AuthException.class, auth::login);
        assertTrue(exception.getMessage().contains("invalid credentials"));
    }

    @Test
    void loginFailsWithWrongUsername() {
        AuthApi auth = new AuthApi(httpClient, HOST, "notRealUser", PASSWORD);

        assertThrows(AuthException.class, auth::login);
    }

    @Test
    void logoutWithoutPriorLoginThrows() {
        AuthApi auth = new AuthApi(httpClient, HOST, USERNAME, PASSWORD);

        assertThrows(QBitException.class, auth::logout);
    }
}
