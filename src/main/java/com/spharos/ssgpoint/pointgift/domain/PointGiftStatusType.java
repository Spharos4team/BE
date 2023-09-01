package com.spharos.ssgpoint.pointgift.domain;

import com.spharos.ssgpoint.global.CodeValue;
import lombok.Getter;

@Getter
public enum PointGiftStatusType implements CodeValue {

    대기("0", "대기"),
    수락("1", "수락"),
    거절("2", "거절");

    private final String code;
    private final String value;

    PointGiftStatusType(String code, String value) {
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
