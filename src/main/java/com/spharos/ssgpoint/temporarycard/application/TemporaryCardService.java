package com.spharos.ssgpoint.temporarycard.application;

import com.spharos.ssgpoint.temporarycard.dto.TemporaryCardGetDto;

import java.util.List;

public interface TemporaryCardService {

    // 임시 발급 카드를 포인트 카드에 등록
    void createTemporaryCardByNumber(String UUID, TemporaryCardGetDto temporaryCardGetDto);

}
