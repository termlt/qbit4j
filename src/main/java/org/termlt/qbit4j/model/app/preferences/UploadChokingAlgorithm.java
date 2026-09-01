package org.termlt.qbit4j.model.app.preferences;

import com.alibaba.fastjson2.annotation.JSONField;

public enum UploadChokingAlgorithm {

    ROUND_ROBIN(0),
    FASTEST_UPLAOD(1),
    ANTI_LEECH(2);

    private final int value;

    UploadChokingAlgorithm(int value) {
        this.value = value;
    }

    @JSONField(value = true)
    public int getValue() {
        return value;
    }
}
