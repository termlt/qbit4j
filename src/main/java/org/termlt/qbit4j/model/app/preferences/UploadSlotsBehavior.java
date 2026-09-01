package org.termlt.qbit4j.model.app.preferences;

import com.alibaba.fastjson2.annotation.JSONField;

public enum UploadSlotsBehavior {

    FIXED_SLOTS(0),
    UPLOAD_RATE_BASED(1);

    private final int value;

    UploadSlotsBehavior(int value) {
        this.value = value;
    }

    @JSONField(value = true)
    public int getValue() {
        return value;
    }

}
