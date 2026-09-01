package org.termlt.qbit4j.model.app.preferences;

import com.alibaba.fastjson2.annotation.JSONField;

public enum SchedulerDays {

    EVERY_DAY(0),
    EVERY_WEEKDAY(1),
    EVERY_WEEKEND(2),
    EVERY_MONDAY(3),
    EVERY_TUESDAY(4),
    EVERY_WEDNESDAY(5),
    EVERY_THURSDAY(6),
    EVERY_FRIDAY(7),
    EVERY_SATURDAY(8),
    EVERY_SUNDAY(9);

    private final int value;

    SchedulerDays(int value) {
        this.value = value;
    }

    @JSONField(value = true)
    public int getValue() {
        return value;
    }
}