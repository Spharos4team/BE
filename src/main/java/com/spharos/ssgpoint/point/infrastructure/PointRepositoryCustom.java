package com.spharos.ssgpoint.point.infrastructure;

import com.spharos.ssgpoint.point.domain.Point;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface PointRepositoryCustom {
    Page<Point> findByFilter(String uuid, LocalDate searchDate, Integer pointUse, Integer pointType, Pageable pageRequest);

}
