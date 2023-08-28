package com.spharos.ssgpoint.offlinepointcard.application;

import com.spharos.ssgpoint.offlinepointcard.dto.OfflinePointCardCreateDto;
import com.spharos.ssgpoint.offlinepointcard.dto.OfflinePointCardGetDto;
import com.spharos.ssgpoint.pointcard.dto.PointCardCreateDto;

import java.util.List;

public interface OfflinePointCardService {

    // 오프라인 포인트 카드 생성 (테스트 용)
    void createOfflinePointCardTest(OfflinePointCardCreateDto offlinePointCardCreateDto);

    // 오프라인 포인트 카드 가져오기
    List<OfflinePointCardGetDto> getOfflinePointCard(String number);

}
