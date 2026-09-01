package org.termlt.qbit4j.model.log;

import com.alibaba.fastjson2.JSON;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LogTest {

    @Test
    void logTypeSerializesToItsCode() {
        assertEquals("1", JSON.toJSONString(LogType.NORMAL));
        assertEquals("2", JSON.toJSONString(LogType.INFO));
        assertEquals("4", JSON.toJSONString(LogType.WARNING));
        assertEquals("8", JSON.toJSONString(LogType.CRITICAL));
    }

    @Test
    void logEntryDeserializesCodeBackToLogType() {
        String json = """
                {"id": 42, "message": "qBittorrent is running", "timestamp": 1662732893, "type": 8}
                """;

        Log entry = JSON.parseObject(json, Log.class);

        assertEquals(42, entry.id());
        assertEquals("qBittorrent is running", entry.message());
        assertEquals(1662732893, entry.timestamp());
        assertEquals(LogType.CRITICAL, entry.type());
    }

    @Test
    void logEntryArrayDeserializesEachTypeCorrectly() {
        String json = """
                [
                  {"id": 1, "message": "a", "timestamp": 1, "type": 1},
                  {"id": 2, "message": "b", "timestamp": 2, "type": 2},
                  {"id": 3, "message": "c", "timestamp": 3, "type": 4},
                  {"id": 4, "message": "d", "timestamp": 4, "type": 8}
                ]
                """;

        List<Log> entries = JSON.parseArray(json, Log.class);

        assertEquals(LogType.NORMAL, entries.get(0).type());
        assertEquals(LogType.INFO, entries.get(1).type());
        assertEquals(LogType.WARNING, entries.get(2).type());
        assertEquals(LogType.CRITICAL, entries.get(3).type());
    }
}
