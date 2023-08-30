package com.spharos.ssgpoint.user.infrastructure;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.spharos.ssgpoint.point.domain.PointType;
import jakarta.persistence.EntityManager;

import java.util.List;

import static com.spharos.ssgpoint.point.domain.QPoint.point1;
import static com.spharos.ssgpoint.user.domain.QUser.user;


public class UserRepositoryImpl implements UserRepositoryCustom{

    private final JPAQueryFactory queryFactory;
    public UserRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Integer> findPointByUUID(String uuid) {
        return null;/*queryFactory
                .select(point1.point.sum())
                .from(point1)
                .join(point1.user,user)
                .on(user.uuid.eq(uuid))
                .where(point1.type.eq(PointType.valueOf("1")).or(point1.type.eq(PointType.valueOf("2"))))
                .fetch();*/

    }
}
