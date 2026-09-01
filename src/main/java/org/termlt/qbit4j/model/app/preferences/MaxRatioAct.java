package org.termlt.qbit4j.model.app.preferences;

import com.alibaba.fastjson2.annotation.JSONField;

public enum MaxRatioAct {

    PAUSE_TORRENT(0),
    REMOVE_TORRENT(1);

    private final int value;

    MaxRatioAct(int value) {
        this.value = value;
    }

    @JSONField(value = true)
    public int getValue() {
        return value;
    }
}
