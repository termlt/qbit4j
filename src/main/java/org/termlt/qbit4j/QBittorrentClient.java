package org.termlt.qbit4j;

import org.termlt.qbit4j.api.app.AppApi;
import org.termlt.qbit4j.api.auth.AuthApi;
import org.termlt.qbit4j.api.log.LogApi;
import org.termlt.qbit4j.http.QBitHttpClient;

public class QBittorrentClient {

    private final AuthApi auth;
    private final AppApi app;
    private final LogApi log;

    QBittorrentClient(QBitHttpClient httpClient, String host, String username, String password) {
        this.auth = new AuthApi(httpClient, host, username, password);
        this.app = new AppApi(httpClient, host);
        this.log = new LogApi(httpClient, host);
    }

    public static Builder builder() {
        return new Builder();
    }

    public AuthApi auth() {
        return auth;
    }

    public AppApi app() {
        return app;
    }

    public LogApi log() {
        return log;
    }

    public static class Builder {

        private String host;
        private String username;
        private String password;

        public Builder host(String host) {
            this.host = host;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public QBittorrentClient build() {
            if (host == null || username == null || password == null) {
                throw new IllegalStateException("host, username, and password are all required");
            }
            return new QBittorrentClient(new QBitHttpClient(), host, username, password);
        }
    }
}