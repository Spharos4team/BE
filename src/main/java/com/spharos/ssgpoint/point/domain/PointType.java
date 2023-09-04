package com.spharos.ssgpoint.point.domain;

import com.spharos.ssgpoint.global.CodeValue;
import lombok.Getter;

@Getter
public enum PointType implements CodeValue {

    결제적립("1", "결제적립"),
    이벤트적립("2", "이벤트적립"),
    이벤트사용("3", "이벤트사용"),
    선물사용("4", "선물사용"),
    선물사용취소("5", "선물사용취소"),
    선물적립("6", "선물적립"),
    전환적립("7", "전환적립"),
    전환사용("8", "전환사용"),
    영수증적립("9", "영수증적립");    // TODO: 영수증으로 적립했을 때 이름 확인해보기

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
