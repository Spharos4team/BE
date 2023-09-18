package com.spharos.ssgpoint.temporarycard.application;

import com.spharos.ssgpoint.pointcard.application.PointCardService;
import com.spharos.ssgpoint.pointcard.dto.PointCardCreateDto;
import com.spharos.ssgpoint.temporarycard.domain.TemporaryCard;
import com.spharos.ssgpoint.temporarycard.dto.TemporaryCardGetDto;
import com.spharos.ssgpoint.temporarycard.infrastructure.TemporaryCardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TemporaryCardServiceImpl implements TemporaryCardService {

    private final PointCardService pointCardService;

    private final TemporaryCardRepository temporaryCardRepository;

    // 임시 발급 카드를 포인트 카드에 등록
    @Transactional
    @Override
    public void createTemporaryCardByNumber(String UUID, TemporaryCardGetDto temporaryCardGetDto) {
        // 카드 정보 확인
        TemporaryCard temporaryCard
                = temporaryCardRepository.findByBirthAndNameAndNumberAndCVC(temporaryCardGetDto.getBirth(),
                temporaryCardGetDto.getName(),
                temporaryCardGetDto.getNumber(),
                temporaryCardGetDto.getCVC()).orElseThrow(() ->
                new IllegalArgumentException("입력하신 정보와 카드에 등록된 정보가 일치하지 않습니다."
                + System.lineSeparator()
                + "확인 후 다시 시도해 주세요."));

        if (temporaryCard.getStatus().equals(0)) {
            // 포인트 카드 테이블에 저장
            PointCardCreateDto pointCardCreateDto = PointCardCreateDto.builder()
                    .number(temporaryCard.getNumber())
                    .agency(temporaryCard.getAgency())
                    .UUID(UUID)
                    .type("OFF")
                    .build();
            pointCardService.createPointCard(UUID, pointCardCreateDto);

            // 포인트 카드 상태 변경
            temporaryCard.update(1);
        } else {
            // TODO: 멘트 확인 필요
            throw new IllegalArgumentException("이미 등록된 카드입니다.");
        }
    }

}
