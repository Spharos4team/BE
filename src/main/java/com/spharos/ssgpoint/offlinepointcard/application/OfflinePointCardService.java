package com.spharos.ssgpoint.offlinepointcard.application;

import com.spharos.ssgpoint.offlinepointcard.dto.OfflinePointCardCreateDto;
import com.spharos.ssgpoint.offlinepointcard.dto.OfflinePointCardCreateTestDto;

public interface OfflinePointCardService {

    // 오프라인 포인트 카드 생성 (테스트 용)
    void createOfflinePointCardTest(OfflinePointCardCreateTestDto offlinePointCardCreateDto);
    
    // 오프라인 포인트 카드 등록
    void createOfflinePointCard(String UUID, OfflinePointCardCreateDto offlinePointCardCreateDto);

}
