package com.spharos.ssgpoint.point.application;

import com.spharos.ssgpoint.point.domain.Point;
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

    @Override
    public void createPoint(String UUID, PointCreateDto pointCreateDto) {
        pointRepository.save(Point.builder()
                .totalPoint(pointCreateDto.getTotalPoint())
                .point(pointCreateDto.getPoint())
                .status(pointCreateDto.getStatus())
                .UUID(pointCreateDto.getUUID())
                .build());
    }

    @Override
    public List<PointGetDto> getPointByUser(String UUID) {
        List<Point> pointList = pointRepository.findByUUID(UUID);
        List<PointGetDto> pointGetDtoList = pointList.stream().map(point -> {
            return PointGetDto.builder()
                    .point(point.getPoint())
                    .status(point.getStatus())
                    .build();
        }).toList();

        return pointGetDtoList;
    }

}
