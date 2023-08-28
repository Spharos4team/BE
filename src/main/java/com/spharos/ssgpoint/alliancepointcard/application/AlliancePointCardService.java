package com.spharos.ssgpoint.alliancepointcard.application;

import com.spharos.ssgpoint.alliancepointcard.dto.AlliancePointCardCreateDto;
import com.spharos.ssgpoint.alliancepointcard.dto.AlliancePointCardGetDto;

import java.util.List;

public interface AlliancePointCardService {

    // 제휴 포인트 카드 등록
    void createAlliancePointCard(String UUID, AlliancePointCardCreateDto alliancePointCardCreateDto);

    // 제휴 포인트 카드 조회
    List<AlliancePointCardGetDto> getAlliancePointCardByUser(String UUID);

}
