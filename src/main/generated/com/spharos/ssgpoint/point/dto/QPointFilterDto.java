package com.spharos.ssgpoint.point.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.spharos.ssgpoint.point.dto.QPointFilterDto is a Querydsl Projection type for PointFilterDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QPointFilterDto extends ConstructorExpression<PointFilterDto> {

    private static final long serialVersionUID = 161375886L;

    public QPointFilterDto(com.querydsl.core.types.Expression<Long> pointId, com.querydsl.core.types.Expression<Integer> point, com.querydsl.core.types.Expression<String> title, com.querydsl.core.types.Expression<String> content, com.querydsl.core.types.Expression<com.spharos.ssgpoint.point.domain.PointType> type, com.querydsl.core.types.Expression<com.spharos.ssgpoint.point.domain.PointStatusType> statusType, com.querydsl.core.types.Expression<java.time.LocalDateTime> createdDate, com.querydsl.core.types.Expression<Long> receiptId) {
        super(PointFilterDto.class, new Class<?>[]{long.class, int.class, String.class, String.class, com.spharos.ssgpoint.point.domain.PointType.class, com.spharos.ssgpoint.point.domain.PointStatusType.class, java.time.LocalDateTime.class, long.class}, pointId, point, title, content, type, statusType, createdDate, receiptId);
    }

}

