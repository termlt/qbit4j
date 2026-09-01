package org.termlt.qbit4j.exception;

public class QBitException extends RuntimeException {

    public QBitException(String message) {
        super(message);
    }

    public QBitException(String message, Throwable cause) {
        super(message, cause);
    }
}