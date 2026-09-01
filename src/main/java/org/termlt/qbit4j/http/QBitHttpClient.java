package org.termlt.qbit4j.http;

import org.termlt.qbit4j.exception.QBitException;

import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class QBitHttpClient {

    private final HttpClient httpClient;

    public QBitHttpClient() {
        CookieManager cookieManager = new CookieManager(null, CookiePolicy.ACCEPT_ALL);

        this.httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .connectTimeout(Duration.ofSeconds(10))
                .cookieHandler(cookieManager)
                .build();
    }

    /**
     * Sends a request to the qBittorrent API.
     *
     * @param host     host where qBittorrent Web UI is running
     * @param endpoint desired endpoint to invoke
     * @param body     body of the request (ignored for GET requests)
     * @return the response
     * @throws IllegalArgumentException if the endpoint's HTTP method is unsupported
     * @throws QBitException            if the request fails to reach qBittorrent
     */
    public HttpResponse<String> send(String host, QBitEndpoint endpoint, String queryString, HttpRequest.BodyPublisher body) {
        body = (body != null) ? body : HttpRequest.BodyPublishers.noBody();

        String endpointPath = endpoint.path;
        if (queryString != null && !queryString.isBlank()) {
            endpointPath = endpointPath + "?" + queryString;
        }

        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(host + endpointPath))
                .header("Referer", host);

        switch (endpoint.method) {
            case "GET" -> builder.GET();
            case "POST" -> {
                builder.POST(body);
                switch (endpoint.contentType) {
                    case FORM -> builder.header("Content-Type", "application/x-www-form-urlencoded");
                    case JSON -> builder.header("Content-Type", "application/json");
                    case NONE -> {
                    }
                }
            }
            default -> throw new IllegalArgumentException("Unsupported method: " + endpoint.method);
        }

        try {
            return httpClient.send(builder.build(), HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new QBitException("Failed to reach qBittorrent at " + host + endpoint.path, e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new QBitException("Failed to reach qBittorrent at " + host + endpoint.path, e);
        }
    }

    public void requireOk(HttpResponse<String> response, String errorPrefix) {
        if (response.statusCode() != 200) {
            throw new QBitException(errorPrefix + ": status " + response.statusCode());
        }
    }
}