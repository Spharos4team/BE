package com.spharos.ssgpoint.exception;


public class AllException extends RuntimeException {
    public AllException() {
        super();
    }

    public AllException(String message) {
        super(message);
    }

    public AllException(String message, Throwable cause) {
        super(message, cause);
    }

    public AllException(Throwable cause) {
        super(cause);
    }


}
