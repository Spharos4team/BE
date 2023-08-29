package com.spharos.ssgpoint.offlinepointcard.application;

import com.spharos.ssgpoint.offlinepointcard.domain.OfflinePointCard;
import com.spharos.ssgpoint.offlinepointcard.dto.OfflinePointCardCreateDto;
import com.spharos.ssgpoint.offlinepointcard.dto.OfflinePointCardGetDto;
import com.spharos.ssgpoint.offlinepointcard.infrastructure.OfflinePointCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OfflinePointCardServiceImpl implements OfflinePointCardService {

    private final OfflinePointCardRepository offlinePointCardRepository;

    // 오프라인 포인트 카드 생성 (테스트 용)
    @Override
    public void createOfflinePointCardTest(OfflinePointCardCreateDto offlinePointCardCreateDto) {
        offlinePointCardRepository.save(OfflinePointCard.builder()
                .number(offlinePointCardCreateDto.getNumber())
                .CVC(offlinePointCardCreateDto.getCVC())
                .alliance(offlinePointCardCreateDto.getAlliance())
                .store(offlinePointCardCreateDto.getStore())
                .build());
    }

    // 오프라인 포인트 카드 가져오기
    @Override
    public List<OfflinePointCardGetDto> getOfflinePointCard(String number) {
        List<OfflinePointCard> offlinePointCardList = offlinePointCardRepository.findByNumber(number);

        return offlinePointCardList.stream().map(offlinePointCard ->
                OfflinePointCardGetDto.builder()
                        .number(offlinePointCard.getNumber())
                        .CVC(offlinePointCard.getCVC())
                        .alliance(offlinePointCard.getAlliance())
                        .store(offlinePointCard.getStore())
                        .build()
        ).toList();
    }

}
