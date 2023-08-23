package com.spharos.ssgpoint.point.application;

import com.spharos.ssgpoint.point.domain.Point;
import com.spharos.ssgpoint.point.domain.PointType;
import com.spharos.ssgpoint.point.domain.PointTypeConverter;
import com.spharos.ssgpoint.point.dto.PointCreateDto;
import com.spharos.ssgpoint.point.dto.PointGetDto;
import com.spharos.ssgpoint.point.infrastructure.PointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PointServiceImpl implements PointService {

    private final PointRepository pointRepository;

    // 포인트 생성
    @Override
    public void createPoint(String UUID, PointCreateDto pointCreateDto) {

        PointType pointType = new PointTypeConverter().convertToEntityAttribute(pointCreateDto.getType());

        pointRepository.save(Point.builder()
                .totalPoint(pointCreateDto.getTotalPoint())
                .point(pointCreateDto.getPoint())
                .pointTitle(pointCreateDto.getPointTitle())
                .pointContent(pointCreateDto.getPointContent())
                .type(pointType)
                .UUID(pointCreateDto.getUUID())
                .build());
    }

    // 포인트 목록
    @Override
    public List<PointGetDto> getPointByUser(String UUID) {
        List<Point> pointList = pointRepository.findByUUID(UUID);

        return pointList.stream().map(point ->
                PointGetDto.builder()
                        .totalPoint(point.getTotalPoint())
                        .point(point.getPoint())
                        .pointTitle(point.getPointTitle())
                        .pointContent(point.getPointContent())
                        .type(String.valueOf(point.getType()))
                        .build()
        ).toList();
    }

}
