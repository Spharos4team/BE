package com.spharos.ssgpoint.alliancepointcard.application;

import com.spharos.ssgpoint.alliancepointcard.domain.AlliancePointCard;
import com.spharos.ssgpoint.alliancepointcard.domain.AlliancePointCardType;
import com.spharos.ssgpoint.alliancepointcard.domain.AlliancePointCardTypeConverter;
import com.spharos.ssgpoint.alliancepointcard.dto.AlliancePointCardCreateDto;
import com.spharos.ssgpoint.alliancepointcard.dto.AlliancePointCardGetDto;
import com.spharos.ssgpoint.alliancepointcard.infrastructure.AlliancePointCardRepository;
import jakarta.persistence.Convert;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlliancePointCardServiceImpl implements AlliancePointCardService {

    private final AlliancePointCardRepository alliancePointCardRepository;

    // 제휴 포인트 카드 등록
    @Override
    @Convert(converter = AlliancePointCardTypeConverter.class)
    public void createAlliancePointCard(String UUID, AlliancePointCardCreateDto alliancePointCardCreateDto) {
        AlliancePointCardType alliancePointCardType
                = new AlliancePointCardTypeConverter().convertToEntityAttribute(alliancePointCardCreateDto.getType());

        alliancePointCardRepository.save(AlliancePointCard.builder()
                .number(alliancePointCardCreateDto.getNumber())
                .UUID(alliancePointCardCreateDto.getUUID())
                .type(alliancePointCardType)
                .build());
    }

    // 제휴 포인트 카드 조회
    @Override
    public List<AlliancePointCardGetDto> getAlliancePointCardByUser(String UUID) {
        List<AlliancePointCard> alliancePointCardList = alliancePointCardRepository.findByUUID(UUID);

        return alliancePointCardList.stream().map(alliance ->
                AlliancePointCardGetDto.builder()
                        .number(alliance.getNumber())
                        .type(String.valueOf(alliance.getType()))
                        .build()
        ).toList();
    }

}
