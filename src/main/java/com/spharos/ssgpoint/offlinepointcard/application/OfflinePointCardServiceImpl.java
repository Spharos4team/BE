package com.spharos.ssgpoint.offlinepointcard.application;

import com.spharos.ssgpoint.offlinepointcard.domain.OfflinePointCard;
import com.spharos.ssgpoint.offlinepointcard.dto.OfflinePointCardCreateDto;
import com.spharos.ssgpoint.offlinepointcard.infrastructure.OfflinePointCardRepository;
import com.spharos.ssgpoint.pointcard.application.PointCardService;
import com.spharos.ssgpoint.pointcard.dto.PointCardCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class OfflinePointCardServiceImpl implements OfflinePointCardService {

    private final PointCardService pointCardService;

    private final OfflinePointCardRepository offlinePointCardRepository;

    // 오프라인 포인트 카드 생성 (테스트 용)
    @Override
    public void createOfflinePointCardTest(OfflinePointCardCreateDto offlinePointCardCreateDto) {
        offlinePointCardRepository.save(OfflinePointCard.builder()
                .number(offlinePointCardCreateDto.getNumber())
                .CVC(offlinePointCardCreateDto.getCVC())
                .alliance(offlinePointCardCreateDto.getAlliance())
                .store(offlinePointCardCreateDto.getStore())
                .status(0)
                .build());
    }

    // 오프라인 포인트 카드 등록
    public void createOfflinePointCard(String UUID, String number, Integer CVC, String alliance, String store) {
        // 카드 정보 확인
        OfflinePointCard offlinePointCard
                = offlinePointCardRepository.findByNumberAndCVCAndAllianceAndStore(number, CVC, alliance, store).orElseThrow(() ->
                new IllegalArgumentException("카드 번호를 확인해주세요."));

        // TODO: 등록 상태인지 확인

        if (offlinePointCard.getStatus().equals(0)) {
            // 포인트 카드 테이블에 저장
            PointCardCreateDto pointCardCreateDto = PointCardCreateDto.builder()
                    .number(offlinePointCard.getNumber())
                    .agency(offlinePointCard.getAlliance())
                    .UUID(UUID)
                    .pointCardType("OFF")
                    .createdDate(LocalDate.from(offlinePointCard.getCreatedDate()))
                    .build();
            pointCardService.createPointCard(UUID, pointCardCreateDto);

            // 오프라인 포인트 카드 상태 변경
            offlinePointCard.update(1);
        }

    }

}
