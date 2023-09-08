package com.spharos.ssgpoint.alliancepoint.infrastructure;

import com.spharos.ssgpoint.alliancepoint.dto.AlliancePointListDto;
import com.spharos.ssgpoint.point.dto.PointFilterSumDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.time.LocalDate;

public interface AlliancePointRepositoryCustom {
    Slice<AlliancePointListDto> findPointAllianceList(Long pointId, String uuid, LocalDate startDate, LocalDate endDate,
                                                         Pageable pageable);
    PointFilterSumDto sumPointsAllianceByFilter(String uuid, LocalDate startDate, LocalDate endDate);

}
