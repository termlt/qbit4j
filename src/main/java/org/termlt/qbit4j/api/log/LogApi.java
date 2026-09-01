package org.termlt.qbit4j.api.log;

import com.alibaba.fastjson2.JSON;
import org.termlt.qbit4j.http.FormEncoder;
import org.termlt.qbit4j.http.QBitEndpoint;
import org.termlt.qbit4j.http.QBitHttpClient;
import org.termlt.qbit4j.model.log.Log;
import org.termlt.qbit4j.model.log.LogFilter;
import org.termlt.qbit4j.model.log.PeerLog;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LogApi {

    private final QBitHttpClient httpClient;
    private final String host;

    public LogApi(QBitHttpClient httpClient, String host) {
        this.httpClient = httpClient;
        this.host = host;
    }

    /**
     * @see <a href="https://github.com/qbittorrent/qBittorrent/wiki/WebUI-API-(qBittorrent-5.0)#get-log">qBittorrent WebUI API - Get logs</a>
     */
    public List<Log> getLogs(LogFilter filter) {
        Map<String, String> params = new LinkedHashMap<>();
        params.put("normal", String.valueOf(filter.normal()));
        params.put("info", String.valueOf(filter.info()));
        params.put("warning", String.valueOf(filter.warning()));
        params.put("critical", String.valueOf(filter.critical()));
        params.put("last_known_id", String.valueOf(filter.lastKnownId()));

        String queryString = FormEncoder.buildFormBody(params);

        HttpResponse<String> response = httpClient.send(host, QBitEndpoint.LOG_GET_LOGS, queryString, HttpRequest.BodyPublishers.noBody());
        httpClient.requireOk(response, "Failed to get logs");

        return JSON.parseArray(response.body(), Log.class);
    }

    public static final int NO_LAST_KNOWN_ID = -1;

    /**
     * @param lastKnownId Exclude messages with "message id" <= last_known_id. Use {@link #NO_LAST_KNOWN_ID} for no filtering.
     * @see <a href="https://github.com/qbittorrent/qBittorrent/wiki/WebUI-API-(qBittorrent-5.0)#get-peer-log">qBittorrent WebUI API - Get peer logs</a>
     */
    public List<PeerLog> getPeerLogs(int lastKnownId) {
        Map<String, String> params = Map.of("last_known_id", String.valueOf(lastKnownId));
        String queryString = FormEncoder.buildFormBody(params);

        HttpResponse<String> response = httpClient.send(host, QBitEndpoint.LOG_GET_PEER_LOGS, queryString, HttpRequest.BodyPublishers.noBody());
        httpClient.requireOk(response, "Failed to get peer logs");

        return JSON.parseArray(response.body(), PeerLog.class);
    }
}
