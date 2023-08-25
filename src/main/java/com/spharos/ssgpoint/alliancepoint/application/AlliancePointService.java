package com.spharos.ssgpoint.alliancepoint.application;

import com.spharos.ssgpoint.alliancepoint.dto.AlliancePointCreateDto;
import com.spharos.ssgpoint.alliancepoint.dto.AlliancePointGetDto;
import com.spharos.ssgpoint.alliancepoint.dto.AlliancePointUpdateDto;

import java.util.List;

public interface AlliancePointService {

    // 제휴사 포인트 생성 (테스트 위해 생성)
    void createAlliancePoint(String UUID, AlliancePointCreateDto alliancePointCreateDto);

    // 제휴사 포인트 조회
    List<AlliancePointGetDto> getAlliancePointByUUID(String UUID);

    // 제휴사 포인트 사용
    void updateAlliancePoint(String UUID, String type, AlliancePointUpdateDto alliancePointUpdateDto);

}
