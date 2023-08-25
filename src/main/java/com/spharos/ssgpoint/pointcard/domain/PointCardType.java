package com.spharos.ssgpoint.pointcard.domain;

import com.spharos.ssgpoint.global.CodeValue;
import lombok.Getter;

@Getter
public enum PointCardType implements CodeValue {

    ONLINE("ON", "온라인"),
    OFFLINE("OFF", "오프라인"),
    ALLIANCE("A", "제휴");

    private final String code;
    private final String value;

    PointCardType(String code, String value) {
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
