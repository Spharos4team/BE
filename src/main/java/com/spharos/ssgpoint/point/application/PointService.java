package com.spharos.ssgpoint.point.application;

import com.spharos.ssgpoint.point.dto.PointCreateDto;
import com.spharos.ssgpoint.point.dto.PointGetDto;

import java.util.List;

public interface PointService {

    // 포인트 생성
    void createPoint(String UUID, PointCreateDto pointCreateDto);
    // 포인트 목록
    List<PointGetDto> getPointByUser(String UUID);

}
