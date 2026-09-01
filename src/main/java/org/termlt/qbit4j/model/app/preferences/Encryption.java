package org.termlt.qbit4j.model.app.preferences;

import com.alibaba.fastjson2.annotation.JSONField;

public enum Encryption {

    DEFAULT(0), // use both encrypted and unencrypted connections
    FORCE_ENCRYPTION_ON(1),
    FORCE_ENCRYPTION_OFF(2);

    private final int value;

    Encryption(int value) {
        this.value = value;
    }

    @JSONField(value = true)
    public int getValue() {
        return value;
    }
}
