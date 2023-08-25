package com.spharos.ssgpoint.associatepointcard.application;

import com.spharos.ssgpoint.associatepointcard.dto.AssociatePointCardCreateDto;
import com.spharos.ssgpoint.associatepointcard.dto.AssociatePointCardGetDto;

import java.util.List;

public interface AssociatePointCardService {

    // 제휴 포인트 카드 등록
    void createAssociatePointCard(String UUID, AssociatePointCardCreateDto associatePointCardCreateDto);
    // 제휴 포인트 카드 조회
    List<AssociatePointCardGetDto> getAssociatePointCardByUser(String UUID);

}
