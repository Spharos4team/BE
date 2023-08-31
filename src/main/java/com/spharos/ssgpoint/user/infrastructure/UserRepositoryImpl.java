package com.spharos.ssgpoint.user.infrastructure;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.spharos.ssgpoint.point.domain.PointType;
import jakarta.persistence.EntityManager;

import java.time.Year;
import java.util.List;

import static com.spharos.ssgpoint.point.domain.PointType.*;
import static com.spharos.ssgpoint.point.domain.QPoint.point1;
import static com.spharos.ssgpoint.user.domain.QUser.user;


public class UserRepositoryImpl implements UserRepositoryCustom{

    private final JPAQueryFactory queryFactory;
    public UserRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    int currentYear = Year.now().getValue();
    @Override
    public Integer findSavePointByUUID(String uuid) {


        return queryFactory
                .select(point1.point.sum())
                .from(point1)
                .join(point1.user,user)
                .on(user.uuid.eq(uuid))
                .where(point1.type.eq(일반적립).
                        or(point1.type.eq(이벤트적립)),
                        point1.createdDate.year().eq(currentYear))
                .fetchOne();
    }

    @Override
    public Integer findUsePointByUUID(String uuid) {
        return queryFactory
                .select(point1.point.sum())
                .from(point1)
                .join(point1.user,user)
                .on(user.uuid.eq(uuid))
                .where(point1.type.eq(일반사용).
                                or(point1.type.eq(이벤트사용)),
                        point1.createdDate.year().eq(currentYear))
                .fetchOne();
    }

    @Override
    public Integer findVisitDateByReceipt(String uuid) {
        return null;
    }
}
