package com.spharos.ssgpoint.alliancepoint.infrastructure;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.spharos.ssgpoint.alliancepoint.dto.AlliancePointListDto;
import com.spharos.ssgpoint.point.domain.PointStatusType;
import com.spharos.ssgpoint.point.dto.PointFilterSumDto;
import com.spharos.ssgpoint.pointgift.dto.PointGiftListDto;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import java.time.LocalDate;
import java.util.List;

import static com.spharos.ssgpoint.point.domain.PointType.*;
import static com.spharos.ssgpoint.point.domain.QPoint.point1;
import static com.spharos.ssgpoint.user.domain.QUser.user;

public class AlliancePointRepositoryImpl implements AlliancePointRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    public AlliancePointRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Slice<AlliancePointListDto> findPointAllianceList(Long pointId, String uuid, LocalDate startDate, LocalDate endDate,
                                                         Pageable pageable) {
        List<AlliancePointListDto> results = queryFactory
                .select(Projections.fields(AlliancePointListDto.class,
                        point1.id , point1.point, point1.title, point1.content, point1.type,
                        point1.statusType, point1.createdDate))
                .from(point1)
                .join(point1.user, user)
                .where(ltStoreId(pointId),
                        point1.user.uuid.eq(uuid),
                        point1.createdDate.between(startDate.atStartOfDay(), endDate.atStartOfDay()),
                        point1.type.in(전환,제휴사전환)
                )
                .orderBy(point1.id.desc())
                .limit(pageable.getPageSize() + 1).fetch();

        return checkLastPage(pageable, results);
    }

    @Override
    public PointFilterSumDto sumPointsAllianceByFilter(String uuid, LocalDate startDate, LocalDate endDate) {
        Integer save=0;
        Integer use=0;
        Integer subtract=0;

        List<PointGiftListDto> results = queryFactory
                .select(Projections.fields(PointGiftListDto.class,
                        point1.point , point1.statusType))
                .from(point1)
                .join(point1.user, user)
                .where(
                        point1.user.uuid.eq(uuid),
                        point1.createdDate.between(startDate.atStartOfDay(), endDate.atStartOfDay()),
                        point1.type.in(전환,제휴사전환)
                )
                .fetch();

        use = results.stream()
                .filter(result -> result.getStatusType() == PointStatusType.사용)
                .mapToInt(PointGiftListDto::getPoint)
                .sum();

        save = results.stream()
                .filter(result -> result.getStatusType() == PointStatusType.적립)
                .mapToInt(PointGiftListDto::getPoint)
                .sum();

        subtract = results.stream()
                .filter(result -> result.getStatusType() != PointStatusType.사용 && result.getStatusType() != PointStatusType.적립)
                .mapToInt(PointGiftListDto::getPoint)
                .sum();

        save -= subtract;

        return PointFilterSumDto.builder()
                .savePoint(save)
                .usePoint(use)
                .build();
    }






















    // no-offset 방식 처리하는 메서드
    private BooleanExpression ltStoreId(Long pointId) {
        if (pointId == null) {
            return null;
        }
        return point1.id.lt(pointId);
    }

    // 무한 스크롤 방식 처리하는 메서드
    private Slice<AlliancePointListDto> checkLastPage(Pageable pageable, List<AlliancePointListDto> results) {

        boolean hasNext = false;

        // 조회한 결과 개수가 요청한 페이지 사이즈보다 크면 뒤에 더 있음, next = true
        if (results.size() > pageable.getPageSize()) {
            hasNext = true;
            results.remove(pageable.getPageSize());
        }

        return new SliceImpl<>(results, pageable, hasNext);
    }
}
