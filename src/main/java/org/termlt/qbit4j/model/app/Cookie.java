package org.termlt.qbit4j.model.app;

/**
 *
 * @param name           Cookie name
 * @param domain         Cookie domain
 * @param path           Cookie path
 * @param value          Cookie value
 * @param expirationDate Seconds since epoch
 */
public record Cookie(

        String name,

        String domain,

        String path,

        String value,

        Integer expirationDate
) {

}
