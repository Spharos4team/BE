package com.spharos.ssgpoint.pointgift.domain;

import jakarta.persistence.AttributeConverter;

import java.util.EnumSet;
import java.util.NoSuchElementException;

public class PointGiftAccessTypeConverter implements AttributeConverter<PointGiftAccessType, String> {

    @Override
    public String convertToDatabaseColumn(PointGiftAccessType attribute) {
        return attribute.getCode();
    }

    @Override
    public PointGiftAccessType convertToEntityAttribute(String dbData) {
        return EnumSet.allOf(PointGiftAccessType.class).stream()
                .filter(c -> c.getCode().equals(dbData))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 포인트 선물 상태입니다."));
    }

}
