package com.spharos.ssgpoint.coupon.exception;

public class UnknownStoreException extends RuntimeException {
    public UnknownStoreException(String storeName) {
        super("Unknown store: " + storeName);
    }
}