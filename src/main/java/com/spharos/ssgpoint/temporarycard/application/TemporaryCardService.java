package com.spharos.ssgpoint.temporarycard.application;

import com.spharos.ssgpoint.temporarycard.dto.TemporaryCardGetDto;

import java.util.List;

public interface TemporaryCardService {

    // 임시 카드 조회
    List<TemporaryCardGetDto> getTemporaryCardByNumber(String number);

}
