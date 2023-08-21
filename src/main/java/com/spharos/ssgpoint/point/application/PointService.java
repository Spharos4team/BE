package com.spharos.ssgpoint.point.application;

import com.spharos.ssgpoint.point.dto.PointCreateDto;
import com.spharos.ssgpoint.point.dto.PointGetDto;

import java.util.List;

public interface PointService {

    void createPoint(String UUID, PointCreateDto pointCreateDto);
    List<PointGetDto> getPointByUser(String UUID);

}
