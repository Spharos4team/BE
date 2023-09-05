package com.spharos.ssgpoint.point.infrastructure;

import com.spharos.ssgpoint.point.domain.Point;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface PointRepositoryCustom {
    Page<Point> findByFilter(String uuid, LocalDate startDate,LocalDate endDate, String pointUse, String pointType,  Pageable pageable);

}
