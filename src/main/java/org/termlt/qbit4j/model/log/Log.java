package org.termlt.qbit4j.model.log;

/**
 * @param id        ID of the message
 * @param message   Text of the message
 * @param timestamp Seconds since epoch
 * @param type      Type of the message: Log::NORMAL: 1, Log::INFO: 2, Log::WARNING: 4, Log::CRITICAL: 8
 */
public record Log(

        Integer id,

        String message,

        Integer timestamp,

        LogType type
) {
}
