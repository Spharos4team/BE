package com.spharos.ssgpoint.pointcard.domain;

import jakarta.persistence.AttributeConverter;

import java.util.EnumSet;
import java.util.NoSuchElementException;

public class PointCardConverter implements AttributeConverter<PointCardType, String> {

    @Override
    public String convertToDatabaseColumn(PointCardType attribute) {
        return attribute.getCode();
    }

    @Override
    public PointCardType convertToEntityAttribute(String dbData) {
        return EnumSet.allOf(PointCardType.class).stream()
                .filter(c -> c.getCode().equals(dbData))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 포인트 카드입니다."));
    }

}
