package com.spharos.ssgpoint.point.infrastructure;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.spharos.ssgpoint.point.domain.Point;

import com.spharos.ssgpoint.point.dto.*;

import com.spharos.ssgpoint.user.domain.User;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.*;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        List<PointFilterDto> results = queryFactory
                .select(new QPointFilterDto(point1.id, point1.point, point1.title, point1.content, point1.type,
                        point1.statusType, point1.createdDate, point1.receipt.id))
                .from(point1)
                .join(point1.user, user)
                .on(user.uuid.eq(uuid))
                .where(ltPointId(pointId),
                        pointUseEq(pointUse), pointTypeEq(pointType),
                        point1.createdDate.between(startDate.atStartOfDay(), endDate.atStartOfDay()))
                .orderBy(point1.id.desc())
                .limit(pageable.getPageSize()+1)
                .fetch();
        return checkLastPage(pageable, results);
    }

    @Override
    public PointFilterSumDto sumPointsByFilter(String uuid, String pointUse, String pointType, LocalDate startDate, LocalDate endDate) {
        Integer totalSavePoints = 0;
        Integer totalUsePoints = 0;

        if ("1".equals(pointUse) || "0".equals(pointUse)) {
            totalSavePoints = calculateTotalPoints(uuid, "1", pointType, startDate, endDate);
        }

        if ("2".equals(pointUse) || "0".equals(pointUse)) {
            totalUsePoints = calculateTotalPoints(uuid, "2", pointType, startDate, endDate);
        }

        return PointFilterSumDto.builder()
                .savePoint(totalSavePoints)
                .usePoint(totalUsePoints)
                .build();
    }

    private Integer calculateTotalPoints(String uuid, String pointUse, String pointType, LocalDate startDate, LocalDate endDate) {
        return queryFactory
                .select(point1.point.sum())
                .from(point1)
                .join(point1.user, user)
                .on(user.uuid.eq(uuid))
                .where(
                        pointUseEq(pointUse),
                        pointTypeEq(pointType),
                        point1.createdDate.between(startDate.atStartOfDay(), endDate.atStartOfDay()))
                .orderBy(point1.id.desc())
                .fetchOne();
    }


    private BooleanExpression pointUseEq(String pointUse) {
        if ("1".equals(pointUse)) { // 적립
            return point1.statusType.eq(적립);
        } else if ("2".equals(pointUse)) { // 사용
            return point1.statusType.in(사용, 사용취소);
        } else { // "0"일때 경우 전체
            return null;
        }
    }

    private BooleanExpression pointTypeEq(String pointType) {
        if ("1".equals(pointType)) { // 일반
            return point1.type.in(결제, 선물, 전환, 추후, 소멸);
        } else if ("2".equals(pointType)) { // 이벤트
            return point1.type.eq(이벤트);
        } else { // "0"일때 경우 전체
            return null;
        }
    }
    // no-offset
    private BooleanExpression ltPointId(Long pointId) {
        if (pointId == null) {
            return null;
        }
        return point1.id.lt(pointId);
    }

    // 무한 스크롤
    private Slice<PointFilterDto> checkLastPage(Pageable pageable, List<PointFilterDto> results) {
        boolean hasNext = false;
        if (results.size() > pageable.getPageSize()) {
            hasNext = true;
            results.remove(pageable.getPageSize());
        }
        return new SliceImpl<>(results, pageable, hasNext);
    }
}
