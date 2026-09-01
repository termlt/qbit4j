package org.termlt.qbit4j.model.app.preferences;

import com.alibaba.fastjson2.annotation.JSONField;

public enum ProxyType {

    @JSONField(name = "None") NONE,
    @JSONField(name = "HTTP") HTTP,
    @JSONField(name = "SOCKS5") SOCKS5,
    @JSONField(name = "SOCKS4") SOCKS4
}