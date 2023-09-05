package com.spharos.ssgpoint.pointgift.application;

import com.spharos.ssgpoint.pointgift.dto.PointGiftCreateDto;
import com.spharos.ssgpoint.pointgift.dto.PointGiftGetDto;

import java.util.List;

public interface PointGiftService {

    // 포인트 선물 수신인 확인
    String getPointGiftUser(String phone, String name);

    // 포인트 선물 보내기
    void createPointGift(String UUID, PointGiftCreateDto pointGiftCreateDto);

    // 포인트 선물 수락/거절
    void updatePoint(Long id, String status);

    // TODO: 안 쓰면 삭제
    // 포인트 선물 목록 (테스트 용)
    List<PointGiftGetDto> getPointGiftByUser(String UUID);

}
