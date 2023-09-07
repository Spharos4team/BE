package com.spharos.ssgpoint.point.domain;

import com.spharos.ssgpoint.global.CodeValue;
import lombok.Getter;

@Getter
public enum PointType implements CodeValue {

    결제("1", "결제"),
    이벤트("2", "이벤트"),
    선물("3", "선물"),
    전환("4", "전환"),
    추후("5", "추후"),
    소멸("6", "소멸"),
    제휴사전환("7", "제휴사전환");

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