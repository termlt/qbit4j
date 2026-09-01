package org.termlt.qbit4j.model.log;

import com.alibaba.fastjson2.annotation.JSONField;

public enum LogType {

    NORMAL(1),
    INFO(2),
    WARNING(4),
    CRITICAL(8);

    private final int value;

    LogType(int value) {
        this.value = value;
    }

    @JSONField(value = true)
    public int getValue() {
        return value;
    }
}
