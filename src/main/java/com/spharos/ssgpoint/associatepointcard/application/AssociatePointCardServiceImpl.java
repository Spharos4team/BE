package com.spharos.ssgpoint.associatepointcard.application;

import com.spharos.ssgpoint.associatepointcard.domain.AssociatePointCard;
import com.spharos.ssgpoint.associatepointcard.domain.AssociatePointCardType;
import com.spharos.ssgpoint.associatepointcard.domain.AssociatePointCardTypeConverter;
import com.spharos.ssgpoint.associatepointcard.dto.AssociatePointCardCreateDto;
import com.spharos.ssgpoint.associatepointcard.dto.AssociatePointCardGetDto;
import com.spharos.ssgpoint.associatepointcard.infrastructure.AssociatePointCardRepository;
import jakarta.persistence.Convert;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AssociatePointCardServiceImpl implements AssociatePointCardService {

    private final AssociatePointCardRepository associatePointCardRepository;

    // 제휴 포인트 카드 등록
    @Override
    @Convert(converter = AssociatePointCardTypeConverter.class)
    public void createAssociatePointCard(String UUID, AssociatePointCardCreateDto associatePointCardCreateDto) {
        AssociatePointCardType associatePointCardType
                = new AssociatePointCardTypeConverter().convertToEntityAttribute(associatePointCardCreateDto.getType());

        associatePointCardRepository.save(AssociatePointCard.builder()
                .number(associatePointCardCreateDto.getNumber())
                .UUID(associatePointCardCreateDto.getUUID())
                .type(associatePointCardType)
                .build());
    }

    // 제휴 포인트 카드 조회
    @Override
    public List<AssociatePointCardGetDto> getAssociatePointCardByUser(String UUID) {
        List<AssociatePointCard> associatePointCardList = associatePointCardRepository.findByUUID(UUID);

        return associatePointCardList.stream().map(associate ->
                AssociatePointCardGetDto.builder()
                        .number(associate.getNumber())
                        .type(String.valueOf(associate.getType()))
                        .build()
        ).toList();
    }

}
