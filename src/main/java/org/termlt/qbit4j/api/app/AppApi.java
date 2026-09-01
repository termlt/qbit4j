package org.termlt.qbit4j.api.app;

import com.alibaba.fastjson2.JSON;
import org.termlt.qbit4j.http.FormEncoder;
import org.termlt.qbit4j.http.QBitHttpClient;
import org.termlt.qbit4j.http.QBitEndpoint;
import org.termlt.qbit4j.model.app.BuildInfo;
import org.termlt.qbit4j.model.app.Cookie;
import org.termlt.qbit4j.model.app.Preferences;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class AppApi {

    private final QBitHttpClient httpClient;
    private final String host;

    public AppApi(QBitHttpClient httpClient, String host) {
        this.httpClient = httpClient;
        this.host = host;
    }

    /**
     * @see <a href="https://github.com/qbittorrent/qBittorrent/wiki/WebUI-API-(qBittorrent-5.0)#get-application-version">qBittorrent WebUI API - Get application version</a>
     */
    public String version() {
        HttpResponse<String> response = httpClient.send(host, QBitEndpoint.APP_VERSION, null, HttpRequest.BodyPublishers.noBody());
        httpClient.requireOk(response, "Failed to get application version");
        return response.body();
    }

    /**
     * @see <a href="https://github.com/qbittorrent/qBittorrent/wiki/WebUI-API-(qBittorrent-5.0)#get-api-version">qBittorrent WebUI API - Get API version</a>
     */
    public String webApiVersion() {
        HttpResponse<String> response = httpClient.send(host, QBitEndpoint.APP_WEBAPI_VERSION, null, HttpRequest.BodyPublishers.noBody());
        httpClient.requireOk(response, "Failed to get WebAPI version");
        return response.body();
    }

    /**
     * @see <a href="https://github.com/qbittorrent/qBittorrent/wiki/WebUI-API-(qBittorrent-5.0)#get-build-info">qBittorrent WebUI API - Get build info</a>
     */
    public BuildInfo buildInfo() {
        HttpResponse<String> response = httpClient.send(host, QBitEndpoint.APP_BUILD_INFO, null, HttpRequest.BodyPublishers.noBody());
        httpClient.requireOk(response, "Failed to get build info");

        return JSON.parseObject(response.body(), BuildInfo.class);
    }

    /**
     * @see <a href="https://github.com/qbittorrent/qBittorrent/wiki/WebUI-API-(qBittorrent-5.0)#shutdown-application">qBittorrent WebUI API - Shutdown the application</a>
     */
    public void shutdown() {
        HttpResponse<String> response = httpClient.send(host, QBitEndpoint.APP_SHUTDOWN, null, HttpRequest.BodyPublishers.noBody());
        httpClient.requireOk(response, "Failed to shutdown the application");
    }

    /**
     * @see <a href="https://github.com/qbittorrent/qBittorrent/wiki/WebUI-API-(qBittorrent-5.0)#preferences">qBittorrent WebUI API - Get application preferences</a>
     */
    public Preferences preferences() {
        HttpResponse<String> response = httpClient.send(host, QBitEndpoint.APP_GET_PREFERENCES, null, HttpRequest.BodyPublishers.noBody());
        httpClient.requireOk(response, "Failed to get application preferences");

        return JSON.parseObject(response.body(), Preferences.class);
    }

    /**
     * @see <a href="https://github.com/qbittorrent/qBittorrent/wiki/WebUI-API-(qBittorrent-5.0)#setPreferences">qBittorrent WebUI API - Set application preferences</a>
     */
    public void setPreferences(Map<String, Object> newPreferences) {
        String form = FormEncoder.buildFormBody(Map.of("json", JSON.toJSONString(newPreferences)));
        HttpRequest.BodyPublisher body = HttpRequest.BodyPublishers.ofString(form);

        HttpResponse<String> response = httpClient.send(host, QBitEndpoint.APP_SET_PREFERENCES, null, body);
        httpClient.requireOk(response, "Failed to set application preferences");
    }

    /**
     * @see <a href="https://github.com/qbittorrent/qBittorrent/wiki/WebUI-API-(qBittorrent-5.0)#get-default-save-path">qBittorrent WebUI API - Get application default save path</a>
     */
    public String defaultSavePath() {
        HttpResponse<String> response = httpClient.send(host, QBitEndpoint.APP_GET_DEFAULT_SAVE_PATH, null, HttpRequest.BodyPublishers.noBody());
        httpClient.requireOk(response, "Failed to get application default save path");

        return response.body();
    }

    /**
     * @see <a href="https://github.com/qbittorrent/qBittorrent/wiki/WebUI-API-(qBittorrent-5.0)#get-cookies">qBittorrent WebUI API - Get application cookies</a>
     */
    public List<Cookie> cookies() {
        HttpResponse<String> response = httpClient.send(host, QBitEndpoint.APP_GET_COOKIES, null, HttpRequest.BodyPublishers.noBody());
        httpClient.requireOk(response, "Failed to get application cookies");

        return JSON.parseArray(response.body(), Cookie.class);
    }

    /**
     * @see <a href="https://github.com/qbittorrent/qBittorrent/wiki/WebUI-API-(qBittorrent-5.0)#set-cookies">qBittorrent WebUI API - Set application cookies</a>
     */
    public void setCookies(List<Cookie> newCookies) {
        String json = JSON.toJSONString(newCookies);
        HttpResponse<String> response = httpClient.send(host, QBitEndpoint.APP_SET_COOKIES, null, HttpRequest.BodyPublishers.ofString(json));
        httpClient.requireOk(response, "Failed to set application cookies");
    }
}