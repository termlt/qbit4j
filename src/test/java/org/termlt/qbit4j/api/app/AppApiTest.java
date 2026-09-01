package org.termlt.qbit4j.api.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.termlt.qbit4j.api.QBitIntegrationTest;
import org.termlt.qbit4j.exception.QBitException;
import org.termlt.qbit4j.http.QBitHttpClient;
import org.termlt.qbit4j.model.app.BuildInfo;
import org.termlt.qbit4j.model.app.Preferences;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AppApiTest extends QBitIntegrationTest {

    private AppApi app;

    @BeforeEach
    void setUp() {
        loginAsDefaultUser();
        app = new AppApi(httpClient, HOST);
    }

    // App Version
    @Test
    void appVersionReturnsNonEmptyString() {
        assertFalse(app.version().isBlank());
    }

    @Test
    void appVersionMatchesExpectedFormat() {
        assertTrue(app.version().matches("v\\d+\\.\\d+.*"));
    }

    @Test
    void appVersionThrowsWithoutLogin() {
        QBitHttpClient freshClient = new QBitHttpClient();
        AppApi unauthenticatedApp = new AppApi(freshClient, HOST);

        assertThrows(QBitException.class, unauthenticatedApp::version);
    }

    // API version
    @Test
    void apiVersionReturnsNonEmptyString() {
        assertFalse(app.webApiVersion().isBlank());
    }

    @Test
    void apiVersionMatchesExpectedFormat() {
        assertTrue(app.webApiVersion().matches("\\d+(\\.\\d+)+"));
    }

    // Build info
    @Test
    void buildInfoReturnsPopulatedFields() {
        BuildInfo info = app.buildInfo();

        assertNotNull(info.qt());
        assertFalse(info.qt().isBlank());
        assertTrue(info.bitness() == 32 || info.bitness() == 64);
    }

    // Preferences
    @Test
    void preferencesReturnsPopulatedFields() {
        Preferences prefs = app.preferences();

        assertNotNull(prefs.currentNetworkInterface());
        assertTrue(prefs.announceIp().isBlank());
    }

    @Test
    void setPreferencesDoesNotThrow() {
        Map<String, Object> change = Map.of("queueing_enabled", true);

        assertDoesNotThrow(() -> app.setPreferences(change));
    }

    @Test
    void setPreferencesActuallyAppliesTheChange() {
        boolean originalValue = app.preferences().queueingEnabled();

        try {
            app.setPreferences(Map.of("queueing_enabled", !originalValue));

            boolean updatedValue = app.preferences().queueingEnabled();
            assertEquals(!originalValue, updatedValue);
        } finally {
            app.setPreferences(Map.of("queueing_enabled", originalValue));
        }
    }

    @Test
    void getDefaultSavePathDoesNotThrow() {
        assertDoesNotThrow(app::defaultSavePath);
    }

    @Test
    void getDefaultSavePathIsNotBlank() {
        assertFalse(app.defaultSavePath().isBlank());
    }

    @Test
    void getCookiesDoesNotThrow() {
        assertDoesNotThrow(app::cookies);
    }
}