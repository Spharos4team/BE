package com.spharos.ssgpoint.alliancepoint.application;

import com.spharos.ssgpoint.alliancepoint.domain.AlliancePoint;
import com.spharos.ssgpoint.alliancepoint.domain.AlliancePointType;
import com.spharos.ssgpoint.alliancepoint.domain.AlliancePointTypeConverter;
import com.spharos.ssgpoint.alliancepoint.dto.AlliancePointCreateDto;
import com.spharos.ssgpoint.alliancepoint.dto.AlliancePointGetDto;
import com.spharos.ssgpoint.alliancepoint.dto.AlliancePointListDto;
import com.spharos.ssgpoint.alliancepoint.dto.AlliancePointUpdateDto;
import com.spharos.ssgpoint.alliancepoint.infrastructure.AlliancePointRepository;
import com.spharos.ssgpoint.point.application.PointService;
import com.spharos.ssgpoint.point.dto.PointCreateDto;
import com.spharos.ssgpoint.point.dto.PointFilterSumDto;
import com.spharos.ssgpoint.pointgift.vo.PointListInVo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AlliancePointServiceImpl implements AlliancePointService {

    private final PointService pointService;

    private final AlliancePointRepository alliancePointRepository;

    // 제휴사 포인트 생성 (테스트 위해 생성)
    @Override
    @Transactional
    public void createAlliancePoint(String UUID, AlliancePointCreateDto alliancePointCreateDto) {

        AlliancePointType alliancePointType
                = new AlliancePointTypeConverter().convertToEntityAttribute(alliancePointCreateDto.getType());

        Optional<AlliancePoint> byAllianceUUID = alliancePointRepository.findByAllianceUUID(UUID,alliancePointType);
        if(byAllianceUUID.isPresent()){
            byAllianceUUID.get().updatePlus(alliancePointCreateDto.getPoint());
        }
        else{
        alliancePointRepository.save(AlliancePoint.builder()
                .point(alliancePointCreateDto.getPoint())
                .type(alliancePointType)
                .UUID(UUID)
                .build());}
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

    // 제휴사 포인트 전환
    @Transactional
    @Override
    public void updateAlliancePoint(String UUID, String type, String status, AlliancePointUpdateDto alliancePointUpdateDto) {
        // 내 제휴사 포인트 찾기
        AlliancePointType alliancePointType = new AlliancePointTypeConverter().convertToEntityAttribute(type);
        List<AlliancePoint> alliancePointList = alliancePointRepository.findByUUIDAndType(UUID, alliancePointType);

        String alliancePointName = "삼성P";
        
        for (AlliancePoint alliancePoint : alliancePointList) {
            if (alliancePoint.getType().getCode().equals("OK")) {
                alliancePointName = "OK캐쉬백(신)P";
            }

            // 제휴사 포인트 -> 신세계 포인트 전환 시
            if (status.equals("1")) {
                // 전환 포인트만큼 제휴사 포인트 (-)
                log.info("alliancePointUpdateDto.getPoint() = " + alliancePointUpdateDto.getPoint());
                alliancePoint.updateMinus(alliancePointUpdateDto.getPoint());
                log.info("alliancePoint.getPoint() = " + alliancePoint.getPoint());
                // 전환 포인트 포인트 테이블에 저장
                PointCreateDto pointCreateDto = PointCreateDto.builder()
                        .point(alliancePointUpdateDto.getPoint())
                        .title("신세계포인트 전환")
                        .content("(" + alliancePointName + "->신세계P)")
                        .statusType("0")
                        .type("7")
                       // .user(UUID)
                        .build();

                pointService.createPoint(UUID, pointCreateDto);
            }

            // 신세계 포인트 -> 제휴사 포인트 전환 시
            if (status.equals("2")) {
                // 전환 포인트만큼 제휴사 포인트 (+)
                alliancePoint.updatePlus(alliancePointUpdateDto.getPoint());

                // 전환 포인트 포인트 테이블에 저장
                PointCreateDto pointCreateDto = PointCreateDto.builder()
                        .point(alliancePointUpdateDto.getPoint())
                        .title("신세계포인트 전환")
                        .content("(신세계P->" + alliancePointName + ")")
                        .statusType("1")
                        .type("4")
                        //.user(UUID)
                        .build();

                pointService.createPoint(UUID, pointCreateDto);
            }
        }
    }

    //전환 포인트 리스트
    @Override
    public Slice<AlliancePointListDto> getPointAllianceList(Long lastId, String uuid, Pageable page, PointListInVo p) {
       return alliancePointRepository.findPointAllianceList(lastId, uuid, p.getStartDate(), p.getEndDate(), page);
    }


    //전환 포인트 적립 사용 합계
    @Override
    public PointFilterSumDto sumPointsAllianceByFilter(String UUID, PointListInVo p) {
        return alliancePointRepository.sumPointsAllianceByFilter(UUID, p.getStartDate(), p.getEndDate());
    }


}
