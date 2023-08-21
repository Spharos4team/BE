package com.spharos.ssgpoint.pointgift.application;

import com.spharos.ssgpoint.pointgift.dto.PointGiftCreateDto;
import com.spharos.ssgpoint.pointgift.dto.PointGiftGetDto;

import java.util.List;

public interface PointGiftService {

    // 포인트 선물 보내기
    void createPointGift(String UUID, PointGiftCreateDto pointGiftCreateDto);
    // 포인트 선물 목록
    List<PointGiftGetDto> getPointByUser(String UUID);

}
