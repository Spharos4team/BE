package com.spharos.ssgpoint.pointcard.application;

import com.spharos.ssgpoint.pointcard.domain.PointCard;
import com.spharos.ssgpoint.pointcard.domain.PointCardType;
import com.spharos.ssgpoint.pointcard.domain.PointCardTypeConverter;
import com.spharos.ssgpoint.pointcard.dto.PointCardCreateDto;
import com.spharos.ssgpoint.pointcard.dto.PointCardGetDto;
import com.spharos.ssgpoint.pointcard.presentation.PointCardRepository;
import jakarta.persistence.Convert;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PointCardServiceImpl implements PointCardService {

    private final PointCardRepository pointCardRepository;

    @Override
    @Convert(converter = PointCardTypeConverter.class)
    public void createPointCard(String UUID, PointCardCreateDto pointCardCreateDto) {

        PointCardType pointCardType
                = new PointCardTypeConverter().convertToEntityAttribute(pointCardCreateDto.getPointCardType());

        pointCardRepository.save(PointCard.builder()
                .number(pointCardCreateDto.getNumber())
                .agency(pointCardCreateDto.getAgency())
                .UUID(pointCardCreateDto.getUUID())
                .type(pointCardType)
                .build());
    }

    @Override
    public List<PointCardGetDto> getPointCardByUser(String UUID) {
        List<PointCard> pointCardList = pointCardRepository.findByUUID(UUID);
        List<PointCardGetDto> pointCardGetDtoList = pointCardList.stream().map(pointCard -> {
            return PointCardGetDto.builder()
                    .name(pointCard.getName())
                    .number(pointCard.getNumber())
                    .agency(pointCard.getAgency())
                    .build();
        }).toList();

        return pointCardGetDtoList;
    }
}
