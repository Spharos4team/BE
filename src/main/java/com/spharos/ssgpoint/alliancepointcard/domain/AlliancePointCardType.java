package com.spharos.ssgpoint.alliancepointcard.domain;

import com.spharos.ssgpoint.global.CodeValue;
import lombok.Getter;

@Getter
public enum AlliancePointCardType implements CodeValue {

    SAMSUNG("S", "삼성전자 포인트"),
    KOREANAIR("K", "대한항공마일리지"),
    ASIANAAIR("A", "아시아나항공마일리지");

    private final String code;
    private final String value;

    AlliancePointCardType(String code, String value) {
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
