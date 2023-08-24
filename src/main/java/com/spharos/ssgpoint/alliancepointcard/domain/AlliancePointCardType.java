package com.spharos.ssgpoint.alliancepointcard.domain;

import com.spharos.ssgpoint.global.CodeValue;
import lombok.Getter;

@Getter
public enum AlliancePointCardType implements CodeValue {

    삼성전자포인트("S", "삼성전자 포인트"),
    대한항공마일리지("K", "대한항공 마일리지"),
    아시아나항공마일리지("A", "아시아나항공 마일리지");

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
