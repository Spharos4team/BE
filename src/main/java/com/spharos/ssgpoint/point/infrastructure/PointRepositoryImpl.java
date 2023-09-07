package com.spharos.ssgpoint.point.infrastructure;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.spharos.ssgpoint.point.domain.Point;

import com.spharos.ssgpoint.point.dto.PointFilterDto;
import com.spharos.ssgpoint.point.dto.PointGetDto;

import com.spharos.ssgpoint.point.dto.QPointFilterDto;
import com.spharos.ssgpoint.user.domain.User;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.*;


import java.time.LocalDate;
import java.util.List;

import static com.spharos.ssgpoint.point.domain.PointStatusType.*;

import static com.spharos.ssgpoint.point.domain.PointType.*;
import static com.spharos.ssgpoint.point.domain.QPoint.point1;
import static com.spharos.ssgpoint.user.domain.QUser.user;

public class PointRepositoryImpl implements PointRepositoryCustom{

    private final JPAQueryFactory queryFactory;
    public PointRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Slice<PointFilterDto> findByFilter(Long pointId, String uuid, LocalDate startDate,LocalDate endDate,
                                              String pointUse, String pointType,  Pageable pageable) {

        Long userId = queryFactory.select(user.id)
                .from(user)
                .where(user.uuid.eq(uuid))
                .fetchOne();

        List<PointFilterDto> results = queryFactory
                .select(new QPointFilterDto(point1.id, point1.point, point1.title, point1.content, point1.type,
                        point1.statusType, point1.createdDate, point1.receipt.id))
                .from(point1)
                .where(ltStoreId(pointId),
                        pointUseEq(pointUse), pointTypeEq(pointType),
                        point1.user.id.eq(userId),
                        point1.createdDate.between(startDate.atStartOfDay(), endDate.atStartOfDay()))
                //.offset(pageable.getOffset())
                .orderBy(point1.id.desc())
                .limit(pageable.getPageSize()+1)
                .fetch();

        /*long total = queryFactory
                .select(point1)
                .from(point1)
                .where(pointUseEq(pointUse), pointTypeEq(pointType),point1.user.id.eq(userId), point1.createdDate.between(startDate.atStartOfDay(), endDate.atStartOfDay()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchCount();*/

        return checkLastPage(pageable, results);
    }

    private BooleanExpression pointUseEq(String pointUse) {
        if ("1".equals(pointUse)) { // 적립
            return point1.statusType.eq(적립);
        } else if ("2".equals(pointUse)) { // 사용
            return point1.statusType.in(사용, 사용취소);
        } else {
            return null;
        }
    }

    private BooleanExpression pointTypeEq(String pointType) {
        if ("1".equals(pointType)) { // 일반
            return point1.type.in(결제, 선물, 전환, 추후, 소멸);
        } else if ("2".equals(pointType)) { // 이벤트
            return point1.type.eq(이벤트);
        } else {
            return point1.type.in(결제, 선물, 전환, 추후, 소멸,이벤트);
        }
    }

    // no-offset 방식 처리하는 메서드
    private BooleanExpression ltStoreId(Long pointId) {
        if (pointId == null) {
            return null;
        }

        return point1.id.lt(pointId);
    }

    // 무한 스크롤 방식 처리하는 메서드
    private Slice<PointFilterDto> checkLastPage(Pageable pageable, List<PointFilterDto> results) {

        boolean hasNext = false;

        // 조회한 결과 개수가 요청한 페이지 사이즈보다 크면 뒤에 더 있음, next = true
        if (results.size() > pageable.getPageSize()) {
            hasNext = true;
            results.remove(pageable.getPageSize());
        }

        return new SliceImpl<>(results, pageable, hasNext);
    }
}
