package org.termlt.qbit4j.model.log;

/**
 * @param id        ID of the peer
 * @param ip        IP of the peer
 * @param timestamp Seconds since epoch
 * @param blocked   Whether the peer was blocked
 * @param reason    Reason of the block
 */
public record PeerLog(

        Integer id,

        String ip,

        Integer timestamp,

        boolean blocked,

        String reason

) {
}
