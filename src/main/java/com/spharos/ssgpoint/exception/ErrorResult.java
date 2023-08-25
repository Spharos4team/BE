package com.spharos.ssgpoint.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResult {
    private String code;
    private String message;

    public ErrorResult(String message) {
        this.message = message;
    }
}
