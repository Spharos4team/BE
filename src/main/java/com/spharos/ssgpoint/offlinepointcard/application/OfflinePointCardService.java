package com.spharos.ssgpoint.offlinepointcard.application;

import com.spharos.ssgpoint.offlinepointcard.dto.OfflinePointCardCreateDto;

public interface OfflinePointCardService {

    // 오프라인 포인트 카드 생성 (테스트 용)
    void createOfflinePointCardTest(OfflinePointCardCreateDto offlinePointCardCreateDto);
    
    // 오프라인 포인트 카드 등록
    void createOfflinePointCard(String UUID, String number, Integer CVC, String alliance, String store);

}
