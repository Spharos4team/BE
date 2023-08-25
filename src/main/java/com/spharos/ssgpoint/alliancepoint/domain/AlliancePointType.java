package com.spharos.ssgpoint.alliancepoint.domain;

import com.spharos.ssgpoint.global.CodeValue;

public enum AlliancePointType implements CodeValue {

    SAMSUNGCARDPOINT("S", "삼성카드 포인트"),
    OKCASHBACKPOINT("OK", "OK캐쉬백 포인트"),
    REWORDPOINT("360", "360 리워드 포인트");

    private final String code;
    private final String value;

    AlliancePointType(String code, String value) {
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
