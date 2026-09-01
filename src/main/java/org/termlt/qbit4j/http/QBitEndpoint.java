package org.termlt.qbit4j.http;

public enum QBitEndpoint {

    // Auth
    LOGIN("/api/v2/auth/login", "POST", ContentType.FORM),
    LOGOUT("/api/v2/auth/logout", "POST", ContentType.NONE),

    // Application
    APP_VERSION("/api/v2/app/version", "GET", ContentType.NONE),
    APP_WEBAPI_VERSION("/api/v2/app/webapiVersion", "GET", ContentType.NONE),
    APP_BUILD_INFO("/api/v2/app/buildInfo", "GET", ContentType.NONE),
    APP_SHUTDOWN("/api/v2/app/shutdown", "POST", ContentType.NONE),
    APP_GET_PREFERENCES("/api/v2/app/preferences", "GET", ContentType.NONE),
    APP_SET_PREFERENCES("/api/v2/app/setPreferences", "POST", ContentType.FORM),
    APP_GET_DEFAULT_SAVE_PATH("/api/v2/app/defaultSavePath", "GET", ContentType.NONE),
    APP_GET_COOKIES("/api/v2/app/cookies", "GET", ContentType.NONE),
    APP_SET_COOKIES("/api/v2/app/setCookies", "POST", ContentType.JSON),

    // Logs
    LOG_GET_LOGS("/api/v2/log/main", "GET", ContentType.FORM),
    LOG_GET_PEER_LOGS("/api/v2/log/peers", "GET", ContentType.FORM);

    public final String path;
    public final String method;
    public final ContentType contentType;

    QBitEndpoint(String path, String method, ContentType contentType) {
        this.path = path;
        this.method = method;
        this.contentType = contentType;
    }
}
