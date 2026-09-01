package org.termlt.qbit4j.api.log;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.termlt.qbit4j.api.QBitIntegrationTest;
import org.termlt.qbit4j.model.log.Log;
import org.termlt.qbit4j.model.log.LogFilter;
import org.termlt.qbit4j.model.log.PeerLog;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LogApiTest extends QBitIntegrationTest {

    private LogApi log;

    @BeforeEach
    void setUp() {
        loginAsDefaultUser();
        log = new LogApi(httpClient, HOST);
    }

    @Test
    void getLogsDoesNotThrow() {
        assertDoesNotThrow(() -> log.getLogs(LogFilter.defaults()));
    }

    @Test
    void getLogsReturnsEntriesWithNonNullTypes() {
        List<Log> entries = log.getLogs(LogFilter.defaults());

        assertNotNull(entries);
        entries.forEach(entry -> assertNotNull(entry.type()));
    }

    @Test
    void getPeerLogsDoesNotThrow() {
        assertDoesNotThrow(() -> log.getPeerLogs(LogApi.NO_LAST_KNOWN_ID));
    }

    @Test
    void getPeerLogsReturnsEntriesWithNonNullTypes() {
        List<PeerLog> entries = log.getPeerLogs(LogApi.NO_LAST_KNOWN_ID);

        assertNotNull(entries);
        entries.forEach(entry -> assertNotNull(entry.timestamp()));
    }
}
