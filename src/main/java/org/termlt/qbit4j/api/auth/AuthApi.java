package org.termlt.qbit4j.api.auth;

import org.termlt.qbit4j.exception.AuthException;
import org.termlt.qbit4j.exception.QBitException;
import org.termlt.qbit4j.http.FormEncoder;
import org.termlt.qbit4j.http.QBitHttpClient;
import org.termlt.qbit4j.http.QBitEndpoint;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class AuthApi {

    private final QBitHttpClient httpClient;
    private final String host;
    private final String username;
    private final String password;

    public AuthApi(QBitHttpClient httpClient, String host, String username, String password) {
        this.httpClient = httpClient;
        this.host = host;
        this.username = username;
        this.password = password;
    }

    /**
     * @see <a href="https://github.com/qbittorrent/qBittorrent/wiki/WebUI-API-(qBittorrent-5.0)#login">qBittorrent WebUI API - Login</a>
     */
    public void login() {
        Map<String, String> params = Map.of(
                "username", username,
                "password", password
        );
        String form = FormEncoder.buildFormBody(params);
        HttpRequest.BodyPublisher body = HttpRequest.BodyPublishers.ofString(form, StandardCharsets.UTF_8);

        HttpResponse<String> response = httpClient.send(host, QBitEndpoint.LOGIN, null, body);

        if (response.statusCode() == 403) {
            throw new AuthException("Login blocked - IP banned for too many failed attempts");
        }
        if (!"Ok.".equals(response.body())) {
            throw new AuthException("Login failed for user '" + username + "': invalid credentials");
        }
    }

    /**
     * @see <a href="https://github.com/qbittorrent/qBittorrent/wiki/WebUI-API-(qBittorrent-5.0)#logout">qBittorrent WebUI API - Logout</a>
     */
    public void logout() {
        HttpResponse<String> response = httpClient.send(host, QBitEndpoint.LOGOUT, null, HttpRequest.BodyPublishers.noBody());
        httpClient.requireOk(response, "Failed to logout");
    }
}