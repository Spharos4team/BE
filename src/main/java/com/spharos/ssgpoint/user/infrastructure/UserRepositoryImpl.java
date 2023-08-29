package com.spharos.ssgpoint.user.infrastructure;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.spharos.ssgpoint.point.domain.Point;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

import static com.spharos.ssgpoint.point.domain.QPoint.point1;


public class UserRepositoryImpl implements UserRepositoryCustom{

    private final JPAQueryFactory queryFactory;
    public UserRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Point findPointByUUID(String uuid) {
        return null;
    }
}
