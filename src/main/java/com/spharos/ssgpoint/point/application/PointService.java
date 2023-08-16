package com.spharos.ssgpoint.point.application;

import com.spharos.ssgpoint.point.dto.PointGetDto;

import java.util.List;

public interface PointService {

    List<PointGetDto> getPointByUser(Long userId);

}
