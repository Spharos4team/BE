package com.spharos.ssgpoint.alliancepoint.infrastructure;

import com.spharos.ssgpoint.alliancepoint.dto.AlliancePointListDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.time.LocalDate;

public interface AlliancePointRepositoryCustom {
    Slice<AlliancePointListDto> findPointAllianceList(Long pointId, String uuid, LocalDate startDate, LocalDate endDate,
                                                         Pageable pageable);

}
