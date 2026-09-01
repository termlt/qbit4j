package org.termlt.qbit4j.model.log;

import com.alibaba.fastjson2.annotation.JSONField;

/**
 * @param normal      Include normal messages (default: true)
 * @param info        Include info messages (default: true)
 * @param warning     Include warning messages (default: true)
 * @param critical    Include critical messages (default: true)
 * @param lastKnownId Exclude messages with "message id" <= last_known_id (default: -1)
 */
public record LogFilter(

        boolean normal,

        boolean info,

        boolean warning,

        boolean critical,

        @JSONField(name = "last_known_id")
        int lastKnownId
) {

    public static LogFilter defaults() {
        return builder().build();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private boolean normal = true;
        private boolean info = true;
        private boolean warning = true;
        private boolean critical = true;
        private int lastKnownId = -1;

        public Builder normal(boolean normal) {
            this.normal = normal;
            return this;
        }

        public Builder info(boolean info) {
            this.info = info;
            return this;
        }

        public Builder warning(boolean warning) {
            this.warning = warning;
            return this;
        }

        public Builder critical(boolean critical) {
            this.critical = critical;
            return this;
        }

        public Builder lastKnownId(int lastKnownId) {
            this.lastKnownId = lastKnownId;
            return this;
        }

        public LogFilter build() {
            return new LogFilter(normal, info, warning, critical, lastKnownId);
        }
    }
}
