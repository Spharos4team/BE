package com.spharos.ssgpoint.alliancepoint.application;

import com.spharos.ssgpoint.alliancepoint.dto.AlliancePointCreateDto;
import com.spharos.ssgpoint.alliancepoint.dto.AlliancePointGetDto;
import com.spharos.ssgpoint.alliancepoint.dto.AlliancePointListDto;
import com.spharos.ssgpoint.alliancepoint.dto.AlliancePointUpdateDto;
import com.spharos.ssgpoint.point.dto.PointFilterSumDto;
import com.spharos.ssgpoint.pointgift.vo.PointListInVo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface AlliancePointService {

    // 제휴사 포인트 생성 (테스트 위해 생성)
    void createAlliancePoint(String UUID, AlliancePointCreateDto alliancePointCreateDto);

    // 제휴사 포인트 조회
    List<AlliancePointGetDto> getAlliancePointByUUID(String UUID);

    // 제휴사 포인트 전환
    void updateAlliancePoint(String UUID, String type, String status, AlliancePointUpdateDto alliancePointUpdateDto);

    //제휴사 포인트 내역
    Slice<AlliancePointListDto> getPointAllianceList(Long pointId, String uuid, Pageable page, PointListInVo pointFilterVo);

    //전환 포인트 적립 사용 합계
    PointFilterSumDto sumPointsAllianceByFilter(String UUID, PointListInVo p);
}
