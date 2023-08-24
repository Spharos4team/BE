package com.spharos.ssgpoint.associatepointcard.domain;

import jakarta.persistence.AttributeConverter;

import java.util.EnumSet;
import java.util.NoSuchElementException;

public class AssociatePointCardTypeConverter implements AttributeConverter<AssociatePointCardType, String> {

    @Override
    public String convertToDatabaseColumn(AssociatePointCardType attribute) {
        return attribute.getCode();
    }

    @Override
    public AssociatePointCardType convertToEntityAttribute(String dbData) {
        return EnumSet.allOf(AssociatePointCardType.class).stream()
                .filter(c -> c.getCode().equals(dbData))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 제휴사입니다."));
    }

}
