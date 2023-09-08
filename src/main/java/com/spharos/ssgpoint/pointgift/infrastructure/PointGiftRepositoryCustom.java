package com.spharos.ssgpoint.pointgift.infrastructure;

import com.spharos.ssgpoint.point.dto.PointFilterDto;
import com.spharos.ssgpoint.point.dto.PointFilterSumDto;
import com.spharos.ssgpoint.pointgift.dto.PointGiftListDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.time.LocalDate;

public interface PointGiftRepositoryCustom {
    Slice<PointGiftListDto> findPointGiftList(Long pointId, String uuid, LocalDate startDate, LocalDate endDate,
                                              Pageable pageable);
    PointFilterSumDto sumPointsGiftByFilter(String uuid, LocalDate startDate, LocalDate endDate);
}
