package com.spharos.ssgpoint.temporarycard.application;

import com.spharos.ssgpoint.temporarycard.domain.TemporaryCard;
import com.spharos.ssgpoint.temporarycard.dto.TemporaryCardGetDto;
import com.spharos.ssgpoint.temporarycard.infrastructure.TemporaryCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TemporaryCardServiceImpl implements TemporaryCardService {

    private final TemporaryCardRepository temporaryCardRepository;

    // 임시 카드 조회
    @Override
    public List<TemporaryCardGetDto> getTemporaryCardByNumber(String number) {
        List<TemporaryCard> temporaryCardList = temporaryCardRepository.findByNumber(number);

        return temporaryCardList.stream().map(temporaryCard ->
                TemporaryCardGetDto.builder()
                        .birth(temporaryCard.getBirth())
                        .name(temporaryCard.getName())
                        .number(temporaryCard.getNumber())
                        .CVC(temporaryCard.getCVC())
                        .agency(temporaryCard.getAgency())
                        .build()
        ).toList();
    }

}
