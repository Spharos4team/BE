package com.spharos.ssgpoint.alliancepointcard.domain;

import jakarta.persistence.AttributeConverter;

import java.util.EnumSet;
import java.util.NoSuchElementException;

public class AlliancePointCardTypeConverter implements AttributeConverter<AlliancePointCardType, String> {

    @Override
    public String convertToDatabaseColumn(AlliancePointCardType attribute) {
        return attribute.getCode();
    }

    @Override
    public AlliancePointCardType convertToEntityAttribute(String dbData) {
        return EnumSet.allOf(AlliancePointCardType.class).stream()
                .filter(c -> c.getCode().equals(dbData))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 제휴사입니다."));
    }

}
