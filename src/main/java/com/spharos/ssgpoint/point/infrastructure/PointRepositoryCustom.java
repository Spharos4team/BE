package com.spharos.ssgpoint.point.infrastructure;

import com.spharos.ssgpoint.point.domain.Point;
import com.spharos.ssgpoint.point.dto.PointFilterDto;
import com.spharos.ssgpoint.point.dto.PointFilterSumDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface PointRepositoryCustom {
    Slice<PointFilterDto> findByFilter(Long pointId, String uuid, LocalDate startDate, LocalDate endDate,
                                       String pointUse, String pointType, Pageable pageable);

    PointFilterSumDto sumPointsByFilter(String uuid, String pointUse, String pointType, LocalDate startDate, LocalDate endDate);
}
