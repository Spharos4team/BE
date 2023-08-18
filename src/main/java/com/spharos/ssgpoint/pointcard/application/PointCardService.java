package com.spharos.ssgpoint.pointcard.application;

import com.spharos.ssgpoint.pointcard.dto.PointCardCreateDto;
import com.spharos.ssgpoint.pointcard.dto.PointCardGetDto;

import java.util.List;

public interface PointCardService {

    void createPointCard(String UUID, PointCardCreateDto pointCardCreateDto);
    List<PointCardGetDto> getPointCardByUser(String UUID);

}
