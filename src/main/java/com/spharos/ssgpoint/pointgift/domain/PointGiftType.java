package com.spharos.ssgpoint.pointgift.domain;

import com.spharos.ssgpoint.global.CodeValue;
import lombok.Getter;

@Getter
public enum PointGiftType implements CodeValue {

    선물사용("0", "선물사용"),
    선물사용취소("1", "선물사용취소"),
    선물적립("2", "선물적립");

    private final String code;
    private final String value;

    PointGiftType(String code, String value) {
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
