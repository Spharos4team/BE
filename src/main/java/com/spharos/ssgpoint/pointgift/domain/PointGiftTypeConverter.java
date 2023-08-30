package com.spharos.ssgpoint.pointgift.domain;

import jakarta.persistence.AttributeConverter;

import java.util.EnumSet;
import java.util.NoSuchElementException;

public class PointGiftTypeConverter implements AttributeConverter<PointGiftType, String> {

    @Override
    public String convertToDatabaseColumn(PointGiftType attribute) {
        return attribute.getCode();
    }

    @Override
    public PointGiftType convertToEntityAttribute(String dbData) {
        return EnumSet.allOf(PointGiftType.class).stream()
                .filter(c -> c.getCode().equals(dbData))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 포인트 선물 구분입니다."));
    }
}
