package com.spharos.ssgpoint.point.domain;

import com.spharos.ssgpoint.global.CodeValue;
import lombok.Getter;

@Getter
public enum PointStatusType implements CodeValue {

    적립("0", "적립"),
    사용("1", "사용"),
    사용취소("2", "사용취소");

    private final String code;
    private final String value;

    PointStatusType(String code, String value) {
        this.code = code;
        this.value = value;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getValue() {
        return value;
    }
}
