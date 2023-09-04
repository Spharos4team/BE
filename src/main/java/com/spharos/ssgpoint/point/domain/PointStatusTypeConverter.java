package com.spharos.ssgpoint.point.domain;

import jakarta.persistence.AttributeConverter;

import java.util.EnumSet;
import java.util.NoSuchElementException;

public class PointStatusTypeConverter implements AttributeConverter<PointStatusType, String> {

    @Override
    public String convertToDatabaseColumn(PointStatusType attribute) {
        return attribute.getCode();
    }

    @Override
    public PointStatusType convertToEntityAttribute(String dbData) {
        return EnumSet.allOf(PointStatusType.class).stream()
                .filter(c -> c.getCode().equals(dbData))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 포인트 상태입니다."));
    }

}
