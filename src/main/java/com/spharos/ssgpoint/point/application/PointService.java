package com.spharos.ssgpoint.point.application;

import com.spharos.ssgpoint.point.domain.Point;
import com.spharos.ssgpoint.point.dto.PointCreateDto;
import com.spharos.ssgpoint.point.dto.PointFilterDto;
import com.spharos.ssgpoint.point.dto.PointFilterSumDto;
import com.spharos.ssgpoint.point.dto.PointGetDto;
import com.spharos.ssgpoint.point.vo.PointFilterVo;
import com.spharos.ssgpoint.pointgift.dto.PointGiftMessageDto;
import com.spharos.ssgpoint.pointgift.vo.PointListInVo;
import com.spharos.ssgpoint.receipt.dto.ReceiptGetDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface PointService {

    // 포인트 생성
    Point createPoint(String UUID, PointCreateDto pointCreateDto);
    // 포인트 목록
    List<PointGetDto> getTotalPointByUser(String UUID, Pageable page);
    List<PointGetDto> getSavePointByUser(String UUID, Pageable page);
    Slice<PointFilterDto> pointFilter(Long id, String UUID, Pageable page, LocalDate startDate, LocalDate endDate, String pointUse, String pointType);
   // Page<PointFilterDto> pointFilter(String UUID, Pageable page, LocalDate startDate, LocalDate endDate, String pointUse, String pointType);

    PointFilterSumDto sumPointsByFilter(String UUID, LocalDate startDate, LocalDate endDate, String pointUse, String pointType);

    ReceiptGetDto getReceiptByPointListReceiptId(Long id);
    PointGiftMessageDto getGiftMessage(Long id);


}
