package com.spharos.ssgpoint.alliancepoint.domain;

import jakarta.persistence.AttributeConverter;

import java.util.EnumSet;
import java.util.NoSuchElementException;

public class AlliancePointTypeConverter implements AttributeConverter<AlliancePointType, String> {

    @Override
    public String convertToDatabaseColumn(AlliancePointType attribute) {
        return attribute.getCode();
    }

    @Override
    public AlliancePointType convertToEntityAttribute(String dbData) {
        return EnumSet.allOf(AlliancePointType.class).stream()
                .filter(c -> c.getCode().equals(dbData))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 제휴 포인트입니다."));
    }

}
