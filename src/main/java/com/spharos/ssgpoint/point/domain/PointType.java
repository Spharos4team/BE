package com.spharos.ssgpoint.point.domain;

import com.spharos.ssgpoint.global.CodeValue;
import lombok.Getter;

@Getter
public enum PointType implements CodeValue {

    일반적립("1", "일반적립"),
    이벤트적립("2", "이벤트적립"),
    일반사용("3", "일반사용"),
    이벤트사용("4", "이벤트사용"),
    선물사용("5", "선물사용"),
    선물사용취소("6", "선물사용취소"),
    선물적립("7", "선물적립"),
    전환적립("8", "전환적립"),
    전환사용("9", "전환사용");

    private final String code;
    private final String value;

    PointType(String code, String value) {
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
