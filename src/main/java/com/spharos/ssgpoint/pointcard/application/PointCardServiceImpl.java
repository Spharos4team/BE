package com.spharos.ssgpoint.pointcard.application;

import com.spharos.ssgpoint.pointcard.domain.PointCard;
import com.spharos.ssgpoint.pointcard.domain.PointCardType;
import com.spharos.ssgpoint.pointcard.domain.PointCardTypeConverter;
import com.spharos.ssgpoint.pointcard.dto.PointCardCreateDto;
import com.spharos.ssgpoint.pointcard.dto.PointCardGetDto;
import com.spharos.ssgpoint.pointcard.infrastructure.PointCardRepository;
import com.spharos.ssgpoint.user.domain.User;
import com.spharos.ssgpoint.user.infrastructure.UserRepository;
import jakarta.persistence.Convert;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class PointCardServiceImpl implements PointCardService {

    private final UserRepository userRepository;
    private final PointCardRepository pointCardRepository;

    // 포인트 카드 번호 생성
    // 포인트 카드 번호 앞 7자리
    private String generateNumber() {
        String fixNumber = "9350";
        Random random = new Random();
        String randomDigits = String.format("%03d", random.nextInt(1000));

        return fixNumber + randomDigits;
    }
    
    // TODO: 추가 수정 필요 (여러 사람이 동시에 가입했을 때)
    // 중복 검사
    private String validateNumber(String number) {
        Optional<PointCard> pointCard = pointCardRepository.findByNumber(number);

        // 중복인 경우
        if (pointCard.isPresent()) {
            String subString = number.substring(4, 7);
            int plus = Integer.parseInt(subString) + 1;
            String newNumber = number.substring(0, 4) + String.format("%03d", plus) + number.substring(7);

            return validateNumber(newNumber);
        } else {
            return number;
        }
    }
    
    // 전체 카드 번호 만들기 9350 + 랜덤 3자리 + UserID 9자리
    private String createPointCardNumber(Long id) {
        String generateNumber = generateNumber();
        String formattedId;

        if (id > 999999999) {
            String s = String.valueOf(id);
            formattedId = s.substring(1);
        } else {
            formattedId = String.format("%09d", id);
        }

        return generateNumber + formattedId;
    }

    // 포인트 카드 생성
    @Override
    @Convert(converter = PointCardTypeConverter.class)
    public void createPointCard(String UUID, PointCardCreateDto pointCardCreateDto) {
        PointCardType pointCardType
                = new PointCardTypeConverter().convertToEntityAttribute(pointCardCreateDto.getType());
        User user = userRepository.findByUuid(UUID).orElseThrow(() ->
                new IllegalArgumentException("존재하지 않는 UUID"));
        
        // 온라인 카드인 경우 포인트 카드 번호 랜덤 생성
        if (pointCardCreateDto.getType().equals("ON")) {
            // 포인트 카드 번호
            String number = createPointCardNumber(user.getId());
            String validateNumber = validateNumber(number);

            pointCardRepository.save(PointCard.builder()
                    .number(validateNumber)
                    .agency(pointCardCreateDto.getAgency())
                    .UUID(pointCardCreateDto.getUUID())
                    .type(pointCardType)
                    .build());
        } else {
            pointCardRepository.save(PointCard.builder()
                    .number(pointCardCreateDto.getNumber())
                    .agency(pointCardCreateDto.getAgency())
                    .UUID(pointCardCreateDto.getUUID())
                    .type(pointCardType)
                    .build());
        }
    }

    // 포인트 카드 목록
    @Override
    public List<PointCardGetDto> getPointCardByUser(String UUID) {
        List<PointCard> pointCardList = pointCardRepository.findByUUID(UUID);

        return pointCardList.stream().map(pointCard ->
                PointCardGetDto.builder()
                        .name(pointCard.getName())
                        .number(pointCard.getNumber())
                        .agency(pointCard.getAgency())
                        .build()
        ).toList();
    }
}
