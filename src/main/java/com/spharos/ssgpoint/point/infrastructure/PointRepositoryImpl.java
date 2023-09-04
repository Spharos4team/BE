package com.spharos.ssgpoint.point.infrastructure;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.spharos.ssgpoint.point.domain.Point;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

import static com.spharos.ssgpoint.point.domain.PointType.이벤트적립;
import static com.spharos.ssgpoint.point.domain.PointType.일반적립;
import static com.spharos.ssgpoint.point.domain.QPoint.point1;

public class PointRepositoryImpl implements PointRepositoryCustom{

    private final JPAQueryFactory queryFactory;
    public PointRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public Page<Point> findByFilter(String uuid, LocalDate searchDate, Integer pointUse, Integer pointType,  Pageable pageRequest) {
        return null; /*queryFactory
                .selectFrom(point1)
                .where(usernameEq(usernameCond), ageEq(ageCond))
                .fetch();*/
}
    /*private BooleanExpression pointUseEq(Integer pointUse) {
        // usernameCond != null ? member.username.eq(usernameCond) : null;
        if (pointUse ==1){
            return point1.type.eq(1);
        }
        else if (pointUse ==2 ){
            return point1.type.eq(일반적립).or(point1.type.eq(이벤트적립));
        }
    }
    private BooleanExpression ageEq(Integer ageCond) {
        return ageCond != null ? member.age.eq(ageCond) : null;
    }*/
}
