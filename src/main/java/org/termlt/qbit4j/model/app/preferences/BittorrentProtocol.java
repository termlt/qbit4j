package org.termlt.qbit4j.model.app.preferences;

import com.alibaba.fastjson2.annotation.JSONField;

public enum BittorrentProtocol {

    TCP_AND_UTP(0),
    TCP(1),
    UTP(2);

    private final int value;

    BittorrentProtocol(int value) {
        this.value = value;
    }

    @JSONField(value = true)
    public int getValue() {
        return value;
    }
}
