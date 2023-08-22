package com.spharos.ssgpoint.pointcard.application;

import com.spharos.ssgpoint.pointcard.dto.PointCardCreateDto;
import com.spharos.ssgpoint.pointcard.dto.PointCardGetDto;

import java.util.List;

public interface PointCardService {

    // 포인트 카드 생성
    void createPointCard(String UUID, PointCardCreateDto pointCardCreateDto);
    // 포인트 카드 목록
    List<PointCardGetDto> getPointCardByUser(String UUID);

}
