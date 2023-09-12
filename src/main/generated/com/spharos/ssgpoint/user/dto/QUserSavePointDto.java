package com.spharos.ssgpoint.user.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.spharos.ssgpoint.user.dto.QUserSavePointDto is a Querydsl Projection type for UserSavePointDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QUserSavePointDto extends ConstructorExpression<UserSavePointDto> {

    private static final long serialVersionUID = 187276351L;

    public QUserSavePointDto(com.querydsl.core.types.Expression<Integer> savePoint) {
        super(UserSavePointDto.class, new Class<?>[]{int.class}, savePoint);
    }

}

