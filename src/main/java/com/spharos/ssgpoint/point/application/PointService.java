package com.spharos.ssgpoint.point.application;

import com.spharos.ssgpoint.point.domain.Point;
import com.spharos.ssgpoint.point.dto.PointCreateDto;
import com.spharos.ssgpoint.point.dto.PointFilterDto;
import com.spharos.ssgpoint.point.dto.PointGetDto;
import com.spharos.ssgpoint.point.vo.PointFilterVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface PointService {

    // 포인트 생성
    Point createPoint(String UUID, PointCreateDto pointCreateDto);
    // 포인트 목록
    List<PointGetDto> getTotalPointByUser(String UUID, Pageable page);
    List<PointGetDto> getSavePointByUser(String UUID, Pageable page);
    Slice<PointFilterDto> test(Long id, String UUID, Pageable page, PointFilterVo pointFilterVo);

}
