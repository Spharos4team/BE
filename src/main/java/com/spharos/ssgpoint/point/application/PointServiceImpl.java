package com.spharos.ssgpoint.point.application;

import com.spharos.ssgpoint.point.domain.Point;
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
    public List<PointGetDto> getPointByUser(Long userId) {
        List<Point> pointList = pointRepository.findByUserId(userId);
        List<PointGetDto> pointGetDtoList = pointList.stream().map(point -> {
            return PointGetDto.builder()
                    .point(point.getPoint())
                    .status(point.getStatus())
                    .build();
        }).toList();

        return pointGetDtoList;
    }
}
