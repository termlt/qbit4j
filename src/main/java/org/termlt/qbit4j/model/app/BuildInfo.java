package org.termlt.qbit4j.model.app;

/**
 *
 * @param qt QT version
 * @param libtorrent LibTorrent version
 * @param boost Boost version
 * @param openssl OpenSSL version
 * @param bitness Application bitness (e.g. 64-bit)
 */
public record BuildInfo(

        String qt,

        String libtorrent,

        String boost,

        String openssl,

        int bitness
) {
}
