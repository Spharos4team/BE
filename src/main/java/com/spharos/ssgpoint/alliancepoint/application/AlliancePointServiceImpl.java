package com.spharos.ssgpoint.alliancepoint.application;

import com.spharos.ssgpoint.alliancepoint.domain.AlliancePoint;
import com.spharos.ssgpoint.alliancepoint.domain.AlliancePointType;
import com.spharos.ssgpoint.alliancepoint.domain.AlliancePointTypeConverter;
import com.spharos.ssgpoint.alliancepoint.dto.AlliancePointCreateDto;
import com.spharos.ssgpoint.alliancepoint.dto.AlliancePointGetDto;
import com.spharos.ssgpoint.alliancepoint.dto.AlliancePointUpdateDto;
import com.spharos.ssgpoint.alliancepoint.infrastructure.AlliancePointRepository;
import com.spharos.ssgpoint.user.infrastructure.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlliancePointServiceImpl implements AlliancePointService {

    private final UserRepository userRepository;
    private final AlliancePointRepository alliancePointRepository;

    // 제휴사 포인트 생성 (테스트 위해 생성)
    @Override
    public void createAlliancePoint(String UUID, AlliancePointCreateDto alliancePointCreateDto) {
        AlliancePointType alliancePointType
                = new AlliancePointTypeConverter().convertToEntityAttribute(alliancePointCreateDto.getType());

        alliancePointRepository.save(AlliancePoint.builder()
                .point(alliancePointCreateDto.getPoint())
                .type(alliancePointType)
                .UUID(alliancePointCreateDto.getUUID())
                .build());
    }

    // 제휴사 포인트 조회
    @Override
    public List<AlliancePointGetDto> getAlliancePointByUUID(String UUID) {
        List<AlliancePoint> alliancePointList = alliancePointRepository.findByUUID(UUID);

        return alliancePointList.stream().map(alliancePoint ->
                AlliancePointGetDto.builder()
                        .point(alliancePoint.getPoint())
                        .type(String.valueOf(alliancePoint.getType().getValue()))
                        .build()
        ).toList();
    }

    @Transactional
    @Override
    public void updateAlliancePoint(String UUID, String type, AlliancePointUpdateDto alliancePointUpdateDto) {
        AlliancePointType alliancePointType = new AlliancePointTypeConverter().convertToEntityAttribute(type);
        List<AlliancePoint> alliancePointList = alliancePointRepository.findByUUIDAndType(UUID, alliancePointType);

        for (AlliancePoint alliancePoint : alliancePointList) {
            alliancePoint.update(alliancePointUpdateDto.getPoint());
        }

    }

}
