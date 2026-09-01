package org.termlt.qbit4j.model.app;

import com.alibaba.fastjson2.JSON;
import org.junit.jupiter.api.Test;
import org.termlt.qbit4j.model.app.preferences.SchedulerDays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SchedulerDaysTest {

    @Test
    void schedulerDaySerializesToItsCode() {
        assertEquals("0", JSON.toJSONString(SchedulerDays.EVERY_DAY));
        assertEquals("1", JSON.toJSONString(SchedulerDays.EVERY_WEEKDAY));
        assertEquals("2", JSON.toJSONString(SchedulerDays.EVERY_WEEKEND));
        assertEquals("3", JSON.toJSONString(SchedulerDays.EVERY_MONDAY));
        assertEquals("4", JSON.toJSONString(SchedulerDays.EVERY_TUESDAY));
        assertEquals("5", JSON.toJSONString(SchedulerDays.EVERY_WEDNESDAY));
        assertEquals("6", JSON.toJSONString(SchedulerDays.EVERY_THURSDAY));
        assertEquals("7", JSON.toJSONString(SchedulerDays.EVERY_FRIDAY));
        assertEquals("8", JSON.toJSONString(SchedulerDays.EVERY_SATURDAY));
        assertEquals("9", JSON.toJSONString(SchedulerDays.EVERY_SUNDAY));
    }
}
