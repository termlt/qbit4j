package org.termlt.qbit4j.model.app.preferences;

import com.alibaba.fastjson2.annotation.JSONField;

public enum UtpTcpMixedMode {

    PREFER_TCP(0),
    PEER_PROPORTIONAL(1);

    private final int value;

    UtpTcpMixedMode(int value) {
        this.value = value;
    }

    @JSONField(value = true)
    public int getValue() {
        return value;
    }
}
