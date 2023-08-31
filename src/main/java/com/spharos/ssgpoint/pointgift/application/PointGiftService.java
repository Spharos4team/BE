package com.spharos.ssgpoint.pointgift.application;

import com.spharos.ssgpoint.pointgift.dto.PointGiftCreateDto;
import com.spharos.ssgpoint.pointgift.dto.PointGiftGetDto;
import com.spharos.ssgpoint.pointgift.dto.PointGiftUpdateDto;

import java.util.List;

public interface PointGiftService {

    // 포인트 선물 수신인 확인
    String getPointGiftUser(String phone, String name);

    // 포인트 선물 보내기
    void createPointGift(String UUID, PointGiftCreateDto pointGiftCreateDto);

    // 포인트 선물 수락
    void updatePointGiftAccept(Long id, PointGiftUpdateDto pointGiftUpdateDto);

    // 포인트 선물 거절
    void updatePointGiftRefuse(Long id, PointGiftUpdateDto pointGiftUpdateDto);

    // 포인트 선물 목록
    List<PointGiftGetDto> getPointGiftByUser(String UUID);

}
